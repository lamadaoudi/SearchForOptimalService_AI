package algorithms;

import classes.City;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class DepthFirst {
    static int size = City.mainCities.size();
    static HashMap<String, Boolean> visited = new HashMap<>();


     public static void executeDFS(City startNode, ArrayList<City> goalNodes) {
        for (int i = 0; i<size;i++ ){
            visited.put(City.mainCities.get(i).getCityName(),false);
        }
        ArrayList<City> explore = new ArrayList<>();
        Stack<City> stackDPF = new Stack<>();
        visited.put(startNode.getCityName(),true);
        City cityToBeAddedFirst = new City(startNode.getCityName(), startNode.getCityInfo());
        cityToBeAddedFirst.setParent(null);
        cityToBeAddedFirst.setAdjacentCities(getCityWithName(startNode.getCityName()).getAdjacentCities());
        explore.add(cityToBeAddedFirst);
        stackDPF.push(cityToBeAddedFirst);
        City goalNode = goalNodes.get(0);

        while ( stackDPF.size() > 0 ) {
            City current = stackDPF.peek();

            for(int i=0 ; i<goalNodes.size() ; i++) {
                if (current.getCityName().equalsIgnoreCase(goalNodes.get(i).getCityName())) {
                    visited.put(current.getCityName(), true);
                    goalNode = current;
                    break;
                }
            }

            boolean flagSuccessors = false;
            for(int j=0; j<current.getAdjacentCities().size(); j++){
                if(visited.get(current.getAdjacentCities().get(j).getCityName()) == false ) {
                    flagSuccessors = true;
                    break;
                }
            }
            if(!flagSuccessors)
                stackDPF.pop();

            //explore.add(current);
            for ( int i=0; i<current.getAdjacentCities().size(); i++){
                City temp = current.getAdjacentCities().get(i);
                if (visited.get(temp.getCityName()) == false){
                    City cityToBeAdded = new City(temp.getCityName(), temp.getCityInfo());
                    cityToBeAdded.setParent(current);
                    cityToBeAdded.setAdjacentCities(getCityWithName(temp.getCityName()).getAdjacentCities());
                    stackDPF.push(cityToBeAdded);
                    visited.put(temp.getCityName(),true);
                    explore.add(cityToBeAdded);
                    break;
                }
            }
        }
        System.out.println("**********************");
         for(int i=0; i< explore.size(); i++)
             System.out.println(explore.get(i));

         Stack<City> reversePath = new Stack<>();

         while (goalNode != null) {
             reversePath.push(goalNode);
             goalNode = goalNode.parent;
         }
         //ADD A QUEUE FOR THE PATH, NOT PRINT
         System.out.println("***********************");
         while (reversePath.size() > 0)
             System.out.println(reversePath.pop());

    }
    private static City getCityWithName(String cityName){
        for(int i=0; i<City.mainCities.size(); i++){
            if(City.mainCities.get(i).getCityName().equalsIgnoreCase(cityName))
                return City.mainCities.get(i);
        }
        return null;
    }
}



