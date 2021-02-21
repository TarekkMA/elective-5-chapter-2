package com.tarekkma;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Prime {
  public static void main(String[] args) {
    long number = 5708517697L;
    ForkJoinPool pool = new ForkJoinPool();
    boolean isPrime = pool.invoke(new CheckPrimeTask(number, 2, number));
    System.out.println(number + " : " + isPrime);
  }

  public static class CheckPrimeTask extends RecursiveTask<Boolean> {
    private static final long serialVersionUID = 1L;

    final long THREASHHOLD = 10000;
    final long number;
    final long start;
    final long end;

    public CheckPrimeTask(long number, long start, long end) {
      this.number = number;
      this.start = start;
      this.end = end;
    }

    @Override
    protected Boolean compute() {
      if ((end - start) > THREASHHOLD) {
        long middle = (end + start) / 2;
        CheckPrimeTask task1 = new CheckPrimeTask(number, start, middle);
        CheckPrimeTask task2 = new CheckPrimeTask(number, middle, end);
        invokeAll(task1, task2);
        if (task1.join() == false || task2.join() == false) {
          return false;
        } else {
          return true;
        }
      }
      for (long i = start; i < end; i++) {
        if (number % i == 0)
          return false;
      }
      return true;
    }

  }
}
