package com.wesmarclothing.tinkertest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName HandlerThreadDemo
 * @Date 2019/3/21 15:31
 * @Author JACK
 * @Describe TODO
 * @Project TinkerTest
 */
public class HandlerThreadDemo {


    public static void main(String[] args) {
//
//        HandlerThread handlerThread = new HandlerThread("HandlerThreadDemo");
//
//        //HandlerThread必须先调用start()方法
//        handlerThread.start();
//
//        new Handler(handlerThread.getLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//            }
//        };

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(0, 0.75f, true);
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);
        map.get(1);
        map.get(2);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());

        }


    }

}
