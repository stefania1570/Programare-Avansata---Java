package org.example;

public class Main {
    public static void main(String[] args) {

            String folderPath = "C:\\Users\\Stefania\\Downloads\\Compulsory12+Homework 12\\target\\classes";
            InfoExtractor extractor = new InfoExtractor(folderPath);
            extractor.analyzeAndTest();
    }
}