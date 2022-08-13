
import java.util.*;
import edu.duke.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    private ArrayList<String> usedWords;   
    private ArrayList<String> usedLabels;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedLabels = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedLabels = new ArrayList<String>();
    }

    private void initializeFromSource(String source) {       
        String[] labels = {"country", "noun", "animal", "adjective", "name", "color",
                           "timeframe", "verb", "fruit"};
        
        for (String s : labels) {
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        
        if (myMap.containsKey(label))
            return randomFrom(myMap.get(label));
        if (label.equals("number"))
            return ""+myRandom.nextInt(50)+5;
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub;
        while (true) {
            String label = w.substring(first+1,last);
            sub = getSubstitute(label);
            if (!sub.equals("**UNKNOWN**") && usedLabels.indexOf(label) == -1)
                usedLabels.add(label);
            int idx = usedWords.indexOf(sub);
            if (idx == -1) {
                usedWords.add(sub);
                break;
            }
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory() {
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        
        //System.out.println("Used words:");
        //for (String s:usedWords)
        //    System.out.println(s);
        
        System.out.println();
        int totalNumWords = totalWordsInMap();
        System.out.println("There were " + totalNumWords + " words to choose from all labels");
        int totalNumWordsFromLabel = totalWordsConsidered();
        System.out.println("There were " + totalNumWordsFromLabel + " words to choose from all labels appeared in this template");
    }
    
    private int totalWordsInMap() {
        int total = 0;
        for (String key : myMap.keySet()) {
            total += myMap.get(key).size();
        }
        return total;
    }
    
    private int totalWordsConsidered() {
        int total = 0;
        for (String key : usedLabels) {
            if (key.equals("number"))
                continue;
            total += myMap.get(key).size();
        }
        return total;
    }
}
