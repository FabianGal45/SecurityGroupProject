/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diarysecureapp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Fabian & Taz
 */
public class SHA {

    MessageDigest md;

    public SHA() throws NoSuchAlgorithmException {
        this.md = MessageDigest.getInstance("SHA3-256");
    }

    public String encrypt(String input) {
        byte[] hashbytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashbytes);
    }

    //From: https://www.baeldung.com/sha-256-hashing-java
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    public boolean compare(String h1, String h2){
        boolean similar = false;
        if(h1.equals(h2)){
            similar = true;
        }
        return similar;
    }

}
