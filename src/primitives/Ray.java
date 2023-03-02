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
            direction = new Vector(v.xyz);
        }
        p0 = new Point(p.xyz);
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDirection() {
        return direction;
    }
}
