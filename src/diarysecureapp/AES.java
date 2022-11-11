/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diarysecureapp;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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

    private SecretKey key;
    private IvParameterSpec ivParameterSpec;
    private String algorithm;

    public AES() {

    }


    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {        //Randomly generates a key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public static IvParameterSpec generateIv() {                                        //Generates initialisation vector
        byte[] iv = new byte[16];                                                       //creates a byte array
        new SecureRandom().nextBytes(iv);                                          //adds random integers
        System.out.println(iv);                                                       //prints out the random ints DELETE WHEN FINISHED
        return new IvParameterSpec(iv);                                            //return it as the IV
    }

    public String encrypt(String algorithm, String input, SecretKey key, //needs algorithm, input and key
            IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, //idk why but it likes to throw alot of exceptions
            BadPaddingException, IllegalBlockSizeException {                                        //ignore this

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, SecretKey key,
            IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }

    //USE THIS ONE - Mark 
    public String getEncryptedInput(String input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        key = generateKey(128);
        ivParameterSpec = generateIv();
        System.out.println(ivParameterSpec+" THIS IS IT!!!!!");
        algorithm = "AES/CBC/PKCS5Padding";
        return encrypt(algorithm, input, key, ivParameterSpec);
    }

    public String getKey() {
        byte encoded[] = key.getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(encoded);
        
//        byte encodedIV[] = ivParameterSpec.getEncoded();
//        String encodedIVID = Base64.getEncoder().encodeToString(encodedIV);
        return encodedKey;
    }

    public String getDecryptedInput(String key, String input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] decoded = key.getBytes();
        SecretKey Okey = new SecretKeySpec(decoded, 0, decoded.length, "AES");
        
        
        System.out.println(Okey);       //TEST PRINT, DELETE LATER
        

        ivParameterSpec = generateIv();
        algorithm = "AES/CBC/PKCS5Padding";
        return decrypt(algorithm, input, Okey, ivParameterSpec);
    }
}

//ivParameterSpec = generateIv();
//        algorithm = "AES/CBC/PKCS5Padding";
//        return decrypt(algorithm, input, Okey, ivParameterSpec);