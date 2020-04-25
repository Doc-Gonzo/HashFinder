package com.company;

public class Main {

    public static void main(String[] args) {
        // Ergebnisobjekt, auf das alle Threads zugreifen könnnen
        Result result = new Result();

        System.out.println("Hello World!");
        // HashFinder.findHash("Hello","0000");

        HashFinder finder1 = new HashFinder(result,"thread1","Hello","0000",0);
        HashFinder finder2 = new HashFinder(result,"thread2","Hello","0000",5000);
    }

}

