package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Tube class represents a Tube in 3D
 *
 * @author Ishay Houri & Elad Radomski
 */
public class Tube extends RadialGeometry {
    /**
     * The Ray of the Tube
     */
    protected final Ray axisRay;

    /**
     * Constructor to initialize Tube based on Radius and Ray
     *
     * @param r   the radius of the Tube
     * @param ray the Ray of the Tube
     */
    public Tube(double r, Ray ray) {
        super(r);
        axisRay = ray;
    }

    /**
     * Method to get the Normal Vector at some Point in the Tube
     *
     * @param p the Point on the Tube to find the Normal to the point
     * @return The Normal Vector to the point
     */
    @Override
    public Vector getNormal(Point p) {
        Point p0 = axisRay.getP0();
        Vector dir = axisRay.getDirection();
        //find the t length using the formula 𝒕 = 𝒗 ∙( 𝑷 − 𝑷0)
        double t = dir.dotProduct(p.subtract(p0));
        //find the O point using the formula 𝑶 = 𝑷𝟎 + (𝒕 ∙ 𝒗)
        Point o = isZero(t) ? p0 : p0.add(dir.scalarProduct(t));
        //calculate the normal using the formula 𝒏 = 𝒏𝒐𝒓𝒎𝒂𝒍𝒊𝒛𝒆(𝑷 − 𝑶)
        return p.subtract(o).normalize();
    }

    @Override
    public List<Point> findIntsersections(Ray ray) { return null; }
}
