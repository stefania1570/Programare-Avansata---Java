package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Student> listOfStudents = new LinkedList<>();
        var arrayOfStudents = IntStream.rangeClosed(0, 5)
                .mapToObj(i -> new Student())
                .sorted(Comparator.comparing(Student::getName))
                .toArray(Student[]::new);
        listOfStudents.addAll(Arrays.asList(arrayOfStudents));

        System.out.println("Afisare studenti: *************************************************** ");
        for (Student listElement : listOfStudents) {
            System.out.println(listElement.getName());
        }

        Set<Project> setOfProjects = new TreeSet<>();
        var projects = IntStream.rangeClosed(0, 8)
                .mapToObj(i -> new Project())
                .collect(Collectors.toCollection(TreeSet::new));
        setOfProjects.addAll(projects);

        System.out.println("\nAfisare proiecte: *************************************************** ");
        for (Project listElement : setOfProjects) {
            System.out.print(listElement.getName() + " ");
        }
        System.out.println("\n\nAfisare studenti + proiectele lor admisibile ************************");
        //Declaram o instanta a problemei
        Problem problemInstance = new Problem();
        problemInstance.setStudents(listOfStudents);
        problemInstance.setProjects(setOfProjects);

        //Asignare proiecte admisibile pt fiecare student
        for (var student : problemInstance.getStudents()) {
            Faker faker = new Faker();
            int randomNumber = faker.number().numberBetween(1, 8);
            System.out.print(student.getName() + ": ");
            var subSet = setOfProjects.stream().limit(randomNumber).collect(Collectors.toSet());
            student.setAdmissibleProjects(subSet);
            student.getAdmissibleProjects().forEach(i -> {
                System.out.print(i.getName() + " ");
            });
            System.out.print("\n");

        }

        Map<Student, Project> solution = new HashMap<>();
        solution.putAll(problemInstance.assignProjectsGreedy());
        System.out.println("\nAfisare distributie studenti-proiecte: **************************************************");
        for (var student : solution.entrySet()) {
            System.out.println(student.getKey().getName() + " " + student.getValue().getName());
        }

        System.out.println("\nAfisare studenti care au un numar de preferinte mai mic decat average-ul: *************");
        problemInstance.studentsWithLowerThanAverageNumberOfPreferences();

    }
}
