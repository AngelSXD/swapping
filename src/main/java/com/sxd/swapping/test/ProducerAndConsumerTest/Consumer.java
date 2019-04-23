package com.sxd.swapping.test.ProducerAndConsumerTest;

public class Consumer implements Runnable{

    private Breakfast breakfast;

    public Consumer(Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    @Override
    public void run() {
        int i = 7;
        for (int i1 = 0; i1 < i; i1++) {
            System.out.println("星期"+(i1+1)+"---消费者要来吃东西了");
            this.breakfast.eatBreakfast();
        }
    }
}
