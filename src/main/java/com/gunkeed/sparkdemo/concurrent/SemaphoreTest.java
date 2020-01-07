package com.gunkeed.sparkdemo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 测试并发信号量semaphore
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static final ExecutorService es = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i=0; i < THREAD_COUNT; i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("I am saving data");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        es.shutdown();
    }
}
