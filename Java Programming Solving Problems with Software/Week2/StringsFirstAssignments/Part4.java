import edu.duke.*;

public class Part4 {
    public void readURL() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String word : ur.words()) {
            int index = word.toLowerCase().indexOf("youtube.com");
            if (index == -1)
                continue;
            int beginIndex = word.lastIndexOf("\"", index);
            if (beginIndex == -1)
                continue;
            int endIndex = word.indexOf("\"", index + "youtube.com".length());
            if (endIndex == -1)
                continue;
            System.out.println(word.substring(beginIndex, endIndex+1));
        }
    }
    
    public static void main(String[] args) {
        Part4 p4 = new Part4();
        p4.readURL();
    }
}
