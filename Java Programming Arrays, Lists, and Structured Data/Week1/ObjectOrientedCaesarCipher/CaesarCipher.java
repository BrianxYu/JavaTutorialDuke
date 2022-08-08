
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            boolean isLower = Character.isLowerCase(currChar);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (isLower)
                    newChar = Character.toLowerCase(newChar);
                encrypted.setCharAt(i, newChar);
            }
        }
        //System.out.println("Message is encryped with key = " + mainKey);
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey); // another object with the key as the reverse of the current object to decrypt the message
        //System.out.println("Message is Decryped with key = " + (26 - mainKey));
        return cc.encrypt(input);
    }
}
