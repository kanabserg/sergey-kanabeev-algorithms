package com.epam.task_2.structures.utils;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * @param <IN> the type of the input to the function
 * @param <OUT> the type of the result of the function
 */
public interface Function<IN, OUT> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    OUT apply(IN value);
}
