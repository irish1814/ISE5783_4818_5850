package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Spheres
 *
 * @author Elad Radomski & Ishay Houri
 */
class SphereTest {
    /**
     * Test method for {@link geometries.Sphere#Sphere}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Sphere s = new Sphere(2, new Point(0, 0, 0));
        // TC01: check the Normal at point (1,1,sqrt(2))
        assertEquals(new Vector(0.5, 0.5, ((double) 1 / Math.sqrt(2))), s.getNormal(new Point(1, 1, Math.sqrt(2))));
    }

    /**
     * Test method for {@link geometries.Sphere#findIntsersections(Ray)}.
     */
    @Test
   public void findIntersections(Ray ray) {
        // ============ Equivalence Partitions Tests ==============

        Sphere s = new Sphere(1, new Point(0, 0, 0));

        // a direction vector (constant in the tests) of the ray
        Vector v  = new Vector(1, 0, 0);

        // TC01: ray is out of the sphere - there is no intersections
        Ray r1 = new Ray(new Vector(2, 0, -2), new Point(0, 0, 2));
        assertNull(s.findIntsersections(r1));

        // TC02: there is an intersection between the ray and the sphere when the ray is in front of the sphere
        Ray r2 = new Ray(v, new Point(-2, 0, 0));
        assertEquals(List.of(new Point(-1, 0, 0), new Point(1, 0, 0)), s.findIntsersections(r2));

        // TC03: there is an intersection between the ray and the sphere when the ray is in the sphere
        Ray r3 = new Ray(v, new Point(0, 0, 0));
        assertEquals(List.of(new Point(1, 0, 0)), s.findIntsersections(r3));

        // TC04: there is an intersection between the ray and the sphere when the ray is after the sphere
        Ray r4 = new Ray(v, new Point(2, 0, 0));
        assertNull(s.findIntsersections(r4));

        // =============== Boundary Values Tests ==================

        // TC01: 6 possible cases - the ray is passing through the center of the sphere

        // 1: the ray is starting from the center of the sphere
        Ray rayCenter = new Ray(v, new Point(0, 0, 0));
        assertEquals(List.of(new Point(1, 0, 0)), s.findIntsersections(rayCenter));

        // 2: the ray is above the boundary of the sphere (in front of the sphere)
        Ray rayBoundaryFront = new Ray(v, new Point(1, 0, 0));
        assertNull(s.findIntsersections(rayBoundaryFront));

        // 3: the ray is above the boundary of the sphere (after the sphere)
        Ray rayBoundaryAfter = new Ray(v, new Point(-1, 0, 0));
        assertEquals(List.of(new Point(1, 0, 0)), s.findIntsersections(rayBoundaryAfter));

        // 4: ray is starting in the boundary of the sphere and pass through the center
        Ray rayStart = new Ray(v, new Point(1, 0, 0));
        assertEquals(List.of(new Point(1, 0, 0)), s.findIntsersections(rayStart));

        // 5: ray is after the sphere
        Ray rayAfter = new Ray(v, new Point(2, 0, 0));
        assertNull(s.findIntsersections(rayAfter));

        // 6: the ray has 2 intersections point
        Ray rayIntersection = new Ray(v, new Point(-2, 0, 0));
        assertEquals(List.of(new Point(-1, 0, 0), new Point(1, 0, 0)), s.findIntsersections(rayIntersection));

        // TC02: The ray is passing through the sphere but not through the center
        // 1: The ray starts from the boundary and pass through the sphere
        Ray oneIntersection = new Ray(new Vector(-1.49, 0, 0.87), new Point(1, 0, 0));
        assertEquals(List.of(new Point(-0.49, 0, 0.83)), s.findIntsersections(oneIntersection));

        // 2: The ray starts from the boundary of the sphere but do not pass through
        Ray zeroIntersection = new Ray(new Vector(-0.51, 0, 0.86), new Point(0.51, 0, 0.86));
        assertNull(s.findIntsersections(zeroIntersection));

        // TC03: 3 cases when the ray is tangent to the sphere
        Vector u = new Vector(-0.86, -0.17, 0.75);
        // 1: ray starts before the sphere
        Ray tangentBefore = new Ray(u, new Point(0.64, -0.17, 0.75));
        assertNull(s.findIntsersections(tangentBefore));

        // 2: ray starts with a tangent to the sphere
        Ray tangentStart = new Ray(u, new Point(1.5, 0, 0));
        assertNull(s.findIntsersections(tangentStart));

        // 3: ray starts after the sphere
        Ray tangentAfter = new Ray(u, new Point(0.29, -0.24, 1.05));
        assertNull(s.findIntsersections(tangentAfter));

        // TC04: ray is orthogonal to the normal that pass through the center of the sphere
        Ray orthgonalRay = new Ray(new Vector(0, 0, 1), new Point(2, 0, 0));
        assertNull(s.findIntsersections(orthgonalRay));

    }
}