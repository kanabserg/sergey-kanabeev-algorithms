package com.epam.task_5.philosophers.newversion;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Fork class for solving dining philosophers problem.
 *  Works with 1.5 or later JDK.
 */
public class Fork {

    private Lock lock = new ReentrantLock();

    public Fork() { }

    /**
     * Acquires the lock if it is free.
     *
     * @return whether lock was successfully acquired
     * @throws InterruptedException if thread was interrupted
     */
    boolean acquire() throws InterruptedException {
        return lock.tryLock(20, TimeUnit.MILLISECONDS);
    }

    /**
     * Releases the lock.
     */
    void release() {
        lock.unlock();
    }
}
