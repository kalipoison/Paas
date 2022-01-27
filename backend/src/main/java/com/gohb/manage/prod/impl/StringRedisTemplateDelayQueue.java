package com.gohb.manage.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.params.bo.prod.OrderBO;
import com.gohb.service.prod.OrderService;
import com.gohb.utils.ApplicationContextHolder;
import com.gohb.utils.JedisDistributedLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class StringRedisTemplateDelayQueue {

    private static String REDIS_CANCEL_ORDER_KEY = "CANCEL_ORDER";
    private static Integer EXPIRE_Time = 20;

    public static void writeToDelayQueue(String orderNumber, Long orderCancelDelayTime) {
        StringRedisTemplate stringRedisTemplate = ApplicationContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);
        Date deleyDate = new Date(new Date().getTime() + orderCancelDelayTime);
        stringRedisTemplate.opsForZSet().add(REDIS_CANCEL_ORDER_KEY, orderNumber, deleyDate.getTime());
    }

    public static void DelayQueueCancelOrder() {
        StringRedisTemplate stringRedisTemplate = ApplicationContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);
        ThreadPoolTaskExecutor executorService = ApplicationContextHolder.getApplicationContext().getBean(ThreadPoolTaskExecutor.class);
        executorService.submit(()->{
            while (true) {
                try{
                    Set<ZSetOperations.TypedTuple<String>> items = stringRedisTemplate.opsForZSet().rangeWithScores(REDIS_CANCEL_ORDER_KEY, 0, 1);
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
                    String orderNumber = ((ZSetOperations.TypedTuple<String>) items.toArray()[0]).getValue();
                    double score = ((ZSetOperations.TypedTuple<String>) items.toArray()[0]).getScore();
                    Date cancelTime = new Date((long) score);
                    Date now = new Date();
                    String requestId = UUID.randomUUID().toString();
                    Boolean isGetLock = stringRedisTemplate.opsForValue().setIfAbsent(orderNumber, requestId,
                            EXPIRE_Time, TimeUnit.MILLISECONDS);
                    if (now.after(cancelTime) && isGetLock) {
                        stringRedisTemplate.opsForZSet().remove(REDIS_CANCEL_ORDER_KEY, orderNumber);
                        stringRedisTemplate.delete(orderNumber);
                    }
                    cancelOrder(orderNumber);
                } catch (Exception e) {
                    log.info(e.getMessage());
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
