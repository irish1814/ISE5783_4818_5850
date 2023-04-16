package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Vector class
 * @author Elad Radomski & Ishay Houri
 */
class VectorTest {
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void add() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 0, 1);

        // =============== Boundary Values Tests ==================
        //two vectors with same direction
        assertEquals(new Vector (3,6,9),v1.add(new Vector (2,4,6)));
        //two vectors with opposite direction
        assertEquals(new Vector (-1,-2,-3),v1.add(new Vector (-2,-4,-6)));
        //same vectors with opposite direction
        assertThrows(IllegalArgumentException.class, ()->v1.add(new Vector(-1, -2, -3)),
                "ERROR: Vector + -itself does not throw an exception");
        //same vector
        assertEquals(new Vector (2,4,6),v1.add(v1));
        //vertical vectors
        assertEquals(new Vector (0,1,1),v2.add(new Vector (0,1,0)));

        // ============ Equivalence Partitions Tests ==============
        //sharp angle vectors
        assertEquals(new Vector (0,1,6),v2.add(new Vector (0,1,5)));
        //obtuse angle vectors
        assertEquals(new Vector (0,1,-4),v2.add(new Vector (0,1,-5)));

    }
    @Test
    void subtract() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 0, 1);

        // =============== Boundary Values Tests ==================
        //two vectors with same direction
        assertEquals(new Vector (-1,-2,-3),v1.subtract(new Vector (2,4,6)));
        //two vectors with opposite direction
        assertEquals(new Vector (3,6,9),v1.subtract(new Vector (-2,-4,-6)));
        //same vectors with opposite direction
        assertEquals(new Vector (2,4,6),v1.subtract(new Vector (-1,-2,-3)));
        //same vector
        assertThrows(IllegalArgumentException.class, ()->v1.subtract(v1),
                "ERROR: Vector - itself does not throw an exception");
        //vertical vectors
        assertEquals(new Vector (0,-1,1),v2.subtract(new Vector (0,1,0)));

        // ============ Equivalence Partitions Tests ==============
        //sharp angle vectors
        assertEquals(new Vector (0,-1,-4),v2.subtract(new Vector (0,1,5)));
        //obtuse angle vectors
        assertEquals(new Vector (0,-1,6),v2.subtract(new Vector (0,1,-5)));
    }

    @Test
    void dotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 0, 1);

        // =============== Boundary Values Tests ==================
        //two vectors with same direction
        assertEquals(28,v1.dotProduct(new Vector (2,4,6)),0.00001);
        //two vectors with opposite direction
        assertEquals(-28,v1.dotProduct(new Vector (-2,-4,-6)),0.00001);
        //same vectors with opposite direction
        assertEquals(-14,v1.dotProduct(new Vector (-1,-2,-3)),0.00001);
        //same vector
        assertEquals(14,v1.dotProduct(v1),0.00001);
        //vertical vectors
        assertEquals(0,v2.dotProduct(new Vector (0,1,0)),0.00001);

        // ============ Equivalence Partitions Tests ==============
        //sharp angle vectors
        assertEquals(5,v2.dotProduct(new Vector (0,1,5)),0.00001);
        //obtuse angle vectors
        assertEquals(-5,v2.dotProduct(new Vector (0,1,-5)),0.00001);
    }

    @Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 0, 1);

        // =============== Boundary Values Tests ==================
        //two vectors with same direction
        assertThrows(IllegalArgumentException.class, ()->v1.crossProduct(new Vector (2,4,6)),
                "ERROR: VectorXVector with same direction does not throw an exception");
        //two vectors with opposite direction
        assertThrows(IllegalArgumentException.class, ()->v1.crossProduct(new Vector (-2,-4,-6)),
                "ERROR: VectorXVector with opposite direction does not throw an exception");
        //same vectors with opposite direction
        assertThrows(IllegalArgumentException.class, ()->v1.crossProduct(new Vector (-1,-2,-3)),
                "ERROR: same vector with opposite direction does not throw an exception");
        //same vector;
        assertThrows(IllegalArgumentException.class, ()->v1.crossProduct(v1),
                "ERROR: (Vector X itself) does not throw an exception");
        //vertical vectors
        assertEquals(new Vector(-1,0,0),v2.crossProduct(new Vector (0,1,0)));

        // ============ Equivalence Partitions Tests ==============
        //sharp angle vectors
        assertEquals(new Vector(-1,0,0),v2.crossProduct(new Vector (0,1,5)));
        //obtuse angle vectors
        assertEquals(new Vector(-1,0,0),v2.dotProduct(new Vector (0,1,-5)));
    }
    @Test
    void length() {
        // =============== Boundary Values Tests ==================
        assertEquals(5,new Vector(0,3,4).length(),0.00001);
    }
    @Test
    void lengthSquared() {
        // =============== Boundary Values Tests ==================
        assertEquals(14,new Vector(1,2,3).lengthSquared(),0.00001);
    }


    @Test
    void normalize() {
        // =============== Boundary Values Tests ==================
        assertEquals(new Vector((1/(Math.sqrt(14))),Math.sqrt((double)2/7),((double)3/Math.sqrt(14))),new Vector(1,2,3).normalize());
    }
}