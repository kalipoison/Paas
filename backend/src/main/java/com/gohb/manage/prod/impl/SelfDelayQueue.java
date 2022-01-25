package com.gohb.manage.prod.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.params.bo.prod.OrderBO;
import com.gohb.service.prod.OrderService;
import com.gohb.utils.ApplicationContextHolder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * 基于 jdk 自带 delay queue
 */
public class SelfDelayQueue {

    public volatile static DelayQueue<OrderBO> orderDelayQueue = new DelayQueue();

    public static DelayQueue<OrderBO> writeToDelayQueue(OrderBO orderBO) {
        ThreadPoolTaskExecutor executorService = ApplicationContextHolder.getApplicationContext().getBean(ThreadPoolTaskExecutor.class);
        executorService.submit(()->{
            try {
                SelfDelayQueue.orderDelayQueue.add(orderBO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return orderDelayQueue;
    }

    /**
     * 自动取消订单
     */
    public static void DelayQueueCancelOrder() {
        ThreadPoolTaskExecutor executorService = ApplicationContextHolder.getApplicationContext().getBean(ThreadPoolTaskExecutor.class);
        OrderService orderService = ApplicationContextHolder.getApplicationContext().getBean(OrderService.class);
        executorService.submit(()->{
            try {
                OrderBO orderBO = orderDelayQueue.take();
                OrderBO order = orderService.getOne(new LambdaQueryWrapper<OrderBO>()
                        .eq(OrderBO::getOrderNumber, orderBO.getOrderNumber()));
                if (orderBO.getCancelTime() == null && orderBO.getFinallyTime() == null &&
                        orderBO.getStatus() != null && orderBO.getStatus() == 1 &&
                        orderBO.getIsPayed() != null && orderBO.getIsPayed() == 1) {
                    order.setFinallyTime(new Date());
                } else {
                    Date cancelTime = orderBO.getCancelTime();
                    order.setCancelTime(cancelTime == null ? new Date() : cancelTime);
                }
                orderService.updateById(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
