package com.epam.task_6.catalog.dao.factory;

import com.epam.task_6.catalog.dao.ArtistDAO;
import com.epam.task_6.catalog.dao.factory.configuration.ConnectionConfiguration;
import com.epam.task_6.catalog.dao.postgredb.PostgreArtistDAO;
import com.epam.task_6.catalog.exceptions.ConnectionConfigurationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreDAOFactory extends DAOFactory {

    private static final String PROPERTY_FILE = "src\\main\\resources\\musicCatalog\\data\\db\\connectionProperty.prop";
    private static ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(PROPERTY_FILE);

    public PostgreDAOFactory() throws ConnectionConfigurationException {
        if (connectionConfiguration == null)
            throw new ConnectionConfigurationException("Connection info wasn't properly initialized!");
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(connectionConfiguration.getUrl(), connectionConfiguration.getUsername(), connectionConfiguration.getPassword());
    }

    @Override
    public ArtistDAO getArtistDAO() {
        return new PostgreArtistDAO();
    }
}
