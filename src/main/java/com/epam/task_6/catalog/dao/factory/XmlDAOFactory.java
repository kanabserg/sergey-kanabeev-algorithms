package com.epam.task_6.catalog.dao.factory;

import com.epam.task_6.catalog.dao.ArtistDAO;
import com.epam.task_6.catalog.dao.xml.XmlArtistDAO;

/**
 * Concrete realization of {@linkplain DAOFactory}, which
 * stands for XML DAO.
 */
public class XmlDAOFactory  extends  DAOFactory{

    /**
     * File Path to data source
     */
    private static final String FILE_PATH = "src\\main\\resources\\musicCatalog\\catalog.xml";

    public static String getFilePath() {
        return FILE_PATH;
    }

    @Override
    public ArtistDAO getArtistDAO() {
        return new XmlArtistDAO();
    }
}
