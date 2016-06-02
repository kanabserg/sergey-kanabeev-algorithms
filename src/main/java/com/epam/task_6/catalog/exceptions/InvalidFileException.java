package com.epam.task_6.catalog.exceptions;

/**
 * Thrown to indicate that a file is structured
 * incorrectly.
 */
public class InvalidFileException extends Exception {

    /**
     * Constructs a <code>InvalidFileException</code> without a detail message.
     */
    public InvalidFileException() {}

    /**
     * Constructs a <code>InvalidFileException</code> with a detail message.
     *
     * @param msg the detail message.
     */
    public InvalidFileException(final String msg) {
        super(msg);
    }
}

