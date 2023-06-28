package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Plane class represents two-dimensional Triangle in 2D Cartesian coordinate
 *
 * @author Ishay Houri &mp; Elad Radomski
 */
public class Triangle extends Polygon {
    /**
     * Constructor to initialize the Triangle based on 3 Points
     *
     * @param p1 The first point
     * @param p2 The second point
     * @param p3 The third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        //check whether the ray intersect with the polygon's plane or not
        var planePoints = plane.findGeoIntersections(ray, maxDistance);
        if (planePoints == null) return null;

        Point p0 = ray.getP0();
        Vector dir = ray.getDirection();

        //vectors calculations
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        double vn1 = alignZero(dir.dotProduct(n1));
        if (vn1 == 0) return null;

        Vector v3 = vertices.get(2).subtract(p0);
        Vector n2 = v2.crossProduct(v3).normalize();
        double vn2 = alignZero(dir.dotProduct(n2));
        if (vn1 * vn2 <= 0) return null;

        Vector n3 = v3.crossProduct(v1).normalize();
        double vn3 = alignZero(dir.dotProduct(n3));
        if (vn1 * vn3 <= 0) return null;

        planePoints.get(0).geometry = this;
        return planePoints;
    }
}
