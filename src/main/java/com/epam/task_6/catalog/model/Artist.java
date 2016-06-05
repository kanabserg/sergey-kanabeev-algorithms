package com.epam.task_6.catalog.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents artist, which has many albums.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Artist {

    @XmlAttribute
    private int id;
    @XmlAttribute
    private String title;
    @XmlElement (name = "album")
    private Set<Album> albums;

    public Artist() {
        albums = new HashSet<>();
    }

    public Artist(int id, String title, Set<Album> albums) {
        this.id = id;
        this.albums = albums;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get total songs length from all albums.
     *
     * @return songs length.
     */
    public double songsLength(){
        double length = 0.0;
        for (Album album:getAlbums())
            for (Song song : album.getSongs()) length += song.getLength();
        return length;
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

    @Override
    public String toString() {
        return "Title: " + title + ", Albums count: " + albums.size();
    }
}

