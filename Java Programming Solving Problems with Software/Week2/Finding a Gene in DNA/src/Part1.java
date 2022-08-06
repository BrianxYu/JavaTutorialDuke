//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Part1 {
    public Part1() {
    }

    public String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        } else {
            int endIndex = dna.indexOf("TAA", startIndex + 3);
            if (endIndex == -1) {
                return "";
            } else {
                return (endIndex - startIndex) % 3 == 0 ? dna.substring(startIndex, endIndex + 3) : "";
            }
        }
    }

    public void testSimpleGene() {
        String dna1 = "TAAAAA";
        String gene1 = this.findSimpleGene(dna1);
        System.out.println("dna1 = " + dna1);
        System.out.println("gene1 = " + gene1);
        String dna2 = "AGTTTTT";
        String gene2 = this.findSimpleGene(dna2);
        System.out.println("dna2 = " + dna2);
        System.out.println("gene2 = " + gene2);
        String dna3 = "GGGGTTTT";
        String gene3 = this.findSimpleGene(dna3);
        System.out.println("dna3 = " + dna3);
        System.out.println("gene3 = " + gene3);
        String dna4 = "ATGTTTTAA";
        String gene4 = this.findSimpleGene(dna4);
        System.out.println("dna4 = " + dna4);
        System.out.println("gene4 = " + gene4);
        String dna5 = "AAATGCCCTAACTAGATTAAGAAACC";
        String gene5 = this.findSimpleGene(dna5);
        System.out.println("dna5 = " + dna5);
        System.out.println("gene5 = " + gene5);
    }

    public static void main(String[] args) {
        Part1 pt1 = new Part1();
        pt1.testSimpleGene();
    }
}
