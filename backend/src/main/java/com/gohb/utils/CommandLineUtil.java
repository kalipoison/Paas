package com.gohb.utils;

import com.gohb.manage.prod.impl.RedisDelayQueue;
import com.gohb.manage.prod.impl.StringRedisTemplateDelayQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 *  启动后触发操作
 */
@Component
public class CommandLineUtil implements CommandLineRunner {

    @Value("${order.cancel.method}")
    private Integer orderCancelMethod;

    @Override
    public void run(String... args) throws Exception {
//        ThreadPoolTaskExecutor executorService = ApplicationContextHolder.getApplicationContext().getBean(ThreadPoolTaskExecutor.class);
//        executorService.submit(()->{
//            RedisDelayQueue.DelayQueueCancelOrder();
//        });
        if (orderCancelMethod == 3) {
            RedisDelayQueue.DelayQueueCancelOrder();
        } else if (orderCancelMethod == 4) {
            StringRedisTemplateDelayQueue.DelayQueueCancelOrder();
        }
    }
}
