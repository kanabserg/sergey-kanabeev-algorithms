package com.epam.task_6.catalog.dao.factory.configuration;

import com.epam.task_6.catalog.exceptions.ConnectionConfigurationException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * Contains information about connection to the database.
 */
public class ConnectionConfiguration {

    /**
     * Logger instance.
     */
    private static Logger log = Logger.getLogger(ConnectionConfiguration.class);
    private String url;
    private String username;
    private String password;

    public ConnectionConfiguration(String filename) {
        readConnectionInfo(filename);
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Reads configuration from property file and loads
     *
     * @param propsFileName property file name
     */
    private void readConnectionInfo(String propsFileName) {
        Properties props = new Properties();
        try (Reader propetiesFileReader = new BufferedReader(new FileReader(propsFileName))) {
            props.load(propetiesFileReader);
            String driver = props.getProperty("jdbc.driver");
            if (driver == null) throw new ConnectionConfigurationException("Driver wasn't find in the property file.");
            Class.forName(driver);
            url = props.getProperty("jdbc.url");
            username = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");
        } catch (IOException | ClassNotFoundException | ConnectionConfigurationException e) {
            log.warn(e.getClass() + " " + e.getMessage() + " - Connection Info wasn't load!");
        }
    }
}
