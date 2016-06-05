package com.epam.task_6.catalog.utils;

import com.epam.task_6.catalog.io.entities.AlbumEntity;
import com.epam.task_6.catalog.io.entities.ArtistEntity;
import com.epam.task_6.catalog.io.entities.CatalogEntity;
import com.epam.task_6.catalog.io.entities.SongEntity;
import com.epam.task_6.catalog.model.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class to convert shop catalog model to serializable form
 * and back to regular form.
 */
public class ModelConverter {

    /**
     * Private constructor to prevent creation of consumers.
     */
    private ModelConverter() {
    }

    /**
     * Converts regular model to serializable model
     * @param catalog instance of regular model
     * @return serializable model
     */
    public static CatalogEntity toEntityModel(Catalog catalog) {
        Set<ArtistEntity> artists = new HashSet<>();
        LinkedList<Song> allSongs = new LinkedList<>();
        for (Artist artist : catalog.getArtists()) {
            Set<AlbumEntity> albums = new HashSet<>();
            for (Album album : artist.getAlbums()) {
                List<Integer> indexList = new ArrayList<>();
                for (Song song : album.getSongs()) {
                    int index = allSongs.indexOf(song);
                    if (index > -1) indexList.add(index);
                    else {
                        allSongs.add(song);
                        indexList.add(allSongs.size()-1);
                    }
                }
                albums.add(new AlbumEntity(
                        album.getTitle(),
                        album.getGenre(),
                        indexList));
            }
            artists.add(new ArtistEntity(artist.getTitle(), albums));
        }
        return new CatalogEntity(artists,allSongs.stream().map(e -> new SongEntity(e.getTitle(),e.getLength())).collect(Collectors.toList()));
    }

    /**
     * Converts serializable model to regular model
     * INFO: in this task id did not considered, therefore 0 is
     *       is passed to the constructor as id
     *
     * @param catalog instance of serializable model
     * @return regular model
     */
    public static Catalog toRegularModel(CatalogEntity catalog){
        Set<Artist> artists = new HashSet<>();
        for (ArtistEntity artist : catalog.getArtists()) {
            Set<Album> albums = new HashSet<>();
            for (AlbumEntity album : artist.getAlbums()) {
                Set<Song> songs = new HashSet<>();
                for (Integer songNumber : album.getSongs()) {
                    SongEntity song = catalog.getAllSongs().get(songNumber);
                    songs.add(new Song(0,song.getTitle(), song.getLength()));
                }
                albums.add(new Album(0,
                        album.getTitle(),
                        album.getGenre(),
                        songs));
            }
            artists.add(new Artist(0,artist.getTitle(), albums));
        }
        return new Catalog(artists);
    }
}
