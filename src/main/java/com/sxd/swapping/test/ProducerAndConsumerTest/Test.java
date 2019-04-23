package com.sxd.swapping.test.ProducerAndConsumerTest;

public class Test {

    public static void main(String[] args) {
        Breakfast breakfast = new Breakfast();
        new Thread(new Producer(breakfast)).start();
        new Thread(new Consumer(breakfast)).start();
    }
}
