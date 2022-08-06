import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0)
                return currIndex;
            else
                currIndex = dna.indexOf(stopCodon, currIndex+1);
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1)
            return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");     
        
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        if (minIndex == dna.length())
            return "";
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty())
                break;
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }
    
    public int countOccurance(String dna, String target) {
        int counter = 0;
        int startIndex = 0;
        while (true) {
            int currIndex = dna.indexOf(target, startIndex);
            if (currIndex == -1)
                break;
            counter++;
            startIndex = currIndex + target.length();
        }
        return counter;
    }
    
    public double cgRatio(String dna) {
        int cCount = countOccurance(dna, "C");
        int gCount = countOccurance(dna, "G");
        double ratio = (double)(cCount + gCount) / dna.length();
        return ratio;
    }
    
    public int countCTG(String dna) {
        return countOccurance(dna, "CTG");
    }
    
    public void testRatio() {
        System.out.println(cgRatio("CCCGGG"));
        System.out.println(cgRatio("CCCAAA"));
        System.out.println(cgRatio("AAAAAA"));
        
        System.out.println(countCTG("AAAAAA"));
        System.out.println(countCTG("CTGAAA"));
        System.out.println(countCTG("CTGACTG"));
    }
    
    public void processGenes(StorageResource sr) {
        StorageResource geneAbove60List = new StorageResource();
        int geneAbove60 = 0;
        
        StorageResource geneAbovePoint35List = new StorageResource();
        int geneAbovePoint35 = 0;
        
        int lengthOfLongestGene = 0;
        for (String s: sr.data()) {
            if (s.length() > lengthOfLongestGene) {
                lengthOfLongestGene = s.length();
            }
            if (s.length() > 60) {
                geneAbove60List.add(s);
                geneAbove60++;
            }
            if (cgRatio(s) > 0.35) {
                geneAbovePoint35List.add(s); 
                geneAbovePoint35++;
            }
            
        }
        System.out.println("Number of string larger than 60: " + geneAbove60);
        for (String s: geneAbove60List.data())
            System.out.println("\t" + s);
        System.out.println("Number of string whose CG ratio higher than 0.35: " + geneAbovePoint35); 
        for (String s: geneAbovePoint35List.data())
            System.out.println("\t" + s);
        System.out.println("Length of the longest gene: " + lengthOfLongestGene); 
    }
        
    public void testProcessGenes() {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println(dna);
        StorageResource sr = getAllGenes(dna);
        System.out.println("Number of sr: " + sr.size());
        for (String s: sr.data())
            System.out.println(s);
        processGenes(sr);
    }
    
    public void testProcessGenesFromFile() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println("Original DNA: " + dna);
        StorageResource geneList = getAllGenes(dna);
        System.out.println("Number of gene from this dna: " + geneList.size());
        for (String s: geneList.data())
            System.out.println("\t" + s);
        processGenes(geneList);
        System.out.println("Number of times CTG appear in this DNA: " + countCTG(dna)); 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
