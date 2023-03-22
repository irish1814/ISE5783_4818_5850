package geometries;

import primitives.Point;
import primitives.Vector;
/** Cylinder class represents a Cylinder in 3D
 * @author Ishay Houri & Elad Radomski */
public class Cylinder extends RadialGeometry {
    /** The Height of the Cylinder */
    double height;

    /** Constructor to initialize Cylinder based on a Radius and Height
     * @param r the radius of the Cylinder
     * @param h the Cylinder's height */
    public Cylinder(double r, double h) {
        super(r);
        height = h;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
