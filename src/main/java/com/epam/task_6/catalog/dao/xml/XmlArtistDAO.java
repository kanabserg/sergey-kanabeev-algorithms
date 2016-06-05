package com.epam.task_6.catalog.dao.xml;

import com.epam.task_6.catalog.dao.ArtistDAO;
import com.epam.task_6.catalog.exceptions.DAOException;
import com.epam.task_6.catalog.model.Album;
import com.epam.task_6.catalog.model.Artist;
import com.epam.task_6.catalog.model.Catalog;
import com.epam.task_6.catalog.model.Song;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Specific realization of {@linkplain ArtistDAO}, which uses
 * XML file as a data storage. Uses SAX, DOM and JAXB to perform
 * CRUD operations.
 */
public class XmlArtistDAO implements ArtistDAO {

    /**
     * File Path to data source
     */
    private static final String FILE_PATH = "src\\main\\resources\\musicCatalog\\catalog.xml";

    @Override
    public int insert(Artist artist) throws DAOException {
        try {
            Catalog catalog = readXML();
            catalog.getArtists().add(artist);
            writeXML(catalog);
        } catch (JAXBException | FileNotFoundException e) {
            //TODO: Logging
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        return artist.getId();
    }

    @Override
    public boolean delete(int id) throws DAOException {
        Node foundNode;
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(FILE_PATH));

            XPath xpath = XPathFactory.newInstance().newXPath();
            foundNode = ((NodeList) xpath.evaluate("//artist[@id=" + id + "]", doc, XPathConstants.NODESET)).item(0);
            if (foundNode != null) foundNode.getParentNode().removeChild(foundNode);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(new File(FILE_PATH)));
        } catch (IOException | ParserConfigurationException | XPathExpressionException | SAXException | TransformerException e) {
            //TODO: Logging
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        return foundNode != null;
    }

    @Override
    public Artist find(int id) throws DAOException {
        Artist artist = null;
        try {
            Catalog catalog = readXML();
            Optional<Artist> optionalArtist = catalog.getArtists().stream().filter(a -> a.getId() == id).findFirst();
            if (optionalArtist.isPresent())
                artist = optionalArtist.get();
        } catch (JAXBException e) {
            //TODO: Logging
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        return artist;
    }

    @Override
    public boolean update(int id, Artist updArtist) throws DAOException {
        boolean isUpdated = false;
        try {
            Catalog catalog = readXML();
            Optional<Artist> optionalArtist = catalog.getArtists().stream().filter(a -> a.getId() == id).findFirst();
            if (optionalArtist.isPresent()) {
                Artist artist = optionalArtist.get();
                artist.setTitle(updArtist.getTitle());
                artist.setAlbums(updArtist.getAlbums());
                isUpdated = true;
                writeXML(catalog);
            }
        } catch (JAXBException | FileNotFoundException e) {
            //TODO: Logging
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        return isUpdated;
    }

    @Override
    public Set<Artist> getAll() throws DAOException {
        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            ArtistContentHandler handler = new ArtistContentHandler();
            saxParser.parse(new File(FILE_PATH), handler);
            return handler.getArtists();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            //TODO: Logging
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public boolean addAll(Set<Artist> artists) throws DAOException {
        try {
            writeXML(new Catalog(artists));
        } catch (JAXBException | FileNotFoundException e) {
            //TODO: Logging
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
        return true;
    }

    /**
     * Writes {@linkplain Catalog} to file with JAXB in order to store artists
     * collection.
     *
     * @param catalog to be written to the file
     * @throws JAXBException         If any unexpected problem occurs during the marshalling
     *                               or if an error was encountered while creating the JAXBContext
     * @throws FileNotFoundException if the file exists but is a directory
     *                               rather than a regular file, does not exist but cannot
     *                               be created, or cannot be opened for any other reason.
     */
    private void writeXML(Catalog catalog) throws JAXBException, FileNotFoundException {
        Marshaller marshaller = JAXBContext.newInstance(Catalog.class).createMarshaller();
        marshaller.marshal(catalog, new FileOutputStream(FILE_PATH));
    }

    /**
     * Read @linkplain Catalog} to file with JAXB in order to get artists
     * collection.
     *
     * @return read catalog instance
     * @throws JAXBException if any unexpected problem occurs during the unmarshalling
     *                       or if an error was encountered while creating the JAXBContext
     */
    private Catalog readXML() throws JAXBException {
        Unmarshaller unmarshaller = JAXBContext.newInstance(Catalog.class).createUnmarshaller();
        return (Catalog) unmarshaller.unmarshal(new File(FILE_PATH));
    }

    /**
     * Content handler for SAX parser implemented for Artist entity.
     *
     * @see SAXParser
     * @see DefaultHandler
     */
    private class ArtistContentHandler extends DefaultHandler {

        private Set<Artist> artists = new HashSet<>();
        private Stack<Object> stack = new Stack<>();

        Set<Artist> getArtists() {
            return artists;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            switch (qName) {
                case "artist":
                    stack.push(new Artist(Integer.parseInt(atts.getValue("id")), atts.getValue("title"), new HashSet<>()));
                    break;
                case "album":
                    stack.push(new Album(Integer.parseInt(atts.getValue("id")), atts.getValue("title"), atts.getValue("genre"), new HashSet<>()));
                    break;
                case "song":
                    stack.push(new Song(Integer.parseInt(atts.getValue("id")), atts.getValue("title"), Double.parseDouble(atts.getValue("length"))));
                    break;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case "artist":
                    artists.add((Artist) stack.pop());
                    break;
                case "album":
                    Album albumToAdd = (Album) stack.pop();
                    ((Artist) stack.peek()).getAlbums().add(albumToAdd);
                    break;
                case "song":
                    Song songToAdd = (Song) stack.pop();
                    ((Album) stack.peek()).getSongs().add(songToAdd);
                    break;
            }
        }
    }
}
