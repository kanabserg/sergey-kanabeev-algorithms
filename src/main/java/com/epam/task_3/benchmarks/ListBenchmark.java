package com.epam.task_3.benchmarks;

import java.util.*;

/**
 * Class to test performance of ArrayList and LinkedList
 * on add, get, remove, sort and iterator methods.
 */
public class ListBenchmark {

    /**
     * Private constructor to prevent creation of instance.
     */
    private ListBenchmark(){}

    /**
     * Measures time of adding elements to the end of
     * list.
     *
     * @param quantity size of testing list
     */
    public static void sequentalAdd(int quantity) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        System.out.printf("Sequential add %d elements\n",quantity);

        // ArrayList
        long startTime = System.currentTimeMillis();
        fillList(arrayList,quantity);
        long endTime = System.currentTimeMillis();
        System.out.printf("ArrayList:  %d\n", endTime - startTime);

        // LinkedList
        startTime = System.currentTimeMillis();
        fillList(linkedList,quantity);
        endTime = System.currentTimeMillis();
        System.out.printf("LinkedList: %d\n", endTime - startTime);
    }

    /**
     * Measures time of adding elements to the random
     * place of the list.
     *
     * @param quantity size of testing list
     */
    public static void randomAdd(int quantity) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        Random randIndex = new Random();
        System.out.printf("Random add %d elements\n",quantity);

        // ArrayList
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < quantity; i++)
            arrayList.add(randIndex.nextInt(arrayList.size()+1),i);
        long endTime = System.currentTimeMillis();
        System.out.printf("ArrayList:  %d\n", endTime - startTime);

        // LinkedList
        startTime = System.currentTimeMillis();
        for (int i = 0; i < quantity; i++)
            linkedList.add(randIndex.nextInt(linkedList.size()+1),i);
        endTime = System.currentTimeMillis();
        System.out.printf("LinkedList: %d\n", endTime - startTime);
    }

    /**
     * Measures time of iteration through the list.
     *
     * @param quantity size of testing list
     */
    public static void iteratorTest (int quantity) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        fillList(arrayList,quantity);
        fillList(linkedList,quantity);
        System.out.printf("Iterator test on %d elements\n",quantity);

        // ArrayList
        long startTime = System.currentTimeMillis();
        for (Integer value: arrayList) {}
        long endTime = System.currentTimeMillis();
        System.out.printf("ArrayList:  %d\n", endTime - startTime);

        // LinkedList
        startTime = System.currentTimeMillis();
        for (Integer value: linkedList) {}
        endTime = System.currentTimeMillis();
        System.out.printf("LinkedList: %d\n", endTime - startTime);
    }

    /**
     * Measures time of sorting the list.
     *
     * @param quantity size of testing list
     */
    public static void sortTest (int quantity) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        fillList(arrayList,quantity);
        fillList(linkedList,quantity);

        System.out.printf("Sort test on %d elements\n",quantity);
        // ArrayList
        long startTime = System.currentTimeMillis();
        Collections.sort(arrayList);
        long endTime = System.currentTimeMillis();
        System.out.printf("ArrayList:  %d\n", endTime - startTime);

        // LinkedList
        startTime = System.currentTimeMillis();
        Collections.sort(linkedList);
        endTime = System.currentTimeMillis();
        System.out.printf("LinkedList: %d\n", endTime - startTime);
    }

    /**
     * Measures time of of removing elements from the random
     * place of the list.
     *
     * @param quantity size of testing list
     */
    public static void randomRemove (int quantity) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        fillList(arrayList,quantity);
        fillList(linkedList,quantity);
        Random randIndex = new Random();

        System.out.printf("Random remove %d elements\n",quantity);

        // ArrayList
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < quantity; i++)
            arrayList.remove(randIndex.nextInt(arrayList.size()));
        long endTime = System.currentTimeMillis();
        System.out.printf("ArrayList:  %d\n", endTime - startTime);

        // LinkedList
        startTime = System.currentTimeMillis();
        for (int i = 0; i < quantity; i++)
            linkedList.remove(randIndex.nextInt(linkedList.size()));
        endTime = System.currentTimeMillis();
        System.out.printf("LinkedList: %d\n", endTime - startTime);
    }

    /**
     * Measures time of getting elements from the random
     * place of the list.
     *
     * @param quantity size of testing list
     */
    public static void randomGet(int quantity){
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        fillList(arrayList,quantity);
        fillList(linkedList,quantity);
        Random randIndex = new Random();
        System.out.printf("Random get %d elements\n",quantity);

        // ArrayList
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < quantity; i++)
            arrayList.get(randIndex.nextInt(arrayList.size()));
        long endTime = System.currentTimeMillis();
        System.out.printf("ArrayList:  %d\n", endTime - startTime);

        // LinkedList
        startTime = System.currentTimeMillis();
        for (int i = 0; i < quantity; i++)
            linkedList.get(randIndex.nextInt(linkedList.size()));
        endTime = System.currentTimeMillis();
        System.out.printf("LinkedList: %d\n", endTime - startTime);
    }

    /**
     * Fill the list for testing purposes.
     *
     * @param toFill list to fill
     * @param quantity size of testing list
     */
    private static void fillList(List<Integer> toFill, int quantity){
        for (int i = 0; i < quantity; i++)
            toFill.add(i);
    }
}
