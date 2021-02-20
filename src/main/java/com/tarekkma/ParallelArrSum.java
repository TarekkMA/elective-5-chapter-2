package com.tarekkma;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelArrSum {

  public static void main(String[] args) {
    final int arrLen = 1_000_000;
    double[] arr = new double[arrLen];

    for (int i = 0; i < arrLen; i++) {
      arr[i] = Math.random();
    }
    ForkJoinPool pool = new ForkJoinPool();
    double sum = pool.invoke(new AddTask(arr, 0, arr.length));
    System.out.println("Sum is " + sum);
  }

  public static class AddTask extends RecursiveTask<Double> {
    private static final long serialVersionUID = 1L;
    final private int THREADHOLD = 100;

    final double[] arr;
    final int start;
    final int end;

    public AddTask(double[] arr, int start, int end) {
      this.arr = arr;
      this.start = start;
      this.end = end;
    }

    @Override
    protected Double compute() {
      if (end - start > THREADHOLD) {
        int middle = (start + end) / 2;
        RecursiveTask<Double> task1 = new AddTask(arr, start, middle);
        RecursiveTask<Double> task2 = new AddTask(arr, middle, end);
        invokeAll(task1, task2);
        return task1.join() + task2.join();
      }

      double sum = 0;
      for (int i = start; i < end; i++) {
        sum += arr[i];
      }
      return sum;
    }

  }
}
