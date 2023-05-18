package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * An interface represents all the Geometric shapes in 3D
 *
 * @author Ishay Houri & Elad Radomski
 */
public interface Geometry extends Intersectable {
    public Vector getNormal(Point p);
}
