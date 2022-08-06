public class FindGeneWhile {

    private static int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0)
                return currIndex;
            else
                currIndex = dna.indexOf(stopCodon, currIndex+1);
        }
        return dna.length();
    }

    public static String findGene (String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)
            return "";

        // find the index of the first stop condon
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);

        if (minIndex == dna.length()) // if none of the three stopCodons is found
            return "";
        return dna.substring(startIndex, minIndex+3); // otherwise, return the closest one
    }
}