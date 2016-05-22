package com.epam.task_4.consumers;

import com.epam.task_4.cache.annotation.InjectCache;

import java.util.Collection;

/**
 * Sample cache consumer class to tests {@linkplain com.epam.task_4.cache.Cache
 * Cache} functionality. Realized as a parent class with private field
 * with cache needed.
 */
public class CacheConsumerParent {

    @InjectCache(cacheName = "HashMapCache")
    private  Collection<String>  hashMapValues;

    public Collection<String> getHashMapValues() {
        return hashMapValues;
    }

    public void setHashMapValues( Collection<String> hashMapValues) {
        this.hashMapValues = hashMapValues;
    }
}
