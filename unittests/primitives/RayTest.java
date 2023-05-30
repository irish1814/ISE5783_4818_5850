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
        Ray ray = new Ray(new Vector(1, 2, 3), new Point(1, 0, 0));
        List<Point> l = new LinkedList<>();
        assertNull(ray.findClosestPoint(l));
    }
}