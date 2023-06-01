package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
/**
 * This class represents a scene in 3 dimension space
 *
 * @author Ishay Houri & Elad Radomski
 */
public class Scene {
    /**
     * The name of the scene
     */
    public String sceneName;

    /**
     * The geometries that include in the scene
     * default - there is no geometries
     */
    public Geometries geometries = new Geometries();

    /**
     * The background color of the scene.
     * default - black
     */
    public Color background = Color.BLACK;

    /**
     * The ambient light of the scene
     * default - dark
     */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /**
     * Scene constructor accepting scene name.
     * @param sceneName the name of scene
     */
    public Scene(String sceneName) {
        this.sceneName = sceneName;
    }

    // *************** setters *************** //
    /**
     * Set a new Background to the scene object
     *
     * @param bg new background color
     * @return Scene object with the new Background
     */
    public Scene setBackground(Color bg) {
        background = bg;
        return this;
    }
    /**
     * Set a new AmbientLight to the scene object
     *
     * @param al new Ambient Light
     * @return Scene object with the new AmbientLight
     */
    public Scene setAmbientLight(AmbientLight al) {
        ambientLight = al;
        return this;
    }
    /**
     * Set a new Geometries to the scene object
     *
     * @param geo new Geometries
     * @return Scene object with the new Geometries
     */
    public Scene setGeometries(Geometries geo) {
        geometries = geo;
        return this;
    }
}
