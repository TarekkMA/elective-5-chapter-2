package com.tarekkma;

import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;

public class PatternUsingParallelFor extends Task {
  public void main(String[] args) {
    parallelFor(1,4).exec(new Loop() {
      @Override
      public void run(int i) throws Exception {
        System.out.println("....");
      }
    });
  }
}
