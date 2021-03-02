package com.cunzai.ym;


import java.util.concurrent.TimeUnit;

public class DeadLockDemo {

    private static String a="a";
    private static String b="b";

    public static void main(String[] args) {
        Thread  threada=new Thread(()->{synchronized (a){
            System.out.println("threada 同步代码快");
            synchronized (b){
                System.out.println("threadb 同步代码快");
            }
        }});
        Thread  threadb=new Thread(()->{synchronized (b){
            System.out.println("threadb 同步代码快");
            synchronized (a){
                System.out.println("threada 同步代码快");
            }
        }});
        threada.start();
        try {
            TimeUnit.MICROSECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadb.start();
    }
}
