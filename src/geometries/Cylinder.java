package geometries;

import primitives.Point;
import primitives.Vector;

public class Cylinder extends RadialGeometry {
    double height;

    public Cylinder(double r, double h) {
        super(r);
        height = h;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
