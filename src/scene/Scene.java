package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {
    public String sceneName;
    public Geometries geometries = new Geometries();
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;

    Scene(String sceneName) {
        this.sceneName = sceneName;
    }

    public Scene setBackground(Color b) {
        background = b;
        return this;
    }

    public Scene setAmbientLight(AmbientLight a) {
        ambientLight = a;
        return this;
    }

    public Scene setGeometries(Geometries g){
        geometries = g;
        return this;
    }
}
