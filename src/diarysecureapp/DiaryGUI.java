/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package diarysecureapp;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

/**
 *
 * @author cummi
 */
public class DiaryGUI extends javax.swing.JFrame {

    /**
     * Creates new form DiaryGUI
     */
    public DiaryGUI() throws NoSuchAlgorithmException {
        initComponents();

    }
    String message = "";        //Input Message
    String aesMessage = "";     //Encrypted Message
    String Okey = "";           //Output Key
    String uKey = "";           //Input Key
    AES a = new AES();          //AES object
    SHA s = new SHA();          //SHA object
    
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        diaryLBL = new javax.swing.JLabel();
        inputLBL = new javax.swing.JLabel();
        loadBTN = new java.awt.Button();
        outputLBL = new javax.swing.JLabel();
        encryptBTN = new java.awt.Button();
        saveBTN = new java.awt.Button();
        decryptBTN = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputTF = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputTF = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        diaryLBL.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        diaryLBL.setText("Diary");

        inputLBL.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        inputLBL.setText("Input");

        loadBTN.setLabel("Load");
        loadBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBTNActionPerformed(evt);
            }
        });

        outputLBL.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        outputLBL.setText("Output");

        encryptBTN.setLabel("Encrypt");
        encryptBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptBTNActionPerformed(evt);
            }
        });

        saveBTN.setLabel("Save");
        saveBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBTNActionPerformed(evt);
            }
        });

        decryptBTN.setLabel("Decrypt");
        decryptBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptBTNActionPerformed(evt);
            }
        });

        inputTF.setColumns(20);
        inputTF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputTF.setRows(5);
        jScrollPane1.setViewportView(inputTF);

        outputTF.setColumns(20);
        outputTF.setRows(5);
        jScrollPane2.setViewportView(outputTF);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(encryptBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(decryptBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loadBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(diaryLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(inputLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(outputLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(diaryLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(inputLBL)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(encryptBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(saveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputLBL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loadBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(decryptBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 60, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBTNActionPerformed
        // TODO add your handling code here:

        try {
            // TODO add your handling code here:

            aesMessage = a.loadFile(message);                                                               //Load encrypted message from file
            outputTF.setText(outputTF.getText() + "Message extracted from file - " + aesMessage + "\n");    //Print encrypted message in output box
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | FileNotFoundException ex) {
            Logger.getLogger(DiaryGUI.class.getName()).log(Level.SEVERE, null, ex);     //Error Handling
        }
    }//GEN-LAST:event_loadBTNActionPerformed

    private void encryptBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptBTNActionPerformed
        message = inputTF.getText();                                                                        //Grab text from text field

        if (message == "") {                                                                                //Check if message exists
            outputTF.setText(outputTF.getText() + "There is nothing to encrypt!\n");                        //If not, print error
        } else {
            try {
                aesMessage = a.getEncryptedInput(message);                                             //use method to return encrypted input
                s.saveToFile(s.encrypt(message));
                Okey = a.getKey();                                                                        //use method to grab key
                outputTF.setText(outputTF.getText() + "Message - " + aesMessage + "\n");                    //print encrypted message to output
                JOptionPane.showMessageDialog(null, "The key is: " + Okey + "\nThe key was saved to your clipboard");   //JOptionPane the key
                StringSelection stringSelection = new StringSelection(Okey);                          //String select Okey
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();                     //
                clipboard.setContents(stringSelection, null);                                  //Clipboard handling
            } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(DiaryGUI.class.getName()).log(Level.SEVERE, null, ex); //Error handling

            }
        }

    }//GEN-LAST:event_encryptBTNActionPerformed

    private void saveBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBTNActionPerformed
        // TODO add your handling code here:

        try {
            // TODO add your handling code here:
            if (aesMessage == "") {                                                                 //Check if message exists
                outputTF.setText(outputTF.getText() + "There is nothing encrypted!\n");             //Print error
            } else {
                a.saveFile(aesMessage);                                                      //use save method
                outputTF.setText(outputTF.getText() + "Saved!\n");                                  //print confirmation
            }

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | FileNotFoundException ex) {
            Logger.getLogger(DiaryGUI.class.getName()).log(Level.SEVERE, null, ex); //Error handling
        }
    }//GEN-LAST:event_saveBTNActionPerformed

    private void decryptBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptBTNActionPerformed
        uKey = JOptionPane.showInputDialog(null, "Please enter your key:\n"); //Ask key from user
        try {
            JOptionPane.showMessageDialog(null, "Your decrypted message is: " + a.getDecryptedInput(uKey, aesMessage)); //Print out decrypted message, by calling the method along with the encrypted message(aesMessage) and the key(Ukey)
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DiaryGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_decryptBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DiaryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiaryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiaryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiaryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DiaryGUI().setVisible(true);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(DiaryGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button decryptBTN;
    private javax.swing.JLabel diaryLBL;
    private java.awt.Button encryptBTN;
    private javax.swing.JLabel inputLBL;
    private javax.swing.JTextArea inputTF;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Button loadBTN;
    private javax.swing.JLabel outputLBL;
    private javax.swing.JTextArea outputTF;
    private java.awt.Button saveBTN;
    // End of variables declaration//GEN-END:variables
}
