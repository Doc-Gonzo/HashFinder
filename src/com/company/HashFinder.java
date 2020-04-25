package com.company;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFinder implements Runnable{
    // Shared Result Object
    Result result;
    String threadName,data,difficulty;
    long nonce,nonceOffset,searchRange;
    boolean ascending;

    // Konstruktor
    HashFinder(Result r, String threadName,String data, String difficulty, long nonce,long nonceOffset,boolean ascending) {
        this.result = r;
        this.threadName = threadName;
        this.data = data;
        this.difficulty = difficulty;
        this.nonce = nonce;
        // Anzahl der generierten Hashes
        this.nonceOffset = nonceOffset;
        // Suchrichtung
        this.ascending=ascending;
        this.searchRange = nonceOffset / 2;

        new Thread(this, this.threadName).start();
    }
    // Run Methode checkt ob Hash im shared objekt gefunden
    public void run() {
        // Wenn Suchrichtung absteigend, setze nonce auf max
        if (!ascending) {
            nonce = nonceOffset;
        }
        // Suche Bereich ab
        while (!result.isFound()) {
            do {
                // Checke ob Suchrange erschöpft, starte dan nerneut mit 10fachem Range + offset, um doppelte verarbeitung der ersten Ranges zu vermeiden
                if (nonce == searchRange) {
                    nonce = searchRange *10 + nonce;
                }
                String strToHash = data+nonce;
                String hash = calculateHash(strToHash);
               // System.out.println("Nonce" + nonce);
                System.out.printf("%s - %s %n",Thread.currentThread().getName(),hash + "    Nonce Nr: " + nonce);
                if(hash.startsWith(difficulty)){
                    result.setFound(true);
                    result.setSuccessfullThread(Thread.currentThread().getName());
                    result.setHash(hash);
                    System.out.printf("Hash gefunden:  %s - %s %n",Thread.currentThread().getName(),hash);
                }
                // Checke ob absteigend
                if (ascending){
                    nonce++;
                }
                else  {
                    nonce--;
                }

            } while (!result.isFound() && nonce < Long.MAX_VALUE);
        }
    }

    public static String calculateHash(String strToHash) {

        byte[] bytesOfMessage;
        try {
            bytesOfMessage = strToHash.getBytes("UTF-8");
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");

            byte[] thedigest = md.digest(bytesOfMessage);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < thedigest.length; i++) {
                String hex = Integer.toHexString(0xff & thedigest[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}