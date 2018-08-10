package com.sxd.swapping.utils;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ThreadTest {


    /**
     * 主线程
     */
    @Test
    public  void  test(){

        //开启10个多线程
        int threadCount = 10;

        //所有线程阻塞，然后统一开始
        CountDownLatch begin = new CountDownLatch(1);

        //主线程阻塞，直到所有分线程执行完毕
        CountDownLatch end = new CountDownLatch(threadCount);

        //开始多线程
        begin.countDown();
        for (Integer i = 0; i < threadCount; i++) {
            Runnable runnable = dealSomeThing(i,begin,end);
            new Thread(runnable).start();
        }

        //多个线程都执行结束
        try {
            end.await();
            System.out.println("多个线程都执行结束，可以做自己的事情了");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("多线程执行中出错了，凉凉了！！！");
        }
    }


    /**
     * 工作线程
     * 本方法  是在构造多线程要做的事情
     *
     * =====================可以做的事===================
     * 当然可以传入ConcurrentHashMap之类的线程安全的 类
     * 来记录线程中的处理结果之类的
     * 最后 在多线程都执行完了以后 就可以对处理结果进行操作了
     * ==================================================
     *
     * @param threadNum 当前线程编号
     * @param begin
     * @param end
     * @return
     */
    private Runnable dealSomeThing(int threadNum, CountDownLatch begin, CountDownLatch end){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("线程"+threadNum+":--------------------->开始工作");
                    begin.await();

                    System.out.println("线程"+threadNum+"做具体的事情,比如去service调用 具体的方法做什么操作之类的");

                    end.countDown();
                    System.out.println("线程"+threadNum+":--------------------->结束工作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        return runnable;
    }




}
