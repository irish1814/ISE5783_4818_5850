package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.*;

/**
 * Implementation of the RayTracerBase class that computes the color of the closest intersection point with the scene.
 *
 * @author Ishay Houri & Elad Radomski
 */
public class RayTracerBasic extends RayTracerBase {


    /**
     * maximum constant for stopping conditions in recursion
     * in calcColor function
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * minimum constant for stopping conditions in recursion
     * in calcColor function
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * the initially K constant
     */
    private static final Double3 INITIAL_K = new Double3(1.0);


    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource ls) {
        Vector lightDirection = l.scalarProduct(-1); // from point to light source
//        Vector epsVector = n.scalarProduct((n.dotProduct(lightDirection) > 0) ? Ray : -DELTA);
        Point point = gp.point;
        Ray lightRay = new Ray(lightDirection, point,n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(point));
        if (intersections == null) return true;
        List<GeoPoint> intersectionsWithZero = new ArrayList<>();
        for (GeoPoint p:intersections ) {
            if(p.geometry.getMaterial().kT == Double3.ZERO)
                intersectionsWithZero.add(p);
        }
        return intersectionsWithZero.isEmpty();
    }


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
     * @param ray the ray to trace the scene with
     * @return color of the closest Intersection to the beginning of the ray
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint gp = findClosestIntersection(ray); // intersect the ray with the geometries
        if (gp == null)
            return this.scene.background;
        return calcColor(gp, ray);
    }

    /**
     * This function return the color of the pixel at the given point
     *
     * @param gp    geoPoint in the space
     * @param ray   the ray casting to the point
     * @param level the level of the recursion
     * @param k     the constant of reflection and transparency recursion
     * @return Color of the given GeoPoint
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray);
        return level == 1 ? color
                : color.add(calcGlobalEffects(gp, ray, level, k));
    }

    /**
     * calculating the color of a specific point, taking into account the lightning,
     * transparency of the point itself and other affects of the surrounding are of the point in space
     *
     * @param geoPoint calculate the color of this point
     * @return for now - the ambient light's intensity
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K));  // recursive call
    }


    /**
     * This function add the local effect color in specific point
     * using the Phong Light Model
     *
     * @param gp  geoPoint in the space
     * @param ray the ray casting to the point
     * @return local Color of the given GeoPoint
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;

        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {
                if (unshaded(gp, l, n, lightSource)) {
                    Color iL = lightSource.getIntensity(gp.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    /**
     * calculate the diffusive component
     *
     * @param material the material that the light hits
     * @param nl       dot product between the normal on point and the light direction vector
     * @return diffusive component value
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * calculate the Specular component
     *
     * @param material the material that the light hits
     * @param n        the normal vector
     * @param l        the light direction vector
     * @param nl       dot product between the normal on point and the light vector
     * @param v        the ray direction vector
     * @return Specular component value
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.add(n.scalarProduct(-2 * nl));
        double minusVR = -alignZero(r.dotProduct(v));
        return minusVR <= 0 ? Double3.ZERO
                : material.kS.scale(Math.pow(minusVR, material.nShininess));
    }

    /**
     * method to find the closest intersection between ray and geometry
     *
     * @param ray specific ray
     * @return the GeoPoint that is closest to the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * method to calculate the reflected ray
     *
     * @param gp the hit point
     * @param v  the ray vector that hits the point
     * @param n  the normal vector
     * @return a reflected ray
     */
    private Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n) {
        double vn = v.dotProduct(n);
        Vector r = v.subtract(n.scalarProduct(2 * vn));
        return new Ray(r, gp.point,n);
    }

    /**
     * method to calculate the refracted ray
     *
     * @param gp the hit point
     * @param v  the ray vector that hits the point
     * @param n  the normal vector
     * @return a refracted ray
     */
    private Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n) {
        return new Ray(v, gp.point,n);
    }

    /**
     * calculate all global environment effects at the point
     *
     * @param gp    the point
     * @param ray   ray that hit the point
     * @param level the level of the recursion
     * @param k     the constant to pass the recursion
     * @return the color we get after all the effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructReflectedRay(gp, v, n),
                level, k, material.kR)
                .add(calcGlobalEffect(constructRefractedRay(gp, v, n),
                        level, k, material.kT));
    }

    /**
     * calculate all global environment effect on specific material
     *
     * @param ray   ray that hit the point
     * @param level the level of the recursion
     * @param k     the constant to pass the recursion
     * @param kx    the constant of reflection or transparency recursion
     * @return the color we get after all the effects
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) return scene.background.scale(kx);

        return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDirection()))
                ? Color.BLACK : calcColor(gp, ray, level - 1, kkx).scale(kx);
    }

}
