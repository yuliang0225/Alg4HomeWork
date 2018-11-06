import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.TreeSet;

public class KdTree {

    TreeSet<Double> pointX;
    TreeSet<Double> pointY;


    // construct an empty set of points
    public KdTree(){
        pointX = new TreeSet<>();
        pointY = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty(){
        return pointX.isEmpty();
    }

    // number of points in the set
    public int size(){
        return pointX.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p){
        try {
            if (!pointX.add(p.x()) && !pointX.add(p.y())){
                throw new IllegalArgumentException();
            }
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
        return null;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p){
        return null;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args){

    }
}
