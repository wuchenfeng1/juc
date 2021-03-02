package com.cunzai;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("我是MyThread。。。");
        return 1024;
    }
}




public class CallableDemo {
    public  static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask(new MyThread());
        try {
            new Thread(futureTask,"A").start();
            Integer result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
