package diarysecureapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
public class AES {                                      //create class

    private final SecretKey key;                        //key, always unique with session
    private final IvParameterSpec ivParameterSpec;      //IV, same as key but can be overread from loading file
    private final String algorithm;                     //Type of algorithm used, CBC

    public AES() throws NoSuchAlgorithmException {
        key = generateKey(128);                       
        ivParameterSpec = generateIv();
        algorithm = "AES/CBC/PKCS5Padding";
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

    public String encrypt(String algorithm, String input, SecretKey key,                            //needs algorithm, input, key and IV
            IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,                                //idk why but it likes to throw alot of exceptions
            BadPaddingException, IllegalBlockSizeException {                                        //ignore this

        Cipher cipher = Cipher.getInstance(algorithm);                                        //Create cipher with the CBC algorithm
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);                                            //set cipher to encrypt mode, using the key and the IV
        byte[] cipherText = cipher.doFinal(input.getBytes());                                 //put the input into a byte array called cipherText
        return Base64.getEncoder()                                                                  //Return the Base64 string that was converted from the byte array
                .encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, SecretKey key,                //needs algorithm, encrypted message, key and IV
            IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);                                      //Create cipher, set CBC
        cipher.init(Cipher.DECRYPT_MODE, key, iv);                                          //Set cipher to decrypt mode, use key and IV
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));       //create a byte array, and decode the ciphertext onto it
        return new String(plainText);                                                       //return a string, of the byte array
    }

    //This method is used by DiaryGUI, to envoke encrypt using user input string
    public String getEncryptedInput(String input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        
        return encrypt(algorithm, input, key, ivParameterSpec);         
    }
    //This method is used to encode the key into a string and return it to the user
    public String getKey() {
        byte encoded[] = key.getEncoded();                                      //create byte array
        String encodedKey = Base64.getEncoder().encodeToString(encoded);    //encode new string from the byte array

//        byte encodedIV[] = ivParameterSpec.getEncoded();
//        String encodedIVID = Base64.getEncoder().encodeToString(encodedIV);
        return encodedKey;                                                      //return string to user
    }
    //This method is used to encode the key back into secretKey, while also envoking decrypt, with the user key and encrypted input
    public String getDecryptedInput(String ukey, String input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

//        byte[] decoded = ukey.getBytes();
//        SecretKey Okey = new SecretKeySpec(decoded, 0, decoded.length, "AES");
        byte[] decodedKey = Base64.getDecoder().decode(ukey);                                                   //encode string back into key
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");        

        return decrypt(algorithm, input, originalKey, ivParameterSpec);                             
    }

    public void saveFile(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, FileNotFoundException {
        File f;                     
        FileOutputStream fStream;
        ObjectOutputStream oStream;

        try {
            f = new File("output.dat");
            fStream = new FileOutputStream(f);
            oStream = new ObjectOutputStream(fStream);

            oStream.writeObject(message);
            oStream.close();

            System.out.println("Its saved" + message);
            //if it fails for whatever reason, it will output an error message in the system output field.
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String loadFile(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, FileNotFoundException {
        File f;
        FileInputStream fStream;
        ObjectInputStream oStream;

        //program will try to load a file called 'output.dat' when information is saved in the commonField ArrayList.
        try {
            f = new File("output.dat");
            fStream = new FileInputStream(f);
            oStream = new ObjectInputStream(fStream);

            message = (String) oStream.readObject();
            oStream.close();

            System.out.println("Its loaded" + message);

            //if it fails for whatever reason, it will output an error message in the system output field.
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return message;
    }
}

//ivParameterSpec = generateIv();
//        algorithm = "AES/CBC/PKCS5Padding";
//        return decrypt(algorithm, input, Okey, ivParameterSpec);
//test poopy head