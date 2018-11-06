import edu.princeton.cs.algs4.StdDraw;

public class Testclient {
    public static void main(String[] args) {

        // read the n points from a file
//        In in = new In(args[0]);
        String datapath = "collinear/input6.txt";
        In in = new In(datapath);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-10000, 32768);
        StdDraw.setYscale(-10000, 32768);
        StdDraw.setPenRadius(0.02);
        for (Point p : points) {
            p.draw();
            System.out.println(p);
        }
        StdDraw.show();

        // print and draw the line segments based on BruteCollinearPoints
//        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
//        System.out.println(collinear.numberOfSegments());


        // print and draw the line segments based on FastCollinearPoints
//        FastCollinearPoints collinear = new FastCollinearPoints(points);
//
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();

    }
}
