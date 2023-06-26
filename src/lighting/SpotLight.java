package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * A Class that represent spotlight
 *
 * @author Elad Radomski & Ishay Houri
 */
public class SpotLight extends PointLight {

    final private Vector direction;

    /**
     * Constructor for the spotlight
     *
     * @param intensity the intensity of the light
     * @param pos       the position point of the light
     * @param dir       the direction vector of the light
     */
    public SpotLight(Color intensity, Point pos, Vector dir) {
        super(intensity, pos);
        direction = dir.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double dotP = alignZero(direction.dotProduct(getL(p)));
        return dotP <= 0 ? Color.BLACK : super.getIntensity(p).scale(dotP);
    }

    @Override
    public double getDistance(Point point){
        return super.getDistance(point);
    }

}
