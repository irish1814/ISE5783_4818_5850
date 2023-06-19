package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a scene in 3 dimension space
 *
 * @author Ishay Houri & Elad Radomski
 */
public class Scene {
    /**
     * The name of the scene
     */
    public final String sceneName;

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
     * The light sources of the scene
     * default - no light source
     */
    public List<LightSource> lights = new LinkedList<>();

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

    /**
     * Set a new light sources to the scene object
     *
     * @param Lights new light sources list
     * @return Scene object with the new light sources
     */
    @SuppressWarnings("unused")
    public Scene setLights(List<LightSource> Lights) {
        lights = Lights;
        return this;
    }
}
