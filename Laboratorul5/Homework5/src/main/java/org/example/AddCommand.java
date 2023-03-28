package org.example;

public class AddCommand implements Command{
    private Catalog catalog;
    private Document doc;

    /**
     * Constructor for the AddCommand
     * @param catalog Catalog
     */
    public AddCommand(Catalog catalog, Document doc) {
        this.catalog = catalog;
        this.doc = doc;
    }
    /**
     * Method that adds a document to the catalog
     */
    public void implementCommand() throws DocumentAlreadyExistsException {
        catalog.addDocument(doc);
    }
}
