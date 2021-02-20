package com.tarekkma;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelArrInit {

  public static void main(String[] args) {
    final int arrLen = 100_000_000;
    double[] arr1 = new double[arrLen];
    double[] arr2 = new double[arrLen];

    long time = System.currentTimeMillis();
    parallelAssignValues(arr1);
    System.out.println((System.currentTimeMillis() - time) + " msec - parallelAssignValues()");

    long time2 = System.currentTimeMillis();
    parallelAssignValues(arr2);
    System.out.println((System.currentTimeMillis() - time2) + " msec - sequentialAssignValues()");
  }

  public static void parallelAssignValues(double[] arr) {
    ForkJoinPool pool = new ForkJoinPool();
    pool.invoke(new AssignTask(arr, 0, arr.length));
  }

  public static void sequentialAssignValues(double[] arr) {
    Random r = new Random();
    for (int i = 0; i < arr.length; i++) {
      arr[i] = r.nextDouble();
    }
  }

  public static class AssignTask extends RecursiveAction {
    private static final long serialVersionUID = 1L;
    final private int THREADHOLD = 10000;

    final double[] arr;
    final int start;
    final int end;

    public AssignTask(double[] arr, int start, int end) {
      this.arr = arr;
      this.start = start;
      this.end = end;
    }

    @Override
    protected void compute() {
      if (end - start > THREADHOLD) {
        int middle = (start + end) / 2;
        invokeAll(new AssignTask(arr, start, middle), new AssignTask(arr, middle, end));
        return;
      }

      final Random r = new Random();
      for (int i = start; i < end; i++) {
        arr[i] = r.nextDouble();
      }

    }

  }
}
