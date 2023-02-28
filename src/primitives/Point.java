package primitives;

public class Point {
    Double3 xyz;

    public Point(double d1, double d2, double d3) {
        xyz = new Double3(d1, d2, d3);
    }

    public Point(Double3 point) {
        xyz = new Double3(point.d1, point.d2, point.d3);
    }

    public Point add(Point p) {
        return new Point(xyz.d1 + p.xyz.d1, xyz.d2 + p.xyz.d2, xyz.d3 + p.xyz.d3);
    }

    public Point subtract(Point p) {
        return new Point(p.xyz.d1 - xyz.d1, p.xyz.d2 - xyz.d2, p.xyz.d3 - xyz.d3);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } if (obj instanceof Point other) {
            return this.xyz.equals(other.xyz);
        }
        return false;
    }
}
