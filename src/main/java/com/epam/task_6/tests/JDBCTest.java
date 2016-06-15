package com.epam.task_6.tests;

import com.epam.task_6.catalog.dao.ArtistDAO;
import com.epam.task_6.catalog.dao.factory.DAOFactory;
import com.epam.task_6.catalog.exceptions.ConnectionConfigurationException;
import com.epam.task_6.catalog.exceptions.DAOException;
import com.epam.task_6.catalog.model.Artist;
import com.epam.task_6.catalog.model.Catalog;
import org.apache.log4j.Logger;


public class JDBCTest {

    /**
     * Logger instance.
     */
    private static Logger log = Logger.getLogger(JDBCTest.class);

    /**
     * Main method to run the test.
     */
    public static void run() {
        try {
            DAOFactory postgeFactory = DAOFactory.newInstance(DAOFactory.POSTGRE);
            assert postgeFactory != null;
            ArtistDAO artistDAO = postgeFactory.getArtistDAO();

            Catalog xmlCatalog = readXmlData();
            log.info(String.format("Data from xml:%s", xmlCatalog));

            log.info("Prepare to put catalog into database...");
            for (Artist artist : xmlCatalog.getArtists())
                artistDAO.insert(artist);
            log.info(String.format("Successfully inserted %d artists", xmlCatalog.getArtists().size()));

            log.info("Requesting result from the database");
            Catalog dbCatalog = new Catalog(artistDAO.getAll());
            log.info(String.format("Data from db: %s", dbCatalog));

            log.info("Deleting info");
            for (Artist artist : dbCatalog.getArtists())
                artistDAO.delete(artist.getId());
        } catch (ConnectionConfigurationException | DAOException e) {
            log.error(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Reads XML in order to get catalog records.
     *
     * @return catalog instance.
     * @throws ConnectionConfigurationException if connection wasn't properly initialized
     * @throws DAOException                     if inner exception on data acces layer was thrown
     */
    private static Catalog readXmlData() throws ConnectionConfigurationException, DAOException {
        DAOFactory xmlFactory = DAOFactory.newInstance(DAOFactory.XML);
        assert xmlFactory != null;
        ArtistDAO xmlArtistDAO = xmlFactory.getArtistDAO();
        return new Catalog(xmlArtistDAO.getAll());
    }
}
