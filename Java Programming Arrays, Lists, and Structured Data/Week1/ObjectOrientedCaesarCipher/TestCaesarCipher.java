
import edu.duke.*;

public class TestCaesarCipher {
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
    
    public void simpleTests() {
        System.out.println("Testing CaesarCipher Class\n\n");
        
        FileResource fr = new FileResource();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(fr.asString());
        System.out.println("Encrypted message: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted message: " + decrypted);
        
        System.out.println("Testing breakCaesarCipher Method\n\n");
        decrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted message: " + decrypted);
        
    } 
    
    public String breakCaesarCipher(String input) {
        int key = getKey(input);
        CaesarCipher cc = new CaesarCipher(26 - key);
        return cc.encrypt(input);
    }
}
