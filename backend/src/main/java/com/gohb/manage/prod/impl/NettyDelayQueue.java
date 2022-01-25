package com.gohb.manage.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.params.bo.prod.OrderBO;
import com.gohb.service.prod.OrderService;
import com.gohb.utils.ApplicationContextHolder;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 基于 netty 时间轮 实现延迟队列
 */
@Slf4j
public class NettyDelayQueue implements TimerTask {

    /**
     * 订单号
     */
    private String orderNumber;

    public static void writeToDelayQueue(String orderNumber, Long orderCancelDelayTime) {
        NettyDelayQueue nettyDelayQueue = new NettyDelayQueue(orderNumber);
        io.netty.util.Timer timer = new HashedWheelTimer();
        timer.newTimeout(nettyDelayQueue, orderCancelDelayTime, TimeUnit.MILLISECONDS);
    }


    public NettyDelayQueue(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public void run(Timeout timeout) throws Exception {
        ThreadPoolTaskExecutor executorService = ApplicationContextHolder.getApplicationContext().getBean(ThreadPoolTaskExecutor.class);
        executorService.submit(()->{
            cancelOrder();
        });
    }

    private void cancelOrder() {
        try {
            OrderService orderService = ApplicationContextHolder.getApplicationContext().getBean(OrderService.class);
            OrderBO orderBO = orderService.getOne(new LambdaQueryWrapper<OrderBO>()
                    .eq(OrderBO::getOrderNumber, this.orderNumber));
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
