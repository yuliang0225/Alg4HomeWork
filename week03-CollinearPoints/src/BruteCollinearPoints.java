import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private int N;
    private int segmentsCount;
    private ArrayList<LineSegment> segmentsSave = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points){
        if (points == null){
            throw new java.lang.IllegalArgumentException();
        }
        N = points.length;
        segmentsCount = 0;
    // sort the input points
        Arrays.sort(points);
        for (int i = 0; i <= N-4; i += 1){
            for (int j = i+1; j <= N-3; j += 1){
                // get the slop between point-i and point-j
                double kij = points[i].slopeTo(points[j]);
                for (int m = j +1; m <= N-2; m += 1){
                    // get the slop between point-i and point-m
                    double kim = points[i].slopeTo(points[m]);
                    // if they are not equaled, continue
                    if (kij != kim){
                        continue;
                    }
                    for (int n = m + 1; n <= N-1; n += 1){
                        //get the slop between point-i and point-n
                        double kin = points[i].slopeTo(points[n]);
                        if (kij != kin){
                            continue;
                        }
                        // count the segments
                        segmentsCount += 1;
                        // add the first point and last point
                        segmentsSave.add(new LineSegment(points[i],points[n]));
                    }
                }
            }
        }

    }

    // the number of line segments
    public int numberOfSegments(){
        return segmentsCount;
    }
//
    // the line segments
    public LineSegment[] segments(){
        return segmentsSave.toArray(new LineSegment[segmentsCount]);
    }
}
