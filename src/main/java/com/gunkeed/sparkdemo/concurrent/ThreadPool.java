package com.gunkeed.sparkdemo.concurrent;
/*
    线程池接口

 */

public interface ThreadPool<Job extends Runnable> {

    void execute(Job job);

    void shutDown();

    void addWorkers(int num);

    void  removeWorker(int num);

    int getJobSize();
}
