package org.example.advancejava;

public class Inheritanceexample {
    public static void main(String[] args) {
        Parrot parrot = new Parrot();
        parrot.wing();
        parrot.color();
    }
}

class Bird{
    void wing(){
        System.out.println("Have 2 wings");
    }
}

class Parrot extends Bird{
    void color(){
        System.out.println("have green color");
    }
}
