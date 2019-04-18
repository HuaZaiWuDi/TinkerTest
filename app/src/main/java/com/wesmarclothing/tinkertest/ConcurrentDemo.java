package com.wesmarclothing.tinkertest;

/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName ConcurrentDemo
 * @Date 2019/3/19 17:26
 * @Author JACK
 * @Describe TODO
 * @Project TinkerTest
 */
public class ConcurrentDemo {


    /**
     * 死锁
     */

    public static void main(String[] args) {



    }

}


//可能发生静态锁顺序死锁的代码
class StaticLockOrderDeadLock {
    private final Object lockA = new Object();
    private final Object lockB = new Object();


    //避免死锁，需要保持锁顺序一致
    public void a() {
        synchronized (lockA) {
            synchronized (lockB) {
                System.out.println("function a");
            }
        }
    }

    public void b() {
        synchronized (lockB) {
            synchronized (lockA) {
                System.out.println("function b");
            }
        }
    }

}