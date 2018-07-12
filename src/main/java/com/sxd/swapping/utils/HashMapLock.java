package com.sxd.swapping.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用 HashMap  实现 同步锁机制
 */
public class HashMapLock {

    public static final Map<String, Object> LOCKS = new HashMap<>();

    /**
     * 设置锁
     * @param uid
     * @return
     */
    private synchronized static Object setLock(String uid) {

        Object object = LOCKS.get(uid);

        if (object == null) {
            object = new Object();
            LOCKS.put(uid, object);
        }

        return object;

    }

    /**
     * 获取锁
     * @param uid
     * @return
     */
    public static Object getLock(String uid) {

        Object object = LOCKS.get(uid);

        if (object == null) {
            object = setLock(uid);
        }
        return object;
    }


    /**
     * 使用锁
     * 测试方法
     */
    public  void test(String uid){

        //其他逻辑

        /**
         *  使用上面实现的锁机制  实现同步
         */
        synchronized(HashMapLock.getLock(uid)){
            System.out.println("被锁住的同步方法 代码块");
        }

    }

}
