package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * An interface represents all the Geometric shapes in 3D
 *
 * @author Ishay Houri & Elad Radomski
 */
public interface Geometry extends Intersectable {
    /**
     * @param p Given point in a geometric shape
     * @return Normal vector from the given point
     * */
    public Vector getNormal(Point p);
}
