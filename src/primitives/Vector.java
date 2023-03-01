package primitives;

public class Vector extends Point {
    public Vector(double d1, double d2, double d3) {
        super(d1, d2, d3);
        if(Util.isZero(d1) && Util.isZero(d2) && Util.isZero(d3)) {
            throw new IllegalArgumentException("Vector of zero's are forbidden !");
        }
    }

    Vector(Double3 point) {
        super(point);
        if(Util.isZero(point.d1) && Util.isZero(point.d2) && Util.isZero(point.d3)) {
            throw new IllegalArgumentException("Vector of zero's are forbidden !");
        }
    }

    public double lengthSquared() {
        return Math.abs(xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3);
    }

    public double length() {
        // length of a Vector is equal to √ (x² + y² + z²)
        return Math.sqrt(lengthSquared());
    }

    public Vector add(Vector v) {
        if(v.xyz.add(this.xyz).equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Addition of the two vector equals to the ZERO vector");
        }
        return new Vector(v.xyz.add(this.xyz));
    }

    public double dotProduct(Vector v) {
        return this.xyz.d1 * v.xyz.d1 + this.xyz.d2 * v.xyz.d2 + this.xyz.d3 * v.xyz.d3;
    }

    public Vector crossProduct(Vector v) {
        // The vectors are a multiplication of a scalar k - (v1, v2, v3) = k(v4, v5, v6)
        if(this.equals(v)) {
            throw new IllegalArgumentException("The crossProduct of parallel vectors is 0.");
        }
        // Based on the matrix multiplication
        return new Vector(this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2,
                - this.xyz.d1 * v.xyz.d3 - this.xyz.d3 * v.xyz.d1, this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1);
    }

    public Vector normalize() {
        return new Vector(xyz.d1 / length(), xyz.d2 / length(), xyz.d3 / length());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;


        if (obj instanceof Vector v) {
            // The vectors are a multiplication of a scalar k - (v1, v2, v3) = k(v4, v5, v6)
            double k = this.xyz.d1 / v.xyz.d1;
            return this.xyz.reduce(k).equals(v.xyz) || v.xyz.reduce(k).equals(this.xyz);
        }

        if (obj instanceof Point other) {
            return this.xyz.equals(other.xyz);
        }

        return false;
    }
}
