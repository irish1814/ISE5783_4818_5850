package primitives;

public class Point {
    protected Double3 xyz;

    public Point(double d1, double d2, double d3) {
        xyz = new Double3(d1, d2, d3);
    }

    Point(Double3 point) {
        xyz = new Double3(point.d1, point.d2, point.d3);
    }

    public Point add(Point p) {
        return new Point(this.xyz.add(p.xyz));
    }

    public Point subtract(Point p) {
        return new Point(p.xyz.subtract(this.xyz));
    }

    public double distanceSquared(Point p) {
        return (this.xyz.d1 - p.xyz.d1) * (this.xyz.d1 - p.xyz.d1) + (this.xyz.d2 - p.xyz.d2) * (this.xyz.d2 - p.xyz.d2)
                + (this.xyz.d3 - p.xyz.d3) * (this.xyz.d3 - p.xyz.d3);
    }

    public double distance(Point p) {
        // distance between 3 point - √ ((x2 - x1)² + (y2 - y1)² + (z2 - z1)²)
        return Math.sqrt(distanceSquared(p));
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
