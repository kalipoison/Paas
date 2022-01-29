一套基于kubernetes开发的Paas系统。



paas-ind :  Paas 服务个人版，包含对kubernetes 的Pod 、Service、Deployment 等操作，同时基于RBAC实现权限控制，同时可统计站点访问量，以及远程接入终端



# 技术栈

后端： springboot + mybatis + kubernetes + springsecurity + jwt + websocket

前端： vue + echarts + element-ui + xterm



## 分支介绍

master ： 主线开发分支  java + redis + rabbitmq

Paas_group_delay_queue： 纯java环境开发

Paas_group_netty_time_wheel ： java + netty 环境开发

Pass_group_rabbit_mq ： java + rabbitmq 环境开发

Pass_group_redis ： java + redis 环境开发

Pass_group : java + redis + rabbitmq 环境开发

paas-ind :  纯java环境开发（仅管理部分）



# 性能分析篇

Paas 采用jdk自带的delay_queue, 基于 netty的 time wheel, 基于 Redis的zset， 基于 RabbitMQ的死信队列这四种方式实现延迟队列，可以满足大多数应用场景的需求。下面将对四种延迟队列性能，做以下对比：



### 基于DelayQueue实现延迟队列

JDK 自带的延迟队列 DelayQueue,  要求所有添加到延迟队列的元素都必须实现 Delayed 接口。

```java
public interface Delayed extends Comparable<Delayed> {
    long getDelay(TimeUnit unit);
}

```

其内部延迟队列是使用 PriorityQueue 实现的

```java

// 元素必须实现 Delayed 接口
public class DelayQueue<E extends Delayed> extends AbstractQueue<E>
    implements BlockingQueue<E> { // 同时也是一个阻塞队列

    private final transient ReentrantLock lock = new ReentrantLock();
    private final PriorityQueue<E> q = new PriorityQueue<E>(); // 优先队列,内部是数组方式实现
    ...
}
```

让我们来看下，它是如何插入元素和获取元素的：

```java
public boolean offer(E e) { // 插入元素
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        q.offer(e);
        if (q.peek() == e) {
            leader = null;
            available.signal();
        }
        return true;
    } finally {
        lock.unlock();
    }
}

public E take() throws InterruptedException {
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
        for (;;) {
            E first = q.peek();
            if (first == null)
                available.await();
            else {
                long delay = first.getDelay(NANOSECONDS);
                if (delay <= 0)
                    return q.poll();
                first = null; // don't retain ref while waiting
                if (leader != null)
                    available.await();
                else {
                    Thread thisThread = Thread.currentThread();
                    leader = thisThread;
                    try {
                        available.awaitNanos(delay);
                    } finally {
                        if (leader == thisThread)
                            leader = null;
                    }
                }
            }
        }
    } finally {
        if (leader == null && q.peek() != null)
            available.signal();
        lock.unlock();
    }
}
```

总之，DelayQueue 是通过 PriorityQueue 来实现的。元素通过实现 Delayed 接口返回延迟时间。但是，延迟队列还是一个容器，需要其他线程来获取和执行任务。插入和删除时间复杂度为O(logn)。如果频繁插入和删除的次数为m，则总时间复杂度为O(mlogn)。而下面介绍的时间轮算法的插入和删除时间复杂度均是O(1)。



### Netty 的 HashedWheelTimer 实现延迟队列

在netty中，时间轮的实现类是HashedWheelTimer。mask的设计与HashMap相同。通过将数组的大小限制为 2 的幂，使用位运算来代替模运算以提高性能。Tick duration是每个网格的时间，即准确率。您可以看到配备了一个工作线程来处理任务的执行。

```java
public HashedWheelTimer(ThreadFactory threadFactory, long tickDuration, TimeUnit unit, int ticksPerWheel, boolean leakDetection, long maxPendingTimeouts) {
    this.worker = new HashedWheelTimer.Worker();
    this.startTimeInitialized = new CountDownLatch(1);
    this.timeouts = PlatformDependent.newMpscQueue();
    this.cancelledTimeouts = PlatformDependent.newMpscQueue();
    this.pendingTimeouts = new AtomicLong(0L);
    ObjectUtil.checkNotNull(threadFactory, "threadFactory");
    ObjectUtil.checkNotNull(unit, "unit");
    ObjectUtil.checkPositive(tickDuration, "tickDuration");
    ObjectUtil.checkPositive(ticksPerWheel, "ticksPerWheel");
    this.wheel = createWheel(ticksPerWheel); // ticksPerWheel 默认512，即槽的数量
    this.mask = this.wheel.length - 1; // mask是为了位运算用的为了提高性能，限制wheel.length 为2的n次方，即 tick & (wheel.length - 1) = tick % wheel.length
    long duration = unit.toNanos(tickDuration); // 每隔的时间
    if (duration >= 9223372036854775807L / (long)this.wheel.length) {
        throw new IllegalArgumentException(String.format("tickDuration: %d (expected: 0 < tickDuration in nanos < %d", tickDuration, 9223372036854775807L / (long)this.wheel.length));
    } else {
        if (duration < MILLISECOND_NANOS) {
            logger.warn("Configured tickDuration {} smaller then {}, using 1ms.", tickDuration, MILLISECOND_NANOS);
            this.tickDuration = MILLISECOND_NANOS;
        } else {
            this.tickDuration = duration;
        }

        this.workerThread = threadFactory.newThread(this.worker); // 工作线程
        this.leak = !leakDetection && this.workerThread.isDaemon() ? null : leakDetector.track(this); // 内存泄漏探测
        this.maxPendingTimeouts = maxPendingTimeouts;
        if (INSTANCE_COUNTER.incrementAndGet() > 64 && WARNED_TOO_MANY_INSTANCES.compareAndSet(false, true)) {
            reportTooManyInstances();
        }

    }
}
```

那么，让我们来看一下它是如何添加任务的

```java
public Timeout newTimeout(TimerTask task, long delay, TimeUnit unit) {
    ObjectUtil.checkNotNull(task, "task");
    ObjectUtil.checkNotNull(unit, "unit");
    long pendingTimeoutsCount = this.pendingTimeouts.incrementAndGet();
    if (this.maxPendingTimeouts > 0L && pendingTimeoutsCount > this.maxPendingTimeouts) {
        this.pendingTimeouts.decrementAndGet();
        throw new RejectedExecutionException("Number of pending timeouts (" + pendingTimeoutsCount + ") is greater than or equal to maximum allowed pending timeouts (" + this.maxPendingTimeouts + ")");
    } else {
        // 开启时间轮轮询
        this.start();
        long deadline = System.nanoTime() + unit.toNanos(delay) - this.startTime;
        if (delay > 0L && deadline < 0L) {
            deadline = 9223372036854775807L;
        }
		// 将定时任务封装成HashedWheelTimeout
        HashedWheelTimer.HashedWheelTimeout timeout = new HashedWheelTimer.HashedWheelTimeout(this, task, deadline);
        // 将定时任务进入到MPSC队列中
        this.timeouts.add(timeout);
        return timeout;
    }
}
```

我们可以看到，任务并没有直接加入时间轮，而是先进入了一个 MPSC 队列。我简单说一下MPSC是jctools中的一个并发队列。当多个生产者可以同时访问队列但只有一个消费者会访问队列时使用它。

那么，来看一下工作线程是如何工作的

```java
do {
    long deadline = this.waitForNextTick(); // 等待执行任务的时间到来
    if (deadline > 0L) {
        idx = (int)(this.tick & (long)HashedWheelTimer.this.mask);
        this.processCancelledTasks(); // 处理那些被取消了的任务，即取消队列poll，移除任务
        bucket = HashedWheelTimer.this.wheel[idx]; // 选择对应的槽
        this.transferTimeoutsToBuckets(); // 将加入 MPSC 队列的任务加入到时间轮的槽中
        bucket.expireTimeouts(deadline); // 处理时间到了的任务
        ++this.tick; // 记一次移动
    }
} while(HashedWheelTimer.WORKER_STATE_UPDATER.get(HashedWheelTimer.this) == 1);
```

它非常直观。接下来，让我们来看看waitForNextTick是如何获取下一次执行时间的。

```java
private long waitForNextTick() {
    long deadline = HashedWheelTimer.this.tickDuration * (this.tick + 1L);
    while(true) {
        long currentTime = System.nanoTime() - HashedWheelTimer.this.startTime;
        // +999999L 保证足够的sleep时间，比如 deadline - currentTime 为 5纳秒，
        // 那么5纳秒转毫秒就为0了，但是实际时间还没到
        long sleepTimeMs = (deadline - currentTime + 999999L) / 1000000L;
        if (sleepTimeMs <= 0L) { // 时间过了，马上执行任务
            if (currentTime == -9223372036854775808L) { // 溢出问题？
                return -9223372036854775807L;
            }
            return currentTime;
        }
        // 在windows下有bug，sleep时间是10的整数倍，
        // http://github.com/netty/netty/issues/356
        // 这个 bug 是由 windows 在 java 调用 Thread.sleep() 时产生中断引起的，如果时间低于 10 毫秒，则通过不休眠来解决。
        if (PlatformDependent.isWindows()) {
            sleepTimeMs = sleepTimeMs / 10L * 10L;
            if (sleepTimeMs == 0L) {
                sleepTimeMs = 1L;
            }
        }
        try {
            Thread.sleep(sleepTimeMs); // 等待时间的到来
        } catch (InterruptedException var8) {
            if (HashedWheelTimer.WORKER_STATE_UPDATER.get(HashedWheelTimer.this) == 2) {
                return -9223372036854775808L;
            }
        }
    }
}
```

简单来说，就是计算下一次通过tick时长检查的时间和已经被tick的次数。在时间到之前等待睡眠。

我们来看看任务是如何进入槽的。

```java
private void transferTimeoutsToBuckets() {
    for(int i = 0; i < 100000; ++i) {
        HashedWheelTimer.HashedWheelTimeout timeout = (HashedWheelTimer.HashedWheelTimeout)HashedWheelTimer.this.timeouts.poll(); // 获取任务
        if (timeout == null) {
            break;
        }
        if (timeout.state() != 1) {
            long calculated = timeout.deadline / HashedWheelTimer.this.tickDuration;
            timeout.remainingRounds = (calculated - this.tick) / (long)HashedWheelTimer.this.wheel.length; // 计算在第几轮
            long ticks = Math.max(calculated, this.tick);
            int stopIndex = (int)(ticks & (long)HashedWheelTimer.this.mask); // 计算在哪个槽
            HashedWheelTimer.HashedWheelBucket bucket = HashedWheelTimer.this.wheel[stopIndex];
            bucket.addTimeout(timeout); // 入槽
        }
    }
}
```

最后让我们来看下如何实现到时执行任务的。

```java
public void expireTimeouts(long deadline) {
    HashedWheelTimer.HashedWheelTimeout next; // 拿到槽的链表头部
    for(HashedWheelTimer.HashedWheelTimeout timeout = this.head; timeout != null; timeout = next) {
        next = timeout.next;
        if (timeout.remainingRounds <= 0L) {
            next = this.remove(timeout);
            if (timeout.deadline > deadline) {
                throw new IllegalStateException(String.format("timeout.deadline (%d) > deadline (%d)", timeout.deadline, deadline));
            }
            timeout.expire(); // 执行，实际上就是调用任务的run方法
        } else if (timeout.isCancelled()) {
            next = this.remove(timeout);
        } else {
            --timeout.remainingRounds; // 否则轮数减一
        }
    }
}
```

即通过轮数和时间的双重判断，完成移除任务。

总之，netty 的实现就是通过上面提到的轮数来实现时间轮次。可以看出，时间精度是由 tickduration 控制的，工作线程在执行的时候除了处理任务之外，还做了其他的操作，所以任务可能执行不准确。而且，如果没有启动新线程或者任务被扔到线程池执行，耗时的任务会阻塞下一个任务的执行。另一方面，如果任务执行速度很快（当然也可以异步执行），任务数量大，添加或删除任务的时间复杂度为O(1)。时间轮比 PriorityQueue  实现的延迟任务更合适。

 以上为netty-all.4.1.24.Final 的 源码分析，其他不同版本可能略有不同。



### Redis 实现延迟队列

利用redis的zset实现延迟队列。zset是一个有序集合，每一个元素(member)都关联了一个score,通过score排序来取集合中的值。

```
zset常用命令
添加元素:ZADD key score member [[score member] [score member] ...]
按顺序查询元素:ZRANGE key start stop [WITHSCORES]
查询元素score:ZSCORE key member
移除元素:ZREM key member [member ...]
```

关于 redis实现延迟队列的分析有很多，在此不做过多分析。

### RabbitMQ 实现延迟队列

基于RabbitMQ的死信队列（Dead Letter Queue）来实现延迟队列。我们可以在发送消息时指定单条消息的存活时间（Time To Live，TTL）。也可以设置一个队列的消息过期时间。

这两种方式，当队列中的消息到达过期时间（比如30分钟）仍未被消费，就会被发送到队列的死信交换机（Dead Letter Exchange，DLX），被再次路由，此时再次路由到的队列就被称为死信队列（Dead Letter Queue）。需要注意，死信交换机和死信交换机都是基于其用途来描述的，它们实际上也是普通的交换机和普通的队列。如果队列没有指定DLX或者无法被路由到一个DLQ，则队列中过期的消息会被直接丢弃。

因此，我们可以利用消息TTL的特性，实现消息的延时投递。

关于 RabbitMQ 实现延迟队列的分析有很多，在此不做过多分析。

## 总结：

|                                    | 优点                                                         | 缺点                                                         |
| ---------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 基于DelayQueue的延迟队列           | 1. 实现简单、性能较好                                        | 1. 只适用于单机环境，分布式/集群需要额外实现；<br>2. 通过先将消息排序再定时触发的方式来实现延迟消息。所以大量消息时，性能不能保证 <br/>3. 想提供一定可靠性（如数据持久性）<br>4. 因为内存条件限制的原因，数据太多，那么很容易就出现OOM异常 |
| 基于Netty HashWheelTimer的延迟队列 | 1. 效率高,任务触发时间延迟时间比delayQueue低，代码复杂度比delayQueue低。<br>2. 插入获取操作的时间复杂度从DelayQueue u的O(logn) 降到O(1) | 1. 内存占用相对较高,对时间精度要求相对不高.<br>2. 和delayQueue有着相同的问题,自己要处理分布式横向扩展的问题<br>3. 因为数据是放在内存里,需要自己写持久化的备案以达到高可用。 |
| 基于Redis的延迟队列                | 1. 由于使用Redis作为消息通道，消息都存储在Redis中。如果发送程序或者任务处理程序挂了，重启之后，还有重新处理数据的可能性。<br>2. 做集群扩展相当方便 <br>3. 时间准确度高 | 1. 数据量大，一zset性能有问题。 当然可以多定义几个zset，再数据量大的时候分散到不同zset里面，但插入和定时器去读的复杂性增加了。<br> 2. 消息处理失败是不能被恢复。需要自己去实现。<br> 3. 需要额外进行redis维护 |
| 基于RabbitMq延迟队列               | 解耦、异常恢复、扩展性强、支持分布式/集群环境                | 本身的易用度要依赖于rabbitMq的运维.因为要引用rabbitMq,所以复杂度和成本变高。其他老版本的MQ大多没有延时队列的实现 |



## 使用Jedis 而不使用 StringRedisTemplate原因

1，个人认为直接使用JedisPool比使用SpringBoot封装的RedisTemplate好用，而且效率高很多（相同的数据量至少5倍以上的差距，本机实测感觉jedis快了几十倍，get一个key,value为有一万多条数据的json，用RedisTemplate 在idea中单步调试在get这行的时候明显的卡半天才反应过来，至少3-5s以上，前后反复测试了十几次都是这样,但是使用jedis则是瞬间出来，进入下一行代码。相同的一个key数据存入redis后，使用同样的Windows redis cli客户端在服务端去get, 用RedisTemplate存入的显示为1.27s,而jedis存入的0.92s）这也是看得见的区别。如果是几十万的话区别就更大了。

2，jedis存入的字符串数据不会被添加转义符，用Windows redis cli客户端手工从服务端set的数据也可以在java jedis中正确读取出来，而使用RedisTemplate则读不了。

3，jedis默认不支持set object类型。而RedisTemplate 因为是二次封装是可以的。如果jedis要实现这功能，可以自己封装，因为jedis提供了set(byte[] key, byte[] value)，可以将要存的对像或bean序列化为string再存入，又或者转为byte[]再存入均可。

4，存入相同数量的List<bean>，比如15000条，同样使用fastjson序列化存入，RedisTemplate存入的字符量至少要比使用jedis存入的多5分之一，在cli 客户端查询只看见铺天盖地的\\\\\转义符，而jedis存入的则低调很多。序列化器均为fastjson1.2.7。

5，关于线程安全，都说jedis线程不安全。lettuce是线程安全的。高并发效率高如何如何。其实本身这个问题并不算问题。因为redis默认即为单线程，但单线程并不代表效率低,像Nginx、Nodejs也是单线程程序,但是它们的效率并不低。而且使用jedis访问redis我相信大家基本都是采用JedisPool连接池来操作的，这样一来发生并发访问冲突的机率就不存在了，这个不明白的可以去了解一下JedisPool，jedis实例的相关概念和工作原理。

6， jedis本身不是多线程安全的，这并不是jedis的bug，而是jedis的设计与redis本身就是单线程相关，jedis实例抽象的是发送命令相关，一个jedis实例使用一个线程与使用100个线程去发送命令没有本质上的区别，所以没必要设置为线程安全的。但是如果需要用多线程方式访问redis服务器怎么做呢？那就使用多个jedis实例，每个线程对应一个jedis实例，而不是一个jedis实例多个线程共享。一个jedis关联一个Client，相当于一个客户端，Client继承了Connection，Connection维护了Socket连接，对于Socket这种昂贵的连接，一般都会做池化，jedis提供了JedisPool。

虽然现在主流都在使用RedisTemplate，但是我个人还是比较推荐直接使用jedispool来操作redis。关于他们效率的对比，网上测试是一堆一堆的可以自己去找。几乎都是5-100倍的性能差距！

```
@SpringBootTest
public class testRedis {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testJedis() throws IOException {
        GreExam greExam = new GreExam();
        greExam.setId(997);
        String greExamStr = greExam.toString();
        long time = 0;
        for (int i = 0; i < 100; i++) {
            try (Jedis jedis = jedisPool.getResource()) {
                // 1、推送
                long time1 = System.currentTimeMillis();
                jedis.lpush("jedis-mq", greExamStr);
                // 2、接收
                String msg = jedis.brpoplpush("jedis-mq", "jedis-mq_bak", 0);
                jedis.lrem("jedis-mq_bak", 1, msg);
                long time2 = System.currentTimeMillis();
                time += time2 - time1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("总时间：" + time);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate() throws IOException {
        GreExam greExam = new GreExam();
        greExam.setId(997);
        String greExamStr = greExam.toString();
        long time = 0;
        for (int i = 0; i < 100; i++) {
            // 1、推送
            long time1 = System.currentTimeMillis();
            redisTemplate.opsForList().leftPush("redisTemplate-mq", greExamStr);
            // 2、接收
            String msg = (String) redisTemplate.opsForList().rightPopAndLeftPush(
                    "redisTemplate-mq", "redisTemplate-mq_bak", 1, TimeUnit.SECONDS);
            redisTemplate.opsForList().remove("redisTemplate-mq_bak", 1, msg);
            long time2 = System.currentTimeMillis();
            time += time2 - time1;
        }
        System.out.println("总时间：" + time);
    }
}

```






# 部署

导入paas_db.sql文件

## 前端部署

```javascript
npm install
npm run serve
```

## 后端

```java
java -jar xxx.jar
```



后台管理默认                           账号： admin  密码：admin



## 前台效果展示：

### 1. 首页

![image-20220119162814287](./frontPic/image-20220119162814287.png)



![image-20220119163018337](./frontPic/image-20220119163018337.png) 

![image-20220119163055156](./frontPic/image-20220119163055156.png)

### 2. 登录页面

![image-20220119162918320](./frontPic/image-20220119162918320.png)





### 3. 产品



![image-20220119163444450](./frontPic/image-20220119163444450.png)



### 4. 工具

![image-20220119163515277](./frontPic/image-20220119163515277.png)



### 5. 通知

![image-20220119163537398](./frontPic/image-20220119163537398.png)

### 6. 我的

![image-20220119164219888](./frontPic/image-20220119164219888.png)



# 后台效果展示：

## 1.登录界面 



![image-20211224224437947](./pic/image-20211224224437947.png)



## 2. 系统管理

### 2.1  管理员列表

![image-20211227001705495](./pic/image-20211227001705495.png)

### 2.2 角色管理

![image-20211227001818169](./pic/image-20211227001818169.png)

### 2.3 菜单管理

![image-20211227001916615](./pic/image-20211227001916615.png)

### 2.4 日志管理

![image-20211227002001770](./pic/image-20211227002001770.png) 



# 3. 容器管理 

### 3.1 Node管理

![image-20211227002247371](./pic/image-20211227002247371.png)

3.2 Namespace 管理

![image-20211227002324099](./pic/image-20211227002324099.png)



3. 3 Deployment管理

![image-20211227002522291](./pic/image-20211227002522291.png)



3.4 Pod 管理

![image-20211227002558832](./pic/image-20211227002558832.png)



3.5 Service 管理

![image-20211227002631695](./pic/image-20211227002631695.png)



## 4. 仪表盘

### 4.1 访问量

![image-20211227002712839](./pic/image-20211227002712839.png)



# 5. 终端 

### 5.1 终端

![image-20211229052324284](./pic/image-20211229052324284.png)



## 6. 商品

### 6.1 产品

![image-20220116222913031](./pic/image-20220116222913031.png)

### 6.2 产品属性

![image-20220116223014184](./pic/image-20220116223014184.png)

![image-20220116223048552](./pic/image-20220116223048552.png)



### 6.3 SKU

![image-20220116223117541](./pic/image-20220116223117541.png)



## 7. 兑换券

### 7.1 兑换券

![image-20220116223159300](./pic/image-20220116223159300.png)

