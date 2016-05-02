package com.epam.algorithms;

/**
 * Class for QuickSort and BinarySearch algorithms implementation.
 */
public class QuickAndBinary extends AbstractSortAndSearch {

    public QuickAndBinary(int[] array) {
        super(array);
    }

    /**
     * QuickSort implementation
     */
    @Override
    public void sort() {
        doSort(0,array.length-1);
    }

    /**
     * Sorts the specified range of the array by QuickSort.
     * @param fInd the index of the first element, inclusive, to be sorted
     * @param sInd the index of the last element, inclusive, to be sorted
     */
    private void doSort(int fInd, int sInd) {
        int i = fInd;
        int j = sInd;
        int supportMember = array[fInd + (sInd - fInd) / 2];
        while (i <= j) {
            while (array[i] < supportMember) {
                i++;
            }
            while (array[j] > supportMember) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (fInd < j)
            doSort(fInd, j);
        if (i < sInd)
            doSort(i, sInd);
    }

    /**
     * First occurrence BinarySearch implementation.
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     */
    @Override
    public int search(int key) {
        int low = 0;
        int high = array.length - 1;
        int first = Integer.MIN_VALUE;

        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            int midVal = array[mid];

            if (midVal == key) {
                first = mid;
                high = mid - 1;
            } else if (midVal < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return first != Integer.MIN_VALUE ? first : -(low + 1);
    }
}
