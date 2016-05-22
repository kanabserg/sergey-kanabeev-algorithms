package com.epam.task_4.cache;

import java.util.Collection;

/**
 * A cache is an area of local memory that holds a copy of frequently
 * accessed data that is otherwise expensive to get or compute.
 * Interface provides access to the data using a unique key. Cache
 * doesn't permit null values.
 *
 * Key - Integer
 * Value - String
 */
public interface Cache {

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     */
    String get(Integer key);

    /**
     * Associates the specified value with the specified key in this map.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return  the previous value associated with key, or
     *         {@code null} if there was no mapping for key.
     */
    String put(Integer key, String value);

    /**
     * Get all values from cache.
     *
     * @return cache values as a Collection
     */
    Collection<String> getValues();
}
