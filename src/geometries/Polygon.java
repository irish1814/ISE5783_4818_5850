package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;


/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected final List<Point> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected final Plane plane;
    private final int size;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by
     *                 edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        size = vertices.length;

        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (size == 3) return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();
        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!Util.isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    // Method to calculate the area of a triangle
    private static double calculateTriangleArea(Point p1, Point p2, Point p3) {
        //check if the three points are collinear
        // Calculate the direction vectors between the points
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        // Check if the vectors are same or opposite direction
        if (Math.sqrt(v1.dotProduct(v2) * v1.dotProduct(v2)) == v1.length() * v2.length())
            return -1;
        //if not - return the triangle area
        return p2.subtract(p1).crossProduct(p3.subtract(p1)).length() / 2.0;
    }

    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }

    /**
     * Method to calculate the Barycentric Coords of a point,
     * relative to the polygon
     *
     * @param p The point we need to check
     * @return an Array of Doubles represents the Barycentric Coords
     * (number of coords is the number of polygon's vertices)
     */
    private double[] getBarycentricCoords(Point p) {
        // Array to store the barycentric coordinates
        double[] barycentricCoords = new double[size];
        //calculate the polygon total area
        double totalArea = 0;
        for (int i = 1; i < size - 1; i++) {
            Point p1 = vertices.get(0);
            Point p2 = vertices.get(i);
            Point p3 = vertices.get(i + 1);

            double triangleArea = calculateTriangleArea(p1, p2, p3);
            //ensure that the point is not on edge's continuation
            if (triangleArea == -1)
                return null;
            totalArea += triangleArea;
        }
        //calculate the barycentric coords of th point - using each vertice of the polygon
        for (int i = 0; i < size; i++) {
            Point v1 = vertices.get(i);
            Point v2 = vertices.get((i + 1) % size);
            Point v3 = p;

            double triangleArea = calculateTriangleArea(v1, v2, v3);
            barycentricCoords[i] = triangleArea / totalArea;
        }
        return barycentricCoords;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        //check whether the ray intersect with the polygon's plane or not
        List<Point> planePoints = plane.findIntersections(ray);
        if (planePoints == null)
            return null;
        Point intersectionPoint = planePoints.get(0);
        //check if the intersection point is one of the polygon's points
        for (Point p : vertices) {
            if (p.equals(intersectionPoint))
                return null;
        }

        //if yes - check if the point is inside the polygon or not - using barycentric coords
        double[] barycentricCoords = getBarycentricCoords(intersectionPoint);
        //check if all barycentric coords are positive or zero
        for (Double d : barycentricCoords) {
            if (d < 0)
                return null;
        }
        //check if the sum of all barycentric coords is no more than 1
        double sum = 0;
        for (Double d : barycentricCoords) {
            sum += d;
        }
        if (sum > 1)
            return null;
        //if all coords positive or zero and sum is no more then 1 - the point is inside the polygon
        return List.of(intersectionPoint);
    }
}
