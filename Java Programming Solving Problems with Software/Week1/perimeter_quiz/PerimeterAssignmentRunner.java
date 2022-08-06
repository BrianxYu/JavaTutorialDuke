import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count = 0;
        for (Point pt : s.getPoints()) {
            count += 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        double totalPerim = getPerimeter(s);
        int count = getNumPoints(s);
        double averageLength = totalPerim / (double)count;
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > largestSide)
                largestSide = currDist;
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = s.getLastPoint().getX();
        for (Point currPt : s.getPoints()) {
            double currentX = currPt.getX();
            if (currentX > largestX)
                largestX = currentX;

        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largest_peri = 0.0;

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double peri = getPerimeter(s);
            if (peri > largest_peri)
                largest_peri = peri;
        }
        return largest_peri;
    }

    public String getFileWithLargestPerimeter() {
        File temp = null;    // replace this code
        double largest_peri = 0.0;

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double peri = getPerimeter(s);
            if (peri > largest_peri) {
                temp = f;
                largest_peri = peri;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int count = getNumPoints(s);
        System.out.println("number of points = " + count);
        double avg = getAverageLength(s);
        System.out.println("average length = " + avg);
        double largest = getLargestSide(s);
        System.out.println("largest side = " + largest);
        double largestX = getLargestX(s);
        System.out.println("largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largest_pari = getLargestPerimeterMultipleFiles();
        System.out.println("Largest parimeter = " + largest_pari);
    }

    public void testFileWithLargestPerimeter() {
        String filename = getFileWithLargestPerimeter();
        System.out.println("File name with largest perimeter = " + filename);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testFileWithLargestPerimeter();
    }
}
