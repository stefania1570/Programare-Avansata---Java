package org.example;

public class ListCommand implements Command{
    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    public void implementCommand() {
        System.out.println(catalog.toString());
    }
}
