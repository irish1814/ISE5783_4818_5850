package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    public Geometries() {
    }

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        if (geometries != null) {
            this.geometries.addAll(List.of(geometries));
        }
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        List<GeoPoint> GeoIntersections = null;
        for (Intersectable geometry : geometries) {
            var tempList = geometry.findGeoIntersections(ray);
            if (tempList != null) {
                if (GeoIntersections == null)
                    GeoIntersections = new LinkedList<>();
                GeoIntersections.addAll(tempList);
            }
        }
        return GeoIntersections;
    }
}

