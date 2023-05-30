package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represents a camera in 3 dimension space
 *
 * @author Ishay Houri & Elad Radomski
 */

public class Camera {
    private final Point p0;
    private final Vector vUp, vTo, vRight;
    private double width, height, distance;

    /**
     * Constructs a new camera with the specified location, direction vectors.
     *
     * @param to An horizontal vector
     * @param up A vertical vector
     */
    public Camera(Point p, Vector to, Vector up) {
        // Check if the two vectors are vertical to each other
        if (!isZero(up.dotProduct(to)))
            throw new IllegalArgumentException("Vectors aren't vertical");

        vUp = up.normalize();
        vTo = to.normalize();
        vRight = vTo.crossProduct(vUp);
        p0 = p;
    }

    // ***************** Getters ********************** //
    public Point getP0() {
        return p0;
    }

    public Vector getVto() {
        return vTo;
    }

    public Vector getVup() {
        return vUp;
    }

    public Vector getVRight() {
        return vRight;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }

    /**
     * Set a new Distance to the ViewPlain
     *
     * @param d Given distance
     * @return camera object with the new distance
     */
    public Camera setVPDistance(double d) {
        if (alignZero(d) <= 0)
            throw new IllegalArgumentException("Distance can't be negative");

        this.distance = d;
        return this;
    }

    /**
     * Set a new Size to the ViewPlain
     *
     * @param width  Given width
     * @param height Given height
     * @return camera object with the new sets of width and height
     */
    public Camera setVPSize(double width, double height) {
        if (alignZero(width) <= 0 || alignZero(height) <= 0)
            throw new IllegalArgumentException("width and height can't be negative");

        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Constructs a ray from the camera to a specific point in the view plane.
     *
     * @param nX  number of pixels in the rows
     * @param nY  number of pixels in the columns
     * @param i,j index of the pixel that the ray pass through, when 'i' is the index of nY
     *            and 'j' is the index of nX
     * @return ray from the camera's lens to the center of a pixel in the view plane
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // View-plain center point
        Point center = p0.add(vTo.scalarProduct(distance));

        // Get the height and width of each pixel
        double rY = height / nY, rX = width / nX;
        double xJ = (j - (nX - 1) / 2d) * rX;
        double yI = -(i - (nX - 1) / 2d) * rY;

        Point pixelCenter = center;
        if (!isZero(xJ)) {
            pixelCenter = pixelCenter.add(vRight.scalarProduct(xJ));
        }
        if (!isZero(yI)) {
            pixelCenter = pixelCenter.add(vUp.scalarProduct(yI));
        }

        Vector viewPlaneVector = pixelCenter.subtract(p0);
        return new Ray(viewPlaneVector, p0);
    }
}
