package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A Class that represent spot light
 *
 * @author Elad Radomski & Ishay Houri
 */
public class SpotLight extends PointLight {

    final private Vector direction;

    /**
     * Constractor for the spot light
     * @param intensity the intensity of the light
     * @param pos the position point of the light
     * @param dir the direction vector of the light
     */
    public SpotLight (Color intensity, Point pos, Vector dir){
        super(intensity,pos);
        direction = dir.normalize();
    }


    @Override
    public Color getIntensity(Point p){
        double dotP =direction.dotProduct(getL(p));
        double max = dotP > 0 ? dotP : 0;

        //double d = getL(p).length();
        double d = p.subtract(position).length();
        return getIntensity().scale(max).reduce(kC + kL*d + kQ*d*d);
    }

}
