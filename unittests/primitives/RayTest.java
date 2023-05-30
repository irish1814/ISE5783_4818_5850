package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Testing primitives.Ray
 *
 * @author Ishay Houri & Elad Radomski
 */
class RayTest {

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List)}.
     */
    @Test
    void findClosestPoint() {
        Point p1 = new Point(0, 0, 1);
        Point p2 = new Point(0, 0, 2);
        Point p3 = new Point(0, 0, 3);
        Ray ray = new Ray(new Vector(0, 0, 1), new Point(0, 0, 0.5));

        // ============ Equivalence Partitions Tests ==============

        //TC01: Point in the middle of list
        List<Point> points1 = new LinkedList<>();
        points1.add(p2);
        points1.add(p1);
        points1.add(p3);
        assertEquals(p1, ray.findClosestPoint(points1), "Found point is not as the expected one");

        // =============== Boundary Values Tests ==================

        //TC01: List is empty
        List<Point> points2 = new LinkedList<>();
        assertNull(ray.findClosestPoint(points2), "Found point is not as the expected one");

        //TC02: Point in the beginning of list
        List<Point> points3 = new LinkedList<>();
        points3.add(p1);
        points3.add(p2);
        points3.add(p3);
        assertEquals(p1, ray.findClosestPoint(points3), "Found point is not as the expected one");

        //TC03: Point in the end of list
        List<Point> points4 = new LinkedList<>();
        points4.add(p2);
        points4.add(p3);
        points4.add(p1);
        assertEquals(p1, ray.findClosestPoint(points4), "Found point is not as the expected one");
    }
}