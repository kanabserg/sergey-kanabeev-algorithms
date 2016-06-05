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

    /**
     * Prints catalog to console.
     */
    public void print(){
        for (Artist artist: artists) {
            System.out.println("Artist: " + artist.getTitle());
            for (Album album:artist.getAlbums()){
                System.out.println("\tAlbum: " +album.getTitle() + ",\tGenre: " + album.getGenre());
                for (Song song:album.getSongs()){
                    System.out.println("\t\tTitle: " + song.getTitle() + ",\tLength: " + song.getLength());
                }
            }
        }
    }
}
