package org.example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    //TODO FA ARTICLE, BOOK ETC.
    private String ID;
    private String title;
    private String path;
    private Map<String, Object> tags;

    Document() {

    }

    /**
     * Constructor for a document
     *
     * @param ID    int
     * @param title String
     * @param path  String
     */
    public Document(String ID, String title, String path) {
        this.ID = ID;
        this.title = title;
        this.path = path;
        this.tags = new HashMap<>();
    }

    /**
     * Getter for ID
     *
     * @return ID int
     */
    public String getID() {
        return ID;
    }

    /**
     * Setter for ID
     *
     * @param ID int
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Getter for title
     *
     * @return title String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     *
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for path
     *
     * @return path string
     */
    public String getPath() {
        return path;
    }

    /**
     * Setter for path
     *
     * @param path string
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Getter for tags
     *
     * @return tags map
     */
    public Map<String, Object> getTags() {
        return tags;
    }

    /**
     * Method that adds a tag to a document map for tags
     *
     * @param name  the key
     * @param value yhe value
     */
    public void addTag(String name, Object value) {
        tags.put(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return ID == document.ID;
    }

    @Override
    public String toString() {
        return "Document{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", tags=" + tags +
                '}';
    }
}
