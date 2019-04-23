package com.sxd.swapping.test.ProducerAndConsumerTest;

/**
 * 早餐基础类
 *
 * wait()
 * notify()
 * notifyAll()
 * 三个方法 需要放在同步代码块中执行 因为要获取对象锁
 */
public class Breakfast{
    private  String food;

    private  String drink;

    private boolean flag = false;//flag = false 表示需要生产  flag = true 表示需要消费


    public synchronized  void  makeBreakfast(String food,String drink){

        System.out.println("生产者进入--->标志值为："+flag);
        if (flag){
            try {
                System.out.println("make---wait()暂停，释放对象锁");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.food = food;
        try {
            System.out.println("make---sleep()休眠,不释放对象锁");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.drink = drink;
        System.out.println("make---生产者制造东西完成----");
        this.flag = true;
        System.out.println("make---notify()唤醒，标志值为"+flag);
        notify();
    }


    public synchronized void eatBreakfast(){

        System.out.println("消费者进入--->标志值为："+flag);
        if(!flag){
            try {
                System.out.println("eat---wait()");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println("eat---sleep()");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("吃东西---"+this.food+";喝东西---"+this.drink);
        this.flag = false;
        System.out.println("eat---notify()唤醒，标志值为"+flag);
        notify();
    }
}