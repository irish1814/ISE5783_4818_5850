package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * An interface represents all the Geometric shapes in 3D
 *
 * @author Ishay Houri & Elad Radomski
 */
public interface Geometry {
    public Vector getNormal(Point p);
}
