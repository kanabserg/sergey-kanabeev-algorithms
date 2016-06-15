package com.epam.task_6.catalog.exceptions;

/**
 * Thrown to indicate that database connection information or database driver
 * wasn't initialized.
 */
public class ConnectionConfigurationException extends Exception {

    /**
     * Constructs a <code>ConnectionConfigurationException</code> without a detail message.
     */
    public ConnectionConfigurationException() {
    }

    /**
     * Constructs a <code>ConnectionConfigurationException</code> with a detail message.
     *
     * @param msg the detail message.
     */
    public ConnectionConfigurationException(final String msg) {
        super(msg);
    }
}
