package org.example;

public class Project implements Comparable<Project> {
    private String name;

    public Project() {

    }

    public Project(String name) {
        this.name = name;
    }

    /**
     * Getter for the name of the project
     *
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the project
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Comparing the students by their name
     *
     * @param project the object to be compared.
     * @return <0 if project1.name<project2.name, =0 if they are equal, >0 if project1.name>project2.name
     */
    @Override
    public int compareTo(Project project) {
        return (this.name).compareTo(project.name);
    }
}
