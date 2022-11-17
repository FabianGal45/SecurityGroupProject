/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diarysecureapp;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian & Taz
 */
public class SHA {

    private MessageDigest messDigest;

    public SHA() throws NoSuchAlgorithmException {
        this.messDigest = MessageDigest.getInstance("SHA-256");
    }

    public String encrypt(String input) {
        byte[] hBytes = messDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hBytes);
    }

    //Method From: https://www.baeldung.com/sha-256-hashing-java
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hexDigit = Integer.toHexString(0xff & hash[i]);
            if (hexDigit.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hexDigit);
        }
        return hexString.toString();
    }

    public void saveToFile(String sha) {
    //create output.dat file for storing message
        try {
            FileWriter fs = new FileWriter(new File("shaFile.dat")); //Creates new file for string
            BufferedWriter bw = new BufferedWriter(fs);
         
            
            bw.write(sha); // writes to file 
            bw.close(); //closes writer
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public String readFromFile () {
        String sha = null;
       
        try {
            BufferedReader br = new BufferedReader (new FileReader("shaFile.dat"));
            
            sha = br.readLine();
            br.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SHA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SHA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sha;
    }
        
    

    public boolean compare(String hashOne, String hashTwo) {
        boolean similar = false;
        if (hashOne.equals(hashTwo)) {
            similar = true;
        }
        return similar;
    }

}
