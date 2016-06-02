package com.epam.task_6.catalog.entities;

import java.io.Serializable;

/**
 * Class to be serialized instance of {@linkplain com.epam.task_6.catalog.model.Song}
 */
public class SongEntity  implements Serializable{

    private String title;
    private double length;

    public SongEntity(String title, double length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
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

        SongEntity song = (SongEntity) obj;

        return length == song.length && (title != null ? title.equals(song.title) : song.title == null);

    }
}
