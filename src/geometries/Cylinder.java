package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class represents a Cylinder in 3D
 *
 * @author Ishay Houri & Elad Radomski
 */
public class Cylinder extends Tube {
    /**
     * The Height of the Cylinder
     */
    private final double height;

    /**
     * Constructor to initialize Cylinder based on a Radius and Height
     *
     * @param rad the radius of the Cylinder
     * @param h   the Cylinder's height
     * @param ray the Cylinder's ray
     */
    public Cylinder(double rad, double h, Ray ray) {
        super(rad, ray);
        height = h;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
