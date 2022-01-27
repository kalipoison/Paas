package com.gohb.utils;

import com.gohb.manage.prod.impl.RedisDelayQueue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 *  启动后触发操作
 */
@Component
public class CommandLineUtil implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
//        ThreadPoolTaskExecutor executorService = ApplicationContextHolder.getApplicationContext().getBean(ThreadPoolTaskExecutor.class);
//        executorService.submit(()->{
//            RedisDelayQueue.DelayQueueCancelOrder();
//        });
        RedisDelayQueue.DelayQueueCancelOrder();
    }
}
