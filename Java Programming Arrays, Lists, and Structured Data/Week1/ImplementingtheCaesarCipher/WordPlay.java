
import edu.duke.*;

public class WordPlay {
    public boolean isVowel(char ch) {
        String vowels = "aeiou";
        int idx = vowels.indexOf(Character.toLowerCase(ch));
        if (idx == -1) // if not found, is not vowel
            return false;
        return true;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = phrase.charAt(i);
            if (isVowel(currChar))
                sb.setCharAt(i, ch);
        }
        return sb.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = phrase.charAt(i);
            if (currChar == ch) {
                if (i % 2 == 0)
                    sb.setCharAt(i,'*');
                else
                    sb.setCharAt(i, '+');
            }
        }  
        return sb.toString();
    }
}
