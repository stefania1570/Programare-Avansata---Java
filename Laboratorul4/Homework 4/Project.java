package org.example;

import com.github.javafaker.Faker;

public class Project implements Comparable<Project> {
    private String name;
    private boolean isTaken = false; //when a student is assigned to this project, it will be true

    /**
     * Constructor for projects
     * Creates a fake name for projects.
     */
    public Project() {
        Faker faker = new Faker();
        this.name = faker.regexify("P[1-9]");
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
     * Getter for isTaken
     *
     * @return true if a project is taken, false otherwise
     */
    public boolean isTaken() {
        return isTaken;
    }

    /**
     * Setter for isTaken
     */
    public void setTaken() {
        isTaken = true;
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
