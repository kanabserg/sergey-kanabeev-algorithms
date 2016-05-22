package com.epam.task_4.cache.exceptions;

/**
 * Thrown to indicate that a field marked with
 * {@linkplain com.epam.task_4.cache.annotation.InjectCache}
 * annotation refers to non existing cache.
 */
public class NoSuchCacheException extends Exception {

    /**
     * Constructs a <code>NoSuchCacheException</code> without a detail message.
     */
    public NoSuchCacheException() {}

    /**
     * Constructs a <code>NoSuchCacheException</code> with a detail message.
     *
     * @param msg the detail message.
     */
    public NoSuchCacheException(final String msg) {
        super(msg);
    }
}
