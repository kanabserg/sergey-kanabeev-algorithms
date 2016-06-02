package com.epam.task_6.catalog.io;


import com.epam.task_6.catalog.exceptions.InvalidFileException;
import com.epam.task_6.catalog.model.Catalog;

import java.io.IOException;

/**
 * Represented interface for read/write catalog from file
 */
public interface Serializer {
    /**
     * Reads model form file
     * @param filePath path to model file
     * @return model
     */
    Catalog readModel (String filePath);

    /**
     * Writes model to file
     * @param catalog shop catalog to be written
     * @param filePath path to file
     */
    void writeModel(Catalog catalog, String filePath);
}
