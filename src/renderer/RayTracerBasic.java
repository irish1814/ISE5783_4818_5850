package renderer;

import jdk.jshell.spi.ExecutionControl;
import primitives.Color;
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
        throw new UnsupportedOperationException("Constructor not implemented");
    }

    /**
     * calculating the color of the closest point to the given ray
     *
     * @param ray Requested ray
     * @return color of closest point or black background if there isn't any close point
     * */
    @Override
    public Color traceRay(Ray ray) {
        return null;
    }
}
