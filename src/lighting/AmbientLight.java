package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * This class represents ambient light
 *
 * @author Ishay Houri & Elad Radomski
 */

public class AmbientLight {
    /**
     * an empty Ambient light - black color
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
    private final Color intensity;

    /**
     * constructor initialize the intensity of the light
     *
     * @param iA intensity of the light based on RGB values
     * @param kA light attenuation coefficient
     */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    /**
     * constructor initialize the intensity of the light
     *
     * @param iA intensity of the light based on RGB values
     * @param kA light attenuation coefficient
     */
    @SuppressWarnings("unused")
    public AmbientLight(Color iA, double kA) {
        intensity = iA.scale(kA);
    }

    /** get the intensity of the ambient light
     * @return intensity
     * */
    public Color getIntensity() {
        return intensity;
    }
}
