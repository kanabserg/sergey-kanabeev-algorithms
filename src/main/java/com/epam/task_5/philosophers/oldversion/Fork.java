package com.epam.task_5.philosophers.oldversion;

/**
 *  Fork class for solving dining philosophers problem.
 *  Works before 1.5 JDK.
 */
public class Fork {

    private boolean unavailable = false;

    public Fork() {
    }

    public boolean isUnavailable() {
        return unavailable;
    }

    public void setUnavailable(boolean unavailable) {
        this.unavailable = unavailable;
    }
}


