package com.epam.task_2.structures.utils;

/**
 * An iterator over a collection in two ways
 *
 * @param <T> the type of elements returned by this iterator
 */
public interface TwoWayIterator<T> {

    /**
     * Returns the next element in the list and moves the cursor
     * position forwards.  This method may be called repeatedly to
     * iterate through the list forwards, or intermixed with calls to
     * {@link #previous()} to go forth and back.
     *
     * @return the next element in the iteration
     */
    T next();

    /**
     * Returns the previous element in the list and moves the cursor
     * position backwards.  This method may be called repeatedly to
     * iterate through the list backwards, or intermixed with calls to
     * {@link #next} to go back and forth.
     *
     * @return the previous element in the list
     */
    T previous();
}
