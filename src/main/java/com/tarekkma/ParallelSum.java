package com.tarekkma;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelSum {

    public static void main(String[] args) {
        final int THREAD_COUNT = 1000;
        ExecutorService e = Executors.newCachedThreadPool();
        IntegerWrapper integerWrapper = new IntegerWrapper();

        for (int i = 0;i<THREAD_COUNT;i++){
            e.execute(new AdderTask(integerWrapper));
        }

        e.shutdown();
        while (!e.isTerminated());
        System.out.println("Number is: "+integerWrapper.getValue());
    }

    public static class IntegerWrapper {
        private int value = 0;
        public int getValue() { return value; }
        public synchronized void increment(){
            value++;
        }
    }

    public static class AdderTask implements Runnable {
        final IntegerWrapper integerWrapper;

        public AdderTask(IntegerWrapper integerWrapper) {
            this.integerWrapper = integerWrapper;
        }

        @Override
        public void run() {
            integerWrapper.increment();
        }
    }

}
