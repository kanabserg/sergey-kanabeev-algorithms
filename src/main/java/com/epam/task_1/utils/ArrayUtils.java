package com.epam.task_1.utils;

import java.util.Random;

/**
 * Contains arrays utility methods
 */
public class ArrayUtils {

    /**
     * To prevent creation of instance
     */
    private ArrayUtils() {}

    /**
     * Initialize randomly generated int array
     * @param arrayLength length of initializing array
     * @param minValue  maximum expected value
     * @param maxValue minimum expected value
     * @return generated array
     */
    public static int[] initRandomArray(int arrayLength, int minValue, int maxValue) {
        if (arrayLength <= 0) {
            throw new IllegalArgumentException("Array length should be greater than 0!");
        }
        if (maxValue < minValue) {
            throw new IllegalArgumentException("The maximum expected value should be greater than minimum expected value");
        }
        return new Random().ints(arrayLength, minValue, maxValue).toArray();
    }
}
