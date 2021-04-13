package algorithms;

import classes.City;

import java.text.SimpleDateFormat;
import java.util.*;

public class OptimalTwo {
    static int size = City.mainCities.size();
    public static double[][] distanceMatrix = new double[size][size];
    static double[][] floydMatrix = new double[size][size];
    static ArrayList<City> overallPath = new ArrayList<>();

    public static double getAlpha (){
        double alpha;
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        Date date = new Date();
        int hour = Integer.parseInt(formatter.format(date));
        if (hour >= 7 && hour <=10 || hour >13 && hour <=17)
            alpha = randomInRange(1,2);
        else if (hour > 10 && hour <= 13)
            alpha = randomInRange(0.7, 1);
        else if (hour > 17 && hour <=20)
            alpha = 1;
        else
            alpha = randomInRange(0.8, 1);
        return alpha;
    }

    public static double randomInRange(double min, double max) {
        Random random = new Random();
        double range = max - min;
        double scaled = random.nextDouble() * range;
        double shifted = scaled + min;
        double rounded = Math.round(shifted * 100.0) / 100.0;
        return rounded;
    }

    public static ArrayList<City> executeOptimalTwo(int startNode, ArrayList<Integer> goalNodes){
        overallPath = new ArrayList<>();
        for (int i = 0; i< distanceMatrix.length; i++){
            for (int j=0; j< distanceMatrix.length; j++) {
                if (distanceMatrix[i][j] != Double.POSITIVE_INFINITY)
                    distanceMatrix[i][j] = Math.round(distanceMatrix[i][j] * getAlpha() * 100.0) / 100.0;
            }
        }
        ArrayList<City> pathOptimalOne = executeOptimalOne(startNode, goalNodes);
        return pathOptimalOne;
    }

    public static ArrayList<City> executeOptimalOne(int startNode, ArrayList<Integer> goalNodes) {
        overallPath = new ArrayList<>();
        floydMatrix = getFloydMatrix();
        printSolution(floydMatrix, size);
        int newSize = goalNodes.size() + 1;
        int[] array = new int[newSize];
        array[0] = startNode;
        int newStart = 0;
        for (int i = 1; i < newSize; i++)
            array[i] = goalNodes.get(i - 1);
        Arrays.sort(array);
        for (int i=0; i<newSize; i++){
            if (array[i] == startNode)
                newStart = i;
        }
        double[][] tspArray = new double[newSize][newSize];
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                tspArray[i][j] = floydMatrix[array[i]][array[j]];
            }
        }

        printSolution(tspArray, newSize);
        TspDynamicProgrammingRecursive solver = new TspDynamicProgrammingRecursive(newStart, tspArray);

        List salesmanTour = solver.getTour();
        ArrayList<City> temp;
        City startCity = City.mainCities.get(array[(Integer) salesmanTour.get(0)]).getHeuristicCities().get(array[(Integer) salesmanTour.get(0)]);
        City goalCity = City.mainCities.get(array[(Integer) salesmanTour.get(1)]);
        temp = AStar.aStarForOneGoal(startCity, goalCity);
        for (int j = 0; j < temp.size(); j++) {
            overallPath.add(temp.get(j));
        }
        for (int i = 1; i < salesmanTour.size() - 1; i++) {
            startCity = City.mainCities.get(array[(Integer) salesmanTour.get(i)]).getHeuristicCities().get(array[(Integer) salesmanTour.get(i)]);
            goalCity = City.mainCities.get(array[(Integer) salesmanTour.get(i + 1)]);
            temp = AStar.aStarForOneGoal(startCity, goalCity);
            for (int j = 1; j < temp.size(); j++) {
                overallPath.add(temp.get(j));
            }
        }

//        for(int i=0; i<overallPath.size(); i++){
//            System.out.println(City.mainCities.get(array[(Integer) salesmanTour.get(i)]).getCityName());
//        }

        System.out.println("THE OVERALL PATH ISSSSS*******************************************");
        for (int i = 0; i < overallPath.size(); i++) {
            System.out.println(overallPath.get(i).getCityName());
        }
        //System.out.println("Tour: " + solver.getTour());

        // Print: 42.0
        System.out.println("Tour cost: " + solver.getTourCost());
        return overallPath;

    }

    static double[][] getFloydMatrix() {
        double dist[][] = new double[size][size];
        int i, j, k;

        for (i = 0; i < size; i++)
            for (j = 0; j < size; j++)
                dist[i][j] = distanceMatrix[i][j];

        for (k = 0; k < size; k++) {
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    if ((dist[i][k] + dist[k][j] < dist[i][j]) && i <= j) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        dist[j][i] = dist[i][j];
                    }
                }
            }
        }
        return dist;
    }

    static void printSolution(double dist[][], int receivedSize) {
        System.out.println("The following matrix shows the shortest " +
                "distances between every pair of vertices");
        for (int i = 0; i < receivedSize; ++i) {
            for (int j = 0; j < receivedSize; ++j) {
                if (dist[i][j] == Double.POSITIVE_INFINITY)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
    }


}
