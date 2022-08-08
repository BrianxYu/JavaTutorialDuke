import edu.duke.*;
import java.io.*;

public class CaesarBreaker {
    public int[] countLetters(String s) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = Character.toLowerCase(s.charAt(i));
            int index = alphabet.indexOf(ch);
            if (index != -1) {
                counters[index]++;
            }
        }
        return counters;
    }
    
    public int maxIndex(int[] list) {
        int mIndex = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] > list[mIndex])
                mIndex = i;
        }
        return mIndex;
    }
    
    public void testCountLetters() {
        int[] counts = countLetters("abracadabra");
        for (int i = 0; i < counts.length; i++)
            System.out.println(i + "\t" + counts[i]);
        System.out.println("Most frequent letter is at index " + maxIndex(counts));
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int decryptKey = getKey(encrypted);
        return cc.encrypt(encrypted, 26 - decryptKey);
    
    }
    
    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        String encripted = cc.encrypt("Write a testDecrypt method in the CaesarBreaker class that prints the decrypted message, and make sure it works for encrypted messages that were encrypted with one key.", 12);
        String decripted = decrypt(encripted);
        System.out.println("Encripted: " + encripted);
        System.out.println("Decripted: " + decripted);        
    }
    
    public String halfOfString(String message, int start) {
        String newMessage = message.substring(start); // truncate anything before start index
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newMessage.length(); i++) { 
            if (i % 2 == 0) // append every other char to stringbuilder
                sb.append(newMessage.charAt(i));
        }
        return sb.toString();
    }
    
    public int getKey(String s) {
        int[] counts = countLetters(s);
        int mIndex = maxIndex(counts);
        int key = mIndex - 4; // assuming the largest value is 'e' so shift everything by 4
        if (key < 0)
            key += 26;
        return key;        
    }
    
    public String decryptTwoKeys(String s) {
        String firstPart = halfOfString(s, 0);
        String secondPart = halfOfString(s, 1);
        int key1 = getKey(firstPart);
        int key2 = getKey(secondPart);
        
        System.out.println("Key1: " + key1 + " Key2: " + key2);
        
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(s, 26 - key1, 26 - key2);
        return decrypted;
    }
    
    public void testDecryptTwoKeys() {
        CaesarCipher cc = new CaesarCipher();
        //String encripted = cc.encryptTwoKeys("Write a testDecrypt method in the CaesarBreaker class that prints the decrypted message, and make sure it works for encrypted messages that were encrypted with one key.", 12, 24);    
        //String decripted = decryptTwoKeys(encripted);
        String encripted = "Top ncmy qkff vi vguv vbg ycpx";
        String decripted = cc.encryptTwoKeys(encripted, 26-2, 26-20);
        System.out.println("Encripted: " + encripted);
        System.out.println("Decripted: " + decripted); 
        
        encripted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        decripted = decryptTwoKeys(encripted);
        System.out.println("Encripted: " + encripted);
        System.out.println("Decripted: " + decripted); 
        
        FileResource fr = new FileResource();
        encripted = fr.asString();
        decripted = decryptTwoKeys(encripted);
        System.out.println("Encripted: " + encripted);
        System.out.println("Decripted: " + decripted);
    }
}
