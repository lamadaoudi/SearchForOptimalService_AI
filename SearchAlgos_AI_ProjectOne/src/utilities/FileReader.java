package utilities;
import algorithms.OptimalTwo;
import classes.City;
import classes.Info;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static algorithms.OptimalTwo.distanceMatrix;

public class FileReader {
    public static HashMap<String, Integer> mapCitiesIndex = new HashMap<>();

    public static void main(String[] args) {
        try {
            File myObj = new File("C:\\Users\\Main\\IdeaProjects\\SearchForOptimalService_AI\\SearchAlgos_AI_ProjectOne\\backup.txt");
            Scanner myReader = new Scanner(myObj);
            String firstRow = myReader.nextLine();
            String[] arrayOfCityNames = firstRow.split("\t");


            // adding the main cities
            for (int i = 0; i < arrayOfCityNames.length; i++) {
                if (!arrayOfCityNames[i].isEmpty()) {
                    City.mainCities.add(new City(arrayOfCityNames[i]));
                    int size = City.mainCities.size() - 1;
                    mapCitiesIndex.put(arrayOfCityNames[i], size);
                }

            }

            int counter = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] nodeInfo = data.split("\t");
                String adjacentCityName = nodeInfo[0];
                // check if there is a relation !!!!
                for (int i = 1; i < nodeInfo.length; i++) {
//                    if (nodeInfo[i].equalsIgnoreCase("-"))
//                        continue;
                    String[] infoParameters = nodeInfo[i].split(",");
                    Info heuristicInfo = new Info(Double.parseDouble(infoParameters[0]));
                    City.mainCities.get(i - 1).addHeuristicCity(new City(adjacentCityName, heuristicInfo));
                    if (!infoParameters[1].equalsIgnoreCase("-")) {
                        distanceMatrix[counter][i - 1] = Double.parseDouble(infoParameters[1]);
                        Info adjacentCityInfo = new Info(Double.parseDouble(infoParameters[0]), Double.parseDouble(infoParameters[1]), "1");
                        City.mainCities.get(i - 1).addAdjacentCity(new City(adjacentCityName, adjacentCityInfo));
                    } else {
                        if (counter != i - 1)
                            distanceMatrix[counter][i - 1] = Double.POSITIVE_INFINITY;
                        else
                            distanceMatrix[counter][i - 1] = 0;
                    }
                }
                counter++;
            }
            myReader.close();

            for (int i = 0; i < City.mainCities.size(); i++)
                System.out.println(City.mainCities.get(i));

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //System.out.println("*******************");
        //System.out.println(Arrays.deepToString(distanceMatrix));
        //DepthFirst.executeDFS(City.mainCities.get(0));
        //AStar.aStarForOneGoal(City.mainCities.get(0).getHeuristicCities().get(0));
        //OptimalTwo.executeOptimalTwo();
        ArrayList<Integer> goal = new ArrayList<>();
        goal.add(4);
        goal.add(5);
        goal.add(7);
        OptimalTwo.executeOptimalTwo(0, goal);


        //AStar.aStarForOneGoal(City.mainCities.get(0).getHeuristicCities().get(0), City.mainCities.get(1));


    }
}
