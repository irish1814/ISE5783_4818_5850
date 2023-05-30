import geometries.Geometries;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Testing Geometries class
 *
 * @author Elad & Ishay
 */
public class GeometriesTests {

    /**
     * Test method for an empty collection of elements
     */

    @Test
    void emptyCollectionElements() {
        // =============== Boundary Values Tests ==================

        // TC01: empty list
        Geometries geometries = new Geometries();
        Ray ray = new Ray(new Vector(1, 0, 0), new Point(0, 1, 1));
        assertNull(geometries.findIntersections(ray), "Empty list of objects");
    }

    /**
     * Test method for elements that don't have any intersections
     */

    @Test
    void noneIntersections() {
        // =============== BVA test ==================

        // TC01: There are no intersections at all between all objects
        Geometries geometries = new Geometries(
                new Sphere(1, new Point(0, 0, -4)),
                new Sphere(1, new Point(0, 0, 7))
        );
        Ray ray = new Ray(new Vector(1, 0, 0), new Point(0, 0, 1));
        assertNull(geometries.findIntersections(ray), "Expected 0 intersection but " +
                "found more");
    }

    /**
     * Test method for an element that have only one intersection
     */

    @Test
    void oneIntersection() {
        // =============== BVA test ==================

        // TC01: One shape is intersected
        Geometries geometries = new Geometries(
                new Sphere(1, new Point(0, 0, 4)),
                new Sphere(1, new Point(0, 0, 7))
        );
        Ray ray = new Ray(new Vector(0, 0, -1), new Point(0, 0, 4.5));
        assertEquals(1, geometries.findIntersections(ray).size(), "Expected 1 intersection but " +
                "found less");
    }

    /**
     * Test method for a couple of elements that have an intersection
     */

    @Test
    void multipleIntersections() {
        // =============== EP test ==================

        // TC01: Some of the shapes are intersected
        Geometries geometries = new Geometries(
                new Sphere(1, new Point(0, 0, 4)),
                new Sphere(1, new Point(0, 0, 7)),
                new Sphere(1, new Point(100, 40, 30))
        );
        Ray ray = new Ray(new Vector(0, 0, 1), new Point(0, 0, -1));
        assertEquals(4, geometries.findIntersections(ray).size(), "Expected 4 intersections between " +
                "some of the objects but found less");
    }

    /**
     * Test method for all the elements that have an intersection
     */

    @Test
    void fullIntersections() {
        // =============== BVA test ==================

        // TC01: All shapes are intersected
        Geometries geometries = new Geometries(
                new Sphere(1, new Point(0, 0, 4)),
                new Sphere(1, new Point(0, 0, 7))
        );
        Ray ray = new Ray(new Vector(0, 0, 1), new Point(0, 0, -1));
        assertEquals(4, geometries.findIntersections(ray).size(), "Expected 4 intersections between " +
                "all objects but found less");
    }
}
