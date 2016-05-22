package com.epam.task_4.utils;

import com.epam.task_4.cache.Cache;

/**
 * Utility class to fill cash for tests.
 */
public class CacheFiller {

    /**
     * Private constructor to prevent creation of consumers.
     */
    private CacheFiller(){}

    /**
     * Filling the cache with the sample values.
     *
     * @param map cache need to be filled
     */
    public static void fill (Cache map){
        map.put(1,"one");
        map.put(3,"three");
        map.put(2,"two");
    }
}
