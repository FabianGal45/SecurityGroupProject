package diarysecureapp;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    private final SecretKey SecretKey;                        //key, always unique with session
    private IvParameterSpec IV;      //IV, same as key but can be overread from loading file
    private final String algorithm;                     //Type of algorithm used, CBC
    private String ivByte;
    byte[] iv;

    public AES() throws NoSuchAlgorithmException {
        SecretKey = generateKey(128);
        IV = generateIv();
        algorithm = "AES/CBC/PKCS5Padding";

    }

    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {        //Randomly generates a key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public IvParameterSpec generateIv() {                                        //Generates initialisation vector

        iv = new byte[16];                                                       //creates a byte array
        new SecureRandom().nextBytes(iv);                                          //adds random integers

        ivByte = Base64.getEncoder().encodeToString(iv);    //encode new string from the byte array
        //System.out.println("BYTE ARRAY IS: " + ivByte);

        return new IvParameterSpec(iv);                                            //return it as the IV
    }

    public String encrypt(String algorithm, String input, SecretKey SecretKey, //needs algorithm, input, key and IV
            IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, //idk why but it likes to throw alot of exceptions
            BadPaddingException, IllegalBlockSizeException {                                        //ignore this

        Cipher encryptCipher = Cipher.getInstance(algorithm);                                        //Create cipher with the CBC algorithm
        encryptCipher.init(Cipher.ENCRYPT_MODE, SecretKey, iv);                                            //set cipher to encrypt mode, using the key and the IV
        byte[] cipherText = encryptCipher.doFinal(input.getBytes());                                 //put the input into a byte array called cipherText
        return Base64.getEncoder() //Return the Base64 string that was converted from the byte array
                .encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, SecretKey SecretKey, //needs algorithm, encrypted message, key and IV
            IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher decryptCipher = Cipher.getInstance(algorithm);                                      //Create cipher, set CBC
        decryptCipher.init(Cipher.DECRYPT_MODE, SecretKey, iv);                                          //Set cipher to decrypt mode, use key and IV
        byte[] plainText = decryptCipher.doFinal(Base64.getDecoder().decode(cipherText));       //create a byte array, and decode the ciphertext onto it
        return new String(plainText);                                                       //return a string, of the byte array
    }

    //This method is used by DiaryGUI, to envoke encrypt using user input string
    public String getEncryptedInput(String input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        return encrypt(algorithm, input, SecretKey, IV);
    }

    //This method is used to encode the key into a string and return it to the user
    public String getKey() {
        byte encoded[] = SecretKey.getEncoded();                                      //create byte array
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

        return decrypt(algorithm, input, originalKey, IV);
    }

    public void saveFile(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, FileNotFoundException {
        File f;
//        File t;
        FileOutputStream fStream;
        ObjectOutputStream oStream;
//        FileOutputStream tStream;
//        ObjectOutputStream toStream;

        try {
            f = new File("output.dat");                             //create output.dat file for storing message
            fStream = new FileOutputStream(f);
            oStream = new ObjectOutputStream(fStream);

            oStream.writeObject(message);                           //write message as object into file

            oStream.close();                                            //close

            System.out.println("Its saved! Message is - " + message);           //REMOVE when final

//            t = new File("iv.dat");                             //create output.dat file for storing message
//            tStream = new FileOutputStream(t);
//            toStream = new ObjectOutputStream(tStream);
//
//            toStream.writeObject(ivByte);                           //write message as object into file
//
//            toStream.close();                                            //close

            FileOutputStream fs = new FileOutputStream(new File("iv.dat"));
            BufferedOutputStream bos = new BufferedOutputStream(fs);
            bos.write(iv);
            bos.close();

            System.out.println("IV SAVED! Message is - " + ivByte);

            //if it fails for whatever reason, it will output an error message in the system output field.
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String loadFile(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, FileNotFoundException {
        File f;
//        File t;
        FileInputStream fStream;
        ObjectInputStream oStream;
//        FileInputStream tStream;
//        ObjectInputStream toStream;

        //program will try to load a file called 'output.dat' when information is saved in the commonField ArrayList.
        try {
            f = new File("output.dat");
            fStream = new FileInputStream(f);
            oStream = new ObjectInputStream(fStream);

            message = (String) oStream.readObject();
            oStream.close();

//            String[] messageArray = message.split(" ", 2);
//            message = messageArray[0];
//            ivByte = messageArray[1];
//
//            System.out.println(ivByte);
//            byte[] newIV  = {82,76,88,116,97,68,69,102,79,116,43,102,99,52,101,112};
//            //newIV = ivByte.getBytes();
//            
//            
//            for(int i = 0; i < newIV.length;i++){
//                System.out.println(newIV[i]);
//            }
//            IV = new IvParameterSpec(newIV);
            System.out.println("Its loaded! Message is - " + message);          //REMOVE when final

            
            
            byte[] fileInfo = new byte[16];
            DataInputStream input = null;
            
            input = new DataInputStream(new FileInputStream(new File("iv.dat")));
            input.readFully(fileInfo);
            if(input != null) {
                input.close();
            }
            
            IV = new IvParameterSpec(fileInfo);
            
            
            
            
//            t = new File("iv.dat");
//            tStream = new FileInputStream(t);
//            toStream = new ObjectInputStream(tStream);
//            
//            ivByte = (String) toStream.readObject();
//            toStream.close();
//            
//
//            
//            iv = ivByte.getBytes();
//            
//            for(int i = 0; i < iv.length;i++){
//                System.out.println(iv[i]);
//            }
//            
//            IV = new IvParameterSpec(iv);
//            
            
            

            //if it fails for whatever reason, it will output an error message in the system output field.
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return message;
    }
}
