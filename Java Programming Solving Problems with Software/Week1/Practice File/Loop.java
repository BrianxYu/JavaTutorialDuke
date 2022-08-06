import edu.duke.FileResource;

public class HelloWorld {
    public void runHello() {
        FileResource f = new FileResource("file.txt");
        for (String line : f.lines()) {
            System.out.println(line);
        }
    }

    public static void main (String[]) {
        HelloWorld hw = new HelloWorld();
        hw.runHello();
    }
}