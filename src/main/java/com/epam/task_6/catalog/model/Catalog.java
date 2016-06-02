package com.epam.task_6.catalog.model;

import java.util.Set;

/**
 * Represents entire music shop catalog, which contains
 * many artists.
 */
public class Catalog {
    
    private Set<Artist> artists;

    public Catalog(Set<Artist> artists) {
        this.artists = artists;
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
                    System.out.println(song);
                }
            }
        }
    }
}
