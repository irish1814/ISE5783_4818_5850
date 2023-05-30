package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * This class represents ambient light
 *
 * @author Ishay Houri & Elad Radomski
 */

public class AmbientLight {
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
    private final Color intensity;

    /**
     * @param iA intensity of the light based on RGB values
     * @param kA light attenuation coefficient
     */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    public AmbientLight(Color iA, double kA) {
        intensity = iA.scale(kA);
    }

    public Color getIntensity() {
        return intensity;
    }
}
