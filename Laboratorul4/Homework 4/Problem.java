package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Problem {
    private List<Student> students;
    private Set<Project> projects; //The names of the projects are unique anyway, therefore we can use a list

    /**
     * Constructor for an instance of a problem
     */

    public Problem() {
        this.students = new ArrayList<>();
        this.projects = new TreeSet<>();
    }

    /**
     * Getter for students in this instance of the problem
     *
     * @return students list
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Setter for students
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Getter for projects
     *
     * @return projects list
     */
    public Set<Project> getProjects() {
        return projects;
    }

    /**
     * Setter for projects
     */
    public void setProjects(Set<Project> projects) {
        this.projects.addAll(projects);
    }

    /**
     * Method that prints the students with lower than average number of preferences
     */
    public void studentsWithLowerThanAverageNumberOfPreferences() {
        //Finding the average value
        double average = students.stream()
                .mapToDouble(s -> s.getAdmissibleProjects().size())
                .average().orElse(0.0);

        //Displaying the students below average value
        students.stream()
                .filter(s -> s.getAdmissibleProjects().size() < average)
                .collect(Collectors.toList())
                .forEach(i -> {
                    System.out.println(i.getName() + " ");
                });
        System.out.println("\n");
    }

    /**
     * Method that assigns a project to each student
     *
     * @return a map that contains the students and the projects they have been allocated to
     */
    public Map<Student, Project> assignProjectsGreedy() {
        Map<Student, Project> solution = new HashMap<>();
        List<Project> projectList;
        projectList = projects.stream().toList();

        for (Student student : students) {
            boolean assignedProjectToStudent = false;
            for (int i = 0; i < student.getAdmissibleProjects().size() && !assignedProjectToStudent; i++) {
                Project studentProject = student.getAdmissibleProjects().get(i);
                //For each project, we verify if it is taken in the list of projects of our instance
                for (int j = 0; j < projectList.size(); j++) {
                    if (projectList.get(j).compareTo(studentProject) == 0 && !projectList.get(j).isTaken()) {
                        projectList.get(j).setTaken();
                        solution.put(student, studentProject);
                        assignedProjectToStudent = true;
                    }
                }
            }
        }
        return solution;
    }
}
