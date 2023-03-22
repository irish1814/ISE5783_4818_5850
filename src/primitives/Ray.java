package primitives;

/** Ray class represents ray in 3D
 * @author Ishay Houri & Elad Radomski
 * */
public class Ray {
    /** A point that show the beginning of the ray */
    private Point p0;

    /** A Vector that tells the direction of the ray */
    private Vector direction;

    /** Constructor to initialize Ray based on Point and Vector
     * @param p The point that shows the beginning of the ray
     * @param v Vector that show the direction of the ray
     * */
    Ray(Vector v, Point p) {
        // The given vector isn't normalized
        if(v.length() != 1) {
            direction = v.normalize();
        }
        else {
            direction = new Vector(v.xyz);
        }
        p0 = new Point(p.xyz);
    }

    /** Get the Point of the Ray
     * @return point that shows the beginning of the ray
     * */
    public Point getP0() {
        return p0;
    }

    /** Get the direction of the Ray
     * @return Vector that show the direction of the ray
     * */
    public Vector getDirection() {
        return direction;
    }
}
