
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            boolean isLower = Character.isLowerCase(currChar);            
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1) {
                char newChar;
                if (i % 2 == 0)
                    newChar = shiftedAlphabet1.charAt(idx);
                else
                    newChar = shiftedAlphabet2.charAt(idx);
                if (isLower)
                    newChar = Character.toLowerCase(newChar);
                encrypted.setCharAt(i, newChar);
            }
        }
        //System.out.println("Message is encryped with key1 = " + mainKey1 + " and key2 = " + mainKey2);
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        //System.out.println("Message is decrypted with key1 = " + (26 - mainKey1) + " and key2 = " + (26 - mainKey2));
        return cct.encrypt(input);
    }
}
