package com.epam.task_6.catalog.utils;

import com.epam.task_6.catalog.entities.CatalogEntity;
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
 * Represented interface for read/write catalog from file and also
 * to serialize and deserialize state of catalog.
 */
public class Serializer {

    private static final String ARTIST_KEY_WORD = "Исполнитель:";
    private static final String ALBUM_KEY_WORD = "Альбом:";
    private static final String SONG_KEY_WORD = "Композиция:";

    /**
     * Private constructor to prevent creation of consumers.
     */
    private Serializer() {
    }

    /**
     * Reads model form txt file
     * @param filePath path to model file
     * @return model
     */
    public static Catalog readModel(String filePath) {
        Set<Artist> artists = new HashSet<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader((filePath))))) {
            while (scanner.hasNext(ARTIST_KEY_WORD)) {
                String t = scanner.nextLine();
                String artist = t.trim().substring(ARTIST_KEY_WORD.length()).trim();
                if (artist.isEmpty()) throw new InvalidFileException("ArtistEntity cannot be an empty string");
                Set<Album> albums = new HashSet<>();
                while (scanner.hasNext(ALBUM_KEY_WORD)) {
                    String[] album = scanner.nextLine().trim().substring(ALBUM_KEY_WORD.length()).trim().split(",");
                    if (album.length != 2) throw new InvalidFileException("Invalid album information");
                    Set<Song> songs = new HashSet<>();
                    while (scanner.hasNext(SONG_KEY_WORD)) {
                        String[] song = scanner.nextLine().trim().substring(SONG_KEY_WORD.length()).trim().split(",");
                        if (song.length != 2) throw new InvalidFileException("Invalid song information");
                        songs.add(new Song(song[0], Double.parseDouble((song[1]))));
                    }
                    if (songs.size() == 0)
                        throw new InvalidFileException("AlbumEntity cannot has zero songs! Money laundering!");
                    albums.add(new Album(album[0].trim(), album[1].trim(), songs));
                }
                if (albums.size() == 0)
                    throw new InvalidFileException("Artists cannot has zero albums! Is he an artist?");
                artists.add(new Artist(artist, albums));
            }
        } catch (InvalidFileException e) {
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println(String.format("File %s not found", filePath));
        }
        return new Catalog(artists);
    }

    /**
     * Writes model to txt file
     * @param catalog shop catalog to be written
     * @param filePath path to file
     */
    public static void writeModel(Catalog catalog, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter((filePath)))) {
            for (Artist artist : catalog.getArtists()) {
                writer.write(ARTIST_KEY_WORD + artist.getTitle() + "\n");
                for (Album album : artist.getAlbums()) {
                    writer.write(ALBUM_KEY_WORD + album.getTitle() + ", " + album.getGenre() + "\n");
                    for (Song song : album.getSongs()) {
                        writer.write(SONG_KEY_WORD + song.getTitle() + ", " + song.getLength() + "\n");
                    }
                }
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Serialize the state of catalog
     * @param catalog model to be serialized
     * @param filePath path to file
     */
    public static void serialize(Catalog catalog, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            out.writeObject(ModelConverter.toEntityModel(catalog));
            out.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Deserialize the state of catalog
     * @param filePath path to file
     * @return model
     */
    public static Catalog deserialize(String filePath){
        Catalog deserializedCatalog = null;
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            deserializedCatalog = ModelConverter.toRegularModel((CatalogEntity) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return deserializedCatalog;
    }
}
