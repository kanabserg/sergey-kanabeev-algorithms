package com.epam.task_6.catalog.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Represents simple song with title and length.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Song {

    @XmlAttribute
    private int id;
    @XmlAttribute
    private String title;
    @XmlAttribute
    private double length;

    public Song() {
    }

    public Song(int id, String title, double length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public double getLength() {
        return length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        StringBuilder sb= new StringBuilder();
        sb.append("Title: ").append(title).append(", Length: ").append(length);
        return sb.toString();
    }
}
