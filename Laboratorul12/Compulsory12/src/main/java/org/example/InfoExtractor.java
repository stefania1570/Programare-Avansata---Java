package org.example;

import Annotations.Test;
import java.lang.reflect.*;

public class InfoExtractor {
    Class aClass;
    public InfoExtractor(Class aClass)
    {
        this.aClass = aClass;
    }

    public void extractInfo()
    {
        System.out.println("******************* Information about the class ********************");
        System.out.println("Class name: " + aClass.getName());

        if(aClass.getPackage() != null)
            System.out.println("Package: " + aClass.getPackage());
        else System.out.println("Package: none" );

        if(aClass.getDeclaredMethods().length != 0)
        {
            System.out.println("Class methods: ");
            for(Method method : aClass.getDeclaredMethods())
                System.out.println(" - " + method.toString());
        }
        else System.out.println("Class methods: none");

        if(aClass.getDeclaredConstructors().length != 0)
        {
            System.out.println("Class constructors: ");
            for(Constructor constructor : aClass.getDeclaredConstructors())
                System.out.println(" - " + constructor.toString());
        }
        else System.out.println("Class constructors: none");

        if(aClass.getDeclaredFields().length != 0)
        {
            System.out.println("Class fields: ");
            for(Field field : aClass.getDeclaredFields())
                System.out.println(" - " + field.toString());
        }
        else System.out.println("Class fields: none");

    }

    public void invokeNoArgsMethods()
    {
        System.out.println("******************** Invoking static methods with no arguments ******************");
        for(Method method : this.aClass.getDeclaredMethods())
        {
            if(method.isAnnotationPresent(Test.class) && Modifier.isStatic(method.getModifiers()))
            {
                Parameter[] parameters = method.getParameters();
                if(parameters.length == 0)
                {
                    try {
                        System.out.println(" - Invoking method " + method.getName());
                        method.invoke(null);
                    } catch (IllegalAccessException | InvocationTargetException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}