package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
/** Tube class represents a Tube in 3D
 * @author Ishay Houri & Elad Radomski */
public class Tube extends RadialGeometry {
    /** The Ray of the Tube */
    protected Ray axisRay;

    /** Constructor to initialize Tube based on Radius and Ray
     * @param r the radius of the Tube
     * @param ray the Ray of the Tube */
    public Tube(double r,Ray ray) {
        super(r);
        axisRay=ray;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
