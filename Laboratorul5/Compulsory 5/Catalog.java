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
     * Method that adds a document to the catalog
     *
     * @param doc Document
     */
    public void add(Document doc) {
        catalog.add(doc);
    }

    /**
     * Method that finds an entry in the catalog by its unique ID
     *
     * @param ID int
     * @return doc Document
     */
    public Document findById(int ID) {
        for (Document doc : catalog) {
            if (doc.getID() == ID)
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
