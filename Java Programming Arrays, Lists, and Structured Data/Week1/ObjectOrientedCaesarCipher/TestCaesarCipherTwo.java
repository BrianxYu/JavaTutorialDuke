
import edu.duke.*;

public class TestCaesarCipherTwo {
    private int[] countLetters(String s) {
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
    
    private int maxIndex(int[] list) {
        int mIndex = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] > list[mIndex])
                mIndex = i;
        }
        return mIndex;
    }
    
    private int getKey(String s) {
        int[] counts = countLetters(s);
        int mIndex = maxIndex(counts);
        int key = mIndex - 4; // assuming the largest value is 'e' so shift everything by 4
        if (key < 0)
            key += 26;
        return key;        
    }
    
    private String halfOfString(String message, int start) {
        String newMessage = "";
        for (int i = start; i < message.length(); i+=2) {
            newMessage += message.charAt(i);
        }
        return newMessage;
    }
    
    public void simpleTest() {
        System.out.println("Testing CaesarCipherTwo Class\n\n");
        
        FileResource fr = new FileResource();
        CaesarCipherTwo cct = new CaesarCipherTwo(17, 3);
        String encrypted = cct.encrypt(fr.asString());
        System.out.println("Encrypted message: " + encrypted);
        String decrypted = cct.decrypt(encrypted);
        System.out.println("Decrypted message: " + decrypted);
        
        System.out.println("Testing breakCaesarCipher Method\n\n");
        decrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted message: " + breakCaesarCipher(encrypted));
    }
    
    public String breakCaesarCipher(String input) {
        int key1 = getKey(halfOfString(input, 0));
        int key2 = getKey(halfOfString(input, 1));
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - key1, 26 - key2);
        System.out.println("Original encrypted Key are: " + key1 + " and " + key2);
        return cct.encrypt(input);
    }
}
