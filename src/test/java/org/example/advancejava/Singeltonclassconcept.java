package org.example.advancejava;

public class Singeltonclassconcept {

    public static void main(String[] args) {
        String a = "apple";
        String b = "apple";
        String c = new String("apple");

        System.out.println(a.equals(c));

        System.out.println(a == c);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(Integer.toHexString(System.identityHashCode(a)));
        System.out.println(Integer.toHexString(System.identityHashCode(c)));

        Singleton x = Singleton.getInstance();
        Singleton y = Singleton.getInstance();

        System.out.println("Single class: ");
        System.out.println(x==y);
        Singleton.getInstance().display();

    }
}

class Singleton{
    private static Singleton single_instance = null;

    private Singleton() {}

    public static Singleton getInstance(){
        if (single_instance == null){
            single_instance = new Singleton();
        }

        return single_instance;
    }

    public void display(){
        System.out.println("Dummy method");
    }
}
