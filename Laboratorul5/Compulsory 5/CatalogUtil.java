package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CatalogUtil {
    /**
     * Method that saves the catalog to an external file using JSON format (using Jackson library)
     *
     * @param catalog Catalog
     * @param path    the path where the file will be saved that also contains the name of the file ex: catalog.json
     * @throws IOException
     */
    public static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), catalog);
    }

    /**
     * Method that loads the catalog from an external file in JSON format
     *
     * @param path the path of the file
     * @return catalog
     * @throws InvalidCatalogException
     * @throws IOException
     */
    public static Catalog load(String path) throws InvalidCatalogException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Catalog catalog = null;
        catalog = objectMapper.readValue(new File(path), Catalog.class);

        return catalog;
    }

    /**
     * Textual representation of the catalog
     *
     * @param catalog Catalog
     * @return string
     */
    public String toString(Catalog catalog) {
        String result = "";
        for (Document doc : catalog.getCatalog()) {
            result += doc.toString();
            result += "\n";
        }
        return result;
    }

    /**
     * Custom exception
     */
    public class InvalidCatalogException extends Exception {
        public InvalidCatalogException(Exception ex) {
            super("Invalid catalog file.", ex);
        }
    }

}
