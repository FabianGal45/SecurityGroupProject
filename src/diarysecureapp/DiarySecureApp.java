/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarysecureapp;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galfa
 */
public class DiarySecureApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //TESTTTTTTTT
        //HEAD

        //TEST LINE BY CHRIS
        //TEST LINE BY CHRIS TWO
        //TEST LINE BY CHRIS THREE

        //TAZ WAS HERE
        //YESSIR
        //DRAKE & 21 SAVAGE, "HER LOSS", FRIDAY 04/11/2022 YEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEER


        //Mark was here :)))


        //Fabian was here :P
        //origin/master
        
        
        try {
            SHA sha = new SHA();
            
            Scanner in = new Scanner(System.in);
            System.out.println("Enter a message: ");
            String input = in.nextLine();
            
            String hash1 = sha.encrypt(input);
            System.out.println(">> "+ hash1);
            
            System.out.println("\nEnter another message: ");
            input = in.nextLine();
            String hash2 = sha.encrypt(input);
            System.out.println(">> "+ hash2);
            
            boolean similar = sha.compare(hash1, hash2);
            System.out.println("\nSimilar: "+ similar);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DiarySecureApp.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        
        
    }
    
}
