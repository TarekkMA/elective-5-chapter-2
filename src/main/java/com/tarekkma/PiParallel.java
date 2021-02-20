package com.tarekkma;

import edu.rit.pj2.LongLoop;
import edu.rit.pj2.Task;
import edu.rit.pj2.vbl.LongVbl;

import java.util.Random;

public class PiParallel extends Task {
    public void main(String[] args) {
        long N = 1000;

        final LongVbl C = new LongVbl.Sum(0);

        parallelFor(0, N - 1).exec(new LongLoop() {
            Random prng;
            LongVbl localC;
            @Override
            public void start() throws Exception {
                prng = new Random();
                localC = threadLocal(C);
            }

            @Override
            public void run(long l) throws Exception {
                double x = prng.nextDouble();
                double y = prng.nextDouble();
                if (x * x + y * y <= 1.0) localC.item++;
            }
        });

        double pi = 4.0 * C.item / N;

        System.out.println("PI = " + pi);
    }
}
