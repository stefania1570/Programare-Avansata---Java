package org.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class InfoExtractor {
    private final String folderPath;
    private final List<Class<?>> testClasses;

    public InfoExtractor(String folderPath) {
        this.folderPath = folderPath;
        this.testClasses = new ArrayList<>();
    }

    public void analyzeAndTest() {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Invalid folder path: " + folderPath);
            return;
        }

        System.out.println("Analyzing and testing Java classes in folder: " + folderPath);

        try {
            URLClassLoader classLoader = getClassLoader(folder);
            processClasses(folder, classLoader);
            performTests();
            printStatistics();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private URLClassLoader getClassLoader(File folder) throws IOException {
        URL url = folder.toURI().toURL();
        return new URLClassLoader(new URL[]{url});
    }

    private void processClasses(File folder, URLClassLoader classLoader) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processClasses(file, classLoader);
                } else if (file.getName().endsWith(".class")) {
                    String className = getClassName(file);
                    try {
                        Class<?> clazz = classLoader.loadClass(className);
                        if (hasTestMethod(clazz)) {
                            testClasses.add(clazz);
                            extractInfo(clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String getClassName(File file) {
        String absolutePath = file.getAbsolutePath();
        String relativePath = absolutePath.substring(folderPath.length() + 1);
        return relativePath.replace(File.separatorChar, '.').replace(".class", "");
    }

    private boolean hasTestMethod(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Annotations.Test.class)) {
                return true;
            }
        }
        return false;
    }

    private void extractInfo(Class<?> clazz) {
        System.out.println("******************* Information about the class ********************");
        System.out.println("Class name: " + clazz.getName());

        if (clazz.getPackage() != null)
            System.out.println("Package: " + clazz.getPackage());
        else
            System.out.println("Package: none");

        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length != 0) {
            System.out.println("Class methods: ");
            for (Method method : methods)
                System.out.println(" - " + method.toString());
        } else
            System.out.println("Class methods: none");

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length != 0) {
            System.out.println("Class constructors: ");
            for (Constructor<?> constructor : constructors)
                System.out.println(" - " + constructor.toString());
        } else
            System.out.println("Class constructors: none");

        Field[] fields = clazz.getDeclaredFields();
        if (fields.length != 0) {
            System.out.println("Class fields: ");
            for (Field field : fields)
                System.out.println(" - " + field.toString());
        } else
            System.out.println("Class fields: none");
    }

    private void performTests() {
        System.out.println("******************** Invoking methods with no arguments (static or not) ******************");
        int totalTests = 0;
        int passedTests = 0;

        for (Class<?> clazz : testClasses) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Annotations.Test.class)) {
                    Parameter[] parameters = method.getParameters();
                    if (parameters.length == 0) {
                        try {
                            Object instance = clazz.getDeclaredConstructor().newInstance();
                            System.out.println(" - Invoking method " + method.getName());
                            method.invoke(instance);
                            passedTests++;
                        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                            System.err.println("Test failed for method: " + method.getName());
                            e.printStackTrace();
                        }
                        totalTests++;
                    }
                }
            }
        }

        System.out.println("******************** Test Results ******************");
        System.out.println("Total tests executed: " + totalTests);
        System.out.println("Tests passed: " + passedTests);
        System.out.println("Tests failed: " + (totalTests - passedTests));
    }


    private void printStatistics() {
        System.out.println("******************** Statistics ******************");
        System.out.println("Total classes analyzed: " + testClasses.size());
    }
}

