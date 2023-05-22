package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing primitives.Ray
 *
 * @author Ishay Houri & Elad Radomski
 */
class RayTest {

    /**
     * Test method for {@link primitives.Ray#(primitives.Ray)}.
     */
    @Test
    void equalsRays() {
        Ray ray1 = new Ray(new Vector(1, 2, 3), new Point(1, 0, 0));
        Ray ray2 = new Ray(new Vector(1, 2, 3), new Point(1, 0, 0));
        assertEquals(ray1, ray2, "Equals doesn't work properly");
    }
}