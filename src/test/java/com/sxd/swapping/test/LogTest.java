package com.sxd.swapping.test;


import org.junit.Test;

public class LogTest {



    @Test
    public void system(){
        long start = System.currentTimeMillis() ;

        for (int i = 0; i < 1000000; i++) {
            System.out.println("System.out.println输出"+i);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(time);
    }
}
