package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

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
     * This function return the color of the point in the space from ray that hit this point
     *
     * @param ray the ray that shoot from the starting point
     * @return Color from the closest point that hit a geometry
     */
    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? scene.background //
                : calcColor(ray.findClosestGeoPoint(intersections));
    }

    /**
     * This function return the color of the pixel at the given point
     *
     * @param gp geoPoint in the space
     * @return Color of the given GeoPoint
     */
    private Color calcColor(GeoPoint gp) {
        return scene.ambientLight.getIntensity()
                .add(gp.geometry.getEmission());
    }
}
