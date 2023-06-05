package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Interface represents all intersection points of a geometric shape
 *
 * @author Ishay Houri & Elad Radomski
 */
public abstract class Intersectable {
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;
    }

    /**
     * @param ray given ray from a geometric shape
     * @return list of intersection points
     */
    public abstract List<Point> findIntersections(Ray ray);
}