package com.epam.task_6.tests;

import com.epam.task_6.catalog.dao.ArtistDAO;
import com.epam.task_6.catalog.dao.factory.DAOFactory;
import com.epam.task_6.catalog.exceptions.DAOException;
import com.epam.task_6.catalog.model.Album;
import com.epam.task_6.catalog.model.Artist;
import com.epam.task_6.catalog.model.Catalog;
import com.epam.task_6.catalog.model.Song;

import java.util.HashSet;
import java.util.Random;

public class XMLTest {

    private static final int ID_TO_FIND = 1;
    private static final int ID_TOD_UPDATE = 3;
    private static final int ID_TO_DELETE = 3;

    public static void run() {
        DAOFactory xmlFactory = DAOFactory.newInstance(DAOFactory.XML);
        assert xmlFactory != null;
        try {
            ArtistDAO xmlArtistDAO = xmlFactory.getArtistDAO();

            //DAO getAll
            System.out.println("All artists from XML file:");
            new Catalog(xmlArtistDAO.getAll()).print();

            //DAO find and Total song length
            Artist foundArtist = xmlArtistDAO.find(ID_TO_FIND);
            if (foundArtist != null)
                System.out.println(String.format("Artist found by id '%d' is: '%s' with total song length: %.2f", ID_TO_FIND, foundArtist, foundArtist.songsLength()));

            //DAO insert
            Artist sampleArtist = createSampleArtist();
            int insertedId = xmlArtistDAO.insert(sampleArtist);
            System.out.println(String.format("Artist '%s' with id '%d' was inserted ", sampleArtist, insertedId));

            //DAO update
            sampleArtist.setTitle("Freddie Mercury");
            if (xmlArtistDAO.update(ID_TOD_UPDATE, sampleArtist))
                System.out.println(String.format("Artist with id '%d' was updated successfully", ID_TOD_UPDATE));
            else
                System.out.println(String.format("Artist with id '%d' WAS NOT updated,  because it doesn't exist", ID_TOD_UPDATE));
            System.out.println("Changed XML file:");
            new Catalog(xmlArtistDAO.getAll()).print();

            //DAO delete
            if (xmlArtistDAO.delete(ID_TO_DELETE))
                System.out.println(String.format("Artist with id '%d' was deleted successfully", ID_TO_DELETE));
            else
                System.out.println(String.format("Artist with id '%d' WAS NOT deleted, because it doesn't exist", ID_TO_DELETE));

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a sample Artist in test purposes.
     *
     * @return simple artist
     */
    private static Artist createSampleArtist() {
        Song songOne = new Song(10, "Bohemian Rhapsody", 5.53);
        Song songTwo = new Song(11, "We are the champions", 3.0);
        HashSet<Song> songs = new HashSet<>();
        songs.add(songOne);
        songs.add(songTwo);

        Album album = new Album(5, "A Night at the Opera", "Рок", songs);
        HashSet<Album> albums = new HashSet<>();
        albums.add(album);

        return new Artist(3, "Queen", albums);
    }
}