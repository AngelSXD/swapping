package com.sxd.swapping.utils;

import java.util.*;

/**
 * 关于Map的所有测试 以及工具类
 * 都在这里
 *
 */
public class MapUtils {


    public static void main3(String[] args) {
        Map<String,String>  hashMap = new TreeMap<>();
        long beginTime = System.currentTimeMillis();
        System.out.println("TreeMap存储开始时间-->"+beginTime);
        for (int i = 0; i < 1000000; i++) {
            hashMap.put(UUID.randomUUID().toString(),UUID.randomUUID().toString());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("TreeMap存储结束时间-->"+endTime);
        System.out.println("TreeMap存储消耗："+(endTime-beginTime)+"ms");

        System.out.println("TreeMap【entrySet方式】读取开始时间-->"+endTime);
        //可以使用外部定义变量
        for (Map.Entry<String,String> entry :  hashMap.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("TreeMap【entrySet方式】读取结束时间-->"+endTime2);
        System.out.println("TreeMap【entrySet方式】读取消耗："+(endTime2-endTime)+"ms");
    }


    public static void main2(String[] args) {
        Map<String,String>  hashMap = new TreeMap<>();
        long beginTime = System.currentTimeMillis();
        System.out.println("TreeMap存储开始时间-->"+beginTime);
        for (int i = 0; i < 1000000; i++) {
            hashMap.put(UUID.randomUUID().toString(),UUID.randomUUID().toString());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("TreeMap存储结束时间-->"+endTime);
        System.out.println("TreeMap存储消耗："+(endTime-beginTime)+"ms");

        System.out.println("TreeMap【keySet方式】读取开始时间-->"+endTime);
        //可以使用外部定义变量
        Set<String> keySet = hashMap.keySet();
        for (String s : keySet) {
            System.out.println(s+"："+hashMap.get(s));
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("TreeMap【keySet方式】读取结束时间-->"+endTime2);
        System.out.println("TreeMap【keySet方式】读取消耗："+(endTime2-endTime)+"ms");
    }


//    public static void main(String[] args) {
//        Map<String,String>  hashMap = new TreeMap<>();
//        long beginTime = System.currentTimeMillis();
//        System.out.println("TreeMap存储开始时间-->"+beginTime);
//        for (int i = 0; i < 10000000; i++) {
//            hashMap.put(UUID.randomUUID().toString(),UUID.randomUUID().toString());
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("TreeMap存储结束时间-->"+endTime);
//        System.out.println("TreeMap存储消耗："+(endTime-beginTime)+"ms");
//
//        System.out.println("TreeMap【forEach方式】读取开始时间-->"+endTime);
//        //不能使用外部定义变量 除非final类型 例如：List
//        hashMap.forEach((k,v)->{
//            System.out.println(k +"："+v);
//        });
//        long endTime2 = System.currentTimeMillis();
//        System.out.println("TreeMap【forEach方式】读取结束时间-->"+endTime2);
//        System.out.println("TreeMap【forEach方式】读取消耗："+(endTime2-endTime)+"ms");
//    }

}
