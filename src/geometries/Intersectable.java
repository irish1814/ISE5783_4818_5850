package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

 /**
  * @author Ishay Houri & Elad Radomski
  */
public interface Intersectable {
    List<Point> findIntersections(Ray ray);
}
