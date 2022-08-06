//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Part2 {
    public Part2() {
    }

    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        int startIndexUpper = dna.indexOf(startCodon.toUpperCase());
        int startIndexLower;
        if (startIndexUpper == -1) {
            startIndexLower = dna.indexOf(startCodon.toLowerCase());
            if (startIndexLower == -1) {
                return "";
            } else {
                int endIndexLower = dna.indexOf(stopCodon.toLowerCase(), startIndexLower + 3);
                if (endIndexLower == -1) {
                    return "";
                } else {
                    return (endIndexLower - startIndexLower) % 3 == 0 ? dna.substring(startIndexLower, endIndexLower + 3) : "";
                }
            }
        } else {
            startIndexLower = dna.indexOf(stopCodon.toUpperCase(), startIndexUpper + 3);
            if (startIndexLower == -1) {
                return "";
            } else {
                return (startIndexLower - startIndexUpper) % 3 == 0 ? dna.substring(startIndexUpper, startIndexLower + 3) : "";
            }
        }
    }

    public void testSimpleGene() {
        String dna1 = "TAAAAA";
        String gene1 = this.findSimpleGene(dna1, "ATG", "TAA");
        System.out.println("dna1 = " + dna1);
        System.out.println("gene1 = " + gene1);
        String dna2 = "AGTTTTT";
        String gene2 = this.findSimpleGene(dna2, "ATG", "TAA");
        System.out.println("dna2 = " + dna2);
        System.out.println("gene2 = " + gene2);
        String dna3 = "GGGGTTTT";
        String gene3 = this.findSimpleGene(dna3, "ATG", "TAA");
        System.out.println("dna3 = " + dna3);
        System.out.println("gene3 = " + gene3);
        String dna4 = "ATGTTTTAA";
        String gene4 = this.findSimpleGene(dna4, "ATG", "TAA");
        System.out.println("dna4 = " + dna4);
        System.out.println("gene4 = " + gene4);
        String dna5 = "atggggtaa";
        String gene5 = this.findSimpleGene(dna5, "ATG", "TAA");
        System.out.println("dna5 = " + dna5);
        System.out.println("gene5 = " + gene5);
    }

    public static void main(String[] args) {
        Part2 pt2 = new Part2();
        pt2.testSimpleGene();
    }
}
