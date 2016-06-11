package com.epam.task_6.tests;

import com.epam.task_6.catalog.io.ByteSerializer;
import com.epam.task_6.catalog.io.Serializer;
import com.epam.task_6.catalog.io.TextSerializer;
import com.epam.task_6.catalog.model.Catalog;

import java.util.Objects;

public class IOStreamTest {

    private static final String FILE_PATH_IN = "src\\main\\resources\\musicCatalog\\data\\serialization\\inMusic.txt";
    private static final String FILE_PATH_OUT = "src\\main\\resources\\musicCatalog\\data\\serialization\\outMusic.txt";
    private static final String FILE_PATH_SERIALIZE = "src\\main\\resources\\musicCatalog\\data\\serialization\\serializeMusic.catalog";

    /**
     * Private constructor to prevent creation of instance.
     */
    private IOStreamTest() {
    }

    /**
     * Main method to run the test.
     */
    public static void run() {
        try {
            System.out.println("This test shows capabilities of IOStream API. Running the test...\n");
            Serializer txtSerializer = new TextSerializer();
            Catalog shopCatalog = txtSerializer.readModel(FILE_PATH_IN);
            Objects.requireNonNull(shopCatalog);
            System.out.println("Model from file:");
            System.out.println(shopCatalog);
            txtSerializer.writeModel(shopCatalog, FILE_PATH_OUT);
            System.out.println("\n---------------------------------------------------------\n");
            Serializer byteSerializer = new ByteSerializer();
            byteSerializer.writeModel(shopCatalog, FILE_PATH_SERIALIZE);
            Catalog deserializeCatalog = byteSerializer.readModel(FILE_PATH_SERIALIZE);
            Objects.requireNonNull(shopCatalog);
            System.out.println("Deserialize model:");
            System.out.println(deserializeCatalog);
        } catch (NullPointerException e) {
            System.err.println("Failed to read catalog!");
        }
    }
}
