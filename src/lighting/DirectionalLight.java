package lighting;

import primitives.*;

/**
 * A Class that represent directional light
 * and implements the Phong Reflectance Model
 *
 * @author Elad Radomski & Ishay Houri
 */
public class DirectionalLight extends Light implements LightSource {

    final private Vector direction;

    /**
     * Constructor for the directional light
     *
     * @param intensity intensity of the light
     * @param dir direction vector of the light
     */
    public DirectionalLight (Color intensity, Vector dir){
        super(intensity);
        direction = dir.normalize();
    }

    @Override
    public Color getIntensity(Point p){
        return intensity;
    }

    @Override
    public Vector getL(Point p){
        return direction;
    }
}
