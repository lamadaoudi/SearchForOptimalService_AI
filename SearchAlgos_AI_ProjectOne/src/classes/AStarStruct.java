package classes;

import java.util.ArrayList;

public class AStarStruct {
    double cost;
    ArrayList<City> path = new ArrayList<>();

    public AStarStruct(double cost, ArrayList<City> path) {
        this.cost = cost;
        this.path = path;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<City> getPath() {
        return path;
    }

    public void setPath(ArrayList<City> path) {
        this.path = path;
    }
}
