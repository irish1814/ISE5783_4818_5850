package lighting;

import primitives.Double3;
import primitives.Color;

/**
 * This class represents ambient light
 *
 * @author Ishay Houri & Elad Radomski
 * */

public class AmbientLight {
    private Color intensity;
    public AmbientLight(Color color, Double3 k) {

    }

    public Color getIntensity(){
        return intensity;
    }
}
