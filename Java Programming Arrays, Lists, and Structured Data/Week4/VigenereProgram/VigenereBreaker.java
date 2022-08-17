import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    private String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    private int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++) {
            CaesarCracker cc = new CaesarCracker(mostCommon);
            String slice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slice);
        }
        return key;
    }
    
    private HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> words = new HashSet<String>();
        for(String word : fr.lines()) {
            words.add(word.toLowerCase());
        }
        return words;
    }
    
    private int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase()))
                count++;
        }
        return count;
    }
    
    private String breakForLanguage(String encrypted, HashSet<String> dictionary) {       
        String decrypted = null;
        int mostRealWordCount = 0;
        int keyLength = 0;
        
        char mostCommon = mostCommonCharIn(dictionary);
        
        for (int i = 1; i <= 100; i++) {
            VigenereCipher cv = new VigenereCipher(tryKeyLength(encrypted, i, mostCommon));
            String currDecrypted = cv.decrypt(encrypted);
            int realWordCount = countWords(currDecrypted, dictionary);
            if (realWordCount > mostRealWordCount) {
                mostRealWordCount = realWordCount;
                decrypted = currDecrypted;
                keyLength = i;
            }
        }
        System.out.println("Final Key Length: " + keyLength + " Number of real words: " + mostRealWordCount);
        return decrypted;
    }
    
    private char mostCommonCharIn(HashSet<String> dictionary) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                int idx = alph.indexOf(Character.toLowerCase(word.charAt(i)));
                if (idx != -1)
                    counts[idx] += 1;
            }
        }
        
        int mostCount = 0;
        int mostCountIndex = 0;
        
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > mostCount) {
                mostCount = counts[i];
                mostCountIndex = i;
            }        
        }
        return alph.charAt(mostCountIndex);       
    }
    
    private void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        String language = null;
        String decrypted = null;
        int numRealWord = 0;
        
        for (String currLanguage : languages.keySet()) {
            HashSet<String> dictionary = languages.get(currLanguage);
            System.out.print("Language: " + currLanguage + " ");
            String currDecrypted = breakForLanguage(encrypted, dictionary);
            int currNumRealWord = countWords(currDecrypted, dictionary);
            
            if (currNumRealWord > numRealWord) {
                language = currLanguage;
                decrypted = currDecrypted;
                numRealWord = currNumRealWord;            
            }
        }
        
        System.out.println("decrypted Messages:");
        System.out.println(decrypted);
        System.out.println("decrypted Language:");
        System.out.println(language);
    }
    
    private HashMap<String, HashSet<String>> readAllDictionaries(DirectoryResource dr) {
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for (File f : dr.selectedFiles()) {
            HashSet<String> words = readDictionary(new FileResource(f));
            languages.put(f.getName(), words);            
        }
        return languages;
    }
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        DirectoryResource dr = new DirectoryResource();
        HashMap<String,HashSet<String>> languages = readAllDictionaries(dr);
        
        breakForAllLangs(encrypted, languages);     
    }
}
