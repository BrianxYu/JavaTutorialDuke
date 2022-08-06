
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int firstIndex = stringb.indexOf(stringa);
        if (firstIndex == -1)
            return false;
        int secondIndex = stringb.indexOf(stringa, firstIndex+stringa.length());
        if (secondIndex == -1)
            return false;
        return true;
    }

    public String lastPart(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        if (index == -1)
           return stringb;
        return stringb.substring(index + stringa.length(), stringb.length());
    }
        
    public void testing() {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("an", "banana"));
        System.out.println(twoOccurrences("zoo", "forest"));

        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));

    }
    
    public static void main(String[] args) {
        Part3 pt3 = new Part3();
        pt3.testing();
    }
}
