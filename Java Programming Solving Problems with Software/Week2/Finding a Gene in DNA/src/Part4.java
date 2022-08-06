//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import edu.duke.URLResource;
import java.util.Iterator;

public class Part4 {
    public Part4() {
    }

    public void readURL() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        Iterator var2 = ur.words().iterator();

        while(var2.hasNext()) {
            String word = (String)var2.next();
            int index = word.toLowerCase().indexOf("youtube.com");
            if (index != -1) {
                int beginIndex = word.lastIndexOf("\"", index);
                if (beginIndex != -1) {
                    int endIndex = word.indexOf("\"", index + "youtube.com".length());
                    if (endIndex != -1) {
                        System.out.println(word.substring(beginIndex, endIndex + 1));
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        Part4 p4 = new Part4();
        p4.readURL();
    }
}
