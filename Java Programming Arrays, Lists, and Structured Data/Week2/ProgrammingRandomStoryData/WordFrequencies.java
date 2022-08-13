
import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        for(String word : fr.words()) {
            word = word.toLowerCase();
            int idx = myWords.indexOf(word);
            if (idx == -1){
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int val = myFreqs.get(idx);
                myFreqs.set(idx, val+1);
            }
        }
    }
    
    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
        }
        int mostFreqIndex = findIndexOfMax();
        System.out.println("Most frequent word is \"" + 
                            myWords.get(mostFreqIndex) + "\" which appeared " +
                            myFreqs.get(mostFreqIndex) + " times");
    }
    
    public int findIndexOfMax() {
        int maxValue = 0;
        int maxIndex = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            int currValue = myFreqs.get(i);
            if (currValue > maxValue) {
                maxValue = currValue;
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
