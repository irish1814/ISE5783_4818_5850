package primitives;

/** This class will serve all primitive and geometries classes based on a Vector
 * @author Ishay Houri & Elad Raz
 * */

public class Vector extends Point {

    /** Constructor to initialize Double3 based object with its three number values
     * @param d1 first number value
     * @param d2 second number value
     * @param d3 third number value */
     public Vector(double d1, double d2, double d3) {
        super(d1, d2, d3);
        if(Util.isZero(d1) && Util.isZero(d2) && Util.isZero(d3)) {
            throw new IllegalArgumentException("Vector of zero's are forbidden !");
        }
    }

    /** Constructor to initialize Double3 based object with its three number values
     * @param point which is an object type Double3
     * */
    Vector(Double3 point) {
        super(point);
        if(Util.isZero(point.d1) && Util.isZero(point.d2) && Util.isZero(point.d3)) {
            throw new IllegalArgumentException("Vector of zero's are forbidden !");
        }
    }

    /** Length Squared of the current vector
     * @return the squared length of the vector according to the formula - | (x² + y² + z²) |
     * */
    public double lengthSquared() {
        return Math.abs(xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3);
    }

    /** Length of the current vector
     * @return the length of the vector according to the formula - √ (x² + y² + z²)
     * */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /** Addition of two vectors
     * @param v will be the second vector that we add to the first vector
     * @return new Vector object of the addition of the two vectors
     * */
    public Vector add(Vector v) {
        if(v.xyz.add(this.xyz).equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Addition of the two vector equals to the ZERO vector");
        }
        return new Vector(v.xyz.add(this.xyz));
    }

    /** Scalar multiplication between two vectors
     * @param v is the second vector of the scalar multiplication
     * @return the scalar multiplication based on the math formula - (x1 * x2 + y1 * y2 + z1 * z2)
     * */
    public double dotProduct(Vector v) {
        return this.xyz.d1 * v.xyz.d1 + this.xyz.d2 * v.xyz.d2 + this.xyz.d3 * v.xyz.d3;
    }

    /** Cross Product between two vectors
     * @param v is the second vector of the operation
     * @return Normal vector - N which sustain N.dotProduct(V1) = 0 AND N.dotProduct(V2) = 0
     * */
    public Vector crossProduct(Vector v) {
        // The vectors are a multiplication of a scalar k - (v1, v2, v3) = k(v4, v5, v6)
        if(this.equals(v)) {
            throw new IllegalArgumentException("The crossProduct of parallel vectors is 0.");
        }
        // Based on the matrix multiplication
        return new Vector(this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2,
                - this.xyz.d1 * v.xyz.d3 - this.xyz.d3 * v.xyz.d1, this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1);
    }

    /** Normalize the current vector
     * @return a normal vector of the current vector which its size is equal to 1
     * */
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

    @Override
    public String toString() {
        return "{ t" + super.toString() + " | t ∈ R }";
    }
}
