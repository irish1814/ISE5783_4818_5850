package renderer;

import jdk.jshell.spi.ExecutionControl;
import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.MissingResourceException;


/**
 * Implementation of the RayTracerBase class that computes the color of the closest intersection point with the scene.
 *
 * @author Ishay Houri & Elad Radomski
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * Creates a new instance of the RayTracerBasic class with the specified scene.
     *
     * @param scene the scene to be traced
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * calculating the color of the closest point to the given ray
     *
     * @param ray Requested ray
     * @return color of closest point or black background if there isn't any close point
     * */
    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findIntersections(ray);
        if (intersections == null) {
            return scene.background;
        }
        Point closestPoint = ray.findClosestPoint(intersections);
        return closestPoint == null ? scene.background : calcColor(closestPoint);
    }

    /**
     * This function return the color of the pixel at the given point
     *
     * @param point coordinates of the pixel
     * @return Color of the background scene
     * */
    private Color calcColor(Point point) {
        return scene.background;
    }
}
