package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/** Testing Spheres
 * @author Elad Radomski & Ishay Houri */
class SphereTest {
    /** Test method for {@link geometries.Sphere#Sphere}. */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Sphere s = new Sphere(2,new Point(0,0,0));
        //check the Normal at point (1,1,sqrt(2))
        assertEquals(new Vector(0.5,0.5,((double)1/Math.sqrt(2))),s.getNormal(new Point(1,1,Math.sqrt(2))));
    }
}