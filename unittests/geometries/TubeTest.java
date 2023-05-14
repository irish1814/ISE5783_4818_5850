package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Tubes
 *
 * @author Elad Radomski & Ishay Houri
 */
class TubeTest {
    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: check if the radius is as expected with the given point and ray
        Tube t1 = new Tube(2, new Ray(new Vector(0, 0, 1), new Point(0, 0, 0)));

        // TC02: check normal at point (-2,0,1)
        assertEquals(new Vector(-1, 0, 0), t1.getNormal(new Point(-2, 0, 1)));

        // TC03: check normal at point (2,0,0)
        assertEquals(new Vector(1, 0, 0), t1.getNormal(new Point(2, 0, 0)));
    }
}