package com.wesmarclothing.tinkertest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName DynamicProxyDemo
 * @Date 2019/3/15 17:30
 * @Author JACK
 * @Describe TODO动态代理
 * @Project TinkerTest
 */
public class DynamicProxyDemo {


    public static void main(String[] args) {

        //1.创建目标对象
        RealSubject2 realSubject = new RealSubject2();
        //2.创建调用处理器对象
        //3.动态生成代理对象
        Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject2.class.getClassLoader(),
                RealSubject2.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        //定义预处理的工作，当然你也可以根据 method 的不同进行不同的预处理工作
                        System.out.println("====before====");
                        //调用RealSubject中的方法
                        Object result = method.invoke(realSubject, args);
                        System.out.println("====after====");
                        return result;
                    }
                });
        //4.通过代理对象调用方法
        proxySubject.request();
    }
}


/**
 * 目标对象类
 */
class RealSubject2 implements Subject {

    @Override
    public void request() {
        System.out.println("====RealSubject Request====");
    }
}

/**
 * 代理类的调用处理器
 */
class ProxyHandler implements InvocationHandler {
    private Subject subject;

    public ProxyHandler(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //定义预处理的工作，当然你也可以根据 method 的不同进行不同的预处理工作
        System.out.println("====before====");
        //调用RealSubject中的方法
        Object result = method.invoke(subject, args);
        System.out.println("====after====");
        return result;
    }
}