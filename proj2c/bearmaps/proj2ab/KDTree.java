package bearmaps.proj2ab;


import java.util.List;

public class KDTree implements PointSet {
    private Node root;
    private Node best;
    private boolean Xdirection;
    private boolean Ydirection;

    public KDTree(List<Point> points){
        this.Xdirection = true;
        this.Ydirection = false;
        for(int i =0 ; i< points.size(); i++){
            this.root = add(this.root, points.get(i));
        }
        this.best = this.root;
    }

    // add method
    private Node add(Node x, Point point){
        if(x == null){
            Node newnode = new Node(point, this.Xdirection, this.Ydirection);
            if(this.Xdirection == true && this.Ydirection == false){
                this.Xdirection = false;
                this.Ydirection = true;
            }
            else{
                this.Xdirection = true;
                this.Ydirection = false;
            }
            return newnode;
        }
        int cmp = comparePoint(point, x.p, x.Xdirection);
        if(cmp == -1){
            x.leftNode = add(x.leftNode, point);
        }
        else if(cmp == 1){
            x.rightNode = add(x.rightNode, point);
        }
        else if(cmp == 0){
            x.p = point;
        }
        return x;

    }

    //compare method
    private int comparePoint(Point p1, Point p2, boolean Xdirection){
        if(Xdirection == true){
            if(p1.getX() < p2.getX()) return -1;
            else if(p1.getX() > p2.getX()) return 1;
            else if(p1.getX() == p2.getX() && p1.getY() == p2.getY()) return 0;
            else{
                return 1;
            }
        }
        else{
            if(p1.getY() < p2.getY()) return -1;
            else if(p1.getY() > p2.getY()) return 1;
            else if(p1.getX() == p2.getX() && p1.getY() == p2.getY()) return 0;
            else{
                //System.out.println("error");
                return 1;
            }
        }
    }

    @Override
    public Point nearest(double x, double y){
        Point goal = new Point(x,y);
        this.best = nearest(root, goal, this.best);
        return this.best.p;
    }

    public Node nearest(Node n, Point goal, Node best){
        if(n == null) return best;
        if(n.distance(goal) < best.distance(goal)){
            this.best = n;
        }
        int cmp = comparePoint(goal, n.p, n.Xdirection);
        if(cmp == -1){
            this.best = nearest(n.leftNode, goal, this.best);
            if(n.oneDirectionDistance(goal, n.Xdirection) < best.distance(goal)){
                this.best = nearest(n.rightNode, goal, this.best);
            }
        }
        else{
            this.best = nearest(n.rightNode, goal, this.best);
            if(n.oneDirectionDistance(goal, n.Xdirection) < best.distance(goal)){
                this.best = nearest(n.leftNode, goal, this.best);
            }
        }
        return this.best;
    }

    //put method

    private class Node{
        private Point p;
        private Node leftNode;
        private Node rightNode;
        private boolean Xdirection;
        private boolean Ydirection;

        public Node(Point p, boolean Xdirection, boolean Ydirection){
            this.p = p;
            this.leftNode = null;
            this.rightNode = null;
            this.Xdirection = Xdirection;
            this.Ydirection = Ydirection;

        }

        public double distance(Point p1){
            return Point.distance(this.p, p1);
        }

        public double oneDirectionDistance(Point p1, boolean Xdirection){
            if(Xdirection){
                return Math.pow(p1.getX() - p.getX(), 2);
            }
            else{
                return Math.pow(p1.getY() - p.getY(),2);
            }
        }

    }

    public static void main(String[] args){
        Point A = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point Z = new Point(4, 2);
        Point B = new Point(4, 2);
        Point C = new Point(4,5);
        Point D = new Point(3,3);
        Point E = new Point(1,5);
        Point F = new Point(4,4);

        KDTree kd = new KDTree(List.of(A, Z, B, C, D, E, F));

    }
}

