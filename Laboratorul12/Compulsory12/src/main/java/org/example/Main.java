package org.example;

public class Main {
    public static void main(String[] args) {
        String PATH = "D:\\Facultate\\Anul II\\Semestrul II\\[PA] Programare avansata (Java)\\Programare-Avansata---Java\\Laboratorul12\\Compulsory12\\target\\classes";
        String className = "org.example.RandomClass";
        try {
            Class aClass = Class.forName(className);
            InfoExtractor obj = new InfoExtractor(aClass);
            obj.extractInfo();
            obj.invokeNoArgsMethods();
        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}