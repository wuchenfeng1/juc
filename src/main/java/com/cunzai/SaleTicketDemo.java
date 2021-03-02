package com.cunzai;

import sun.misc.Contended;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    @Contended
    private int number=30;
    private Lock lock=new ReentrantLock();
    public  void  saleTicket(){
        lock.lock();
        try{
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"卖了第"+number--+"票，已卖"+number+"张票");
            }else{
                System.out.println("没票了");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
@FunctionalInterface
interface Foo{
    void  eat();

}

/**
 *题目：三个售票员   卖出   30张票
 * 多线程编程的企业级套路+模板
 * 1.在高内聚低耦合的前提下，线程    操作(对外暴露的调用方法)     资源类
 */
public class SaleTicketDemo {
    public static void main(String[] args) {
        Executors.newCachedThreadPool();
//        Thread.State.BLOCKED
         final Ticket ticket=new Ticket();
        for (int i = 1; i <= 8; i++) {
             new Thread(() -> {  ticket.saleTicket();},"a").start();
             new Thread(() -> {  ticket.saleTicket();},"b").start();
             new Thread(() -> {  ticket.saleTicket();},"c").start();
             new Thread(()->{ticket.saleTicket();},"d").start();
        }

        Foo f=()-> System.out.println("111");
        f.eat();
    }
}
