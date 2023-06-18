package primitives;

import lighting.LightSource;
import scene.Scene;

import java.util.List;

/**
 * A class represent the material properties of the geometry
 *
 * @author Elad Radomski & Ishay Houri
 */
public class Material {
    /**
     * diffuse reflection coefficient of the material
     */
    public Double3 kD = Double3.ZERO;

    /**
     * specular reflection coefficient of the material
     */
    public Double3 kS = Double3.ZERO;

    /**
     * shininess coefficient of the material
     */
    public int nShininess = 1;

    /**
     * Set a new kD coefficient to the Material
     * with Double3 parameter
     * @param KD new kD coefficient
     * @return Material object with the new kD coefficient
     */
    public Material setKd(Double3 KD) {
        kD = KD;
        return this;
    }
    /**
     * Set a new kD coefficient to the Material
     * with double parameter
     * @param KD new kD coefficient
     * @return Material object with the new kD coefficient
     */
    public Material setKd(double KD) {
        kD = new Double3(KD);
        return this;
    }

    /**
     * Set a new kS coefficient to the Material
     * with Double3 parameter
     * @param KS new kS coefficient
     * @return Material object with the new kS coefficient
     */
    public Material setKs(Double3 KS) {
        kS = KS;
        return this;
    }
    /**
     * Set a new kS coefficient to the Material
     * with double parameter
     * @param KS new kS coefficient
     * @return Material object with the new kS coefficient
     */
    public Material setKs(double KS) {
        kS = new Double3(KS);
        return this;
    }

    /**
     * Set a new nShininess coefficient to the Material
     *
     * @param ns new nShininess coefficient
     * @return Material object with the new nShininess coefficient
     */
    public Material setShininess(int ns) {
        nShininess = ns;
        return this;
    }



}
