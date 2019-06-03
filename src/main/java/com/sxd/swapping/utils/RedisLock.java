package com.sxd.swapping.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import java.util.Collections;


/**
 *
 * spring boot 1.x版本
 * @author sxd
 * @date 2019/5/27 10:52
 */
@Component
public class RedisLock {

    @Autowired
    RedisTemplate redisTemplate;

    private static final Long SUCCESS = 1L;

    /**
     * 获取锁
     *
     * @param lockKey
     * @param value
     * @param expireTime：单位-秒
     * @return
     */
    public  boolean lock(String lockKey, String value, int expireTime) {

        String script = "if redis.call('setNx',KEYS[1],ARGV[1])  then " +
                "   if redis.call('get',KEYS[1])==ARGV[1] then " +
                "      return redis.call('expire',KEYS[1],ARGV[2]) " +
                "   else " +
                "      return 0 " +
                "   end " +
                "end";

        RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

        //对非string类型的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value, String.valueOf(expireTime));

        return SUCCESS.equals(result);

    }

    /**
     * 释放锁
     *
     * @param lockKey
     * @param value
     * @return
     */
    public  boolean unlock(String lockKey, String value) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

        try {
            Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value);
            if (SUCCESS.equals(result)) {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }



















//    ThreadLocal<String> localKeys = new ThreadLocal();
//
//    ThreadLocal<String> localRequestIds = new ThreadLocal();
//
//
//    public static final String REDIS_KEY_LOG_WAITING_KEY = "MS_ZHONGQI:WAITING_KEY";
//
//
//
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    Logger logger = Logger.getRootLogger();
//
//    /**
//     * 定义获取锁的lua脚本
//     * pexpire 设置过期时间以毫秒为单位
//     *
//     *  -- KEYS[1]表示key
//     *  -- KEYS[2]表示value
//     *  -- KEYS[3]表示过期时间
//     */
//    private final static DefaultRedisScript<Object> LOCK_LUA_SCRIPT = new DefaultRedisScript<>(
//            "if redis.call('setnx', KEYS[1], KEYS[2]) == 1 then return redis.call('pexpire', KEYS[1], KEYS[3]) else return 0 end"
//            , Object.class
//    );
//
//    public static final String LOCK_SUCCESS = "OK";
//
//
//    /**
//     * 定义释放锁的lua脚本
//     *  -- KEYS[1]表示key
//     *  -- KEYS[2]表示value  要求是随机数的原因  是防止释放别的线程的锁
//     */
//    private final static DefaultRedisScript<Object> UNLOCK_LUA_SCRIPT = new DefaultRedisScript<>(
//            "if redis.call('get',KEYS[1]) == KEYS[2] then return redis.call('del',KEYS[1]) else return -1 end"
//            , Object.class
//    );
//
//    public static final String RELEASE_SUCCESS = "1"; //删除命令成功执行，返回1
//
//    public static final String LOCK_EXPIRED = "-1"; //未删除则返回-1
//
//    /**
//     * 加锁
//     * @param key Key
//     * @param timeout 过期时间  设置为5分钟 5*60*1000 = 300000
//     * @param retryTimes 重试次数   重试次数设置为3次
//     * @param retryTime  重试间隔休眠时长  100000
//     * @return
//     */
//    public boolean lock(String key, long timeout, int retryTimes,long retryTime) {
//        try {
//            final String redisKey = this.getRedisKey(key); //redis的key
//            final String requestId = this.getRequestId();  //redis的key对应的 value要求是一个随机字符串[可以是业务数据的 UID]
//
//            logger.debug("准备key和value：lock :::: redisKey = " + redisKey + " requestid = " + requestId);
//            //组装lua脚本参数
//            List<Object> keys = Arrays.asList(redisKey, requestId, timeout);
//            //执行脚本
//            String result = String.valueOf(redisTemplate.execute(LOCK_LUA_SCRIPT, keys));
//            //存储本地变量
//            if(!StringUtils.isEmpty(result) && LOCK_SUCCESS.equals(result)) {
//                localRequestIds.set(requestId);
//                localKeys.set(redisKey);
//                logger.info("加锁成功：success to acquire lock:" + Thread.currentThread().getName() + ", Status code reply:" + result);
//                return true;
//            } else if (retryTimes == 0) {
//                //重试次数为0直接返回失败
//                return false;
//            } else {
//                //重试获取锁
//                logger.info("重试加锁：retry to acquire lock:" + Thread.currentThread().getName() + ", Status code reply:" + result);
//                int count = 0;
//                while(true) {
//                    try {
//                        //休眠一定时间后再获取锁，这里时间可以通过外部设置
//                        Thread.sleep(retryTime);
//                        result = String.valueOf(redisTemplate.execute(LOCK_LUA_SCRIPT, keys));
//                        if(!StringUtils.isEmpty(result) && LOCK_SUCCESS.equals(result)) {
//                            localRequestIds.set(requestId);
//                            localKeys.set(redisKey);
//                            logger.info("加锁成功：success to acquire lock:" + Thread.currentThread().getName() + ", Status code reply:" + result);
//                            return true;
//                        } else {
//                            count++;
//                            if (retryTimes == count) {
//                                logger.info("加锁失败：fail to acquire lock for " + Thread.currentThread().getName() + ", Status code reply:" + result);
//                                return false;
//                            } else {
//                                logger.warn("加锁失败，重试："+count + " times try to acquire lock for " + Thread.currentThread().getName() + ", Status code reply:" + result);
//                                continue;
//                            }
//                        }
//                    } catch (Exception e) {
//                        logger.error("加锁异常：acquire redis occured an exception:" + Thread.currentThread().getName(), e);
//                        break;
//                    }
//                }
//            }
//        } catch (Exception e1) {
//            logger.error("加锁异常：acquire redis occured an exception:" + Thread.currentThread().getName(), e1);
//        }
//        return false;
//    }
//
//
//
//    /**
//     * 获取RedisKey
//     * @param key 原始KEY，如果为空，自动生成随机KEY
//     * @return
//     */
//    private String getRedisKey(String key) {
//        //如果Key为空且线程已经保存，直接用，异常保护
//        if (StringUtils.isEmpty(key) && !StringUtils.isEmpty(localKeys.get())) {
//            return localKeys.get();
//        }
//        //如果都是空那就抛出异常
//        if (StringUtils.isEmpty(key) && StringUtils.isEmpty(localKeys.get())) {
//            throw new RuntimeException("key is null");
//        }
//        return REDIS_KEY_LOG_WAITING_KEY;
//    }
//
//    /**
//     * 获取随机请求ID
//     * @return
//     */
//    private String getRequestId() {
//        return UUID.randomUUID().toString();
//    }
//
//
//
//
//
//
//
//
//    /**
//     * 释放KEY
//     * @param key
//     * @return
//     */
//    public boolean unlock(String key) {
//        try {
//            String localKey = localKeys.get();
//            //如果本地线程没有KEY，说明还没加锁，不能释放
//            if(StringUtils.isEmpty(localKey)) {
//                logger.error("解锁失败：release lock occured an error: lock key not found");
//                return false;
//            }
//            String redisKey = getRedisKey(key);
//            //判断KEY是否正确，不能释放其他线程的KEY
//            if(!StringUtils.isEmpty(localKey) && !localKey.equals(redisKey)) {
//                logger.error("解锁失败：release lock occured an error: illegal key:" + key);
//                return false;
//            }
//            //组装lua脚本参数
//            List<String> keys = Arrays.asList(redisKey, localRequestIds.get());
//            logger.debug("准备解锁：unlock :::: redisKey = " + redisKey + " requestid = " + localRequestIds.get());
//            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
//            String result = String.valueOf(redisTemplate.execute(UNLOCK_LUA_SCRIPT, keys));
//            //如果这里抛异常，后续锁无法释放
//            if (!StringUtils.isEmpty(result) && RELEASE_SUCCESS.equals(result)) {
//                logger.info("解锁成功：release lock success:" + Thread.currentThread().getName() + ", Status code reply=" + result);
//                return true;
//            } else if (!StringUtils.isEmpty(result) && LOCK_EXPIRED.equals(result)) {
//                //返回-1说明获取到的KEY值与requestId不一致或者KEY不存在，可能已经过期或被其他线程加锁
//                // 一般发生在key的过期时间短于业务处理时间，属于正常可接受情况
//                logger.warn("解锁失败：release lock exception:" + Thread.currentThread().getName() + ", key has expired or released. Status code reply=" + result);
//            } else {
//                //其他情况，一般是删除KEY失败，返回0
//                logger.error("解锁成功：release lock failed:" + Thread.currentThread().getName() + ", del key failed. Status code reply=" + result);
//            }
//        } catch (Exception e) {
//            logger.error("解锁异常：release lock occured an exception", e);
//        } finally {
//            //清除本地变量
//            this.clean();
//        }
//        return false;
//    }
//
//
//
//    /**
//     * 清除本地线程变量，防止内存泄露
//     */
//    private void clean() {
//        localRequestIds.remove();
//        localKeys.remove();
//    }
//







}
