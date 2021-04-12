package controllers;

/**
 * Sample Skeleton for 'MainScreen.fxml' Controller Class
 */

import algorithms.AStar;
import algorithms.DepthFirst;
import algorithms.OptimalTwo;
import classes.City;
import classes.CoordinateNode;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import utilities.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

import static algorithms.OptimalTwo.distanceMatrix;
import static utilities.FileReader.cityCoordinates;
import static utilities.FileReader.mapCitiesIndex;

public class MainScreenController {

    ArrayList<City> pathDepthFirst = new ArrayList<>();
    ArrayList<City> pathAStar = new ArrayList<>();
    ArrayList<City> pathOptimalOne = new ArrayList<>();
    ArrayList<City> pathOptimalTwo = new ArrayList<>();
    ArrayList<String> cityOptions;
    City startNode;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tabPane"
    private JFXTabPane tabPane; // Value injected by FXMLLoader

    @FXML // fx:id="tab1"
    private Tab tab1; // Value injected by FXMLLoader

    @FXML // fx:id="startNodeComboBox"
    private JFXComboBox<String> startNodeComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox1"
    private JFXComboBox<String> destinationComboBox1; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox2"
    private JFXComboBox<String> destinationComboBox2; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox3"
    private JFXComboBox<String> destinationComboBox3; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox4"
    private JFXComboBox<String> destinationComboBox4; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox5"
    private JFXComboBox<String> destinationComboBox5; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox6"
    private JFXComboBox<String> destinationComboBox6; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddDestination"
    private JFXButton btnAddDestination; // Value injected by FXMLLoader

    @FXML // fx:id="btnRunAlgorithms"
    private JFXButton btnRunAlgorithms; // Value injected by FXMLLoader

    @FXML
    private JFXButton btnReset;

    @FXML
    private Tab tab2;

    @FXML
    private Label labelDepth;

    @FXML
    private LineChart<Number, Number> chartDepthFirst;

    @FXML
    private NumberAxis xDepthFirst;

    @FXML
    private NumberAxis yDepthFirst;

    @FXML
    private Tab tab3;

    @FXML
    private Label labelAStar;

    @FXML
    private LineChart<Number, Number> chartAStar;

    @FXML
    private NumberAxis xAStar;

    @FXML
    private NumberAxis yAStar;

    @FXML
    private Tab tab4;

    @FXML
    private Label labelOptimalTwo;

    @FXML
    private LineChart<Number, Number> chartOptimalTwo;

    @FXML
    private NumberAxis xOptimalTwo;

    @FXML
    private NumberAxis yOptimalTwo;

    @FXML
    private Tab tab5;

    @FXML
    private Label labelOptimalOne;

    @FXML
    private LineChart<Number, Number> chartOptimalOne;

    @FXML
    private NumberAxis xOptimalOne;

    @FXML
    private NumberAxis yOptimalOne;

    @FXML
    private Tab tab6;

    @FXML
    private ScatterChart<Number, Number> mapChart;

    static int counter = 1;

    @FXML
    void btnAddDestinationClicked(ActionEvent event) {
        counter++;
        switch (counter) {
            case 2:
                destinationComboBox2.setVisible(true);
                break;
            case 3:
                destinationComboBox3.setVisible(true);
                break;
            case 4:
                destinationComboBox4.setVisible(true);
                break;
            case 5:
                destinationComboBox5.setVisible(true);
                break;
            case 6:
                destinationComboBox6.setVisible(true);
                break;
            default:
                System.out.println("No more options available");
        }
    }

    @FXML
    void btnResetClicked(ActionEvent event) {
        mapCitiesIndex = new HashMap<>();
        cityCoordinates = new HashMap<>();
        pathOptimalTwo = new ArrayList<>();
        labelOptimalTwo.setText("");
        pathOptimalOne = new ArrayList<>();
        labelOptimalOne.setText("");
        pathAStar = new ArrayList<>();
        labelAStar.setText("");
        pathDepthFirst = new ArrayList<>();
        labelDepth.setText("");

        startNodeComboBox.getSelectionModel().clearSelection();
        startNodeComboBox.getItems().clear();
        destinationComboBox1.getSelectionModel().clearSelection();
        destinationComboBox1.getItems().clear();
        destinationComboBox2.getSelectionModel().clearSelection();
        destinationComboBox2.getItems().clear();
        destinationComboBox3.getSelectionModel().clearSelection();
        destinationComboBox3.getItems().clear();
        destinationComboBox4.getSelectionModel().clearSelection();
        destinationComboBox4.getItems().clear();
        destinationComboBox5.getSelectionModel().clearSelection();
        destinationComboBox5.getItems().clear();
        destinationComboBox6.getSelectionModel().clearSelection();
        destinationComboBox6.getItems().clear();

        chartDepthFirst.getData().clear();
        chartOptimalOne.getData().clear();
        chartOptimalTwo.getData().clear();
        chartAStar.getData().clear();
        mapChart.getData().clear();
        chartDepthFirst.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        chartOptimalTwo.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        chartOptimalOne.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        chartAStar.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        counter = 1;
        initialize();
    }

    @FXML
    void comboBox1Clicked(MouseEvent event) {
        ArrayList<String> cityOptions = getUnSelectedCities();
        ObservableList<String> dataListInfo;
        dataListInfo = FXCollections.observableArrayList(cityOptions);
        startNodeComboBox.setItems(dataListInfo);
    }

    @FXML
    void comboBox2Clicked(MouseEvent event) {
        ArrayList<String> cityOptions = getUnSelectedCities();
        ObservableList<String> dataListInfo;
        dataListInfo = FXCollections.observableArrayList(cityOptions);
        destinationComboBox1.setItems(dataListInfo);
    }

    @FXML
    void comboBox3Clicked(MouseEvent event) {
        ArrayList<String> cityOptions = getUnSelectedCities();
        ObservableList<String> dataListInfo;
        dataListInfo = FXCollections.observableArrayList(cityOptions);
        destinationComboBox2.setItems(dataListInfo);
    }

    @FXML
    void comboBox4Clicked(MouseEvent event) {
        ArrayList<String> cityOptions = getUnSelectedCities();
        ObservableList<String> dataListInfo;
        dataListInfo = FXCollections.observableArrayList(cityOptions);
        destinationComboBox3.setItems(dataListInfo);
    }

    @FXML
    void comboBox5Clicked(MouseEvent event) {
        ArrayList<String> cityOptions = getUnSelectedCities();
        ObservableList<String> dataListInfo;
        dataListInfo = FXCollections.observableArrayList(cityOptions);
        destinationComboBox4.setItems(dataListInfo);
    }

    @FXML
    void comboBox6Clicked(MouseEvent event) {
        ArrayList<String> cityOptions = getUnSelectedCities();
        ObservableList<String> dataListInfo;
        dataListInfo = FXCollections.observableArrayList(cityOptions);
        destinationComboBox5.setItems(dataListInfo);
    }

    @FXML
    void comboBox7Clicked(MouseEvent event) {
        ArrayList<String> cityOptions = getUnSelectedCities();
        ObservableList<String> dataListInfo;
        dataListInfo = FXCollections.observableArrayList(cityOptions);
        destinationComboBox6.setItems(dataListInfo);
    }

    ArrayList<String> getUnSelectedCities() {
        ArrayList<String> selectedCities = new ArrayList<>();
        Set<String> setMainCities = new HashSet<String>(cityOptions);
        if (startNodeComboBox.isVisible() && startNodeComboBox.getValue() != null && !startNodeComboBox.getValue().isEmpty()) {
            selectedCities.add(startNodeComboBox.getValue());
        }
        if (destinationComboBox1.isVisible() && destinationComboBox1.getValue() != null && !destinationComboBox1.getValue().isEmpty()) {
            selectedCities.add(destinationComboBox1.getValue());
        }
        if (destinationComboBox2.isVisible() && destinationComboBox2.getValue() != null && !destinationComboBox2.getValue().isEmpty()) {
            selectedCities.add(destinationComboBox2.getValue());
        }
        if (destinationComboBox3.isVisible() && destinationComboBox3.getValue() != null && !destinationComboBox3.getValue().isEmpty()) {
            selectedCities.add(destinationComboBox3.getValue());
        }
        if (destinationComboBox4.isVisible() && destinationComboBox4.getValue() != null && !destinationComboBox4.getValue().isEmpty()) {
            selectedCities.add(destinationComboBox4.getValue());
        }
        if (destinationComboBox5.isVisible() && destinationComboBox5.getValue() != null && !destinationComboBox5.getValue().isEmpty()) {
            selectedCities.add(destinationComboBox5.getValue());
        }
        if (destinationComboBox6.isVisible() && destinationComboBox6.getValue() != null && !destinationComboBox6.getValue().isEmpty()) {
            selectedCities.add(destinationComboBox6.getValue());
        }
        Set<String> setSelectedCites = new HashSet<>(selectedCities);
        setMainCities.removeAll(setSelectedCites);
        ArrayList<String> returnValue = new ArrayList<>(setMainCities);
        Collections.sort(returnValue);
        return returnValue;
    }

    @FXML
    void btnRunAlgorithmsClicked(ActionEvent event) {
        pathOptimalTwo = new ArrayList<>();
        pathOptimalOne = new ArrayList<>();
        pathAStar = new ArrayList<>();
        pathDepthFirst = new ArrayList<>();
        ArrayList<City> goals = new ArrayList<>();
        ArrayList<Integer> goalInteger = new ArrayList<>();
        if (destinationComboBox1.isVisible() && destinationComboBox1.getValue() != null && !destinationComboBox1.getValue().isEmpty()) {
            int index = mapCitiesIndex.get(destinationComboBox1.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (destinationComboBox2.isVisible() && destinationComboBox2.getValue() != null && !destinationComboBox2.getValue().isEmpty()) {
            int index = mapCitiesIndex.get(destinationComboBox2.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (destinationComboBox3.isVisible() && destinationComboBox3.getValue() != null && !destinationComboBox3.getValue().isEmpty()) {
            int index = mapCitiesIndex.get(destinationComboBox3.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (destinationComboBox4.isVisible() && destinationComboBox4.getValue() != null && !destinationComboBox4.getValue().isEmpty()) {
            int index = mapCitiesIndex.get(destinationComboBox4.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (destinationComboBox5.isVisible() && destinationComboBox5.getValue() != null && !destinationComboBox5.getValue().isEmpty()) {
            int index = mapCitiesIndex.get(destinationComboBox5.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (destinationComboBox6.isVisible() && destinationComboBox6.getValue() != null && !destinationComboBox6.getValue().isEmpty()) {
            int index = mapCitiesIndex.get(destinationComboBox6.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (!startNodeComboBox.getValue().isEmpty())
            startNode = City.mainCities.get(mapCitiesIndex.get(startNodeComboBox.getValue())).getHeuristicCities().get(mapCitiesIndex.get(startNodeComboBox.getValue()));
        if (goals.size() > 0 && startNode != null) {
            pathDepthFirst = DepthFirst.executeDFS(startNode, goals);
            pathAStar = AStar.executeAStar(mapCitiesIndex.get(startNodeComboBox.getValue()), goalInteger);
            if (goalInteger.size() >= 2) {
                pathOptimalOne = OptimalTwo.executeOptimalOne(mapCitiesIndex.get(startNodeComboBox.getValue()), goalInteger);
                pathOptimalTwo = OptimalTwo.executeOptimalTwo(mapCitiesIndex.get(startNodeComboBox.getValue()), goalInteger);
            } else {
                pathOptimalOne = AStar.executeAStar(mapCitiesIndex.get(startNodeComboBox.getValue()), goalInteger);
                for (int i = 0; i < distanceMatrix.length; i++) {
                    for (int j = 0; j < distanceMatrix.length; j++) {
                        if (distanceMatrix[i][j] != Double.POSITIVE_INFINITY)
                            distanceMatrix[i][j] = Math.round(distanceMatrix[i][j] * OptimalTwo.getAlpha() * 100.0) / 100.0;
                    }
                }
                pathOptimalTwo = AStar.executeAStar(mapCitiesIndex.get(startNodeComboBox.getValue()), goalInteger);
            }
            showDepthFirst();
            showAStar();
            showOptimalOne();
            showOptimalTwo();
        }
    }

    public void showDepthFirst() {
        for (int i = 0; i < pathDepthFirst.size() - 1; i++) {
            labelDepth.setText(labelDepth.getText() + pathDepthFirst.get(i).getCityName() + " -> ");
        }
        labelDepth.setText(labelDepth.getText() + pathDepthFirst.get(pathDepthFirst.size() - 1).getCityName());
        chartDepthFirst.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        XYChart.Series series = new XYChart.Series();
        series.setName("Visualizing The Path");
        for (int i = 0; i < pathDepthFirst.size(); i++) {
            XYChart.Data chartData;
            double x = cityCoordinates.get(mapCitiesIndex.get((pathDepthFirst.get(i).getCityName()))).getX();
            double y = cityCoordinates.get(mapCitiesIndex.get((pathDepthFirst.get(i).getCityName()))).getY();
            chartData = new XYChart.Data(x, y);
            chartData.setNode(new Label(pathDepthFirst.get(i).getCityName()));
            series.getData().add(chartData);

        }
        chartDepthFirst.setAnimated(false);
        chartDepthFirst.getData().add(series);
        XYChart.Data chartData;
        XYChart.Series startNode = new XYChart.Series();
        startNode.setName("Start Node");
        double x = cityCoordinates.get(mapCitiesIndex.get((pathDepthFirst.get(0).getCityName()))).getX();
        double y = cityCoordinates.get(mapCitiesIndex.get((pathDepthFirst.get(0).getCityName()))).getY();
        chartData = new XYChart.Data(x, y);
        chartData.setNode(new Label(pathDepthFirst.get(0).getCityName()));
        startNode.getData().add(chartData);
        chartDepthFirst.setAnimated(false);
        chartDepthFirst.getData().add(startNode);
    }

    public void showAStar() {
        for (int i = 0; i < pathAStar.size() - 1; i++) {
            labelAStar.setText(labelAStar.getText() + pathAStar.get(i).getCityName() + " -> ");
        }
        labelAStar.setText(labelAStar.getText() + pathAStar.get(pathAStar.size() - 1).getCityName());
        chartAStar.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        XYChart.Series series = new XYChart.Series();
        series.setName("Visualizing The Path");
        for (int i = 0; i < pathAStar.size(); i++) {
            XYChart.Data chartData;
            double x = cityCoordinates.get(mapCitiesIndex.get((pathAStar.get(i).getCityName()))).getX();
            double y = cityCoordinates.get(mapCitiesIndex.get((pathAStar.get(i).getCityName()))).getY();
            chartData = new XYChart.Data(x, y);
            chartData.setNode(new Label(pathAStar.get(i).getCityName()));
            series.getData().add(chartData);
        }
        chartAStar.setAnimated(false);
        chartAStar.getData().add(series);
        XYChart.Data chartData;
        XYChart.Series startNode = new XYChart.Series();
        startNode.setName("Start Node");
        double x = cityCoordinates.get(mapCitiesIndex.get((pathAStar.get(0).getCityName()))).getX();
        double y = cityCoordinates.get(mapCitiesIndex.get((pathAStar.get(0).getCityName()))).getY();
        chartData = new XYChart.Data(x, y);
        chartData.setNode(new Label(pathAStar.get(0).getCityName()));
        startNode.getData().add(chartData);
        chartAStar.setAnimated(false);
        chartAStar.getData().add(startNode);
    }

    public void showOptimalTwo() {
        for (int i = 0; i < pathOptimalTwo.size() - 1; i++) {
            labelOptimalTwo.setText(labelOptimalTwo.getText() + pathOptimalTwo.get(i).getCityName() + " -> ");
        }
        labelOptimalTwo.setText(labelOptimalTwo.getText() + pathOptimalTwo.get(pathOptimalTwo.size() - 1).getCityName());
        chartOptimalTwo.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        XYChart.Series series = new XYChart.Series();
        series.setName("Visualizing The Path");
        for (int i = 0; i < pathOptimalTwo.size(); i++) {
            XYChart.Data chartData;
            double x = cityCoordinates.get(mapCitiesIndex.get((pathOptimalTwo.get(i).getCityName()))).getX();
            double y = cityCoordinates.get(mapCitiesIndex.get((pathOptimalTwo.get(i).getCityName()))).getY();
            chartData = new XYChart.Data(x, y);
            chartData.setNode(new Label(pathOptimalTwo.get(i).getCityName()));
            series.getData().add(chartData);

        }
        chartOptimalTwo.setAnimated(false);
        chartOptimalTwo.getData().add(series);
        XYChart.Data chartData;
        XYChart.Series startNode = new XYChart.Series();
        startNode.setName("Start Node");
        double x = cityCoordinates.get(mapCitiesIndex.get((pathOptimalTwo.get(0).getCityName()))).getX();
        double y = cityCoordinates.get(mapCitiesIndex.get((pathOptimalTwo.get(0).getCityName()))).getY();
        chartData = new XYChart.Data(x, y);
        chartData.setNode(new Label(pathOptimalTwo.get(0).getCityName()));
        startNode.getData().add(chartData);
        chartOptimalTwo.setAnimated(false);
        chartOptimalTwo.getData().add(startNode);
    }

    public void showOptimalOne() {
        for (int i = 0; i < pathOptimalOne.size() - 1; i++) {
            labelOptimalOne.setText(labelOptimalOne.getText() + pathOptimalOne.get(i).getCityName() + " -> ");
        }
        labelOptimalOne.setText(labelOptimalOne.getText() + pathOptimalOne.get(pathOptimalOne.size() - 1).getCityName());
        chartOptimalOne.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        XYChart.Series series = new XYChart.Series();
        series.setName("Visualizing The Path");
        for (int i = 0; i < pathOptimalOne.size(); i++) {
            XYChart.Data chartData;
            double x = cityCoordinates.get(mapCitiesIndex.get((pathOptimalOne.get(i).getCityName()))).getX();
            double y = cityCoordinates.get(mapCitiesIndex.get((pathOptimalOne.get(i).getCityName()))).getY();
            chartData = new XYChart.Data(x, y);
            chartData.setNode(new Label(pathOptimalOne.get(i).getCityName()));
            series.getData().add(chartData);

        }
        chartOptimalOne.setAnimated(false);
        chartOptimalOne.getData().add(series);
        XYChart.Data chartData;
        XYChart.Series startNode = new XYChart.Series();
        startNode.setName("Start Node");
        double x = cityCoordinates.get(mapCitiesIndex.get((pathOptimalOne.get(0).getCityName()))).getX();
        double y = cityCoordinates.get(mapCitiesIndex.get((pathOptimalOne.get(0).getCityName()))).getY();
        chartData = new XYChart.Data(x, y);
        chartData.setNode(new Label(pathOptimalOne.get(0).getCityName()));
        startNode.getData().add(chartData);
        chartOptimalOne.setAnimated(false);
        chartOptimalOne.getData().add(startNode);
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        chartDepthFirst.getData().clear();
        chartDepthFirst.getYAxis().autosize();
        chartDepthFirst.getYAxis().setAutoRanging(true);
        destinationComboBox2.setVisible(false);
        destinationComboBox3.setVisible(false);
        destinationComboBox4.setVisible(false);
        destinationComboBox5.setVisible(false);
        destinationComboBox6.setVisible(false);
        FileReader.initializeReader();
        cityOptions = new ArrayList<>();
        for (int i = 0; i < City.mainCities.size(); i++) {
            cityOptions.add(City.mainCities.get(i).getCityName());
        }
        ObservableList<String> dataListInfo;
        dataListInfo = FXCollections.observableArrayList(cityOptions);
        startNodeComboBox.setItems(dataListInfo);
        destinationComboBox1.setItems(dataListInfo);
        destinationComboBox2.setItems(dataListInfo);
        destinationComboBox3.setItems(dataListInfo);
        destinationComboBox4.setItems(dataListInfo);
        destinationComboBox5.setItems(dataListInfo);
        destinationComboBox6.setItems(dataListInfo);

        chartDepthFirst.autosize();

        HashMap<Integer, CoordinateNode> coordinates = new HashMap<>();
        try {
            File readerCoordinate = new File("C:\\Users\\Main\\IdeaProjects\\SearchForOptimalService_AI\\SearchAlgos_AI_ProjectOne\\cityCoordinates.txt");
            Scanner scannerCoordinate = new Scanner(readerCoordinate);
            int countCities = 0;
            while (scannerCoordinate.hasNextLine()) {
                String data = scannerCoordinate.nextLine();
                String[] coordinateInfo = data.split(",");
                CoordinateNode tempNode = new CoordinateNode(Double.parseDouble(coordinateInfo[0]), Double.parseDouble(coordinateInfo[1]));
                coordinates.put(countCities, tempNode);
                countCities++;
            }
            scannerCoordinate.close();
            for (int i = 0; i < coordinates.size(); i++) {
                System.out.println(coordinates.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        showMap(coordinates);
    }

    public void showMap(HashMap<Integer, CoordinateNode> coordinates) {
        ;
        XYChart.Series series = new XYChart.Series();
        series.setName("Palestine Map");
        for (int i = 0; i < coordinates.size(); i++) {
            XYChart.Data chartData;
            double x = coordinates.get(i).getX();
            double y = coordinates.get(i).getY();
            chartData = new XYChart.Data(x, y);
            chartData.setNode(new Label(City.mainCities.get(i).getCityName()));
            series.getData().add(chartData);

        }
        mapChart.getData().add(series);

    }
}
