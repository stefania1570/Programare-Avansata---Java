package models;

public abstract class Model {
    private int id;
    private String name;

    public Model(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
