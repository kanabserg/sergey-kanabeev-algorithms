package com.epam.task_6.catalog.io;

import com.epam.task_6.catalog.exceptions.InvalidFileException;
import com.epam.task_6.catalog.model.Album;
import com.epam.task_6.catalog.model.Artist;
import com.epam.task_6.catalog.model.Catalog;
import com.epam.task_6.catalog.model.Song;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Represents reading and writing catalog model from
 * text file.
 * INFO: in this task id did not considered, therefore 0 is
 * is passed to the constructor as id
 */
public class TextSerializer implements Serializer {

    private static final String ARTIST_KEY_WORD = "Исполнитель:";
    private static final String ALBUM_KEY_WORD = "Альбом:";
    private static final String SONG_KEY_WORD = "Композиция:";

    @Override
    public Catalog readModel(String filePath) {
        Set<Artist> artists = new HashSet<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader((filePath))))) {
            while (scanner.hasNext(ARTIST_KEY_WORD)) {
                String t = scanner.nextLine();
                String artist = t.trim().substring(ARTIST_KEY_WORD.length()).trim();
                if (artist.isEmpty()) throw new InvalidFileException("Artist cannot be an empty string");
                Set<Album> albums = new HashSet<>();
                while (scanner.hasNext(ALBUM_KEY_WORD)) {
                    String[] album = scanner.nextLine().trim().substring(ALBUM_KEY_WORD.length()).trim().split(",");
                    if (album.length != 2) throw new InvalidFileException("Invalid album information");
                    Set<Song> songs = new HashSet<>();
                    while (scanner.hasNext(SONG_KEY_WORD)) {
                        String[] song = scanner.nextLine().trim().substring(SONG_KEY_WORD.length()).trim().split(",");
                        if (song.length != 2) throw new InvalidFileException("Invalid song information");
                        songs.add(new Song(0, song[0], Double.parseDouble((song[1]))));
                    }
                    if (songs.size() == 0)
                        throw new InvalidFileException("Album cannot has zero songs! Money laundering!");
                    albums.add(new Album(0, album[0].trim(), album[1].trim(), songs));
                }
                if (albums.size() == 0)
                    throw new InvalidFileException("Artists cannot have zero albums! Is he an artist?");
                artists.add(new Artist(0, artist, albums));

            }
        } catch (InvalidFileException e) {
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println(String.format("File %s not found", filePath));
        }
        return artists.size() == 0 ? null : new Catalog(artists);
    }

    @Override
    public void writeModel(Catalog catalog, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter((filePath)))) {
            for (Artist artist : catalog.getArtists()) {
                writer.write(ARTIST_KEY_WORD + " " + artist.getTitle() + "\n");
                for (Album album : artist.getAlbums()) {
                    writer.write("\t" + ALBUM_KEY_WORD + " " + album.getTitle() + ", " + album.getGenre() + "\n");
                    for (Song song : album.getSongs()) {
                        writer.write("\t\t" + SONG_KEY_WORD + " " + song.getTitle() + ", " + song.getLength() + "\n");
                    }
                }
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
