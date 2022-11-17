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

        try {
            DiaryGUI myGUI = new DiaryGUI(); //Create GUI object
            myGUI.setVisible(true);                   //Initialise GUI object
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DiarySecureApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
