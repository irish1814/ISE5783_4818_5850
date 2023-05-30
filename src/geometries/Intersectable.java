package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Interface represents all intersection points of a geometric shape
 *
 * @author Ishay Houri & Elad Radomski
 */
public interface Intersectable {
    /**
     * @param ray given ray from a geometric shape
     * @return list of intersection points
     */

    List<Point> findIntersections(Ray ray);
}
