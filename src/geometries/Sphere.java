package geometries;

import primitives.Point;
import primitives.Vector;
/** Sphere class represents Sphere in 3D
 * @author Ishay Houri & Elad Radomski
 * */
public class Sphere extends RadialGeometry{
    /** Point that represents the Center of the Sphere */
    private Point center;

    /** Constructor to initialize Sphere based on Point and Radius
     * @param c A point in the center of the sphere
     * @param r the Radius of the sphere
     * */
    public Sphere(double r, Point c) {
        super(r);
        center=c;
    }

    @Override
    public Vector getNormal(Point p) { return null; }
}
