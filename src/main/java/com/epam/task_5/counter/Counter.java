package com.epam.task_5.counter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class of thread safe counter.
 */
public class Counter implements Runnable{

    private static final int ITERATIONS = 100_000;

    public static AtomicInteger counter = new AtomicInteger();

    @Override
    public void run() {
        int iteration = 0;
        while (iteration < ITERATIONS){
            increment();
            iteration++;
        }
    }

    /**
     * Safely increment counter.
     */
    private void increment(){
        counter.getAndAdd(1);
    }

}
