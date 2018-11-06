import java.util.ArrayList;
import java.util.Arrays;


public class FastCollinearPoints {
    private int N;
    private Point[] pointsCopy;
    private int index;
    private ArrayList<LineSegment> segmentsSave = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points){
        if (points == null){
            throw new java.lang.IllegalArgumentException();
        }
        N = points.length;

        pointsCopy = points.clone();
        // Sort the points
        Arrays.sort(pointsCopy);

        // Check the repeat points
        if (checkRepeatPoints(pointsCopy)){
            throw new java.lang.IllegalArgumentException();
        }

        Point[] otherPoints = new Point[N-1];
        // Calculate the slopes between all points
        for (int i = 0; i < N-1; i += 1){
            index = 0;
            for (int j = 0; j < N; j += 1){
                if (i==j){
                    continue;
                }
                otherPoints[index++] = pointsCopy[j];
            }
            // sort the points based on the slopes between point-i and other points
            Arrays.sort(otherPoints,pointsCopy[i].slopeOrder());

            // count the number of collinearPoints based on point-i

            int count = 2;
            for (int k = 1; k < otherPoints.length; k += 1){
                double k1 = pointsCopy[i].slopeTo(otherPoints[k-1]);
                double k2 = pointsCopy[i].slopeTo(otherPoints[k]);

                if (k1 == k2){
                    count += 1;
                    if (k == otherPoints.length-1){
                        if (count >= 4 && pointsCopy[i].compareTo(otherPoints[k-count+2])==-1){
                            Point start = pointsCopy[i];
                            Point end = otherPoints[k];
                            segmentsSave.add(new LineSegment(start,end));
                        }
                    }
                }
                else{
                    if (count >= 4 && pointsCopy[i].compareTo(otherPoints[k-count+1])==-1){
                        Point start = pointsCopy[i];
                        Point end = otherPoints[k-1];
                        segmentsSave.add(new LineSegment(start,end));
                    }
                    count=2;
                }
            }
        }
    }


    // check the repeat points
    private boolean checkRepeatPoints (Point[] p){
        for (int i = 1; i < p.length; i += 1){
            if (p[i] == p[i-1] || p[i] == null || p[i-1] == null){
                return true;
            }
        }
        return false;
    }




    // the number of line segments
    public int numberOfSegments(){
        return segmentsSave.size();

    }

    // the line segments
    public LineSegment[] segments(){
        LineSegment[] segments = new LineSegment[segmentsSave.size()];
        int index=0;
        for(LineSegment Line : segmentsSave) {
            segments[index++] = Line;
        }
        return segments;
    }
}
