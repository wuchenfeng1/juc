package com.cunzai;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private  Map<Integer,Integer> map=new HashMap();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void put(Integer key,Integer value){
        readWriteLock.writeLock().lock();
        try {
             System.out.println(key+"在写入了");
             try {
                 TimeUnit.MICROSECONDS.sleep(300);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             map.put(key,value);
             System.out.println("已写入");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    public void get(Integer key){
        readWriteLock.writeLock().lock();
        try {  
        System.out.println(key+"在读入了");
          try {
              TimeUnit.MICROSECONDS.sleep(300);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }                                               

        Integer integer = map.get(key);
        System.out.println(key+"已读。。。"+integer);

          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              readWriteLock.writeLock().unlock();
          }

    }
}



public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache=new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt,tempInt);
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt);
            },String.valueOf(i)).start();
        }

    }
}
