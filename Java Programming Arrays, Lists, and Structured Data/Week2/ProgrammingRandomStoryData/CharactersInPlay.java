
import java.util.ArrayList;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    private void update(String person) {
        person = person.toLowerCase();
        int idx = names.indexOf(person);
        if (idx == -1) {
            names.add(person);
            counts.add(1);
        } else {
            int val = counts.get(idx);
            counts.set(idx, val+1);
        }
    }
    
    public void findAllCharacters() {
        names.clear();
        counts.clear();
        
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int idx = line.indexOf(".");
            if (idx != -1) {
                String person = line.substring(0, idx);
                update(person);
            }
        }        
    }

    public int findIndexOfMax() {
        int maxValue = 0;
        int maxIndex = 0;
        for (int i = 0; i < counts.size(); i++) {
            int currValue = counts.get(i);
            if (currValue > maxValue) {
                maxValue = currValue;
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester() {
        findAllCharacters();
        
        int maxIdx = findIndexOfMax();
        System.out.println(names.get(maxIdx) + " appeared the most with " + counts.get(maxIdx) + " times");
        
        for(int i = 0; i < names.size(); i++) {
            int freq = counts.get(i);
            if (freq > 2) {
               System.out.println(names.get(i) + " appeared " + 
                                  freq + " times.");
            }
        }
        
        int num1 = 10;
        int num2 = 15;
        System.out.println("\ncharacters with at least " + num1 + " parts but no more than " + num2 + " parts:");
        charactersWithNumParts(num1, num2);
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for(int i = 0; i < names.size(); i++) {
            int freq = counts.get(i);
            if (freq >= num1 && freq <= num2) {
                System.out.println(names.get(i) + " appeared " + 
                                   freq + " times.");
            }
        }
    }
}
