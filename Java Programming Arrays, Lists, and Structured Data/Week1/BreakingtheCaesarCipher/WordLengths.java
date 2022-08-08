
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for(String word: resource.words()) {
            int wordLength = word.length();
            if (wordLength == 0)
                continue;
            if (!Character.isLetter(word.charAt(0)))
                wordLength--;
            if (!Character.isLetter(word.charAt(word.length() - 1)))
                wordLength--;
            
            if(wordLength >= 0) {
                if (wordLength >= counts.length)
                    counts[counts.length -1]++;
                else
                    counts[wordLength]++;
            }    
        }
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        
        for(int i = 0; i < counts.length; i++) {
            System.out.println(i + "\t" + counts[i]);
        }
    }
}
