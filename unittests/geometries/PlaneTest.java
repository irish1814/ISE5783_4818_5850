package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
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

        // TC01: trying to create a plane with 2 identical points
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 2, 3), new Point(1, 2, 3),
                        new Point(1, 5, 8)), "ERROR: Two of the points are the same - does not throw an exception");

        // TC02: trying to create a plane with 3 identical points
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 2, 3), new Point(2, 4, 6),
                        new Point(4, 8, 12)), "ERROR: All of the points are on the same Vector - does not throw an exception");
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
     * Test method for {@link geometries.Plane#findIntersections(Ray)}.
     */
    @Test
    public void findIntersections() {
        Plane plane = new Plane(new Point(-3, 0, 0), new Point(0, -3, 0), new Point(3, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: ray is not parallel to the plane and has an intersection with the plane
        Ray notParallelYes = new Ray(new Vector(0, 1, -1), new Point(0, 0, 3));
        assertEquals(List.of(new Point(0, 3, 0)), plane.findIntersections(notParallelYes));

        // TC02: ray is not parallel to the plane and hasn't an intersection with the plane
        Ray notParallelNo = new Ray(new Vector(0, 3, 1), new Point(0, 0, 1));
        assertNull(plane.findIntersections(notParallelNo));

        // =============== Boundary Values Tests ==================

        // 2 test cases when the ray is parallel to the plane

        // TC03: ray is parallel and out of the plane
        Ray parallelOut = new Ray(new Vector(0, -2, 0), new Point(0, 0, 1));
        assertNull(plane.findIntersections(parallelOut));

        // TC04: ray is parallel and inside the plane
        Ray parallelIn = new Ray(new Vector(0, -3, 0), new Point(0, 0, 0));
        assertNull(plane.findIntersections(parallelIn));

        // 3 tests cases when ray is vertical to the plane

        // TC05: ray is vertical and start before the plane
        Ray verticalBefore = new Ray(new Vector(0, 0, 4), new Point(0, 0, -1));
        assertEquals(List.of(new Point(0, 0, 0)), plane.findIntersections(verticalBefore));

        // TC06: ray is vertical and start in the plane
        Ray verticalIn = new Ray(new Vector(0, 0, 3), new Point(0, 0, 0));
        assertNull(plane.findIntersections(verticalIn));

        // TC07: ray is vertical and start after the plane
        Ray verticalAfter = new Ray(new Vector(0, 0, 3), new Point(0, 0, 0));
        assertNull(plane.findIntersections(verticalAfter));

        // TC08: ray starts at plane's ref.point
        Ray rayFromQ0 = new Ray(new Vector(0, 3, 1), new Point(-3, 0, 0));
        assertNull(plane.findIntersections(rayFromQ0));
    }
}