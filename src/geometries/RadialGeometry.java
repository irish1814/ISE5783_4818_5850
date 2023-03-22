package geometries;

/** Aמ Abstract class that represents all the Radial Geometry shapes
 * @author Ishay Houri & Elad Radomski */
public abstract class RadialGeometry implements Geometry{
    /** The Radius of the Shape */
    protected double radius;

    /** Constructor to initialize Radial Shape based on Radius
     * @param r the radius of the Shape * */
    public RadialGeometry(double r) {
        radius = r;
    }
}
