public class Part3 {
    public Part3() {
    }

    public boolean twoOccurrences(String stringa, String stringb) {
        int firstIndex = stringb.indexOf(stringa);
        if (firstIndex == -1) {
            return false;
        } else {
            int secondIndex = stringb.indexOf(stringa, firstIndex + stringa.length());
            return secondIndex != -1;
        }
    }

    public String lastPart(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        return index == -1 ? stringb : stringb.substring(index + stringa.length(), stringb.length());
    }

    public void testing() {
        System.out.println(this.twoOccurrences("by", "A story by Abby Long"));
        System.out.println(this.twoOccurrences("an", "banana"));
        System.out.println(this.twoOccurrences("zoo", "forest"));
        System.out.println(this.lastPart("an", "banana"));
        System.out.println(this.lastPart("zoo", "forest"));
    }

    public static void main(String[] args) {
        Part3 pt3 = new Part3();
        pt3.testing();
    }
}
