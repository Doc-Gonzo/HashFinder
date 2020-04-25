package com.company;

public class Main {

    public static void main(String[] args) {
        // Ergebnisobjekt, auf das alle Threads zugreifen könnnen
        Result result = new Result();
        String data = "Manufaktur";
        String difficulty = "0000";
        // Beginne immer bei "0"
        long nonce = 0;
        // Anzahl an generierten Hashes
        long nonceOffset = 10000;


        System.out.println("Hello World!");
        // HashFinder.findHash("Hello","0000");


        HashFinder finder1 = new HashFinder(result,"thread1",data,difficulty,nonce,nonceOffset,true);
        HashFinder finder2 = new HashFinder(result,"thread2",data,difficulty,nonceOffset,nonceOffset,false);


    }

}

