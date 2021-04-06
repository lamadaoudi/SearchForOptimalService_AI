package utilities;

import algorithms.AStar;
import algorithms.DepthFirst;
import algorithms.DepthFirst.*;
import classes.City;
import classes.Info;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class FileReader {
   public static HashMap<String, Integer> mapCitiesIndex = new HashMap<>();

    public static void main(String[] args) {
        try {
            File myObj = new File("C:\\Users\\Main\\IdeaProjects\\SearchForOptimalService_AI\\SearchAlgos_AI_ProjectOne\\backup.txt");
            Scanner myReader = new Scanner(myObj);
            String firstRow = myReader.nextLine();
            String[] arrayOfCityNames = firstRow.split("\t");

            
            // adding the main cities
            for(int i=0; i< arrayOfCityNames.length; i++){
                if(!arrayOfCityNames[i].isEmpty()){
                    City.mainCities.add(new City(arrayOfCityNames[i]));
                    int size = City.mainCities.size()-1;
                    mapCitiesIndex.put(arrayOfCityNames[i], size);
                }

            }
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] nodeInfo = data.split("\t");
                String adjacentCityName = nodeInfo[0];
                // check if there is a relation !!!!
                for(int i=1; i<nodeInfo.length;i++){
//                    if (nodeInfo[i].equalsIgnoreCase("-"))
//                        continue;
                    String [] infoParameters = nodeInfo[i].split(",");
                    Info heuristicInfo = new Info(Double.parseDouble(infoParameters[0]));
                    City.mainCities.get(i-1).addHeuristicCity(new City(adjacentCityName, heuristicInfo));
                    if (!infoParameters[1].equalsIgnoreCase("-")) {
                        Info adjacentCityInfo = new Info(Double.parseDouble(infoParameters[0]), Double.parseDouble(infoParameters[1]), "1");
                        City.mainCities.get(i - 1).addAdjacentCity(new City(adjacentCityName, adjacentCityInfo));
                    }
                }            
            }
            myReader.close();
            
            for(int i=0; i< City.mainCities.size(); i++)
                System.out.println(City.mainCities.get(i));
                
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //DepthFirst.executeDFS(City.mainCities.get(0));
        AStar.aStarForOneGoal(City.mainCities.get(0).getHeuristicCities().get(0));




    }
}
