package lighting;

import primitives.Color;

/**
 * This class is abstract class for all the light source
 *
 * @author Ishay Houri & Elad Radomski
 */
abstract class Light {
    final private Color intensity;

    /**
     * Constructor for the Light
     *
     * @param intense The intensity of the light
     */
    protected Light(Color intense) {
        intensity = intense;
    }

    /**
     * Get the intensity of the light
     * @return intensity color of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}
