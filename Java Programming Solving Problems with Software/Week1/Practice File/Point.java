public class Point {
    private int x;
    private int y;
    public Point(int startx, int starty) {
        x = startx;
        y = starty;
    }
    public int getx() { return x; }
    public int gety() { return y; }
    public double distance(Point otherPt) {
        int dx = x - otherPt.getx();
        int dy = y - otherPt.gety();
        return Math.sqrt(dx * dx + dy * dy);
    }
    public static void main (String[] args) { // static method belongs to the class object, not individual object
        Point p1 = new Point(3, 4);
        Point p2 = new Point(6, 8);
        System.out.println(p1.distance(p20));
    }
}
