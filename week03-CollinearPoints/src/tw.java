import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

//much faster than the brute-force solution
public class tw {
    //to store line segments
    private ArrayList<LineSegment> LineSegmentList;
    //to store the given points
    private Point[] points;

    // finds all line segments containing 4 or more points
    public tw(Point[] pointsIn) {
        //三种异常
        //一:if the argument to the constructor is null
        if(pointsIn == null) throw new IllegalArgumentException("there is no point");
        //number of points
        int N=pointsIn.length;
        //二:if any point in the array is null
        for(int i=0;i<N;i++) if(pointsIn[i]==null) throw new IllegalArgumentException("exist null point");
        //三:if the argument to the constructor contains a repeated point
        //检查是否有重复的点,先排序,再查重会方便,作业允许这样: For example, you may use        Arrays.sort()
        //同时有利于后面排除重复线段
        points = new Point[N];
        for(int i=0;i<N;i++) points[i] = pointsIn[i];
        //用的是结合了归并和插入的tim排序,稳定排序
        Arrays.sort(points);
        for(int i=1;i<N;i++) if(points[i-1].compareTo(points[i])==0) throw new IllegalArgumentException("exist repeated point");

        //to save every required line segment
        LineSegmentList = new ArrayList<LineSegment>();


        //当前的参考点
        Point currentCheck;
        //对照点重新存储,不包括参考点,共N-1个
        Point[] otherPoints = new Point[N-1];
        //开始比较斜率,一个一个来
        for (int i=0;i<N;i++) {
            currentCheck = points[i];
            // copy points without Point currentCheck to otherPoints
            for(int j=0;j<N;j++) {
                if(j<i) otherPoints[j] = points[j];
                if(j>i) otherPoints[j-1] = points[j];
            }

            //根据斜率对点排序
            //用的是结合了归并和插入的tim排序,稳定排序
            Arrays.sort(otherPoints,currentCheck.slopeOrder());
            //遍历已经排序的otherPoints找线段
            //注意,归并和插入排序都是稳定的,所以tim排序是稳定的,这非常重要
            //配合Point的compareTo方法,可以直接过滤掉重复线段
            //一开始没太注意compareTo方法,后来发现这个方法能固定住点之间的相对位置,所以可以过滤重复线段
            //两点共线
            int count=2;
            for(int k=1;k<N-1;k++) {
                double k1 = otherPoints[k-1].slopeTo(currentCheck);
                double k2 = otherPoints[k].slopeTo(currentCheck);
                if(k1==k2) {
                    count++;
                    //当循环到最后一个点,同时这个点和前面的点共线
                    if(k==N-2) {
                        //如果4点及以上共线,并且otherPoints中与参考点共线且排在最左边的点比参考点大的话,注意此处是遍历到头,所以索引是k-count+2
                        if(count>=4 && currentCheck.compareTo(otherPoints[k-count+2])==-1) {
                            //线段起点
                            Point start = currentCheck;
                            //线段终点
                            Point end = otherPoints[k];
                            LineSegment linesegment = new LineSegment(start,end);
                            LineSegmentList.add(linesegment);
                        }
                    }
                }
                else{
                    //如果4点及以上共线,并且otherPoints中与参考点共线且排在最左边的点比参考点大的话,索引是k-count+1
                    if(count>=4 && currentCheck.compareTo(otherPoints[k-count+1])==-1) {
                        Point start = currentCheck;
                        Point end = otherPoints[k-1];
                        LineSegment linesegment = new LineSegment(start,end);
                        LineSegmentList.add(linesegment);
                    }
                    count=2;
                }
            }
        }
    }

    // the number of line segments
    public  int numberOfSegments() {
        return LineSegmentList.size();
    }
    // the line segments
    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[LineSegmentList.size()];
        int index=0;
        for(LineSegment Line : LineSegmentList) {
            segments[index++] = Line;
        }
        return segments;
    }

    //main
    public static void main(String[] args) {
        In in = new In("src/week3/input9.txt");
        int n = in.readInt();
        StdOut.println("total "+n+" points");
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            StdOut.println("("+x+","+y+")");
            points[i] = new Point(x,y);
        }

        //draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.01);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        //print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StdOut.println(collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

