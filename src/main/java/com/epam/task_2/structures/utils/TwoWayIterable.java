package com.epam.task_2.structures.utils;
/**
 * Implementing this interface allows an object to get two way iterator
 * to iterate trough in two ways.
 *
 * @param <T> the type of elements
 *
 * @see TwoWayIterator
 */

public interface TwoWayIterable<T>{

    /**
     * Returns an two way iterator over elements of type {@code T}.
     *
     * @return an TwoWayIterator.
     */
    TwoWayIterator<T> twoWayIterator();
}
