import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class PointSET {

    private List<Point2D> pointSET;



    // construct an empty set of points
    public PointSET(){
        pointSET= new ArrayList<>();
    }

    // is the set empty?
    public boolean isEmpty(){
        return pointSET.isEmpty();
    }

    // number of points in the set
    public int size(){
        return pointSET.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p){
        try {
            if (pointSET.contains(p)){
                throw new IllegalArgumentException();
            }
            pointSET.add(p);
        } catch (Exception e){
            throw new IllegalArgumentException();
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p){
        for (Point2D temp:pointSET){
            if (temp == p){
                return true;
            }
        }
        return false;
    }

    // draw all points to standard draw
    public void draw(){
        for (Point2D temp:pointSET){
            temp.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect){
        List<Point2D> contain = new ArrayList<>();
        for (Point2D temp:pointSET){
            if (rect.contains(temp)){
                contain.add(temp);
            }
        }
        return contain;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p){
        Point2D nearestP = null;
        Double dis = Double.POSITIVE_INFINITY;

        for (Point2D temp:pointSET){
            if (temp.distanceTo(p) < dis){
                nearestP = temp;
                dis = nearestP.distanceTo(p);
            }
        }

        return nearestP;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args){

    }
}
