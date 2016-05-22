package com.epam.task_4.tests;

import com.epam.task_4.cache.HashMapBasedCache;
import com.epam.task_4.cache.TreeMapBasedCache;
import com.epam.task_4.consumers.CacheConsumerChild;
import com.epam.task_4.utils.CacheFiller;
import com.epam.task_4.utils.Injector;

/**
 * Class to tests Cache and Injector functionality.
 */
public class CacheTest {

    /**
     * Private constructor to prevent creation of consumers.
     */
    private CacheTest() { }

    /**
     * Main method to run the test.
     */
    public static void run() {
        System.out.println("This test will show injection of different caches into an consumer object which has a an inherited and private fields.\n" +
                "Caches differ in type of storage. After injection linked hash map based cache will save an insertion order, rather than tree map based\n" +
                "where values will be sorted.\n");
        System.out.println("Running cache tests...");
        System.out.println("Filling cashes...");
        CacheFiller.fill(HashMapBasedCache.getInstance());
        CacheFiller.fill(TreeMapBasedCache.getInstance());
        System.out.println("Caches are filled!");
        CacheConsumerChild consumer = new CacheConsumerChild();
        Injector.inject(consumer);
        System.out.println(String.format("Hash map based cache: %s",consumer.getHashMapValues().toString()));
        System.out.println(String.format("Tree map based cache: %s",consumer.getTreeMapValues().toString()));
    }
}
