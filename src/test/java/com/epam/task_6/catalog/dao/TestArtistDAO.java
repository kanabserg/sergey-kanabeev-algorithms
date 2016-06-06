package com.epam.task_6.catalog.dao;


import com.epam.task_6.catalog.dao.factory.DAOFactory;
import com.epam.task_6.catalog.exceptions.DAOException;
import com.epam.task_6.catalog.model.Album;
import com.epam.task_6.catalog.model.Artist;
import com.epam.task_6.catalog.model.Song;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Test case for ArtistDAO
 */
public class TestArtistDAO {

    private ArtistDAO artistDAO = DAOFactory.newInstance(DAOFactory.XML).getArtistDAO();

    @Test
    public void testGetAll() throws DAOException {
        int expectedCount = 2;
        Set<Artist> artists = artistDAO.getAll();
        assertEquals(String.format("Number of artists should be %d.", expectedCount), expectedCount, artists.size());
    }

    @Test
    public void testFind() throws DAOException {
        int idToFind = 1;
        Artist artist = artistDAO.find(idToFind);

        assertNotNull(String.format("Artist with id %d expected to be presented.", idToFind), artist);
        assertEquals(String.format("Found artist should be with id %d.", idToFind), idToFind, artist.getId());
    }

    @Test
    public void testInsert() throws DAOException {
        int idToInsert = 0;
        int insertedId = insertSampleArtist(idToInsert);

        //test saved artist
        assertEquals(String.format("Inserted artist should be with id %d.", idToInsert), idToInsert, insertedId);

        //test retrieved artist just saved
        Artist artist = artistDAO.find(idToInsert);
        assertEquals(String.format("Inserted artist should be with id %d.", idToInsert), idToInsert, artist.getId());

        //cleanup
        artistDAO.delete(idToInsert);
    }

    @Test
    public void testUpdate() throws DAOException {
        int idToUpdate = 0;
        insertSampleArtist(idToUpdate);

        Artist updatedInfoArtist = createSampleArtist(idToUpdate);
        if (!artistDAO.update(idToUpdate, updatedInfoArtist))
            fail(String.format("Artist with id %d was not find to be updated.", idToUpdate));

        Artist foundByIdArtist = artistDAO.find(idToUpdate);
        assertTrue("Updated artist should be equal artist wit updated info.", updatedInfoArtist.equals(foundByIdArtist));

        //cleanup
        artistDAO.delete(idToUpdate);
    }

    @Test
    public void testDelete() throws DAOException {
        int idToDelete = 0;
        insertSampleArtist(idToDelete);

        if (!artistDAO.delete(idToDelete))
            fail(String.format("Artist with id %d was not find to be deleted.", idToDelete));

        Artist foundByIdArtist = artistDAO.find(idToDelete);
        assertNull("Deleted artist should should not be found after deletion.", foundByIdArtist);
    }

    @Test
    public void testAddAll() throws DAOException {
        // artists to be inserted into new file
        // (no need to create a new collection if we have one)
        Set<Artist> artistsToInsert = artistDAO.getAll();

        artistDAO.addAll(artistsToInsert);

        int expectedCount = 2;
        Set<Artist> artists = artistDAO.getAll();
        assertEquals(String.format("Number of artists should be %d.", expectedCount), expectedCount, artists.size());
    }

    /**
     * Creates a simple artist for test purposes.
     *
     * @param artistId artist id.
     * @return artist instance.
     */
    private Artist createSampleArtist(int artistId) {
        Song songOne = new Song(0, "Bohemian Rhapsody", 5.53);
        Song songTwo = new Song(-1, "We are the champions", 3.0);
        HashSet<Song> songs = new HashSet<>();
        songs.add(songOne);
        songs.add(songTwo);

        Album album = new Album(0, "A Night at the Opera", "Рок", songs);
        HashSet<Album> albums = new HashSet<>();
        albums.add(album);

        return new Artist(artistId, "Queen", albums);
    }

    /**
     * Insert simple artist with specified ID for test purposes.
     *
     * @param artistId artist id.
     * @return id of inserted record.
     * @throws DAOException in case of inner exception.
     */
    private int insertSampleArtist(int artistId) throws DAOException {
        return artistDAO.insert(createSampleArtist(artistId));
    }
}
