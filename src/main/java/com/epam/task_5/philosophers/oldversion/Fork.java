package com.epam.task_5.philosophers.oldversion;

/**
 *  Fork class for solving dining philosophers problem.
 *  Works before 1.5 JDK.
 */
public class Fork {

    private boolean unavailable;

    public Fork() {
    }

    /**
     * Acquires the lock if it is free.
     *
     * @throws InterruptedException if thread was interrupted
     */
    synchronized void acquire() throws InterruptedException {
        while (unavailable) {
            wait();
        }
        unavailable = true;
    }

    /**
     * Releases the lock.
     */
    synchronized void release() {
        unavailable = false;
        notify();
    }
}


