package renderer;

import org.junit.jupiter.api.Test;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    /**
     * Calculates number of intersections between a geometric form and rays from the camera.
     * We'll use this function for the following tests
     *
     * @param camera Camera object used to construct the rays
     * @param geometric Geometric object form which has intersections with the rays of the camera
     * @return Number of intersections found
     */
    private int countIntersections(Camera camera, Intersectable geometric, int nX, int nY) {
        int count = 0;
        List<Point> list;
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                list = geometric.findIntersections(camera.constructRay(nX, nY, i, j));
                if (!list.isEmpty())
                    count += list.size();
            }
        }
        return count;
    }

    /**
    * Test method for
    * {@link geometries.Sphere#findIntersections(Ray)}.
    */
    @Test
    void testIntegrationSphere() {
        String diffCount = "Different number of intersections were found";
        Vector vTo = new Vector(0, 0, -1), vUp = new Vector(0, 1, 0);
        int nX = 3, nY = 3;

        // TC01: Sphere r = 1
        Camera cam1 = new Camera(new Point(0, 0, 0), vTo, vUp);
        cam1.setVPSize(3, 3);
        cam1.setVPDistance(1);
        assertEquals(2, countIntersections(cam1, new Sphere(1,
                        new Point(0, 0, -3)), nX, nY), diffCount);

        // TC02: Sphere r = 2.5
        Camera cam2 = new Camera(new Point(0, 0, 0.5), vTo, vUp);
        cam2.setVPSize(3, 3);
        cam2.setVPDistance(1);
        assertEquals(18, countIntersections(cam2, new Sphere(2.5,
                        new Point(0, 0, -2.5)), nX, nY), diffCount);

        // TC03: Sphere r = 2
        Camera cam3 = new Camera(new Point(0, 0, 0.5), vTo, vUp);
        cam3.setVPSize(3, 3);
        cam3.setVPDistance(1);
        assertEquals(10, countIntersections(cam3, new Sphere(2,
                        new Point(0, 0, -2)), nX, nY), diffCount);

        // TC04: Sphere r = 4
        Camera cam4 = new Camera(new Point(0, 0, 0.5), vTo, vUp);
        cam4.setVPSize(3, 3);
        cam4.setVPDistance(1);
        assertEquals(9, countIntersections(cam4, new Sphere(4,
                        new Point(0, 0, -0.5)), nX, nY), diffCount);

        // TC05: Sphere r = 0.5
        Camera cam5 = new Camera(new Point(0, 0, 0.5), vTo, vUp);
        cam5.setVPSize(3, 3);
        cam5.setVPDistance(1);
        assertEquals(0, countIntersections(cam5, new Sphere(0.5,
                        new Point(0, 0, 1)), nX, nY), diffCount);

    }

    /**
     * {@link geometries.Plane#findIntersections(Ray)}.
     */
    @Test
    void testIntegrationPlane() {
        String diffCount = "Different number of intersections were found";
        Vector vTo = new Vector(0, 0, -1), vUp = new Vector(0, 1, 0);
        int nX = 3, nY = 3;

        // TC01: Plane is parallel to the view plane
        Camera cam1 = new Camera(new Point(0, 0, 0.5), vTo, vUp);
        cam1.setVPSize(3, 3);
        cam1.setVPDistance(1);
        assertEquals(9, countIntersections(cam1, new Plane(new Point(1, 1, -5),
                        new Vector(0, 0, 1)), nX, nY), diffCount);

        // TC02: Plane is not parallel to the view plane (intersected in 9 points)
        Camera cam2 = new Camera(new Point(0, 0, 0.5), vTo, vUp);
        cam2.setVPSize(3, 3);
        cam2.setVPDistance(1);
        assertEquals(9,
                countIntersections(cam2, new Plane(new Point(1, 1, -5),
                        new Vector(0, 1, -5)), nX, nY), diffCount);

        // TC03: Plane is not parallel to the view plane (intersected in 6 points)
        Camera cam3 = new Camera(new Point(0, 0, 0.5), vTo, vUp);
        cam3.setVPSize(3, 3);
        cam3.setVPDistance(1);
        assertEquals(6, countIntersections(cam3, new Plane(new Point(0, 0, -5),
                        new Vector(0, 6, -5)), nX, nY), diffCount);
    }

    /**
     * {@link geometries.Triangle#findIntersections(Ray)}.
     */
    @Test
    void testIntegrationTriangle() {
        String diffCount = "Different number of intersections were found";
        Vector vTo = new Vector(0, 0, -1), vUp = new Vector(0, 1, 0);
        int nX = 3, nY = 3;

        // TC01: Triangle is parallel to the view plane and the size  of one pixel
        Camera cam1 = new Camera(new Point(0, 0, 0.5), vTo, vUp);
        cam1.setVPSize(3, 3);
        cam1.setVPDistance(1);
        assertEquals(1,
                countIntersections(cam1, new Triangle(new Point(0, 1, -2), new Point(1, -1, -2),
                        new Point(-1, -1, -2)), nX, nY), diffCount);

        // TC02: Plane is parallel to the view plane (intersected in 2 points)
        Camera cam2 = new Camera(new Point(0, 0, 0), vTo, vUp);
        cam2.setVPSize(3, 3);
        cam2.setVPDistance(1);
        assertEquals(2,
                countIntersections(cam2, new Triangle(new Point(0, 20, -2), new Point(1, -1, -2),
                        new Point(-1, -1, -2)), nX, nY), diffCount);
    }
}
