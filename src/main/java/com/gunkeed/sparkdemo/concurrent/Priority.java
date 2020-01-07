package com.gunkeed.sparkdemo.concurrent;

public class Priority {

    private static volatile boolean NOT_START = true;

    private static volatile boolean NOT_END = true;

    public static void main(String[] args) {

    }

    static class Job implements Runnable{
        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (NOT_START){
                Thread.yield();
            }

            while (NOT_END){
                Thread.yield();
                jobCount++;
            }
        }
    }
}
