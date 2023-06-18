package lighting;

import primitives.*;

/**
 * An interface of all light sources that implements
 * the Phong Reflectance Model
 *
 * @author Elad Radomski & Ishay Houri
 */
public interface LightSource {
    /**
     * method that calculate the intensity of the light
     * by using Phong Reflectance Model
     * @param p The point on which the calculation is made
     * @return The color in the given point
     */
    public Color getIntensity(Point p);

    /**
     * get the vector from the light source to the given point
     * @param p The point on which the light is directed
     * @return the direction vector
     */
    public Vector getL(Point p);
}
