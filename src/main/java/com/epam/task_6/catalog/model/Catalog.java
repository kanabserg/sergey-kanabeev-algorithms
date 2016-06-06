package com.epam.task_6.catalog.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Represents entire music shop catalog, which contains
 * many artists.
 */
@XmlRootElement(name="catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog {

    @XmlElement(name = "artist")
    private Set<Artist> artists;

    public Catalog(Set<Artist> artists) {
        this.artists = artists;
    }

    public Catalog() {
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Artist artist: artists) {
            sb.append("\nArtist: ").append(artist.getTitle());
            for (Album album:artist.getAlbums()){
                sb.append("\n\tAlbum: ").append(album.getTitle()).append(",\tGenre: ").append(album.getGenre());
                for (Song song:album.getSongs()){
                    sb.append("\n\t\tTitle: ").append(song.getTitle()).append(",\tLength: ").append(song.getLength());
                }
            }
        }
        return sb.toString();
    }
}
