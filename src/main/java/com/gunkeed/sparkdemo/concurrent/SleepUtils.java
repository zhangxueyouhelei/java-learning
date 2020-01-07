package com.gunkeed.sparkdemo.concurrent;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

    public static final void sleepSeconds(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
