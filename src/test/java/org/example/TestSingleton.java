package org.example;

public class TestSingleton {
    public static void main(String[] args) {
        Animal animal = new Animal("Tiger");
        animal.print();

        Animal animal2 = new Animal("Lion");
        animal2.print();

        System.out.println(animal == animal2);

        AnimalSingleTon a = AnimalSingleTon.getSingletonInstance();
        AnimalSingleTon b = AnimalSingleTon.getSingletonInstance();

        System.out.println(a == b);
    }
}

class Animal {
    private String name;
    public Animal(String name){
        this.name = name;
    }
    public void print(){
        System.out.println("My name is: "+this.name);
    }
}

