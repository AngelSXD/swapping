package com.sxd.swapping.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

/**
 *
 * spring boot 2.x版本
 * @author sxd
 * @date 2019/5/27 16:11
 */
@Component
public class RedisLock2 {


    Logger  logger = Logger.getRootLogger();

    static final Long LOCK_SUCCESS = 1L;

    static final Long LOCK_EXPIRED = -1L;

    @Autowired
    RedisTemplate redisTemplate;

    //定义获取锁的lua脚本
    private final static DefaultRedisScript<Long> LOCK_LUA_SCRIPT = new DefaultRedisScript<>(
            "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then return redis.call('pexpire', KEYS[1], ARGV[2]) else return 0 end"
            , Long.class
    );



    //定义释放锁的lua脚本
    private final static DefaultRedisScript<Long> UNLOCK_LUA_SCRIPT = new DefaultRedisScript<>(
            "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return -1 end"
            , Long.class
    );



    /**
     * 加锁
     * @param key redis键值对 的 key
     * @param value redis键值对 的 value  随机串作为值
     * @param timeout redis键值对 的 过期时间   pexpire 以毫秒为单位
     * @param retryTimes 重试次数   即加锁失败之后的重试次数，根据业务设置大小
     * @return
     */
    public boolean lock(String key,String value ,long timeout, int retryTimes) {
        try {

            logger.debug("加锁信息：lock :::: redisKey = " + key + " requestid = " + value);
            //组装lua脚本参数
            List<String> keys = Arrays.asList(key);
            //执行脚本
            Object result = redisTemplate.execute(LOCK_LUA_SCRIPT, keys,value,timeout);
            //存储本地变量
            if(LOCK_SUCCESS.equals(result)) {

                logger.info("成功加锁：success to acquire lock:" + Thread.currentThread().getName() + ", Status code reply:" + result);
                return true;
            } else if (retryTimes == 0) {
                //重试次数为0直接返回失败
                return false;
            } else {
                //重试获取锁
                logger.info("重试加锁：retry to acquire lock:" + Thread.currentThread().getName() + ", Status code reply:" + result);
                int count = 0;
                while(true) {
                    try {
                        //休眠一定时间后再获取锁，这里时间可以通过外部设置
                        Thread.sleep(100);
                        result = redisTemplate.execute(LOCK_LUA_SCRIPT, keys);
                        if(LOCK_SUCCESS.equals(result)) {

                            logger.info("成功加锁：success to acquire lock:" + Thread.currentThread().getName() + ", Status code reply:" + result);
                            return true;
                        } else {
                            count++;
                            if (retryTimes == count) {
                                logger.info("加锁失败：fail to acquire lock for " + Thread.currentThread().getName() + ", Status code reply:" + result);
                                return false;
                            } else {
                                logger.warn(count + " times try to acquire lock for " + Thread.currentThread().getName() + ", Status code reply:" + result);
                                continue;
                            }
                        }
                    } catch (Exception e) {
                        logger.error("加锁异常：acquire redis occured an exception:" + Thread.currentThread().getName(), e);
                        break;
                    }
                }
            }
        } catch (Exception e1) {
            logger.error("加锁异常：acquire redis occured an exception:" + Thread.currentThread().getName(), e1);
        }
        return false;
    }








    /**
     * 释放KEY
     * @param key   释放本请求对应的锁的key
     * @param value 释放本请求对应的锁的value  是不重复随即串 用于比较，以免释放别的线程的锁
     * @return
     */
    public boolean unlock(String key,String value) {
        try {

            //组装lua脚本参数
            List<String> keys = Arrays.asList(key);
            logger.debug("解锁信息：unlock :::: redisKey = " + key + " requestid = " + value);
            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁

            Object result = redisTemplate.execute(UNLOCK_LUA_SCRIPT, keys, value);
            //如果这里抛异常，后续锁无法释放
            if (LOCK_SUCCESS.equals(result)) {
                logger.info("解锁成功：release lock success:" + Thread.currentThread().getName() + ", Status code reply=" + result);
                return true;
            } else if (LOCK_EXPIRED.equals(result)) {
                //返回-1说明获取到的KEY值与requestId不一致或者KEY不存在，可能已经过期或被其他线程加锁
                // 一般发生在key的过期时间短于业务处理时间，属于正常可接受情况
                logger.warn("解锁异常：release lock exception:" + Thread.currentThread().getName() + ", key has expired or released. Status code reply=" + result);
            } else {
                //其他情况，一般是删除KEY失败，返回0
                logger.error("解锁失败：release lock failed:" + Thread.currentThread().getName() + ", del key failed. Status code reply=" + result);
            }
        } catch (Exception e) {
            logger.error("解锁异常：release lock occured an exception", e);
        }

        return false;
    }





}
