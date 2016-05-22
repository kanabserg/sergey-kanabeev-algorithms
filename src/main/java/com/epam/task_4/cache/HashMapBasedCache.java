package com.epam.task_4.cache;

import java.util.Collection;
import java.util.LinkedHashMap;

import com.epam.task_4.cache.annotation.Cache;

/**
 * Class represented cache store as a LinkedHashMap (Insertion order
 * is saved) and implemented as a Singleton.
 *
 *  @see     LinkedHashMap
 */
@Cache( name ="HashMapCache")
public class HashMapBasedCache implements com.epam.task_4.cache.Cache {

    private static HashMapBasedCache instance = new HashMapBasedCache();

    private LinkedHashMap<Integer, String> cacheMap;

    private HashMapBasedCache() {
        this.cacheMap = new LinkedHashMap<>();
    }

    public static HashMapBasedCache getInstance(){
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
