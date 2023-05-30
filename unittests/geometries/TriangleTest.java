package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  * Unit tests for geometries.Triangle class
 *
 * @author Elad Radomski & Ishay Houri
 */
class TriangleTest {
    /**
     * Test method for {@link geometries.Triangle#Triangle}.
     */

    @Test
    public void testConstructor() {
        // =============== Boundary Values Tests ==================

        // TC01: trying to create a plane with 2 identical points
        assertThrows(IllegalArgumentException.class, () -> new Triangle(new Point(1, 2, 3), new Point(1, 2, 3),
                        new Point(1, 5, 8)), "ERROR: Two of the points are the same - does not throw an exception");

        // TC02: trying to create a plane with 3 identical points
        assertThrows(IllegalArgumentException.class, () -> new Triangle(new Point(1, 2, 3), new Point(2, 4, 6),  new Point(4, 8, 12)),
                "ERROR: All of the points are on the same Vector - does not throw an exception");
    }

    /**
     * Test method for {@link geometries.Triangle#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        Triangle t1 = new Triangle(new Point(2, 0, 0), new Point(0, 2, 0), new Point(0, 0, 0));
        // generate the test result
        Vector result = t1.getNormal(new Point(0, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: check if there are no exceptions
        assertDoesNotThrow(() -> t1.getNormal(new Point(0, 2, 0)), "");

        // TC02: check if the normal vector length = 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");

        // TC03: check if the result is as expected
        assertEquals(new Vector(0, 0, 1), result);
    }

    @Test
    public void findIntersections() {
        Triangle triangle = new Triangle(new Point(2, 0, 0), new Point(0, 1, 0), new Point(0, -1, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: ray has an intersection with the triangle - inside the triangle
        Ray insideRay = new Ray(new Vector(1, 0, -2), new Point(0, 0, 2));
        assertEquals(List.of(new Point(1, 0, 0)), triangle.findIntersections(insideRay));

        // TC02: ray is not intersect with the triangle - outside against edge of the triangle
        Ray outsideAgainstEdgeRay = new Ray(new Vector(3, 0, -2), new Point(0, 0, 2));
        assertNull(triangle.findIntersections(outsideAgainstEdgeRay));

        // TC03: ray is not intersect with the triangle - outside against vertex of the triangle
        Ray outsideAgainstVertexRay = new Ray(new Vector(-1, 0, -2), new Point(0, 0, 2));
        assertNull(triangle.findIntersections(outsideAgainstVertexRay));

        // =============== Boundary Values Tests ==================

        // TC01: ray has an intersection with the triangle - on the edge
        Ray onEdgeRay = new Ray(new Vector(2, 0, -2), new Point(0, 0, 2));
        assertNull(triangle.findIntersections(onEdgeRay));

        // TC02: ray has an intersection with the triangle - on the vertex
        Ray onVertexRay = new Ray(new Vector(0, 0, -2), new Point(0, 0, 2));
        assertNull(triangle.findIntersections(onVertexRay));

        // TC03: ray is not intersect with the triangle - outside on edge's continuation
        Ray outsideEdgeContinuationRay = new Ray(new Vector(0, 2, -2), new Point(0, 0, 2));
        assertNull(triangle.findIntersections(outsideEdgeContinuationRay));
    }
}