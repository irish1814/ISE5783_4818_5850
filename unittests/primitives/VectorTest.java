package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for primitives.Vector class
 *
 * @author Elad Radomski &amp; Ishay Houri
 */
class VectorTest {

    /**
     * Test method for {@link primitives.Vector#add(Point)}.
     */
    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: check if Add function works
        assertEquals(new Vector(-1, -2, -3), v1.add(new Vector(-2, -4, -6)), "add function dose not work correctly");

        // =============== Boundary Values Tests ==================

        // TC01: addition of the same opposite vector does not throw a zero vector error
        assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1, -2, -3)),
                "addition of the same opposite vector does not throw a zero vector error");
    }

    /**
     * Test method for {@link primitives.Vector#subtract(Point)}.
     */
    @Test
    void testSubtract() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 0, 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: check if subtract function works
        assertEquals(new Vector(1, 2, 2), v1.subtract(v2));

        // =============== Boundary Values Tests ==================

        // TC01: subtraction of the same vector does not throw zero vector error
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1),
                "ERROR: Vector - itself does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @Test
    void dotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 0, 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: check if the function works
        assertEquals(3, v1.dotProduct(v2),
                "dotProduct does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC01: dot product between orthogonal vector (V = kU | k ∈ R)
        assertEquals(0, v1.dotProduct(new Vector(0, 3, -2)),
                "dotProduct between orthogonal vectors is not 0");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}.
     */
    @Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 0, 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: cross product with a vector with a less than 90-degree angle
        assertEquals(new Vector(-1, 0, 0), v2.crossProduct(new Vector(0, 1, 5)));

        // TC02: cross product with a vector with a greater than 90-degree angle
        assertEquals(new Vector(-1, 0, 0), v2.crossProduct(new Vector(0, 1, -5)));

        // =============== Boundary Values Tests ==================

        // TC01: two vectors with same direction
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(new Vector(2, 4, 6)),
                "ERROR: VectorXVector with same direction does not throw an exception");

        // TC02: two vectors with opposite direction
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(new Vector(-2, -4, -6)),
                "ERROR: VectorXVector with opposite direction does not throw an exception");

        // TC03: same vectors with opposite direction
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(new Vector(-1, -2, -3)),
                "ERROR: same vector with opposite direction does not throw an exception");

        // TC04: cross product with the same vector
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v1),
                "ERROR: Vector x itself does not throw an exception");

        // TC05: vertical vectors
        assertEquals(new Vector(-1, 0, 0), v2.crossProduct(new Vector(0, 1, 0)));
    }

    /**
     * Test method for {@link primitives.Vector#scalarProduct(double)}.
     * */
    @Test
    void scalarProductTest() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: check if the scalarProduct function works
        assertEquals(new Vector(2, 4, 6), v1.scalarProduct(2),
                "scalarProduct function does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC01: a scalar multiplication by 0 will create (0, 0, 0) vector
        assertThrows(IllegalArgumentException.class, () -> v1.scalarProduct(0),
                "scalar product with 0 value are forbidden");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: check if the length is as expected
        assertEquals(5, new Vector(0, 3, 4).length(), 0.00001);
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: check if the squared length is as expected
        assertEquals(14, new Vector(1, 2, 3).lengthSquared(), 0.00001);
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void normalize() {
        Vector v1 = new Vector(1, 2, 3);
        Vector u = v1.normalize();

        // ============ Equivalence Partitions Tests ==============

        // TC01: check if the normalize vector is as expected
        assertEquals(1, u.length(), 0.00000001, "The normalized vector is not a unit vector");

        // =============== Boundary Values Tests ==================

        // TC01: cross product between parallel vector does not throw an exception
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(u),
                "The normalized vector is not parallel to the original one");
    }
}