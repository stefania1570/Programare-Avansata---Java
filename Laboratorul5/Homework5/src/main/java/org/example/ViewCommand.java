package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Command{
    private Document document;

    public ViewCommand(Document document) {
        this.document = document;
    }

   public void implementCommand() throws InvalidCatalogException {
        try {
            Desktop.getDesktop().open(new File(document.getPath()));
        } catch (IOException e) {
            throw new InvalidCatalogException("Error opening the document");
        }
    }
}
