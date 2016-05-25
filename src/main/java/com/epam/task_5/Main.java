package com.epam.task_5;

import com.epam.task_5.tests.CounterTest;
import com.epam.task_5.tests.PhilosopherNewTest;
import com.epam.task_5.tests.PhilosopherOldTest;

public class Main {

    public static void main(String[] args) {
        CounterTest.run();
        System.out.println("\n--------------------------------------------------------\n");
        PhilosopherNewTest.run();
        System.out.println("\n--------------------------------------------------------\n");
        PhilosopherOldTest.run();
    }

}
