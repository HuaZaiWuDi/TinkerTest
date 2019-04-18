package com.wesmarclothing.tinkertest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName ThreadSignalDemo
 * @Date 2019/3/20 11:14
 * @Author JACK
 * @Describe TODO线程通信的方式
 * @Project TinkerTest
 */
public class ThreadSignalDemo {

    public static String StringObject = "";

    public static void main(String[] args) {
        String lock = "";
        Product product = new Product("");
        Consumer consumer = new Consumer(lock);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    product.setValue();
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    consumer.getValue();
//                }
//            }
//        }).start();


        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        product.setValue();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        consumer.getValue();
                    }
                }
            }).start();
        }

    }


}


///////////////////////////////////////////////////////////////////////////
// 生产者/消费者模式
///////////////////////////////////////////////////////////////////////////

//生产者

class Product {
    private String lock;
    private Condition mCondition;
    private ReentrantLock mReentrantLock;

    public Product(String lock) {
        this.lock = lock;
    }

    public Product(Condition condition, ReentrantLock reentrantLock) {
        mCondition = condition;
        mReentrantLock = reentrantLock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                if (!ThreadSignalDemo.StringObject.equals("")) {
                    //有值，不生产
                    lock.wait();
                }
                String value = System.currentTimeMillis() + "" + System.nanoTime();
                System.out.println("set的值是：" + value);
                ThreadSignalDemo.StringObject = value;
//                lock.notify();
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setMultiValue() {
        try {
            mReentrantLock.lock();
            while (!ThreadSignalDemo.StringObject.equals("")) {
                // 有值，不生产
                mCondition.await();
            }
            String value = System.currentTimeMillis() + "" + System.nanoTime();
            System.out.println("set的值是：" + value);
            ThreadSignalDemo.StringObject = value;
            mCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mReentrantLock.unlock();
        }


    }

}

//消费者
class Consumer {
    private String lock;
    private Condition mCondition;
    private ReentrantLock mReentrantLock;

    public Consumer(String lock) {
        this.lock = lock;
    }

    public Consumer(Condition condition, ReentrantLock reentrantLock) {
        mCondition = condition;
        mReentrantLock = reentrantLock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                if (ThreadSignalDemo.StringObject.equals("")) {
                    //没值，不进行消费
                    lock.wait();
                }
                System.out.println("get的值是：" + ThreadSignalDemo.StringObject);
                ThreadSignalDemo.StringObject = "";
//                lock.notify();
                lock.notifyAll();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getmultiValue() {
        try {
            mReentrantLock.lock();
            while (ThreadSignalDemo.StringObject.equals("")) {
                //没值，不进行消费
                mCondition.await();
            }
            System.out.println("get的值是：" + ThreadSignalDemo.StringObject);
            ThreadSignalDemo.StringObject = "";
            mCondition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mReentrantLock.unlock();
        }
    }

}
