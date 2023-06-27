package org.example;

public class Main {
    public static void main(String[] args) {
        String a = "Apple";

        if(a.contains("le")){
            System.err.println("Passed");
        }
        else {
            System.err.println("Failed");
        }
    }
}