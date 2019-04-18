package com.wesmarclothing.tinkertest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName ThreadDemo
 * @Date 2019/3/19 16:20
 * @Author JACK
 * @Describe TODO
 * @Project TinkerTest
 */
public class ThreadDemo {


    public static void main(String[] args) {

//        //Thread
//        new FirstThread().start();
//        new FirstThread().start();
//
//        //Runnable
//        new Thread(new RunnableTest(), "Runnable1").start();
//        new Thread(new RunnableTest(), "Runnable2").start();

        //Callable
        CallableTest callableTest = new CallableTest();
        FutureTask<Integer> futureTask = new FutureTask<>(callableTest);

        new Thread(futureTask, "futureTask1").start();


        try {
            System.out.println("futureTask :" + futureTask.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class FirstThread extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            System.out.println(getName() + ":" + i);
        }
    }
}


class RunnableTest implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }

    }
}

class CallableTest implements Callable {

    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
        return 10;
    }
}
