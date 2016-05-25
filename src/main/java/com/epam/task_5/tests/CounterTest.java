package com.epam.task_5.tests;

import com.epam.task_5.counter.Counter;

/**
 * Class to tests threadsafe counter.
 */
public class CounterTest {

    private static final int THREADS_COUNT = 100;

    /**
     * Private constructor to prevent creation of instance.
     */
    private CounterTest() {    }

    /**
     * Main method to run the test.
     */
    public static void run() {
        System.out.println("Testing threadsafe counter\n");
        int countThreads = 0;
        try {
            while (countThreads < THREADS_COUNT) {
                new Thread(new Counter()).start();
                countThreads++;
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
           System.err.println("Main thread was interrupted!");
        }
        System.out.printf("Counter result: %d",Counter.counter.get());
    }
}
