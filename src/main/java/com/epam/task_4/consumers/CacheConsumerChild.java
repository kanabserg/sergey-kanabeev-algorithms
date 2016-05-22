package com.epam.task_4.consumers;

import com.epam.task_4.cache.annotation.InjectCache;

import java.util.Collection;

/**
 * Sample cache consumer class to tests {@linkplain com.epam.task_4.cache.Cache
 * Cache} functionality. Realized as a inherited class with private field
 * with cache needed.
 */
public class CacheConsumerChild extends CacheConsumerParent {

    @InjectCache(cacheName = "TreeMapCache")
    private Collection<String> treeMapValues;

    public  Collection<String>  getTreeMapValues() {
        return treeMapValues;
    }

    public void setTreeMapValues( Collection<String>  treeMapValues) {
        this.treeMapValues = treeMapValues;
    }

}
