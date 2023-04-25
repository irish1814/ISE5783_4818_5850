package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane class represents two-dimensional plane in 3D Cartesian coordinate
 *
 * @author Ishay Houri & Elad Radomski
 */
public class Plane implements Geometry {
    /**
     * A point contained in a plane
     */
    private final Point q0;

    /**
     * The normal to the plane
     */
    private final Vector normal;

    /**
     * Constructor to initialize Plane based on 3 Points
     *
     * @param p1 A point in the plane
     * @param p2 A point in the plane
     * @param p3 A point in the plane
     */
    Plane(Point p1, Point p2, Point p3) {
        //𝑣1 = 𝑃2 − 𝑃1
        Vector v1 = p2.subtract(p1);
        //𝑣2 = 𝑃3 − 𝑃1
        Vector v2 = p3.subtract(p1);
        //calculate the normal using the formula 𝑛 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒(𝑣1 × 𝑣2)
        normal = v1.crossProduct(v2).normalize();
        q0 = p1;
    }

    /**
     * Constructor to initialize Plane based on Point and Normal to the Plane
     *
     * @param p A point in the plane
     * @param v the Normal Vector to the plane
     */
    Plane(Point p, Vector v) {
        q0 = p;
        normal = v.normalize();
    }

    /**
     * Get the normal of the Plane
     *
     * @return the normal field of the current plane
     */
    public Vector getNormal() {
        return normal;
    }


    @Override
    public Vector getNormal(Point p) {
        return normal;
    }
}
