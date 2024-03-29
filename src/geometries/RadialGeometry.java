package geometries;

/**
 * An Abstract class that represents all the Radial Geometry shapes
 *
 * @author Ishay Houri &mp; Elad Radomski
 */
public abstract class RadialGeometry extends Geometry {
    /**
     * The Radius of the Shape
     */
    protected final double radius;
    /**
     * The squared Radius of the Shape
     */
    protected final double radiusSquared;

    /**
     * Constructor to initialize Radial Shape based on Radius
     *
     * @param r the radius of the Shape *
     */
    public RadialGeometry(double r) {
        radius = r;
        radiusSquared = r * r;
    }
}
