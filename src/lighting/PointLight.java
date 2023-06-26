package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A Class that represent Point light
 * and implements the Phong Reflectance Model
 *
 * @author Elad Radomski & Ishay Houri
 */
public class PointLight extends Light implements LightSource {

    final private Point position;

    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * Constructor for the point light
     *
     * @param intensity intensity of the light
     * @param pos       the position point of the light
     */
    public PointLight(Color intensity, Point pos) {
        super(intensity);
        position = pos;
    }

    /**
     * set kC coefficient
     *
     * @param c
     * @return the Point light itself
     */
    public PointLight setKc(double c) {
        kC = c;
        return this;
    }

    /**
     * set kL coefficient
     *
     * @param l
     * @return the Point light itself
     */
    public PointLight setKl(double l) {
        kL = l;
        return this;
    }

    /**
     * set kQ coefficient
     *
     * @param q
     * @return the Point light itself
     */
    public PointLight setKq(double q) {
        kQ = q;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        return intensity.reduce(kC + kL * d + kQ * d * d);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point){
        return point.distance(position);
    }

}
