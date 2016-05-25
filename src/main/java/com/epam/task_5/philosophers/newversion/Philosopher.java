package com.epam.task_5.philosophers.newversion;

import java.util.Random;

/**
 * Philosopher class for solving dining philosophers problem.
 */
public class Philosopher implements Runnable {

    private static Random generator = new Random();
    private int id;
    private Fork leftFork;
    private Fork rightFork;
    private volatile boolean isFull = false;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    /**
     * Sets to indicate whether philosopher is full.
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
                if (leftFork.acquire()) {
                    if (rightFork.acquire()) {
                        eat();
                        rightFork.release();
                    }
                    leftFork.release();
                }
            }
        } catch (InterruptedException e) {
            System.err.println(String.format("Philospher %d thread was interrupted!", id));
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
}
