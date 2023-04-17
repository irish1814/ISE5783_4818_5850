package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/** Testing Polygons
 * @author Elad Radomski & Ishay Houri */
class CylinderTest {
    /** Test method for {@link geometries.Cylinder#Cylinder}. */
    @Test
    void getNormal() {
        Cylinder c = new Cylinder(2,5,new Ray(new Vector(0,0,1),new Point(0,0,0)));
        // ============ Equivalence Partitions Tests ==============
        //check on the round surface
        assertEquals(new Vector(0,1,0),c.getNormal(new Point(0,2,4)));
        //check on the first base
        assertEquals(new Vector(0,0,1),c.getNormal(new Point(-1,0,0)));
        //check on the second base
        assertEquals(new Vector(0,0,1),c.getNormal(new Point(0,1,5)));

        // =============== Boundary Values Tests ==================
        //check on the first base edge - should be like "base" normal
        assertEquals(new Vector(0,0,1),c.getNormal(new Point(0,2,0)));
        //check on the second base edge - should be like "base" normal
        assertEquals(new Vector(0,0,1),c.getNormal(new Point(2,0,5)));
        //check in the center of the first base
        assertEquals(new Vector(0,0,1),c.getNormal(new Point(0,0,0)));
        //check in the center of the second base
        assertEquals(new Vector(0,0,1),c.getNormal(new Point(0,0,5)));

    }
}