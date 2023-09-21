package org.example.advancejava;

public class Constructorexample {

    public static void main(String[] args) {
        Animal animal = new Animal("Tim", 1);
        animal.display();

        Animal animal2 = new Animal("William", 2);
        animal2.display();

        Animal animal3 = new Animal();
    }
}

class Animal{

    String name;
    int id;

    void display(){
        System.out.println("The name is: "+name);
        System.out.println("The id is: "+id);
    }
    Animal(String name, int id){
        this.name = name;
        this.id = id;
    }

    Animal(){
        System.out.println("This is empty constructor");
    }
}
