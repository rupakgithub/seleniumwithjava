package org.example;

public class AnimalSingleTon {
    private static AnimalSingleTon singletonInstance = null;
    private String name;

    private AnimalSingleTon() {
        name = "Tiger";
    }

    public static AnimalSingleTon getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new AnimalSingleTon();
        }
        return singletonInstance;
    }

    public void print(){
        System.out.println("My name is: "+name);
    }
}
