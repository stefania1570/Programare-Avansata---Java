package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Student> listOfStudents = new LinkedList<>();
        var arrayOfStudents = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Student("S" + i))
                .sorted(Comparator.comparing(Student::getName))
                .toArray(Student[]::new);
        listOfStudents.addAll(Arrays.asList(arrayOfStudents));
        System.out.println("Afisare studenti: ");

        for (Student listElement : listOfStudents) {
            System.out.print(listElement.getName() + " ");
        }

        Set<Project> setOfProjects = new TreeSet<>();
        var projects = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Project("P" + i))
                .sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toCollection(TreeSet::new));
        setOfProjects.addAll(projects);
        System.out.println("\nAfisare proiecte: ");

        for (Project listElement : setOfProjects) {
            System.out.print(listElement.getName() + " ");
        }
    }
}
