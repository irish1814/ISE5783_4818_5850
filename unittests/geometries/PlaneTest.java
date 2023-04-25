package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
        // ensure there are no exceptions
        assertDoesNotThrow(() -> p1.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = p1.getNormal();
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");
        // calculate the result
        assertEquals(new Vector(0, 0, 1), result);
    }
}