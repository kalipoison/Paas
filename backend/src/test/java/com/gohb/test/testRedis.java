package com.gohb.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
