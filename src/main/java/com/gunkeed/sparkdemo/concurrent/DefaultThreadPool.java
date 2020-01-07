package com.gunkeed.sparkdemo.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends  Runnable> implements ThreadPool<Job> {

    /**
     * 线程最大限制数
     */
    private static final  int MAX_WORKER_NUMBERS = 10;

    /**
     * 线程池默认的数据
     */
    private static final  int DEFUALT_WORKER_NUMBERS = 5;

    /**
     * 线程池最小的数量
     */
    private static final int MIN_WORKER_NUMBERS = 1;

    /**
     *
     */
    private final LinkedList<Job> jobs = new LinkedList<>();

    /**
     * 工作者列表
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    /**
     * 工作线程数量
     */
    private int workerNum = DEFUALT_WORKER_NUMBERS;

    /**
     * 线程编号生成器
     */
    private AtomicLong threadNum = new AtomicLong();


    public DefaultThreadPool(int number) {
        this. workerNum = number > MAX_WORKER_NUMBERS? MAX_WORKER_NUMBERS : number<MAX_WORKER_NUMBERS? MAX_WORKER_NUMBERS : number ;
        initializedWorkers(workerNum);
    }

    private void initializedWorkers(int workerNum) {
        for (int i = 0; i <workerNum ; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }


    @Override
    public void execute(Job job) {
        if(job!=null){
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutDown() {
        for (Worker worker : workers){
            worker.shutDown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs){
            if(num + this.workerNum > MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializedWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs){
            if (num > this.workerNum){
                throw new IllegalArgumentException();
            }

            int count = 0;
            while (num < 0){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutDown();
                    count ++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }


    class Worker implements Runnable{

        private volatile boolean running  =  false;

        @Override
        public void run() {
            while (running){
                Job job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }

                if(job != null){
                    try {
                        job.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        public void shutDown(){
            running = false;
        }
    }

}
