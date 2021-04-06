package algorithms;

import classes.City;
import utilities.CityComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

import static utilities.FileReader.mapCitiesIndex;

public class AStar {
    // let's do A* for one start, one goal
    static int size = City.mainCities.size();
    static HashMap<String, Double> visited = new HashMap<>();


    public static ArrayList<City> aStarForOneGoal(City startNode) {
//        for (int i = 0; i<size;i++ ){
//            visited.put(City.mainCities.get(i).getCityName(),false);
//        }

        ArrayList<City> explore = new ArrayList<>();
        PriorityQueue<City> queue = new PriorityQueue<>(20, new CityComparator());
        City goalNode = City.mainCities.get(7);
        visited.put(startNode.getCityName(), getHeuristic(startNode, goalNode));
        City cityToBeAddedFirst = new City(startNode.getCityName(), startNode.getCityInfo());
        cityToBeAddedFirst.setParent(null);
        cityToBeAddedFirst.getCityInfo().setSummation(getHeuristic(startNode, goalNode));
        cityToBeAddedFirst.setAdjacentCities(getCityWithName(startNode.getCityName()).getAdjacentCities());
        explore.add(cityToBeAddedFirst);

        queue.add(cityToBeAddedFirst);


        while (queue.size() != 0) {
            City current = queue.poll();

            if (current.getCityName().equalsIgnoreCase(goalNode.getCityName())) {
                // visited.put(current.getCityName(), true); NO NEED?
                goalNode = current;
                break;
            }

            for (int i = 0; i < current.getAdjacentCities().size(); i++) {
                // create a new instance --> done?
                // visited not required???
                // multiple goals
                // check the summation --> zabat jarabto 3a test string queue
                // Ask rawan about ui
                // YA RAB

                City temp = current.getAdjacentCities().get(i);

                City cityToBeAdded = new City(temp.getCityName(), temp.getCityInfo());
                cityToBeAdded.setParent(current);
                cityToBeAdded.setAdjacentCities(getCityWithName(temp.getCityName()).getAdjacentCities());
                cityToBeAdded.setHeuristicCities(getCityWithName(temp.getCityName()).getHeuristicCities());
                City tempParent = cityToBeAdded;
                double realCost = 0;
                while (tempParent.parent != null) {
                    realCost += tempParent.getCityInfo().getDrivingDistance();
                    tempParent = tempParent.parent;
                }
                //get heuristic value
                double heuristic = getHeuristic(cityToBeAdded, goalNode);
                double summation = realCost + heuristic;
                cityToBeAdded.getCityInfo().setSummation(summation);
                if (visited.get(cityToBeAdded.getCityName()) != null) {
                    double valueToBeCompared = visited.get(cityToBeAdded.getCityName());
                    if (valueToBeCompared <= summation)
                        continue;
                }
                queue.add(cityToBeAdded);
                visited.put(temp.getCityName(), summation);
                explore.add(cityToBeAdded);
            }


        }

        Stack<City> reversePath = new Stack<>();
        while (goalNode != null) {
            reversePath.push(goalNode);
            goalNode = goalNode.parent;
        }
        ArrayList<City> path = new ArrayList<>();
        while (reversePath.size() > 0)
            path.add(reversePath.pop());
        System.out.println("*****************PATH**********************");
        for(int i=0; i<path.size();i++)
            System.out.println(path.get(i));
        return path;
    }

    private static double getHeuristic(City from, City to) {
        City temp = getCityWithName(from.getCityName());
        double heuristic = City.mainCities.get(mapCitiesIndex.get(temp.getCityName())).getHeuristicCities().get(mapCitiesIndex.get(to.getCityName())).getCityInfo().getArealDistance();
        return heuristic;
    }

    private static City getCityWithName(String cityName) {
        for (int i = 0; i < City.mainCities.size(); i++) {
            if (City.mainCities.get(i).getCityName().equalsIgnoreCase(cityName))
                return City.mainCities.get(i);
        }
        return null;
    }

}
