package com.gohb.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

public class JedisDistributedLockUtil {

    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static Boolean tryGetDistributedLock(JedisPool jedisPool, String lockKey, String requestId, int expireTime) throws Exception {
        Jedis jedis = jedisPool.getResource();
        try {
            /*
             *  redis2.8版本之后引入了指令 set key value [EX seconds] [PX milliseconds] [NX|XX] ,该指令可以同时执行 setnx 和 expire ，于是解决了死锁问题；
             *  EX seconds: 设定过期时间，单位为秒
             *  PX milliseconds: 设定过期时间，单位为毫秒
             *  NX: key不存在时设置值
             *  XX: key存在时设置值
             *
             *  set(String lockKey, String requestId, String NX, String PX, int expireTime)
             *  lockKey     锁键
             *  requestId   锁值(发起请求的客户端的标识)---互斥性。在任意时刻，只有一个客户端能持有锁
             *  NX          (set if not exist)如果该key没有值则设值为requestId,如果有值则不操作
             *  PX          (set with expire time)设置过期时间
             *  expireTime  过期时间(ms)
             */
            for (int i = 0; i < 5; i++) { //最多尝试5次
                Thread.sleep(3 * 1000); // 设置间隔时间 3 秒
                String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
                if (LOCK_SUCCESS.equals(result)) {
//                    break;
                    return true;
                }
                if (i == 4 && !LOCK_SUCCESS.equals(result)) {
//                    throw new Exception("获取Redis分布式锁超时，请重试");
                    return false;
                }
            }
        } catch (Exception e) {
//            throw new Exception("获取Redis分布式锁异常", e);
            return false;
        } finally {
            jedis.close();
        }
        return false;
    }


    /**
     * 释放分布式锁
     * @param jedisPool jedis链接池
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static void releaseDistributedLock(JedisPool jedisPool, String lockKey, String requestId) throws Exception {
        Jedis jedis = jedisPool.getResource();
        try {
            // lua脚本[保证执行命令的原子性]
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
            if (!RELEASE_SUCCESS.equals(result)) {
                throw new Exception("释放Redis分布式锁失败");
            }
        } catch (Exception e) {
            throw new Exception("释放Redis分布式锁异常", e);
        } finally {
            jedis.close();
        }
    }


}
