package primitives;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Ray class represents ray in 3D
 *
 * @author Ishay Houri & Elad Radomski
 */
public class Ray {
    /**
     * A point that show the beginning of the ray
     */
    private final Point p0;

    /**
     * A Vector that tells the direction of the ray
     */
    private final Vector direction;

    /**
     * Constructor to initialize Ray based on Point and Vector
     *
     * @param p The point that shows the beginning of the ray
     * @param v Vector that show the direction of the ray
     */
    public Ray(Vector v, Point p) {
        direction = v.normalize();
        p0 = p;
    }

    /**
     * Get the Point of the Ray
     *
     * @return point that shows the beginning of the ray
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Get the direction of the Ray
     *
     * @return Vector that show the direction of the ray
     */
    public Vector getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj instanceof Ray other) {
            return this.p0.equals(other.p0) && this.direction.equals(other.direction);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Ray = " + p0.toString() + " + " + direction.toString();
    }

    /**
     * Returns a point on the ray at a given distance from the ray head
     * @param t TODO
     * @return the point on the ray
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(direction.scalarProduct(t));
    }

    /**
     * @param list list of random points
     * @return the closest point to the P0 of the ray from a list of point
     */
    public Point findClosestPoint(List<Point> list) {
        if (list == null) return null;

        Point closest = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Point p : list) {
            double distance = p.distance(p0);
            if (distance < minDistance) {
                closest = p;
                minDistance = distance;
            }
        }
        return closest;
    }
}
