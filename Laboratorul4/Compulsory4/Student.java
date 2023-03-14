package org.example;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student> {
    private String name;
    private List<Project> admissibleProjects;

    public Student() {

    }

    /**
     * Constructor for student
     * @param name String
     */
    public Student(String name) {
        this.name = name;
        admissibleProjects = new ArrayList<>();
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
        return admissibleProjects;
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
