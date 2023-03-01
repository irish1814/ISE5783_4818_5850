package primitives;

public class Ray {
    private Point p0;
    private Vector direction;

    Ray(Vector v, Point p) {
        // The given vector isn't normalized
        if(v.length() != 1) {
            direction = v.normalize();
        }
        else {
            direction = new Vector(v.xyz.d1, v.xyz.d2, v.xyz.d3);
        }
        p0 = new Vector(p.xyz);
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDirection() {
        return direction;
    }
}
