package primitives;

/**
 * This class will serve all primitive and geometries classes based on a Point
 *
 * @author Ishay Houri & Elad Radomski
 */

public class Point {
    protected final Double3 xyz;
    public static final Point ZERO = new Point(0,0,0);
    /**
     * Constructor to initialize Double3 based object with its three number values
     *
     * @param d1 first number value
     * @param d2 second number value
     * @param d3 third number value
     */
    public Point(double d1, double d2, double d3) {
        xyz = new Double3(d1, d2, d3);
    }

    /**
     * Constructor to initialize Double3 based object with its three number values
     *
     * @param point which is an object type Double3
     */
    Point(Double3 point) {
        xyz = new Double3(point.d1, point.d2, point.d3);
    }

    /**
     * Addition of two points
     *
     * @param p will be the second point that we add to the first point
     * @return new Point object of the addition of the two points
     */
    public Point add(Point p) {
        return new Point(this.xyz.add(p.xyz));
    }

    /**
     * Subtraction of two points or vectors
     *
     * @param p will be the second point that we subtract to the first point
     * @return Vector object of the subtraction of the two points
     */
    public Vector subtract(Point p) {
        return new Vector(this.xyz.subtract(p.xyz));
    }

    /**
     * Calculates the squared distance between two points - this one and another one
     *
     * @param p is the another point
     * @return the squared distance value
     */
    public double distanceSquared(Point p) {
        // d²((x1, y1, z1), (x2, y2, z2)) = (x2 - x1)² + (y2 - y1)² + (z2 - z1)²
        double dx = this.xyz.d1 - p.xyz.d1;
        double dy = this.xyz.d2 - p.xyz.d2;
        double dz = this.xyz.d3 - p.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Distance between two Points
     *
     * @param p is the second point that we calculate the distance between it and the first point
     * @return the distance between them according to the math formula -
     * d((x1, y1, z1), (x2, y2, z2)) = √ ((x2 - x1)² + (y2 - y1)² + (z2 - z1)²)
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Point other && this.xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return "" + xyz;
    }
}
