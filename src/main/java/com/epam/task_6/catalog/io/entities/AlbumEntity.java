package com.epam.task_6.catalog.io.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Class to be serialized instance of {@linkplain com.epam.task_6.catalog.model.Album}.
 * Contains links to songs, stored in {@linkplain CatalogEntity} instead of songs.
 */
public class AlbumEntity implements Serializable{

    private String title;
    private String genre;
    private List<Integer> songs;

    public AlbumEntity(String title, String genre, List<Integer> songs) {
        this.title = title;
        this.genre = genre;
        this.songs = songs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Integer> getSongs() {
        return songs;
    }

    public void setSongs(List<Integer> songs) {
        this.songs = songs;
    }

    @Override
    public int hashCode() {
        int result = 31 * (title != null ? title.hashCode() : 0);
        for (Integer song: songs) result += song.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        AlbumEntity album = (AlbumEntity) obj;

        return title != null ? title.equals(album.title) : album.title == null && songs.size() == album.songs.size();

    }
}
