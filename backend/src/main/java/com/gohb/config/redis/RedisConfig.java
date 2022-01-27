package com.gohb.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String REDIS_HOST;

    @Value("${redis.port}")
    private Integer REDIS_PORT;

    @Value("${redis.password}")
    private String PASSWORD;

    @Value("${redis.jedis.timeout}")
    private Integer TIME_OUT;

    @Value("${redis.jedis.pool.max-idle}")
    private Integer MAX_IDLE;

    @Value("${redis.jedis.pool.max-wait}")
    private Long MAX_WAIT;

    @Value("${redis.jedis.pool.min-idle}")
    private Integer MIN_IDLE;



    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setMinIdle(MIN_IDLE);
        if ("".equals(PASSWORD)) {
            return new JedisPool(config, REDIS_HOST, REDIS_PORT, TIME_OUT);
        }
        return new JedisPool(config, REDIS_HOST, REDIS_PORT, TIME_OUT, PASSWORD);
    }

}
