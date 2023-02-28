package primitives;

public class Ray {
    Point center;
    Vector direction;

    Ray(Vector v, Point p) {
        // The given vector isn't normalized
        if(v.length() != 1) {
            direction = v.normalize();
        }
        else {
            direction = new Vector(v.xyz.d1, v.xyz.d2, v.xyz.d3);
        }
        center = new Point(p.xyz.d1, p.xyz.d2, p.xyz.d3);
    }
}
