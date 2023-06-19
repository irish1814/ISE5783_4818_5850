package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * A class represents group of geometry's shapes
 *
 * @author Ishay Houri & Elad Radomski
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * default constructor to build the list with
     * initialized values
     */
    public Geometries() {
    }

    /**
     * constructor that get geometries and add them to the list
     *
     * @param geometries to build the list
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * add geometries to the list
     *
     * @param geometries the shapes to add to the list
     */
    public void add(Intersectable... geometries) {
        if (geometries != null)
            this.geometries.addAll(List.of(geometries));
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> GeoIntersections = null;
        for (Intersectable geometry : geometries) {
            var tempList = geometry.findGeoIntersections(ray);
            if (tempList != null) {
                if (GeoIntersections == null)
                    GeoIntersections = new LinkedList<>(tempList);
                else
                    GeoIntersections.addAll(tempList);
            }
        }
        return GeoIntersections;
    }
}

