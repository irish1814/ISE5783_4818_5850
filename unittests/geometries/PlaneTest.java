package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Planes
 *
 * @author Elad Radomski & Ishay Houri
 */
class PlaneTest {
    /**
     * Test method for {@link geometries.Plane#Plane}.
     */

    @Test
    public void testConstructor() {
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 2, 3), new Point(1, 2, 3), new Point(1, 5, 8)),
                "ERROR: Two of the points are the same - does not throw an exception");
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(4, 8, 12)),
                "ERROR: All of the points are on the same Vector - does not throw an exception");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        Plane p1 = new Plane(new Point(2, 0, 0), new Point(0, 2, 0), new Point(0, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: ensure there are no errors when we call the getNormal function
        assertDoesNotThrow(() -> p1.getNormal(new Point(0, 0, 1)), "");

        Vector result = p1.getNormal();
        // TC02: verify that the length of the vector is = 1 as expected
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");

        // calculate the result
        assertEquals(new Vector(0, 0, 1), result);
    }

    /**
     * Test method for {@link geometries.Plane#findIntsersections(Ray)}.
     */
    @Test
    public void findIntersections() {
        Plane plane = new Plane(new Point(-3, 0, 0), new Point(0, -3, 0), new Point(3, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: ray is not parallel to the plane and has an intersection with the plane

        // TC02: ray is not parallel to the plane and hasn't an intersection with the plane


        // =============== Boundary Values Tests ==================

        // TC01: 2 cases when the ray is parallel to the plane

        // 1: ray is parallel and out of the plane
        Ray parallelOut = new Ray(new Vector(0, -2, 0), new Point(0, 0, 1));
        assertNull(plane.findIntsersections(parallelOut));

        // 2: ray is parallel and inside the plane
        Ray parallelIn = new Ray(new Vector(0, -3, 0), new Point(0, 0, 0));
        assertNull(plane.findIntsersections(parallelIn));

        // TC02: 3 cases when ray is vertical to the plane

        // 1: ray is vertical and start before the plane
        Ray verticalBefore = new Ray(new Vector(0, 0, 4), new Point(0, 0, -1));
        assertEquals(List.of(new Point(0, 0, 0)), plane.findIntsersections(verticalBefore));

        // 2: ray is vertical and start in the plane
        Ray verticalIn = new Ray(new Vector(0, 0, 3), new Point(0, 0, 0));
        assertNull(plane.findIntsersections(verticalIn));

        // 3: ray is vertical and start after the plane
        Ray verticalAfter = new Ray(new Vector(0, 0, 3), new Point(0, 0, 0));
        assertNull(plane.findIntsersections(verticalAfter));
    }
}