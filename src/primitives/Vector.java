package primitives;

public class Vector extends Point {
    public Vector(double d1, double d2, double d3) {
        super(d1, d2, d3);
    }

    public Vector(Double3 point) {
        super(point);
    }

    public double lengthSquared() {
        return Math.abs(xyz.d1 * xyz.d1 +  xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector subtract(Vector v) {
        return new Vector(v.xyz.subtract(this.xyz));
    }

    public double dotProduct(Vector v) {
        return this.xyz.d1 * v.xyz.d1 + this.xyz.d2 * v.xyz.d2 + this.xyz.d3 * v.xyz.d3;
    }

    public Vector crossProduct(Vector v) {
        return new Vector(this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2,
                this.xyz.d1 * v.xyz.d3 - this.xyz.d3 * v.xyz.d1, this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1);
    }

    public Vector normalize() {
        return new Vector(xyz.d1 / lengthSquared(), xyz.d2 / lengthSquared(), xyz.d3 / lengthSquared());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Vector other)
            return this.xyz.equals(other.xyz);
        return false;
    }
}
