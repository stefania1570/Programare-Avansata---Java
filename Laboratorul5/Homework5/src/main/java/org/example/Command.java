package org.example;

public interface Command {

    void implementCommand() throws InvalidCommandException, InvalidCatalogException, DocumentAlreadyExistsException;

}
