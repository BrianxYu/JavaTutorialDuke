public class Main {

    public static void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    }
    public static void main(String[] args) {
//        String dna = "AATGCGTAATTAATCG";
//        System.out.println(FindGeneWhile.findGene(dna));

    }
}
