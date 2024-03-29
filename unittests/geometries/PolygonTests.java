package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for geometries.Polygon class
 *
 * @author Dan
 */
public class PolygonTests {

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                {new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1)};
        Polygon pol = new Polygon(pts);

        // TC02: ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");

        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));

        // TC03: check if the normal vector length = 1
        assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");

        // TC04: check if the result is orthogonal to all edges
        for (int i = 0; i < 3; ++i)
            assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1]))),
                    "Polygon's normal is not orthogonal to one of the edges");
    }

    @Test
    public void findIntersections() {
        Polygon polygon = new Polygon(new Point(1, 2, 0), new Point(-2, 0, 0), new Point(0, -2, 0),
                new Point(2, -2, 0), new Point(3, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: ray has an intersection with the triangle - inside the triangle
        Ray insideRay = new Ray(new Vector(1, 0, -2), new Point(0, 0, 2));
        assertEquals(List.of(new Point(1, 0, 0)), polygon.findIntersections(insideRay));

        // TC02: ray is not intersect with the triangle - outside against edge of the triangle
        Ray outsideAgainstEdgeRay = new Ray(new Vector(-1, 1, -1), new Point(0, 0, 1));
        assertNull(polygon.findIntersections(outsideAgainstEdgeRay));

        // TC03: ray is not intersect with the triangle - outside against vertex of the triangle
        Ray outsideAgainstVertexRay = new Ray(new Vector(-3, 0, -2), new Point(0, 0, 2));
        assertNull(polygon.findIntersections(outsideAgainstVertexRay));

        // =============== Boundary Values Tests ==================

        // TC01: ray has an intersection with the triangle - on the edge
        Ray onEdgeRay = new Ray(new Vector(2.5, -1, 0), new Point(0, 0, 2));
        assertNull(polygon.findIntersections(onEdgeRay));

        // TC02: ray has an intersection with the triangle - on the vertex
        Ray onVertexRay = new Ray(new Vector(3, 0, -1), new Point(0, 0, 1));
        assertNull(polygon.findIntersections(onVertexRay));

        // TC03: ray is not intersect with the triangle - outside on edge's continuation
        Ray outsideEdgeContinuationRay = new Ray(new Vector(0, 3, -2), new Point(0, 0, 2));
        assertNull(polygon.findIntersections(outsideEdgeContinuationRay));
    }

    @Test
    public void findGeoIntersections() {
        Polygon polygon = new Polygon(new Point(1, 2, 0), new Point(-2, 0, 0), new Point(0, -2, 0),
                new Point(2, -2, 0), new Point(3, 0, 0));

        // TC01: ray has an intersection with the triangle - inside the triangle, and distance is less than max
        Ray insideRay = new Ray(new Vector(1, 0, -2), new Point(0, 0, 2));
        assertEquals(List.of(new Intersectable.GeoPoint(polygon,new Point(1, 0, 0))),
                polygon.findGeoIntersections(insideRay,4));

        // TC02: ray has an intersection with the triangle - inside the triangle, and distance is less than max
        assertNull(polygon.findGeoIntersections(insideRay,1));


    }
}


