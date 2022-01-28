package com.gohb.config.mq;


import com.gohb.constant.QueueConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class MqOrderConfig {

    @Value("${order.cancel.delay.time}")
    private long orderCancelDelayTime;

    @Bean
    public Queue orderMsQueue() {
        Map<String, Object> args = new HashMap<>();
        // 设置这个队列里面的消息 死亡后 走哪个交换机
        args.put("x-dead-letter-exchange", QueueConstant.ORDER_DEAD_EX);
        // 设置这个队列里面的消息 死亡后 走哪个路由key
        args.put("x-dead-letter-routing-key", QueueConstant.ORDER_DEAD_KEY);
        // 设置这个队列里面的消息的死亡时间 三分钟时间
        args.put("x-message-ttl", orderCancelDelayTime);
        return new Queue(QueueConstant.ORDER_MS_QUEUE, true, false, false, args);
    }

    @Bean
    public Queue orderDeadQueue() {
        return new Queue(QueueConstant.ORDER_DEAD_QUEUE);
    }

    @Bean
    public DirectExchange orderDeadEx() {
        return new DirectExchange(QueueConstant.ORDER_DEAD_EX);
    }

    @Bean
    public Binding orderDeadBing() {
        return BindingBuilder.bind(orderDeadQueue()).to(orderDeadEx()).with(QueueConstant.ORDER_DEAD_KEY);
    }


}

