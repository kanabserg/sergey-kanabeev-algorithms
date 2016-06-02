package com.epam.task_6.catalog.model;

import java.util.Set;

/**
 * Represents artist, which has many albums
 */
public class Artist {

    private String title;
    private Set<Album> albums;

    public Artist(String title, Set<Album> albums) {
        this.albums = albums;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    @Override
    public int hashCode() {
        int result = 31 * (title != null ? title.hashCode() : 0);
        for (Album album: albums) result += album.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Artist artist = (Artist) obj;

        return title != null ? title.equals(artist.title) : artist.title == null && albums.size() == artist.albums.size();

    }
}

