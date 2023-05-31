package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * An abstract class implements the coloring operation of the rays sent from the viewPlane
 *
 * @author Ishay Houri & Elad Radomski
 */
public abstract class RayTracerBase {
    protected final Scene scene;

    /**
     * Creates a new instance of the RayTracerBase class with the specified scene.
     *
     * @param scene the scene to be traced
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Computes the color of the intersection point of the specified ray with the scene.
     *
     * @param ray the ray to trace
     * @return the color of the intersection point, or null if there are any intersection
     */
    abstract Color traceRay(Ray ray);
}