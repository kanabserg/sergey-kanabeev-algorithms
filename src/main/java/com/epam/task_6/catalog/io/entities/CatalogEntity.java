package com.epam.task_6.catalog.io.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Class to be serialized instance of {@linkplain com.epam.task_6.catalog.model.Catalog},
 * which has a list of all songs and links to them from albums. Therefore,
 * only one copy of song is stored.
 */
public class CatalogEntity implements Serializable{
    
    private Set<ArtistEntity> artists;
    private List<SongEntity> allSongs;

    public CatalogEntity(Set<ArtistEntity> artists, List<SongEntity> allSongs) {
        this.artists = artists;
        this.allSongs = allSongs;
    }

    public Set<ArtistEntity> getArtists() {
        return artists;
    }

    public void setArtists(Set<ArtistEntity> artists) {
        this.artists = artists;
    }

    public List<SongEntity> getAllSongs() {
        return allSongs;
    }

    public void setAllSongs(List<SongEntity> allSongs) {
        this.allSongs = allSongs;
    }
}
