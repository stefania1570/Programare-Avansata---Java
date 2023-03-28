package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private List<Document> catalog;

    /**
     * Constructor for a catalog
     */
    Catalog() {
        catalog = new ArrayList<Document>();
    }

    /**
     * Method for adding a document to the catalog
     * Checks if the document ID already exists in the catalog. If it exists, it will throw an exception.
     * @param doc Document
     */
    public void addDocument(Document doc) throws DocumentAlreadyExistsException {
        for (Document d : catalog) {
            if (doc.getID().equals(d.getID())) {
                throw new DocumentAlreadyExistsException("Document ID already exists in the catalog.");
            }
        }
        catalog.add(doc);
    }

    /**
     * Method that finds an entry in the catalog by its unique ID
     *
     * @param ID String
     * @return doc Document
     */
    public static Document findById(Catalog catalog, String ID) {
        for (Document doc : catalog.getCatalog()) {
            if (doc.getID().equals(ID))
                return doc;
        }
        return null;
    }

    /**
     * Getter for the catalog
     *
     * @return catalog list of documents
     */
    public List<Document> getCatalog() {
        return catalog;
    }

    /**
     * Setter for the catalog
     *
     * @param catalog list of documents
     */
    public void setCatalog(List<Document> catalog) {
        this.catalog = catalog;
    }

    @Override
    public String toString() {
        String aux = "";
        for (Document doc : catalog) {
            aux = aux + doc.getTitle() + " ";
        }
        return "Catalog{" + "The contents of the catalog:" + aux + '}';
    }
}
