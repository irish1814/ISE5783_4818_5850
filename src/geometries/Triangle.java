package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.List;

/**
 * Plane class represents two-dimensional Triangle in 2D Cartesian coordinate
 *
 * @author Ishay Houri & Elad Radomski
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
    public List<Point> findIntersections(Ray ray){
        //check whether the ray intersect with the polygon's plane or not
        List<Point> planePoints = plane.findIntersections(ray);
        if(planePoints == null)
            return null;
        //if yes - check if the intersection point is inside the polygon or not
        else {
            //vectors calculations
            Vector v1 = vertices.get(0).subtract(ray.getP0());
            Vector v2 = vertices.get(1).subtract(ray.getP0());
            Vector v3 = vertices.get(2).subtract(ray.getP0());
            Vector n1 = v1.crossProduct(v2).normalize();
            Vector n2 = v2.crossProduct(v3).normalize();
            Vector n3 = v3.crossProduct(v1).normalize();
            double vn1 = ray.getDirection().dotProduct(n1);
            double vn2 = ray.getDirection().dotProduct(n2);
            double vn3 = ray.getDirection().dotProduct(n3);

            //check if one of v⋅ni is zero, if true - no intersections
            if(isZero(vn1) || isZero(vn2) || isZero(vn3) )
                return null;
            //check if all v⋅ni have the same sign, if true - the point intersect the triangle
            if(checkSign(vn1,vn2) && checkSign(vn1,vn3) && checkSign(vn2,vn3))
                return List.of(planePoints.get(0));
            else
                return null;
        }
    }
}
