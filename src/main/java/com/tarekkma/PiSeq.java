package com.tarekkma;

import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;

import java.util.Random;

public class PiSeq extends Task {
    public void main(String[] args) {
        long N = 1000;
        Random prng = new Random();

        long C = 0;

        for (long i = 0; i < N; i++) {
            double x = prng.nextDouble();
            double y = prng.nextDouble();
            if (x * x + y * y <= 1.0) C++;
        }

        double pi = 4.0 * C / N;

        System.out.println("PI = " + pi);
    }

    protected static int coresRequired() {
        return 1;
    }
}
