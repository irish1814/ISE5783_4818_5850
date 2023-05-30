package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;


/**
 * Sphere class represents Sphere in 3D
 *
 * @author Ishay Houri & Elad Radomski
 */
public class Sphere extends RadialGeometry {
    /**
     * Point that represents the Center of the Sphere
     */
    private final Point center;

    /**
     * Constructor to initialize Sphere based on Point and Radius
     *
     * @param c A point in the center of the sphere
     * @param r the Radius of the sphere
     */
    public Sphere(double r, Point c) {
        super(r);
        center = c;
    }

    /**
     * Method to get the Normal Vector at some Point in the Sphere
     *
     * @param p the Point on the Sphere to find the Normal on the point
     * @return The Normal Vector to the point
     */
    @Override
    public Vector getNormal(Point p) {
        //calculate n using the formula 𝒏 = 𝒏𝒐𝒓𝒎𝒂𝒍𝒊𝒛𝒆(𝑷 − 𝑶)
        return p.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        //first - check if the ray start at the center of the sphere
        if (ray.getP0().equals(center))
            return List.of(ray.getPoint(radius));

        //calculate the vector between the sphere center and the ray start point
        Vector u = center.subtract(ray.getP0());
        //calculate the length of tm line in the formula
        double tm = ray.getDirection().dotProduct(u);
        //calculate the d length between the center of the sphere and the ray
        double dSquared = u.lengthSquared() - (tm * tm);
        double thSquared = radiusSquared - dSquared;

        //if d is less than sphere's radius - there is no intersections
        if (alignZero(thSquared) <= 0) return null;

        //calculate the length of th line in the formula
        double th = Math.sqrt(thSquared);

        //calculate the intersections
        double t2 = alignZero(tm + th);
        if (t2 <= 0) return null;

        double t1 = alignZero(tm - th);
        return t1 <= 0 ? List.of(ray.getPoint(t2)) : List.of(ray.getPoint(t2), ray.getPoint(t1));
    }
}
