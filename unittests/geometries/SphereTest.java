package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for geometries.Sphere class
 *
 * @author Elad Radomski &amp; Ishay Houri
 */
class SphereTest {
    /**
     * Test method for {@link geometries.Sphere#Sphere}.
     */
    @Test
    void getNormal() {
        Sphere s = new Sphere(2, new Point(0, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: check the Normal at point (1,1,sqrt(2))
        assertEquals(new Vector(0.5, 0.5, ((double) 1 / Math.sqrt(2))), s.getNormal(new Point(1, 1, Math.sqrt(2))));
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(Ray)}.
     */
    @Test
    public void findIntersections() {
        Sphere s = new Sphere(1, new Point(0, 0, 0));

        // a direction vector (constant in the tests) of the ray
        Vector v = new Vector(1, 0, 0);

        // ============ Equivalence Partitions Tests ==============

        // TC01: ray is out of the sphere - there is no intersections
        Ray r1 = new Ray(new Vector(2, 0, -2), new Point(0, 0, 2));
        assertNull(s.findIntersections(r1));

        // TC02: there is an intersection between the ray and the sphere when the ray is in front of the sphere
        Ray r2 = new Ray(v, new Point(-2, 0, 0));
        assertEquals(List.of(new Point(1, 0, 0), new Point(-1, 0, 0)), s.findIntersections(r2));

        // TC03: there is an intersection between the ray and the sphere when the ray is in the sphere
        Ray r3 = new Ray(v, new Point(0, 0, 0));
        assertEquals(List.of(new Point(1, 0, 0)), s.findIntersections(r3));

        // TC04: there is an intersection between the ray and the sphere when the ray is after the sphere
        Ray r4 = new Ray(v, new Point(2, 0, 0));
        assertNull(s.findIntersections(r4));

        // =============== Boundary Values Tests ==================

        // 6 tests cases - the ray is passing through the center of the sphere

        // TC01: the ray is starting from the center of the sphere
        Ray rayCenter = new Ray(v, new Point(0, 0, 0));
        assertEquals(List.of(new Point(1, 0, 0)), s.findIntersections(rayCenter));

        // TC02: the ray is above the boundary of the sphere (in front of the sphere)
        Ray rayBoundaryFront = new Ray(v, new Point(1, 0, 0));
        assertNull(s.findIntersections(rayBoundaryFront));

        // TC03: the ray is above the boundary of the sphere (after the sphere)
        Ray rayBoundaryAfter = new Ray(v, new Point(-1, 0, 0));
        assertEquals(List.of(new Point(1, 0, 0)), s.findIntersections(rayBoundaryAfter));

        // TC04: ray is starting in the boundary of the sphere and pass through the center
        Ray rayStart = new Ray(v, new Point(-1, 0, 0));
        assertEquals(List.of(new Point(1, 0, 0)), s.findIntersections(rayStart));

        // TC05: ray is after the sphere
        Ray rayAfter = new Ray(v, new Point(2, 0, 0));
        assertNull(s.findIntersections(rayAfter));

        // TC06: the ray has 2 intersections point
        Ray rayIntersection = new Ray(v, new Point(-2, 0, 0));
        assertEquals(List.of( new Point(1, 0, 0), new Point(-1, 0, 0)), s.findIntersections(rayIntersection));

        // 2 tests cases - The ray is passing through the sphere but not through the center

        // TC01: The ray starts from the boundary and pass through the sphere
        Ray oneIntersection = new Ray(new Vector(-1, 0, 1), new Point(1, 0, 0));
        assertEquals(List.of(new Point(0, 0, 1)), s.findIntersections(oneIntersection));

        // TC02: The ray starts from the boundary of the sphere but do not pass through
        Ray zeroIntersection = new Ray(new Vector(1, 0, 1), new Point(1, 0, 0));
        assertNull(s.findIntersections(zeroIntersection));

        // 3 cases - The ray is tangent to the sphere

        Vector u = new Vector(1, 1, 0);
        // TC01: ray starts before the sphere
        Ray tangentBefore = new Ray(u, new Point(1, -1, 0));
        assertNull(s.findIntersections(tangentBefore));

        // TC02: ray starts with a tangent to the sphere
        Ray tangentStart = new Ray(u, new Point(1, 0, 0));
        assertNull(s.findIntersections(tangentStart));

        // TC03: ray starts after the sphere
        Ray tangentAfter = new Ray(u, new Point(1, 1, 0));
        assertNull(s.findIntersections(tangentAfter));

        // TC12: ray is orthogonal to the normal that pass through the center of the sphere
        Ray orthgonalRay = new Ray(new Vector(0, 0, 1), new Point(2, 0, 0));
        assertNull(s.findIntersections(orthgonalRay));

    }

    @Test
    public void findGeoIntersections() {
        Sphere s = new Sphere(1, new Point(0, 0, 0));

        // a direction vector (constant in the tests) of the ray
        Vector v = new Vector(1, 0, 0);

        // TC01: there is an intersection between the ray and the sphere and less than the max distance
        Ray r = new Ray(v, new Point(-2, 0, 0));
        assertEquals(List.of(new Intersectable.GeoPoint(s,new Point(1, 0, 0)),
                        new Intersectable.GeoPoint(s,new Point(-1, 0, 0)))
                ,s.findGeoIntersections(r,4));

        // TC02: there is an intersection between the ray and the sphere and one more than the max distance one less
        assertEquals(List.of(new Intersectable.GeoPoint(s,new Point(-1, 0, 0)))
                ,s.findGeoIntersections(r,2));

        // TC03: there is an intersection between the ray and the sphere and two of them less than max distance
        assertNull(s.findGeoIntersections(r,0.5));

    }
}