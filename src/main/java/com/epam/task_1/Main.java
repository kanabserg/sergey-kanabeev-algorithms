package com.epam.task_1;

import com.epam.task_1.algorithms.CocktailAndInterpolation;
import com.epam.task_1.algorithms.QuickAndBinary;
import com.epam.task_1.utils.ArrayUtils;

public class Main {

    private static final int ARRAY_LENGTH = 10;
    private static final int MAX_VALUE = 10;
    private static final int MIN_VALUE = 2;
    private static final int FIND_VALUE = 6;

    public static void main(String[] args) {

        System.out.println("QuickSort and BinarySearch\n");
        QuickAndBinary quickAndBinary = new QuickAndBinary(ArrayUtils.initRandomArray(ARRAY_LENGTH,MIN_VALUE,MAX_VALUE));

        System.out.println(new StringBuilder().append("Generated Array:\n").append(quickAndBinary));
        if (quickAndBinary.getArray().length > 1) // We don't have to sort if there are less than 2 elements
            quickAndBinary.sort();
        System.out.println(new StringBuilder().append("Sorted Array:\n").append(quickAndBinary));

        System.out.println(new StringBuilder().append("Binary search. Looking for value : ").append(FIND_VALUE));

        int index = quickAndBinary.search (FIND_VALUE);
        if (index < 0) {
            System.out.println("Can't find this element!");
        } else {
            System.out.println(new StringBuilder().append("Element index: ").append(index));
        }

        System.out.println("\nCocktailSort and InterpolationSearch\n");
        CocktailAndInterpolation cocktailAndInterpolation = new CocktailAndInterpolation(ArrayUtils.initRandomArray(ARRAY_LENGTH,MIN_VALUE,MAX_VALUE));

        System.out.println(new StringBuilder().append("Generated Array:\n").append(cocktailAndInterpolation));
        if (cocktailAndInterpolation.getArray().length > 1) // We don't have to sort if there are less than 2 elements
            cocktailAndInterpolation.sort();
        System.out.println(new StringBuilder().append("Sorted Array:\n").append(cocktailAndInterpolation));

        System.out.println(new StringBuilder().append("InterpolationSearch. Looking for value : ").append(FIND_VALUE));

        index = cocktailAndInterpolation.search (FIND_VALUE);
        if (index < 0) {
            System.out.println("Can't find this element!");
        } else {
            System.out.println(new StringBuilder().append("Element index: ").append(index));
        }
    }
}
