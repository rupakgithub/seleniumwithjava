package org.example.advancejava;

public class Interfaceexample {
    public static void main(String[] args) {
        //Aeroplane plane = new Boeing();

        Aeroplane plane = new Airbus();

        plane.fly();
        plane.landing();
    }
}

//interface Aeroplane{
//    void fly();
//}

abstract class Aeroplane{
    abstract void fly();

    void landing(){
        System.out.println("Aeroplane is landing");
    }
}

class Boeing extends Aeroplane{
    public void fly(){
        System.out.println("Boeing is flying");
    }
}

class Airbus extends Aeroplane{
    public void fly(){
        System.out.println("Airbus is flying");
    }
}