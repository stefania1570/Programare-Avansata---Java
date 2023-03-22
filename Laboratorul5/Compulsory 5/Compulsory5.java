package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CatalogUtil.InvalidCatalogException {
        Catalog catalog = new Catalog();

        Document doc1 = new Document(1, "Document 1", "d://document1.txt");
        doc1.addTag("author", "John Mitchell");
        doc1.addTag("year", "2003");
        catalog.add(doc1);

        Document doc2 = new Document(2, "Document 2", "c://document2.html");
        doc2.addTag("author", "Manny Iscusitul");
        doc2.addTag("year", "2021");
        catalog.add(doc2);

        CatalogUtil.save(catalog, "catalog.json");

        Catalog loadedCatalog = CatalogUtil.load("catalog.json");
        System.out.println(loadedCatalog.toString());
    }
}
