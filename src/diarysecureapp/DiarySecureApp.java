/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarysecureapp;

import java.security.NoSuchAlgorithmException;
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
        
        //FABIANS SHA TEST
//        try {
//            //Creating SHA method that will encrypt the initial diary entry
//            SHA sha = new SHA();
//            
//            //Keyboard input from user
//            Scanner in = new Scanner(System.in);
//            System.out.println("Enter a message: ");
//            String input = in.nextLine();
//            
//            //Creating a string called hash1 that will encrypt the input using sha
//            String hash1 = sha.encrypt(input);
//            System.out.println(">> "+ hash1);
//            
//            System.out.println("\nEnter another message: ");
//            input = in.nextLine();
//            String hash2 = sha.encrypt(input);
//            System.out.println(">> "+ hash2);
//            
//            //Boolean variable to compare the two hashes against each other
//            boolean similar = sha.compare(hash1, hash2);
//            System.out.println("\nSimilar: "+ similar);
//            
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(DiarySecureApp.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            DiaryGUI myGUI = new DiaryGUI(); //Create GUI object
            myGUI.setVisible(true);                   //Initialise GUI object
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DiarySecureApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
