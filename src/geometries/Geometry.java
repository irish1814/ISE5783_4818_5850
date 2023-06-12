package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * An interface represents all the Geometric shapes in 3D
 *
 * @author Ishay Houri & Elad Radomski
 */
public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;

    /**
     * @return emission field
     * */
    public Color getEmission() {
        return emission;
    }

    /**
     * set the emission field to a new emission value
     *
     * @param emission the new emission value
     * @return new object with new sets
     * */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
    /** calculate the normal vector at given point on the geometry
     * @param p Given point in a geometric shape
     * @return Normal vector from the given point
     * */
    public abstract Vector getNormal(Point p);
}
