package com.wesmarclothing.tinkertest;


/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName TestProxyDemo
 * @Date 2019/3/15 17:08
 * @Author JACK
 * @Describe TODO静态代理
 * @Project TinkerTest
 */
public class StaticProxyDemo {
    public static void main(String args[]) {
        RealSubject subject = new RealSubject();
        ProxyTest p = new ProxyTest(subject);
        p.request();
    }
}


class RealSubject implements Subject {


    public void getName() {
        System.out.println("request");
    }

    @Override
    public void request() {
        System.out.println("request");
    }
}

class ProxyTest implements Subject {
    private Subject subject;

    public ProxyTest(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        System.out.println("PreProcess");
        subject.request();
        System.out.println("PostProcess");
    }
}