package com.epam.task_6.catalog.dao;

import com.epam.task_6.catalog.exceptions.DAOException;
import com.epam.task_6.catalog.model.Artist;

import java.util.Set;

/**
 * Data Access Object for {@linkplain Artist} object, which encapsulates
 * connection to the data source.
 */
public interface ArtistDAO {
    /**
     * Inserts Artist instance to the data source storage.
     * Returns id of inserted record.
     *
     * @param artist instance to be inserted.
     * @return id of inserted record.
     * @throws DAOException in case of inner exception.
     */
    int insert(Artist artist) throws DAOException;

    /**
     * Deletes Artist instance form the data source. If it
     * exists in data source and can be deleted successfully,
     * if there is no outer constraints.
     *
     * @param id of record to be deleted.
     * @return true if record was deleted successfully.
     * @throws DAOException  in case of inner exception.
     */
    boolean delete(int id) throws DAOException;

    /**
     * Finds Artist record by it's id.
     *
     * @param id of record to be searched
     * @return found Artist instance or null, if it wasn't found
     * @throws DAOException in case of inner exception.
     */
    Artist find(int id) throws DAOException;

    /**
     * Updates record by id, if it was find in data source.
     *
     * @param id of record to be updated.
     * @param artist new data to be stored.
     * @return true if record was updated successfully.
     * @throws DAOException in case of inner exception.
     */
    boolean update(int id, Artist artist) throws DAOException;

    /**
     * Gets all Artist records from data source.
     *
     * @return Set of Artists found in the data source.
     * @throws DAOException in case of inner exception.
     */
    Set<Artist> getAll() throws DAOException;

    /**
     * Inserts Artist  =instances to the data source storage.
     *
     * @param artists  Set of Artist instances to be inserted.
     * @return true if all records inserted successfully.
     * @throws DAOException in case of inner exception.
     */
    boolean addAll(Set<Artist> artists) throws DAOException;
}
