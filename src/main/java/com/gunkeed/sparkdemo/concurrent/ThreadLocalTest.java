package com.gunkeed.sparkdemo.concurrent;

/**
 * ThreadLocal测试
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();


    static void print(String str){
        System.out.println(str + ":" + threadLocal.get());
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("threadone local variable");
                print("thread one");
             //   S
            }
        });
    }
}
