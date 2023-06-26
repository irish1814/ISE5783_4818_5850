package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * abstract class represents all intersection points of a geometric shape
 *
 * @author Ishay Houri & Elad Radomski
 */
public abstract class Intersectable {
    /**
     * inner class that associates point to specific geometry
     */
    public static class GeoPoint {
        /**
         * the geometry that the point is on
         */
        public Geometry geometry;

        /**
         * the point on the geometry
         */
        public Point point;

        /**
         * Constructor to initialize GeoPoint
         *
         * @param geo A geometric shape that contains th point
         * @param p   the point on the shape
         */
        public GeoPoint(Geometry geo, Point p) {
            geometry = geo;
            point = p;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            return obj instanceof GeoPoint other &&
                    this.geometry == other.geometry && this.point.equals(other.point);
        }

        @Override
        public String toString() {
            return "Geometry = " + geometry + " Point = " + point;
        }
    }

    /**
     * Finds intersections between the geometry object and a ray.
     *
     * @param ray The ray to intersect.
     * @return The list of intersection points, or null if no intersections occur.
     */
    public final List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds the geometric intersection points between the geometry object and a
     * ray, considering a maximum distance.
     *
     * @param ray The ray to intersect.
     * @return The list of geometric intersection points, or null if no
     *         intersections occur.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * Finds the geometric intersection points between the geometry object and a
     * ray, up to a maximum distance.
     *
     * @param ray         The ray to intersect.
     * @param maxDistance The maximum distance to consider for intersections.
     * @return The list of geometric intersection points, or null if no
     *         intersections occur.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * Finds the geometric intersection points between the geometry object and a
     * ray.
     *
     * @param ray      The ray to intersect.
     * @param distance The maximum distance to consider for intersections.
     * @return The list of geometric intersection points, or null if no
     *         intersections occur.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double distance);

}