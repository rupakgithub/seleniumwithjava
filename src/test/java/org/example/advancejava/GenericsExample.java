package org.example.advancejava;

public class GenericsExample {

    static <T> void display(T element){
        System.out.println(element);
    }

    public static void main(String[] args) {
        Test<String> a = new Test<>("abc");
        System.out.println(a.getObj());

        display("Test");
        display(14);
    }
}

class Test<T> {
    T obj;

    Test(T obj){
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }
}
