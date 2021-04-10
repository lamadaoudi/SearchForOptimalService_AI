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


    public static ArrayList<City> aStarForOneGoal(City startNode, City goalNode) {
        int size = City.mainCities.size();
        HashMap<String, Double> visited = new HashMap<>();

        City goalNodeNew = new City(goalNode.getCityName(), goalNode.getCityInfo());
        goalNodeNew.setHeuristicCities(goalNode.getHeuristicCities());
        goalNodeNew.setAdjacentCities(goalNode.getAdjacentCities());
        goalNodeNew.setParent(goalNode.getParent());
        ArrayList<City> explore = new ArrayList<>();
        PriorityQueue<City> queue = new PriorityQueue<>(20, new CityComparator());
        visited.put(startNode.getCityName(), getHeuristic(startNode, goalNodeNew));
        City cityToBeAddedFirst = new City(startNode.getCityName(), startNode.getCityInfo());
        cityToBeAddedFirst.setParent(null);
        cityToBeAddedFirst.getCityInfo().setSummation(getHeuristic(startNode, goalNodeNew));
        cityToBeAddedFirst.setAdjacentCities(getCityWithName(startNode.getCityName()).getAdjacentCities());
        cityToBeAddedFirst.setHeuristicCities(getCityWithName(startNode.getCityName()).getHeuristicCities());
        explore.add(cityToBeAddedFirst);

        queue.add(cityToBeAddedFirst);


        while (queue.size() != 0) {
            City current = queue.poll();

            if (current.getCityName().equalsIgnoreCase(goalNodeNew.getCityName())) {
                // visited.put(current.getCityName(), true); NO NEED?
                goalNodeNew = current;
                break;
            }

            for (int i = 0; i < current.getAdjacentCities().size(); i++) {
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
                double heuristic = getHeuristic(cityToBeAdded, goalNodeNew);
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
        while (goalNodeNew != null) {
            reversePath.push(goalNodeNew);
            goalNodeNew = goalNodeNew.parent;
        }
        ArrayList<City> path = new ArrayList<>();
        while (reversePath.size() > 0)
            path.add(reversePath.pop());
        System.out.println("*****************PATH**********************");
        for (int i = 0; i < path.size(); i++)
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
            if (City.mainCities.get(i).getCityName().equalsIgnoreCase(cityName)) {
                City temp = new City(City.mainCities.get(i).getCityName(), City.mainCities.get(i).getCityInfo());
                temp.setParent(City.mainCities.get(i).getParent());
                temp.setAdjacentCities(City.mainCities.get(i).getAdjacentCities());
                temp.setHeuristicCities(City.mainCities.get(i).getHeuristicCities());
                return temp;
            }
        }
        return null;
    }

}
