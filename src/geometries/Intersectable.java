package geometries;
import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * An interface represents all the Intersect shapes in the project
 * @author Ishay Houri & Elad Radomski
 */
public interface Intersectable {
    /**
     * Methode that find all the intersections from a ray into a shape
     *
     * @param ray the ray shoot from the camera and hot the shape
     * @return List of Points that intersects the shape
     */
    public List<Point> findIntersections(Ray ray);
}
