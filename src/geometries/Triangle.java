package geometries;

import primitives.Point;

/**
 * Plane class represents two-dimensional Triangle in 2D Cartesian coordinate
 *
 * @author Ishay Houri & Elad Radomski
 */
public class Triangle extends Polygon {
    /**
     * Constructor to initialize the Triangle based on 3 Points
     *
     * @param p1 The first point
     * @param p2 The second point
     * @param p3 The third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }
}
