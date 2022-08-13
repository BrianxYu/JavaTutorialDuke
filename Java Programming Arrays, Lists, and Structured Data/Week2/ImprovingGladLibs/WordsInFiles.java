

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    
    public WordsInFiles() {
        map = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        String filename = f.getName();
        FileResource fr = new FileResource(f);
        for (String word: fr.words()) {
            if (map.containsKey(word)) {
                ArrayList<String> list = map.get(word);
                if (list.indexOf(filename) == -1)
                    list.add(filename);
            }
            else {
                ArrayList<String> list = new ArrayList();
                list.add(filename);
                map.put(word, list);
            }
        }
    }
    
    private void buildWordFileMap() {
        map.clear();
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int maxCount = 0;
        for (String key : map.keySet()) {
            int currCount = map.get(key).size();
            if (currCount > maxCount)
                maxCount = currCount;
        }
        return maxCount;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> list = new ArrayList<String>();
        for (String key : map.keySet()) {
            int currCount = map.get(key).size();
            if (currCount == number)
                list.add(key);
        }
        return list;
    }
    
    private void printFilesIn(String word) {
        ArrayList<String> list = map.get(word);
        for (String filename : list) {
            System.out.println(filename);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int maxNumOfFiles = maxNumber();
        //int maxNumOfFiles = 4;
        System.out.print("The greatest number of files a word appears in is " + maxNumOfFiles + ", ");
        ArrayList<String> wordsInMaxNumOfFiles = wordsInNumFiles(maxNumOfFiles);
        System.out.print("and there are " + wordsInMaxNumOfFiles.size() + " such words\n");

        for (String word : wordsInMaxNumOfFiles) {
            System.out.print("\"" + word + "\" appears in the files: ");
            ArrayList<String> filenames = map.get(word);
            for (String filename : filenames) {
                System.out.print(filename + " ");
            }
            System.out.println();
        }
        
        String targetWord = "sea";
        System.out.println("\nThe word " + targetWord + " appeared in the following files:");
        printFilesIn(targetWord);
    }
    
}
