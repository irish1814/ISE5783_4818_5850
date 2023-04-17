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

    /** Method to get the Normal Vector at some Point in the Sphere
     * @param p the Point on the Sphere to find the Normal on the point
     * @return The Normal Vector to the point */
    @Override
    public Vector getNormal(Point p) {
        //calculate n using the formula 𝒏 = 𝒏𝒐𝒓𝒎𝒂𝒍𝒊𝒛𝒆(𝑷 − 𝑶)
        Vector n = p.subtract(center).normalize();
        return n;
    }
}
