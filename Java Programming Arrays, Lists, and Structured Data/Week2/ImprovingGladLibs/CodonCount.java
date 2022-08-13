
import java.util.HashMap;
import edu.duke.*;

public class CodonCount {
    private HashMap<String,Integer> counts;
    
    public CodonCount() {
        counts = new HashMap<String,Integer>(); 
    }
    
    private void buildCodonMap(int start, String dna) {
        counts.clear();
        
        for (int i = start; i < dna.length()-3; i+=3) {
            String currCodon = dna.substring(i, i+3);
            if (counts.containsKey(currCodon))
                counts.put(currCodon, counts.get(currCodon)+1);
            else
                counts.put(currCodon, 1);
        }
    }
    
    private String getMostCommonCodon() {
        int mostCommonCount = 0;
        String mostCommon = null;
        for (String key: counts.keySet()) {
            int currCount = counts.get(key);
            if (mostCommon == null || currCount > mostCommonCount) {
                mostCommonCount = currCount;
                mostCommon = key;
            } 
        }
        return mostCommon;
    }
    
    private void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " + start + 
                           " and " + end + " inclusive are:");
        for (String key: counts.keySet()) {
            int currCount = counts.get(key);
            if (currCount >= start && currCount <= end)
                System.out.println(key + "\t" + currCount);
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim().toUpperCase();
        
        for (int i = 0; i < 3; i++) {
            buildCodonMap(i, dna);
            String mostCommon = getMostCommonCodon();
            System.out.println("Reading frame starting with " +  i + 
                               " results in " + counts.size() + 
                               " unique codons");
                               
            System.out.println(" and most common codon is " + mostCommon + 
                               " with count " + counts.get(mostCommon));
                               
            printCodonCounts(4, 10);
            System.out.println("\n");
        }
    }
}
