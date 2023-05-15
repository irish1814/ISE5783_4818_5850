package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.*;

public class Geometries implements Intersectable {
    private List<Intersectable> geometries;
    Geometries (){
        geometries = new LinkedList<Intersectable>();
    }
    public Geometries(Intersectable... geometries){
        this.geometries = new ArrayList<>();
        this.geometries.addAll(Arrays.asList(geometries));
    }

    public void add(Intersectable... geometries){

    }
    public Iterator<Intersectable> iterator() {
        return geometries.iterator();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        boolean flag = false;
        int count = 0;
        List<Point> intersections = null;
        for (Intersectable geom : geometries) {
            if (geom.findIntersections(ray) != null) {
                flag = true;
                if (count == 0)
                    intersections = new ArrayList<>();
                count++;
                if (flag)
                    intersections.addAll(geom.findIntersections(ray));
            }
        }
        return intersections;
    }
}

