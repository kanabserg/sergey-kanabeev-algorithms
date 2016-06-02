package com.epam.task_6.catalog.model;

import java.util.Set;

/**
 * Represents album, which contains many songs.
 */
public class Album {

    private String title;
    private String genre;
    private Set<Song> songs;

    public Album(String title, String genre, Set<Song> songs) {
        this.title = title;
        this.genre = genre;
        this.songs = songs;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    @Override
    public int hashCode() {
        int result = 31 * (title != null ? title.hashCode() : 0);
        for (Song song: songs) result += song.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Album album = (Album) obj;

        return title != null ? title.equals(album.title) : album.title == null && songs.size() == album.songs.size();

    }
}
