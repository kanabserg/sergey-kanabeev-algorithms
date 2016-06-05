package com.epam.task_6.catalog.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents album, which contains many songs.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Album {
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String title;
    @XmlAttribute
    private String genre;
    @XmlElement (name = "song")
    private Set<Song> songs;

    public Album() {
        songs = new HashSet<>();
    }

    public Album(int id, String title, String genre, Set<Song> songs) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("Title: ").append(title).append(", Songs count: ").append(songs.size());
        return sb.toString();
    }
}
