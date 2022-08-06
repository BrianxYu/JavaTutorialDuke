
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
            int startIndexUpper = dna.indexOf(startCodon.toUpperCase());
            if (startIndexUpper == -1) {
                int startIndexLower = dna.indexOf(startCodon.toLowerCase());
                if (startIndexLower == -1)
                    return "";
                else {
                    int endIndexLower = dna.indexOf(stopCodon.toLowerCase(), startIndexLower + 3);
                    if (endIndexLower == -1)
                        return "";
                    else
                        if ((endIndexLower - startIndexLower) % 3 == 0)
                            return dna.substring(startIndexLower, endIndexLower + 3);
                        else
                            return "";
                }
            }
            else {
                int endIndexUpper = dna.indexOf(stopCodon.toUpperCase(), startIndexUpper + 3);
                if (endIndexUpper == -1)
                    return "";
                else
                    if ((endIndexUpper - startIndexUpper) % 3 == 0)
                        return dna.substring(startIndexUpper, endIndexUpper + 3);
                    else
                        return "";
            }
        }

        public void testSimpleGene() {
            String dna1 = "TAAAAA"; // NO ATG
            String gene1 = findSimpleGene(dna1, "ATG", "TAA");
            System.out.println("dna1 = " + dna1);
            System.out.println("gene1 = " + gene1);
            String dna2 = "AGTTTTT"; // NO TAA
            String gene2 = findSimpleGene(dna2, "ATG", "TAA");
            System.out.println("dna2 = " + dna2);
            System.out.println("gene2 = " + gene2);
            String dna3 = "GGGGTTTT"; // NO ATG & TAA
            String gene3 = findSimpleGene(dna3, "ATG", "TAA");
            System.out.println("dna3 = " + dna3);
            System.out.println("gene3 = " + gene3);
            String dna4 = "ATGTTTTAA";
            String gene4 = findSimpleGene(dna4, "ATG", "TAA");
            System.out.println("dna4 = " + dna4);
            System.out.println("gene4 = " + gene4);
            String dna5 = "atggggtaa";
            String gene5 = findSimpleGene(dna5, "ATG", "TAA");
            System.out.println("dna5 = " + dna5);
            System.out.println("gene5 = " + gene5);
        }
        
        public static void main (String[] args) {
            Part2 pt2 = new Part2();
            pt2.testSimpleGene();
        }
}
