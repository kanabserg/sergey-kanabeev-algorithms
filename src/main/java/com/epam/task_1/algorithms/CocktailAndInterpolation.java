package com.epam.task_1.algorithms;

/**
 * Class for CocktailSort and InterpolationSort algorithms implementation.
 */
public class CocktailAndInterpolation extends AbstractSortAndSearch {

    public CocktailAndInterpolation(int[] array) {
        this.array = array;
    }

    /**
     * CocktailSort implementation.
     */
    @Override
    public void sort() {
        int left = 0;
        int right = array.length - 1;

        do {
            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1]=temp;
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (array[i] < array[i - 1]) {
                    int temp = array[i];
                    array[i] = array[i-1];
                    array[i-1]=temp;
                }
            }
            left++;
        } while (left <= right);
    }

    /**
     * First occurrence InterpolationSearch.
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     */
    @Override
    public int search(int key) {
        int low = 0;
        int high = array.length - 1;
        int mid = 0, first = Integer.MIN_VALUE;

        while (high != low && array[low] <= key && array[high] >= key) {

            mid = low + ((key - array[low]) * (high - low)) / (array[high] - array[low]);

            if (array[mid] == key) {
                first = mid;
                high = mid - 1 < 0 ? mid : mid - 1;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return first != Integer.MIN_VALUE ? first : -(low + 1);
    }
}
