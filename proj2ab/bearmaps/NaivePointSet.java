package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    //attributes
    private List<Point> points;
    private Point best;

    //constructor
    public NaivePointSet(List<Point> points){
        this.points = points;
        this.best = null;
    }

    @Override
    public Point nearest(double x, double y) {
        for(int i=0; i<points.size(); i++){
            double distance1 = Point.distance(points.get(i), new Point(x,y));
            if(best == null){
                best = points.get(i);
            }
            else{
                double distance2 = Point.distance(best, new Point(x,y));
                if(distance2 > distance1){
                    best = points.get(i);
                }
            }
        }
        return best;
    }
}
