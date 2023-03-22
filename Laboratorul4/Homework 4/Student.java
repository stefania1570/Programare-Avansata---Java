package org.example;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Student implements Comparable<Student> {
    private String name;
    private Set<Project> admissibleProjects;

    /**
     * Constructor for student.
     * Creates a fake name for each student automatically.
     */
    public Student() {
        Faker faker = new Faker();
        this.name= faker.name().fullName();
        admissibleProjects = new TreeSet<>();
    }

    /**
     * Getter for name
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the list of admissible projects
     * @return list of projects
     */
    public List<Project> getAdmissibleProjects() {
        return admissibleProjects.stream().toList();
    }

    /**
     * Setter for the admissible projects
     */
    public void setAdmissibleProjects(Set<Project> admissibleProjects) {
        this.admissibleProjects.addAll(admissibleProjects);
    }

    /**
     * Comparing the students by their name
     *
     * @param student the object to be compared.
     * @return <0 if student1.name<student2.name, =0 if they are equal, >0 if student1.name>student2.name
     */
    @Override
    public int compareTo(Student student) {
        return (this.name).compareTo(student.name);
    }
}
