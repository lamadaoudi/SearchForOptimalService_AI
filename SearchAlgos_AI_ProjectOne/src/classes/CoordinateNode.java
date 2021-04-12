package classes;

public class CoordinateNode {
    double x;
    double y;

    public CoordinateNode(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "CoordinateNode{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
