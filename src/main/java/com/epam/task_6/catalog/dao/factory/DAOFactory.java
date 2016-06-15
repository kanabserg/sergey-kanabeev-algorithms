package com.epam.task_6.catalog.dao.factory;

import com.epam.task_6.catalog.dao.ArtistDAO;
import com.epam.task_6.catalog.exceptions.ConnectionConfigurationException;

/**
 * Interprets an Abstract Factory pattern for DAO objects.
 */
public abstract class DAOFactory {

    public final static int XML = 1;
    public final static int POSTGRE = 2;

    /**
     * Protected constructor to force use of {@link #newInstance(int)}.
     */
    protected DAOFactory(){

    }
    /**
     * Obtain a new instance of a SAXParserFactory. This static
     * method creates a new factory instance.
     *
     * @param type type of factory instance
     * @return initialized instance
     */
    public static DAOFactory newInstance(int type) throws ConnectionConfigurationException {
        switch (type) {
            case XML:
                return new XmlDAOFactory();
            case POSTGRE:
                return new PostgreDAOFactory();
            default:
                return null;
        }
    }

    /**
     * Returns concrete instance of Artist DAO
     *
     * @return concrete Artist DAO instance
     */
    public abstract ArtistDAO getArtistDAO();
}
