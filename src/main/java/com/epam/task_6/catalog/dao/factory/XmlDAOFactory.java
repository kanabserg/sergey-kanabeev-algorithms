package com.epam.task_6.catalog.dao.factory;

import com.epam.task_6.catalog.dao.ArtistDAO;
import com.epam.task_6.catalog.dao.xml.XmlArtistDAO;

/**
 * Concrete realization of {@linkplain DAOFactory}, which
 * stands for XML DAO.
 */
public class XmlDAOFactory  extends  DAOFactory{
    @Override
    public ArtistDAO getArtistDAO() {
        return new XmlArtistDAO();
    }
}
