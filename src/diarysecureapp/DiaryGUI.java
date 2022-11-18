/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package diarysecureapp;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;
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
    String message = "";        //Input Entry
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
        jPanel1 = new javax.swing.JPanel();
        inputLBL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputTF = new javax.swing.JTextArea();
        encryptBTN = new java.awt.Button();
        saveBTN = new java.awt.Button();
        jPanel2 = new javax.swing.JPanel();
        outputLBL = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputTF = new javax.swing.JTextArea();
        loadBTN = new java.awt.Button();
        decryptBTN = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        diaryLBL.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        diaryLBL.setText("Cryptographic Diary");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        inputLBL.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        inputLBL.setText("Unencrypted Entry");

        inputTF.setColumns(20);
        inputTF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputTF.setRows(5);
        jScrollPane1.setViewportView(inputTF);

        encryptBTN.setBackground(new java.awt.Color(102, 255, 0));
        encryptBTN.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        encryptBTN.setLabel("Encrypt");
        encryptBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptBTNActionPerformed(evt);
            }
        });

        saveBTN.setBackground(new java.awt.Color(51, 204, 0));
        saveBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        saveBTN.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        saveBTN.setLabel("Save");
        saveBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputLBL)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(encryptBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputLBL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(encryptBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(saveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        outputLBL.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        outputLBL.setText("Encrypted Output");

        outputTF.setEditable(false);
        outputTF.setColumns(20);
        outputTF.setRows(5);
        jScrollPane2.setViewportView(outputTF);

        loadBTN.setBackground(new java.awt.Color(102, 204, 255));
        loadBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        loadBTN.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        loadBTN.setLabel("Load");
        loadBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBTNActionPerformed(evt);
            }
        });

        decryptBTN.setBackground(new java.awt.Color(0, 153, 255));
        decryptBTN.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        decryptBTN.setLabel("Decrypt");
        decryptBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outputLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loadBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(decryptBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outputLBL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(loadBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(decryptBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(diaryLBL)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(diaryLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBTNActionPerformed

        aesMessage = a.loadFile();                                                            //Load encrypted entry from file

        outputTF.setText(outputTF.getText() + "Diary Entry extracted from file - " + aesMessage + "\n");    //Print encrypted entry in output box
        if(aesMessage==null){
            outputTF.append("The AES check has failed! Something has been tampered with.\n");   
        }
    }//GEN-LAST:event_loadBTNActionPerformed

    private void encryptBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptBTNActionPerformed
        message = inputTF.getText();                                                                        //Grab text from text field

        if (message == "") {                                                                                //Check if entry exists
            outputTF.setText(outputTF.getText() + "There is nothing to encrypt!\n");                        //If not, print error
        } else {

            aesMessage = a.encrypt(message);                                             //use method to return encrypted input
            s.saveToFile(s.encrypt(message));                                                       //encrypts and saves to file
            Okey = a.getKey();                                                                        //use method to grab key
            outputTF.setText(outputTF.getText() + "Encrypted Diary Entry: " + aesMessage + "\n");                    //print encrypted entry to output
            JOptionPane.showMessageDialog(null, "The key is: " + Okey + "\nThe key was saved to your clipboard");   //JOptionPane the key
            StringSelection stringSelection = new StringSelection(Okey);                          //String select Okey
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();                     //
            clipboard.setContents(stringSelection, null);                                  //Clipboard handling

        }

    }//GEN-LAST:event_encryptBTNActionPerformed

    private void saveBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBTNActionPerformed

        if (aesMessage == "") {                                                                 //Check if message exists
            outputTF.setText(outputTF.getText() + "There is nothing encrypted!\n");             //Print error
        } else {
            a.saveFile(aesMessage);                                                      //use save method
            outputTF.setText(outputTF.getText() + "Saved!\n");                                  //print confirmation
        }
        inputTF.setText("");

    }//GEN-LAST:event_saveBTNActionPerformed

    private void decryptBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptBTNActionPerformed
        uKey = JOptionPane.showInputDialog(null, "Please enter your key:\n"); //Ask key from user
        String decryptedMessage = null;
        try {
            decryptedMessage = a.decrypt(uKey);//Decrypt the original entry. 
        } catch (IllegalArgumentException | NullPointerException e) {
            outputTF.append("The key does not match!\n");
        } 
        
        String hashOne = s.encrypt(decryptedMessage);//Generate a new hash that will be compared with the original hash saved in the file 
        String hashTwo = s.readFromFile(); //Original hash from the saved file.

        if (s.compare(hashOne, hashTwo)) { //if the hashes match then display the message back where it can be edited again.
            inputTF.setText(decryptedMessage);

            outputTF.append("Diary Entry decrypted\n");
        } else {
            outputTF.append("The SHA check has Failed! Something has been tampered with.\n");

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Button loadBTN;
    private javax.swing.JLabel outputLBL;
    private javax.swing.JTextArea outputTF;
    private java.awt.Button saveBTN;
    // End of variables declaration//GEN-END:variables
}
