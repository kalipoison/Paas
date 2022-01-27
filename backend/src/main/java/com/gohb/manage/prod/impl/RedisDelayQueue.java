package com.gohb.manage.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.params.bo.prod.OrderBO;
import com.gohb.service.prod.OrderService;
import com.gohb.utils.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.Date;
import java.util.Set;

@Slf4j
public class RedisDelayQueue{

    private static String REDIS_CANCEL_ORDER_KEY = "CANCEL_ORDER";

    public static void writeToDelayQueue(String orderNumber, Long orderCancelDelayTime) {
        JedisPool jedisPool = ApplicationContextHolder.getApplicationContext().getBean(JedisPool.class);
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            Date deleyDate = new Date(new Date().getTime() + orderCancelDelayTime);
            jedis.zadd(REDIS_CANCEL_ORDER_KEY, deleyDate.getTime(), orderNumber);
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static void DelayQueueCancelOrder() {
        JedisPool jedisPool = ApplicationContextHolder.getApplicationContext().getBean(JedisPool.class);
        if (jedisPool == null) {
            log.info("Redis 连接失败");
            return;
        }
        ThreadPoolTaskExecutor executorService = ApplicationContextHolder.getApplicationContext().getBean(ThreadPoolTaskExecutor.class);
        executorService.submit(()->{
            while (true) {
                Jedis jedis = null;
                try{
                    jedis = jedisPool.getResource();
                    Set<Tuple> items = jedis.zrangeWithScores(REDIS_CANCEL_ORDER_KEY, 0, 1);
                    if(items == null || items.isEmpty()){
                        log.debug("当前没有等待的任务");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
//                         TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        continue;
                    }
                    long score = (long) ((Tuple)items.toArray()[0]).getScore();
                    String orderNumber = ((Tuple)items.toArray()[0]).getElement();
                    Date cancelTime = new Date(score);
                    Date now = new Date();
                    if (now.after(cancelTime)) {
                        jedis.zrem(REDIS_CANCEL_ORDER_KEY, orderNumber);
                    }
                    cancelOrder(orderNumber);
                    log.info("Redis score memeber" + score + " : " + orderNumber);
                    log.info("jedis 回收" + jedis);
                } catch (Exception e) {
                    log.info(e.getMessage());
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }
            }
        });
    }

    private static void cancelOrder(String orderNumber) {
        try {
            OrderService orderService = ApplicationContextHolder.getApplicationContext().getBean(OrderService.class);
            OrderBO orderBO = orderService.getOne(new LambdaQueryWrapper<OrderBO>()
                    .eq(OrderBO::getOrderNumber, orderNumber));
            if (orderBO.getCancelTime() == null && orderBO.getFinallyTime() == null &&
                    orderBO.getStatus() != null && orderBO.getStatus() == 1 &&
                    orderBO.getIsPayed() != null && orderBO.getIsPayed() == 1) {
                orderBO.setFinallyTime(new Date());
            } else {
                Date cancelTime = orderBO.getCancelTime();
                orderBO.setCancelTime(cancelTime == null ? new Date() : cancelTime);
            }
            orderService.updateById(orderBO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
