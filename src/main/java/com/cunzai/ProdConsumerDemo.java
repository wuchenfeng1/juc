package com.cunzai;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition{

    private int num=0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void  increment()  {
        lock.lock();
        try {
        while(num!=0){
         condition.await();
        }
        num++;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
    public void  deincrement() {
        lock.lock();
        try{
        while(num==0){
            condition.await();
        }
        num--;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
         condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}




public class ProdConsumerDemo {



    public static void main(String[] args) {
        Aircondition a=new Aircondition();
        for (int i = 0; i < 20; i++) {
            new  Thread(()->{a.increment();},"a").start();
            new  Thread(()->{a.deincrement();},"b").start();
            new  Thread(()->{a.increment();},"c").start();
            new  Thread(()->{a.deincrement();},"d").start();
        }
    }

}
