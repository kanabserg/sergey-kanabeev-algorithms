package com.epam.task_6.catalog.dao.postgredb;

import com.epam.task_6.catalog.dao.ArtistDAO;
import com.epam.task_6.catalog.dao.factory.PostgreDAOFactory;
import com.epam.task_6.catalog.exceptions.DAOException;
import com.epam.task_6.catalog.model.Album;
import com.epam.task_6.catalog.model.Artist;
import com.epam.task_6.catalog.model.Song;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PostgreArtistDAO implements ArtistDAO {

    /**
     * Logger instance.
     */
    private static Logger log = Logger.getLogger(PostgreArtistDAO.class);

    @Override
    public int insert(Artist artist) throws DAOException {
        String query = "INSERT INTO artist (title) VALUES (?)";
        Connection connection = null;
        try {
            connection = PostgreDAOFactory.createConnection();
            connection.setAutoCommit(false);
            PreparedStatement prepStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepStatement.setString(1, artist.getTitle());
            if (prepStatement.executeUpdate() == 0) throw new SQLException("Creating Artist Failed, no rows affected.");
            ResultSet generatedKeys = prepStatement.getGeneratedKeys();
            if (generatedKeys.next())
                artist.setId(generatedKeys.getInt(1));

            for (Album album : artist.getAlbums())
                insertAlbum(artist.getId(), album, connection);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.error(ex.getClass() + " " + ex.getMessage());
                }
            }
            log.error(e.getClass() + " " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return artist.getId();
    }

    @Override
    public boolean delete(int id) throws DAOException {
        int countDeleted = 0;
        String query = "DELETE FROM artist WHERE artist_id = ?";
        try (Connection connection = PostgreDAOFactory.createConnection()) {
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setInt(1, id);
            countDeleted = prepStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getClass() + " " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return countDeleted > 0;
    }

    @Override
    public Artist find(int id) throws DAOException {
        Artist artist = null;
        String query = "SELECT * FROM artist WHERE artist_id = ?";
        try (Connection connection = PostgreDAOFactory.createConnection()) {
            PreparedStatement prepStatement = connection.prepareStatement(query);
            ResultSet result = prepStatement.executeQuery();
            while (result.next()) {
                int artistId = result.getInt("artist_id");
                artist = new Artist(artistId, result.getString("title"), getArtistAlbum(artistId, connection));
            }
        } catch (SQLException e) {
            log.error(e.getClass() + " " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return artist;
    }

    @Override
    public boolean update(int id, Artist artist) throws DAOException {
        throw new NotImplementedException();
    }

    @Override
    public Set<Artist> getAll() throws DAOException {
        Set<Artist> artists = new HashSet<>();
        String query = "SELECT * FROM artist";
        try (Connection connection = PostgreDAOFactory.createConnection()) {
            PreparedStatement prepStatement = connection.prepareStatement(query);
            ResultSet result = prepStatement.executeQuery();
            while (result.next()) {
                int artistId = result.getInt("artist_id");
                artists.add(new Artist(artistId, result.getString("title"), getArtistAlbum(artistId, connection)));
            }
        } catch (SQLException e) {
            log.error(e.getClass() + " " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return artists;
    }

    @Override
    public boolean addAll(Set<Artist> artists) throws DAOException {
        throw new NotImplementedException();
    }

    /**
     * Finds all albums by provided artist id.
     *
     * @param artistId   Artist which found albums belongs to.
     * @param connection Open connection instance.
     * @return Set of found albums
     * @throws SQLException if a database access error occurs or this
     *                      method is called on a closed result set.
     */
    private Set<Album> getArtistAlbum(int artistId, Connection connection) throws SQLException {
        Set<Album> albums = new HashSet<>();
        String query = "SELECT * FROM album WHERE artist_id = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);
        prepStatement.setInt(1, artistId);
        ResultSet result = prepStatement.executeQuery();
        while (result.next()) {
            int albumId = result.getInt("album_id");
            albums.add(new Album(albumId, result.getString("title"), result.getString("genre"), getAlbumSongs(albumId, connection)));
        }
        return albums;
    }

    /**
     * Finds all songs by provided album id.
     *
     * @param albumId    Album which found song belongs to.
     * @param connection Open connection instance.
     * @return Set of found songs
     * @throws SQLException if a database access error occurs or this
     *                      method is called on a closed result set.
     */
    private Set<Song> getAlbumSongs(int albumId, Connection connection) throws SQLException {
        Set<Song> songs = new HashSet<>();
        String query = "SELECT * FROM song WHERE album_id = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);
        prepStatement.setInt(1, albumId);
        ResultSet result = prepStatement.executeQuery();
        while (result.next())
            songs.add(new Song(result.getInt("song_id"), result.getString("title"), result.getDouble("length")));
        return songs;
    }

    /**
     * Insert album record using artist id.
     *
     * @param artistId   id of artist album belongs to
     * @param album      album to be inserted
     * @param connection connection instance
     * @throws SQLException if a database access error occurs or this
     *                      method is called on a closed result set.
     */
    private void insertAlbum(int artistId, Album album, Connection connection) throws SQLException {
        String query = "INSERT INTO album (title,genre,artist_id) VALUES (?,?,?)";
        PreparedStatement prepStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        prepStatement.setString(1, album.getTitle());
        prepStatement.setString(2, album.getGenre());
        prepStatement.setInt(3, artistId);
        if (prepStatement.executeUpdate() == 0) throw new SQLException("Creating Album Failed, no rows affected.");
        ResultSet generatedKeys = prepStatement.getGeneratedKeys();
        if (generatedKeys.next())
            album.setId(generatedKeys.getInt(1));
        for (Song song : album.getSongs())
            insertSong(album.getId(), song, connection);
    }

    /**
     * Insert song record using album id.
     *
     * @param albumId    id of album song belongs to
     * @param song       song to be inserted
     * @param connection connection instance
     * @throws SQLException if a database access error occurs or this
     *                      method is called on a closed result set.
     */
    private void insertSong(int albumId, Song song, Connection connection) throws SQLException {
        String query = "INSERT INTO song (title,length,album_id) VALUES (?,?,?)";
        PreparedStatement prepStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        prepStatement.setString(1, song.getTitle());
        prepStatement.setDouble(2, song.getLength());
        prepStatement.setInt(3, albumId);
        if (prepStatement.executeUpdate() == 0) throw new SQLException("Creating Song Failed, no rows affected.");
        ResultSet generatedKeys = prepStatement.getGeneratedKeys();
        if (generatedKeys.next())
            song.setId(generatedKeys.getInt(1));
    }

}
