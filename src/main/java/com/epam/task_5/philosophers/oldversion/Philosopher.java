package com.epam.task_5.philosophers.oldversion;

import java.util.Random;

/**
 * Philosopher class for solving dining philosophers problem.
 */
public class Philosopher implements Runnable {

    private static Random generator = new Random();
    private final Fork leftFork;
    private final Fork rightFork;
    private int id;
    private volatile boolean isFull = false;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    /**
     * Sets to indicate whether philosopher is full.
     *
     * @param full philosopher status
     */
    public void setFull(boolean full) {
        isFull = full;
    }

    @Override
    public void run() {
        try {
            while (!isFull) {
                think();
                synchronized (leftFork) {
                    grabFork(leftFork);
                    synchronized (rightFork) {
                        grabFork(rightFork);
                        eat();
                        releaseFork(rightFork);
                    }
                    releaseFork(leftFork);
                }
            }
        } catch (InterruptedException e) {
            System.err.println(String.format("Philosopher %d thread was interrupted!", id));
        }
    }

    /**
     * Think for a random time.
     *
     * @throws InterruptedException if thread was interrupted
     */
    private void think() throws InterruptedException {
        System.out.println("PHILOSOPHER " + id + " THINK");
        Thread.sleep(generator.nextInt(100));
    }

    /**
     * Eat for a random time.
     *
     * @throws InterruptedException if thread was interrupted
     */
    private void eat() throws InterruptedException {
        System.out.println("PHILOSOPHER " + id + " EAT");
        Thread.sleep(generator.nextInt(100));
    }

    /**
     * Grabs the monitor.
     *
     * @param fork monitor to grab
     * @throws InterruptedException if thread was interrupted
     */
    private void grabFork(Fork fork) throws InterruptedException {
        while (fork.isUnavailable())
            fork.wait();
        fork.setUnavailable(true);
    }

    /**
     * Releases monitor.
     *
     * @param fork monitor to release
     */
    private void releaseFork(Fork fork) {
        fork.setUnavailable(false);
        fork.notify();
    }
}
