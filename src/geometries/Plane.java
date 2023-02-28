package geometries;

import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
    Point q0;
    Vector normal;
    Plane(Point p1, Point p2, Point p3) {

    }

    public Vector getNormal() {
        return null;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
