package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * This class represents ambient light
 *
 * @author Ishay Houri & Elad Radomski
 */

public class AmbientLight extends Light {
    /**
     * an empty Ambient light - black color
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);


    /**
     * constructor initialize the intensity of the light with Double3
     *
     * @param iA intensity of the light based on RGB values
     * @param kA light attenuation coefficient
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * constructor initialize the intensity of the light with double
     *
     * @param iA intensity of the light based on RGB values
     * @param kA light attenuation coefficient
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }
}
