package com.cunzai;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch c=new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println("累死了");
                c.countDown();
            },String.valueOf(i)).start();
        }
        c.await();
        System.out.println("完成了");
    }
}
