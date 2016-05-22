package com.epam.task_4.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that field is using as a Cache storage.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectCache {
    /**
     * Used to identify which cache needs to be injected
     *
     * @return cache name to be injected
     */
    String cacheName();
}

