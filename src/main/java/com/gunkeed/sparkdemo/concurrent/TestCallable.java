package com.gunkeed.sparkdemo.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable implements Callable {

    @Override
    public String call() throws Exception {
        return  "hello";
    }


    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new TestCallable());
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
