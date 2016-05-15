package com.epam.task_3;

import com.epam.task_3.benchmarks.ListBenchmark;

public class Main {

    public static void main(String[] args){
        ListBenchmark.sequentalAdd(10000000);
        ListBenchmark.randomAdd(100000);
        ListBenchmark.randomGet(100000);
        ListBenchmark.randomRemove(100000);
        ListBenchmark.iteratorTest(10000000);
        ListBenchmark.sortTest(1000000);
    }
}
