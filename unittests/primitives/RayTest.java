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
        List<Point> middleOfList = new LinkedList<>();
        middleOfList.add(p2);
        middleOfList.add(p1);
        middleOfList.add(p3);
        assertEquals(p1, ray.findClosestPoint(middleOfList), "Found point is not as the expected one");

        // =============== Boundary Values Tests ==================

        //TC01: List is empty
        List<Point> emptyList = new LinkedList<>();
        assertNull(ray.findClosestPoint(emptyList), "Found point is not as the expected one");

        //TC02: Point in the beginning of list
        List<Point> startOfList = new LinkedList<>();
        startOfList.add(p1);
        startOfList.add(p2);
        startOfList.add(p3);
        assertEquals(p1, ray.findClosestPoint(startOfList), "Found point is not as the expected one");

        //TC03: Point in the end of list
        List<Point> endOfList = new LinkedList<>();
        endOfList.add(p2);
        endOfList.add(p3);
        endOfList.add(p1);
        assertEquals(p1, ray.findClosestPoint(endOfList), "Found point is not as the expected one");
    }
}