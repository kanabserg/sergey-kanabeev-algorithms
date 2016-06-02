package com.epam.task_6.catalog.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to be serialized instance of {@linkplain com.epam.task_6.catalog.model.Artist}
 */
public class ArtistEntity  implements Serializable{

    private String title;
    private Set<AlbumEntity> albums;

    public ArtistEntity(String title, Set<AlbumEntity> albums) {
        this.albums = albums;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumEntity> albums) {
        this.albums = albums;
    }

    @Override
    public int hashCode() {
        int result = 31 * (title != null ? title.hashCode() : 0);
        for (AlbumEntity album: albums) result += album.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ArtistEntity artist = (ArtistEntity) obj;

        return title != null ? title.equals(artist.title) : artist.title == null && albums.size() == artist.albums.size();

    }
}

