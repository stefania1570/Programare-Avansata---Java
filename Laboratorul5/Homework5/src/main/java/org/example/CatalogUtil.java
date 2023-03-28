package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CatalogUtil {
    private Catalog catalog;

    CatalogUtil(){

    }

    CatalogUtil(Catalog catalog) {
        this.catalog = catalog;
    }
    /**
     * Method that saves the catalog to an external file using JSON format (using Jackson library)
     *
     * @param catalog Catalog
     * @param path    the path where the file will be saved that also contains the name of the file ex: catalog.json
     * @throws IOException
     * @throws InvalidCatalogException
     */
    public static void save(Catalog catalog, String path) throws InvalidCatalogException,IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), catalog);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path), catalog);
        } catch (IOException e) {
            throw new InvalidCatalogException("Error saving catalog");
        }
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

}
