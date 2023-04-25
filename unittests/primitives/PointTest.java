package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 *
 * @author Ishay Houri & Elad Radomski
 */

class PointTest {
    /**
     * Test method for {@link primitives.Point#add(primitives.Point)}.
     */
    @Test
    public void testAdd() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(0, 0, 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Sum of two point
        assertEquals(new Point(1, 2, 4), p1.add(p2));

        // Addition of a point and same vector equal Point(0, 0, 0)
        assertEquals(new Point(0, 0, 0), p1.add(new Vector(-1, -2, -3)));

    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}.
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(0, 0, 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Subtraction of two points
        assertEquals(new Point(1, 2, 2), p1.subtract(p2));

        // =============== Boundary Values Tests ==================

        // TC02: Subtraction of the same points
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1),
                "ERROR: subtraction of same points does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);
        // =============== Boundary Values Tests ==================

        // TC01: Calculates squared distance between p1 and p2
        assertEquals(27, p1.distanceSquared(p2));
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void testDistance() {
        Point p1 = new Point(1, 2, 1);
        Point p2 = new Point(2, 0, 3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Calculates distance between two given points
        assertEquals(3, p1.distance(p2));
    }

}