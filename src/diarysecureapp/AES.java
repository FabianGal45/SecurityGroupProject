/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diarysecureapp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Christopher
 */
public class AES {

    public static final int AES_KEY_SIZE = 256;
    byte[] cipherText;
    
    public String encrypt(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

        keyGenerator.init(AES_KEY_SIZE);

        SecretKey key = keyGenerator.generateKey();

        SecretKeySpec my_keySpec = new SecretKeySpec(key.getEncoded(), "AES");

        Cipher my_cipher = Cipher.getInstance("AES/CTR/NoPadding");
       
        my_cipher.init(Cipher.ENCRYPT_MODE, my_keySpec);
            
        cipherText = my_cipher.doFinal(message.getBytes());
        
        String s = new String(cipherText, StandardCharsets.UTF_8);
        
        return s;
    }
    
    
    
    public String decrypt(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        Cipher my_cipher = Cipher.getInstance("AES/CTR/NoPadding");

        byte [] encodedParams = my_cipher.getParameters().getEncoded();
        System.out.println(encodedParams.length);
        System.out.println("IV = " + Arrays.toString(encodedParams));
        System.out.println("Ciphertext = " + Arrays.toString(cipherText));
        
        String d = new String(encodedParams);
        
        return d;
        
    }
    
    
    
    
}
