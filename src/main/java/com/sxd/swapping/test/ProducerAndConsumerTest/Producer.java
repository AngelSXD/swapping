package com.sxd.swapping.test.ProducerAndConsumerTest;

public class Producer implements Runnable{

    private Breakfast breakfast;

    public Producer(Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    @Override
    public void run() {
        int i = 7;
        for (int i1 = 0; i1 < i; i1++) {
            if (i1 %2 == 0){
                this.breakfast.makeBreakfast("馒头","豆浆");
            }else {
                this.breakfast.makeBreakfast("面包","冷饮");
            }
        }
    }
}