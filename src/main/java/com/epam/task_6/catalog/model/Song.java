package com.epam.task_6.catalog.model;

/**
 * Represents simple song with title and length
 */
public class Song {

    private String title;
    private double length;

    public Song(String title, double length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public double getLength() {
        return length;
    }

    @Override
    public int hashCode() {
        int result = 15 * (title != null ? title.hashCode() : 0);
        result = 15 * result + ((int)length ^ ((int)length >>> 16));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Song song = (Song) obj;

        return length == song.length && (title != null ? title.equals(song.title) : song.title == null);

    }

    @Override
    public String toString() {
        return "\t\tTitle: " + title + ",\tLength: " + length;
    }
}
