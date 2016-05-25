package com.epam.task_5.tests;

import com.epam.task_5.philosophers.newversion.Fork;
import com.epam.task_5.philosophers.newversion.Philosopher;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Class to test dining philosophers problem.
 * Implementation with JDK 1.8.
 */
public class PhilosopherNewTest {

    private static final int PHILOSOPHERS_FORKS_COUNT = 5;
    private static final int SIMULATION_MILLIS = 1_000;

    /**
     * Private constructor to prevent creation of instance.
     */
    private PhilosopherNewTest(){}

    /**
     * Main method to run the test.
     */
    public static void run() {

        System.out.println("Testing dining philosophers problem with JDK 1.8\n");

        List<Fork> forks = Stream.generate(Fork::new)
                .limit(PHILOSOPHERS_FORKS_COUNT)
                .collect(Collectors.toList());

        List<Philosopher> philosophers = IntStream.range(0, PHILOSOPHERS_FORKS_COUNT)
                .mapToObj(i -> new Philosopher(i, forks.get(i), forks.get((i + 1) % PHILOSOPHERS_FORKS_COUNT)))
                .collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(PHILOSOPHERS_FORKS_COUNT);
        philosophers.forEach(executorService::submit);

        try {
            Thread.sleep(SIMULATION_MILLIS);
            philosophers.forEach(i -> i.setFull(true));
        } catch (InterruptedException e) {
            System.err.println("Thread was  interrupted!");
        } finally {
            executorService.shutdown();
        }
    }
}
