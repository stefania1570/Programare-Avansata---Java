package org.example;

import java.io.IOException;
import java.util.Scanner;

import static org.example.Catalog.findById;


public class Main {
    public static void main(String[] args) throws IOException, DocumentAlreadyExistsException {
        Catalog catalog = new Catalog();

        Document doc1 = new Document("book", "Document 1", "bookskjrfid.txt");
        doc1.addTag("author", "John Mitchell");
        doc1.addTag("year", "2003");
        catalog.addDocument(doc1);

        Document doc2 = new Document("article", "Document 2", "articledjjcdbnj.txt");
        doc2.addTag("author", "Manny Iscusitul");
        doc2.addTag("year", "2021");
        catalog.addDocument(doc2);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command:");
            String commandText = scanner.nextLine();
            if (commandText.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                if (commandText.startsWith("add")) {
                    String[] tokens = commandText.split("\\s+"); //splits the string based on whitespace
                    String id = tokens[1];
                    String name = tokens[2];
                    String path = tokens[3];
                    Document doc = new Document(id, name, path);
                    for (int i = 4; i < tokens.length-1; i += 2) {
                        String tag = tokens[i];
                        String value = tokens[i+1];
                        doc.addTag(tag, value);
                    }
                    new AddCommand(catalog, doc).implementCommand();
                }  else if (commandText.equals("list")) {
                    new ListCommand(catalog).implementCommand();
                } else if (commandText.startsWith("view")) {
                    String[] tokens = commandText.split("\\s+");
                    String id = tokens[1];
                    Document doc = findById(catalog, id);
                    if (doc == null) {
                        throw new InvalidCatalogException("Document not found");
                    }
                   new ViewCommand(doc).implementCommand();
                } else if (commandText.equals("report")) {
                    new ReportCommand(catalog).implementCommand();
                } else if (commandText.startsWith("save")) {
                    String[] tokens = commandText.split("\\s+");
                    String path = tokens[1];
                    CatalogUtil.save(catalog, path);
                } else if (commandText.startsWith("load")) {
                    String[] tokens = commandText.split("\\s+");
                    String path= tokens[1];
                    catalog=CatalogUtil.load(path);
                    System.out.println(catalog.toString());
                } else {
                    throw new InvalidCommandException("Invalid command");
                }
            } catch (InvalidCatalogException | InvalidCommandException | IllegalArgumentException | DocumentAlreadyExistsException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}