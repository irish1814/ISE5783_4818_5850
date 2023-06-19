package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * abstract classm represents all intersection points of a geometric shape
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
         * @param geo A geometric shape that contains th point
         * @param p the point on the shape
         */
        public GeoPoint(Geometry geo, Point p) {
            geometry = geo;
            point = p;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj instanceof GeoPoint other) {
                return this.point.equals(other.point) && this.geometry.equals(other.geometry);
            }
            return false;
        }

        @Override
        public String toString() {
            return "Geometry = " + geometry.toString() + " Point = " + point.toString();
        }
    }

    /**
     * find all intersections points with a ray
     *
     * @ray the given ray we find the intersections
     * @return list of all intersections points
     * */
    public final List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /** find all the GeoIntersections points from a given ray to the shape
     *
     * @param ray given ray from a geometric shape
     * @return list of intersection GeoPoints
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * find intersections with a ray and which shape it belongs to
     *
     * @param ray ray we want to find intersection with
     * @return list of points of the corresponding shape
     * */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}