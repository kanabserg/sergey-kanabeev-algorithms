package com.epam.task_6.catalog.exceptions;

/**
 * Thrown to indicate exception was thrown on Data Access Layer
 * of application and needed to be rethrown with this wrapper.
 */
public class DAOException extends Exception {

    /**
     * Constructs a <code>DAOException</code> without a detail message.
     */
    public DAOException() {
    }

    /**
     * Constructs a <code>DAOException</code> with a detail message.
     *
     * @param msg the detail message.
     */
    public DAOException(final String msg) {
        super(msg);
    }
}
