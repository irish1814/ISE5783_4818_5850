package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
    private Point center;

    public Sphere(double r) {
        super(r);
    }

    @Override
    public Vector getNormal(Point p) { return null; }
}
