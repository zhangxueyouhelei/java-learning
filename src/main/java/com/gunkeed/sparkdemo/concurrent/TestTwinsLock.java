package com.gunkeed.sparkdemo.concurrent;

import java.util.concurrent.locks.Lock;

public class TestTwinsLock {
    private static final Lock lock = new TwinsLock();

    public static void main(String[] args) {

        for(int i=0; i < 10; i++){
            Worker worker = new Worker();
            Thread thread = new Thread(worker, "work" + 1);
            thread.start();
        }

        for(int i=0 ;i < 10; i++){
            SleepUtils.sleepSeconds(1);
            System.out.println();
        }
    }

    static class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    SleepUtils.sleepSeconds(1);
                    System.out.println(Thread.currentThread().getContextClassLoader());
                    SleepUtils.sleepSeconds(1);
                } finally {
                    lock.unlock();
                }
            }
        }
    }


}
