package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        // TC01: check if the add function works properly
        assertEquals(new Point(1, 2, 4), p1.add(p2), "Add function does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}.
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(0, 0, 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: check if the subtract function works properly
        assertEquals(new Point(1, 2, 2), p1.subtract(p2), "Subtract function does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC01: check if subtraction of identical points throw an exception
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(new Vector(1, 2, 3)),
                "Subtraction of identical points do not throw a zero vector error");

    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);
        // ============ Equivalence Partitions Tests ==============

        // TC01: check if squared distance between p1 and p2 is as expected
        assertEquals(27, p1.distanceSquared(p2));

        // =============== Boundary Values Tests ==================

        // TC01: check if the squared distance between a point and itself equal to 0
        assertEquals(0, p1.distanceSquared(p1),
                "squared distance of same points should be 0");
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void testDistance() {
        Point p1 = new Point(1, 2, 1);
        Point p2 = new Point(2, 0, 3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: check if distance between p1 and p2 is as expected
        assertEquals(3, p1.distance(p2));

        // =============== Boundary Values Tests ==================

        // TC01: check if the distance between a point and itself equal to 0
        assertEquals(0, p1.distance(p1),
                "The subtraction is not working when the given point is the same point");
    }
}