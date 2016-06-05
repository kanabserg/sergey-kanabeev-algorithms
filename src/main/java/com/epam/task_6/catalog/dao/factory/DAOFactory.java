package com.epam.task_6.catalog.dao.factory;

import com.epam.task_6.catalog.dao.ArtistDAO;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Interprets an Abstract Factory pattern for DAO objects.
 */
public abstract class DAOFactory {

    public final static int XML = 1;
    public final static int ORACLE = 2;

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
    public static DAOFactory newInstance(int type) {
        switch (type) {
            case XML:
                return new XmlDAOFactory();
            case ORACLE:
                throw new NotImplementedException();
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
