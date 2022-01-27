package com.gohb.manage.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.constant.QueueConstant;
import com.gohb.params.bo.prod.OrderBO;
import com.gohb.service.prod.OrderService;
import com.gohb.utils.ApplicationContextHolder;
import com.rabbitmq.client.Channel;
import io.netty.util.HashedWheelTimer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class RabbitMQDelayQueue {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void writeToDelayQueue(String orderNumber, Long orderCancelDelayTime) {
        RabbitTemplate rabbitTemplate = ApplicationContextHolder.getApplicationContext().getBean(RabbitTemplate.class);
        rabbitTemplate.convertAndSend(QueueConstant.ORDER_MS_QUEUE, orderNumber);
    }

    /**
     * 处理订单超时未支付的监听
     *
     * @param message
     * @param channel
     */
    @RabbitListener(queues = QueueConstant.ORDER_DEAD_QUEUE, concurrency = "3-5")
    public void orderDeadHandler(Message message, Channel channel) {
        String orderNum = new String(message.getBody());
        // 签收消息
        try {
            cancelOrder(orderNum);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error("延迟订单处理失败，可以重试，也可以记录数据库");
        }
    }

    private void cancelOrder(String orderNumber) {
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
