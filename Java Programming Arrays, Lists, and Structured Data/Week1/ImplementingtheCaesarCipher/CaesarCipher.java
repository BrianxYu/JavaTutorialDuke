
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean isLower = Character.isLowerCase(currChar);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (isLower)
                    newChar = Character.toLowerCase(newChar);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);        
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String encrypted1 = encrypt(input, key1);
        String encrypted2 = encrypt(input, key2);
        for (int i = 0; i < input.length(); i++) {        
            if (i % 2 == 0)
                encrypted.setCharAt(i, encrypted1.charAt(i));
            else
                encrypted.setCharAt(i, encrypted2.charAt(i));
        }
        return encrypted.toString();
    }
}
