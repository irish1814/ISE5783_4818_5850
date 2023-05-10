import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

public class GeometriesTests {

    /**
     * Test method for an empty collection of elements
     */

    @Test
    void emptyCollectionElements() {
        // =============== Boundary Values Tests ==================

    }

    /**
     * Test method for elements that don't have any intersections
     */

    @Test
    void noneIntersections() {
        // =============== BVA test ==================

        // TC01: check if there is no intersection for the given plane and ray
//        Plane plane = new Plane(new Point(-3, 0, 0), new Point(0, -3, 0), new Point(3, 0, 0));
//        Ray notParallelNo = new Ray(new Vector(0,3,-1),new Point(0,0,1));
//        assertNull(plane.findIntersections(notParallelNo));
    }

    /**
     * Test method for an element that have only one intersection
     */

    @Test
    void oneIntersection() {
        // =============== BVA test ==================

    }

    /**
     * Test method for a couple of elements that have an intersection
     */

    @Test
    void multipleIntersections() {
        // =============== EP test ==================

    }

    /**
     * Test method for all the elements that have an intersection
     */

    @Test
    void fullIntersections() {
        // =============== BVA test ==================

    }
}
