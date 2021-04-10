package algorithms;

import classes.City;

import java.util.*;

public class OptimalTwo {
    static int size = City.mainCities.size();
    public static double[][] distanceMatrix = new double[size][size];
    static double[][] floydMatrix = new double[size][size];
    static ArrayList<City> overallPath = new ArrayList<>();

    public static void executeOptimalTwo(int startNode, ArrayList<Integer> goalNodes) {
        floydMatrix = getFloydMatrix();
        printSolution(floydMatrix, size);
        int newSize = goalNodes.size() + 1;
        int[] array = new int[newSize];
        array[0] = startNode;
        for (int i = 1; i < newSize; i++)
            array[i] = goalNodes.get(i - 1);
        Arrays.sort(array);
        double[][] tspArray = new double[newSize][newSize];
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                tspArray[i][j] = floydMatrix[array[i]][array[j]];
            }
        }

        printSolution(tspArray, newSize);
        TspDynamicProgrammingRecursive solver = new TspDynamicProgrammingRecursive(startNode, tspArray);

        List lama = solver.getTour();
        ArrayList<City> temp;
        City startCity = City.mainCities.get(array[(Integer) lama.get(0)]).getHeuristicCities().get(array[(Integer) lama.get(0)]);
        City goalCity = City.mainCities.get(array[(Integer) lama.get(1)]);
        temp = AStar.aStarForOneGoal(startCity, goalCity);
        for (int j = 0; j < temp.size(); j++) {
            overallPath.add(temp.get(j));
        }
        for (int i = 1; i < lama.size() - 1; i++) {
            startCity = City.mainCities.get(array[(Integer) lama.get(i)]).getHeuristicCities().get(array[(Integer) lama.get(i)]);
            goalCity = City.mainCities.get(array[(Integer) lama.get(i + 1)]);
            temp = AStar.aStarForOneGoal(startCity, goalCity);
            for (int j = 1; j < temp.size(); j++) {
                overallPath.add(temp.get(j));
            }
        }

//        for(int i=0; i<overallPath.size(); i++){
//            System.out.println(City.mainCities.get(array[(Integer) lama.get(i)]).getCityName());
//        }

        System.out.println("THE OVERALL PATH ISSSSS*******************************************");
        for (int i = 0; i < overallPath.size(); i++) {
            System.out.println(overallPath.get(i).getCityName());
        }
        //System.out.println("Tour: " + solver.getTour());

        // Print: 42.0
        System.out.println("Tour cost: " + solver.getTourCost());

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
