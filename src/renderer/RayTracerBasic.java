package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.*;

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
        return intersections == null ? scene.background
                : calcColor(ray.findClosestGeoPoint(intersections),ray);
    }

    /**
     * This function return the color of the pixel at the given point
     *
     * @param gp geoPoint in the space
     * @param ray the ray casting to the point
     * @return Color of the given GeoPoint
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(gp, ray));
    }

    /**
     * This function add the local effect color in specific point
     * using the Phong Light Model
     *
     * @param gp geoPoint in the space
     * @param ray the ray casting to the point
     * @return local Color of the given GeoPoint
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection ();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }

    /**
     * calculate the diffusive component
     *
     * @param material the material that the light hits
     * @param nl dot product between the normal on point and the light direction vector
     * @return diffusive component value
     */
    private Double3 calcDiffusive(Material material, double nl){
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * calculate the Specular component
     *
     * @param material the material that the light hits
     * @param n the normal vector
     * @param l the light direction vector
     * @param nl dot product between the normal on point and the light vector
     * @param v the ray direction vector
     * @return Specular component value
     */
    private Double3 calcSpecular(Material material, Vector n,Vector l, double nl,Vector v){
        Vector R = l.add(n.scalarProduct(-2 * nl));
        double dp = R.dotProduct(v);
        double pow = Math.pow(dp,material.nShininess);
        return material.kS.scale(Math.abs(pow));
    }
}
