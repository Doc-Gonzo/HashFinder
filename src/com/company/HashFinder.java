package com.company;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFinder {
    public static boolean findHash(String data, String difficulty) {
        boolean isFound = false;

        long nonce = 0;
        do {
            String strToHash = data+nonce;
            String hash = calculateHash(strToHash);
            System.out.printf("%s - %s %n",Thread.currentThread().getName(),hash);
            if(hash.startsWith(difficulty)){
                isFound = true;
            }
            nonce++;
        } while (!isFound && nonce < Long.MAX_VALUE);

        return isFound;
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
