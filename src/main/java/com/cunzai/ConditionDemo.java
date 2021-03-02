package com.cunzai;

import sun.misc.Unsafe;

import java.net.UnknownServiceException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
     private int number = 1;//A:1,B:2,C:3
     private Lock lock=new ReentrantLock();
     private Condition condition3 = lock.newCondition();
     private Condition condition1 = lock.newCondition();
     private Condition condition2= lock.newCondition();
     public  void  prnit1(){
         lock.lock();
         try {
             while (number!=1){
                 condition1.await();
             }
             for (int i = 0; i < 5; i++) {
                 System.out.println("a:"+i);
             }
             number=2;
             condition2.signal();
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             lock.unlock();
         }
     }
    public  void  prnit2(){
        lock.lock();
        try {
            Unsafe.getUnsafe().compareAndSwapInt()



            while(number!=2){
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("b:"+i);
            }
            number=3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public  void  prnit3(){
        lock.lock();
        try{
        while(number!=3){
                condition3.await();
            }
            for (int i = 0; i < 7; i++) {
                System.out.println("c:"+i);
            }
            number=1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }











}




public class ConditionDemo{


    public   static void main(String[] args) {
        ShareData shareData=new ShareData();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{shareData.prnit1();},"a").start();
            new Thread(()->{shareData.prnit2();},"b").start();
            new Thread(()->{shareData.prnit3();},"c").start();
        }

    }
}