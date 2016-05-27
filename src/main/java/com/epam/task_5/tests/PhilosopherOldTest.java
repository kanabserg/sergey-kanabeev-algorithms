package com.epam.task_5.tests;

import com.epam.task_5.philosophers.oldversion.Fork;
import com.epam.task_5.philosophers.oldversion.Philosopher;

/**
 * Class to test dining philosophers problem.
 * Implementation for JDK less than 1.5 version.
 */
public class PhilosopherOldTest {

    private static final int PHILOSOPHERS_FORKS_COUNT = 5;
    private static final int SIMULATION_MILLIS = 5_000;

    /**
     * Private constructor to prevent creation of instance.
     */
    private PhilosopherOldTest() {
    }

    /**
     * Main method to run the test.
     */
    public static void run() {
        System.out.println("Testing dining philosophers problem for JDK less than 1.5 version\n");

        Fork[] forks = new Fork[PHILOSOPHERS_FORKS_COUNT];
        for (int i = 0; i < PHILOSOPHERS_FORKS_COUNT; i++)
            forks[i] = new Fork();

        Philosopher[] philosophers = new Philosopher[PHILOSOPHERS_FORKS_COUNT];
        for (int i = 0; i < PHILOSOPHERS_FORKS_COUNT - 1; i++)
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % PHILOSOPHERS_FORKS_COUNT]);
        philosophers[PHILOSOPHERS_FORKS_COUNT - 1] = new Philosopher(PHILOSOPHERS_FORKS_COUNT - 1, forks[0], forks[PHILOSOPHERS_FORKS_COUNT - 1] );

        for (int i = 0; i < PHILOSOPHERS_FORKS_COUNT; i++) {
            new Thread(philosophers[i]).start();
        }

        try {
            Thread.sleep(SIMULATION_MILLIS);
            for (int i = 0; i < PHILOSOPHERS_FORKS_COUNT; i++)
                philosophers[i].setFull(true);
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted!");
        }

    }
}
