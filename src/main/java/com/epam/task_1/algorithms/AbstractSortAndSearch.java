package com.epam.task_1.algorithms;

import java.util.Arrays;

/**
 * Class to be inherited from to implement own search
 * and sort algorithms.
 */
public abstract class AbstractSortAndSearch {

    protected int[] array;

    public int[] getArray() {
        return array;
    }

    @Override
    public String toString(){
        return Arrays.toString(array);
    }

    /**
     * Sorts an array in ascending order.
     */
    public abstract void sort();

    /**
     * Search algorithm for first occurrence.
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     */
    public abstract int search(int key);
}
