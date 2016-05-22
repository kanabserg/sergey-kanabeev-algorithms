package com.epam.task_4.cache;

import java.util.Collection;
import java.util.TreeMap;

import com.epam.task_4.cache.annotation.Cache;

/**
 * Class represented cache store as a TreeMap (The map is sorted according
 * to the {@linkplain Comparable natural ordering} of its keys) and
 * implemented as a Singleton.
 *
 *  @see     TreeMap
 */
@Cache( name ="TreeMapCache")
public class TreeMapBasedCache implements com.epam.task_4.cache.Cache{

    private static TreeMapBasedCache instance  = new TreeMapBasedCache();

    private TreeMap<Integer, String> cacheMap;

    private TreeMapBasedCache() {
        this.cacheMap = new TreeMap<>();
    }

    public static TreeMapBasedCache getInstance(){
        return instance;
    }

    @Override
    public String get(Integer key) {
        return cacheMap.get(key);
    }

    @Override
    public String put(Integer key, String value) {
        return cacheMap.put(key, value);
    }

    @Override
    public Collection<String> getValues() {
        return cacheMap.values();
    }
}
