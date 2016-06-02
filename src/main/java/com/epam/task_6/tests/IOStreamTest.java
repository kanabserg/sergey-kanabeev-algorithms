package com.epam.task_6.tests;

import com.epam.task_6.catalog.model.Catalog;
import com.epam.task_6.catalog.utils.Serializer;

public class IOStreamTest {

    private static final String FILE_PATH_IN = "src\\main\\resources\\musicCatalog\\inMusic.txt";
    private static final String FILE_PATH_OUT = "src\\main\\resources\\musicCatalog\\outMusic.txt";
    private static final String FILE_PATH_SERIALIZE = "src\\main\\resources\\musicCatalog\\serializeMusic.catalog";

    /**
     * Private constructor to prevent creation of instance.
     */
    private IOStreamTest(){}

    /**
     * Main method to run the test.
     */
    public static void run() {
        System.out.println("This test shows capabilities of IOStream API. Running the test...\n");
        Catalog shopCatalog = Serializer.readModel(FILE_PATH_IN);
        System.out.println("Model from file:");
        shopCatalog.print();
        Serializer.writeModel(shopCatalog,FILE_PATH_OUT);
        System.out.println("\n---------------------------------------------------------\n");
        Serializer.serialize(shopCatalog,FILE_PATH_SERIALIZE);
        Catalog deserializedCatalog = Serializer.deserialize(FILE_PATH_SERIALIZE);
        System.out.println("Deserialize model:");
        deserializedCatalog.print();
    }
}
