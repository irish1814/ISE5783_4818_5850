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

    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * @param iA intensity of the light based on RGB values
     * @param kA
     * */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    public AmbientLight(Color iA, double kA) {
        intensity = iA.scale(kA);
    }

    public Color getIntensity(){
        return intensity;
    }
}
