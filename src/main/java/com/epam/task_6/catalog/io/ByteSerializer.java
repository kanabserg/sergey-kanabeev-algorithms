package com.epam.task_6.catalog.io;

import com.epam.task_6.catalog.entities.CatalogEntity;
import com.epam.task_6.catalog.model.Catalog;
import com.epam.task_6.catalog.utils.ModelConverter;

import java.io.*;

/**
 * Represents serialization and deserialization of catalog model.
 */
public class ByteSerializer implements Serializer {

    @Override
    public Catalog readModel(String filePath) {
        Catalog deserializerCatalog = null;
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            deserializerCatalog = ModelConverter.toRegularModel((CatalogEntity) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return deserializerCatalog;
    }

    @Override
    public void writeModel(Catalog catalog, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            out.writeObject(ModelConverter.toEntityModel(catalog));
            out.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
