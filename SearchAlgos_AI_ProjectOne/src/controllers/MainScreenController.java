package controllers;

/**
 * Sample Skeleton for 'MainScreen.fxml' Controller Class
 */

import algorithms.AStar;
import algorithms.DepthFirst;
import algorithms.OptimalTwo;
import classes.City;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import utilities.FileReader;

import static utilities.FileReader.cityCoordinates;
import static utilities.FileReader.mapCitiesIndex;

public class MainScreenController {

    ArrayList<City> pathDepthFirst = new ArrayList<>();
    ArrayList<City> pathAStar = new ArrayList<>();
    ArrayList<City> pathOptimalTwo = new ArrayList<>();
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



    static int counter = 1;
    @FXML
    void btnAddDestinationClicked(ActionEvent event) {
        counter++;
        switch (counter){
            case 2: destinationComboBox2.setVisible(true);
            break;
            case 3: destinationComboBox3.setVisible(true);
            break;
            case 4: destinationComboBox4.setVisible(true);
            break;
            case 5: destinationComboBox5.setVisible(true);
            break;
            case 6: destinationComboBox6.setVisible(true);
            break;
            default: System.out.println("No more options available");

        }
    }


    @FXML
    void btnRunAlgorithmsClicked(ActionEvent event) {
        ArrayList<City> goals = new ArrayList<>();
        ArrayList<Integer> goalInteger = new ArrayList<>();
        if (destinationComboBox1.isVisible() && !destinationComboBox1.getValue().isEmpty()){
            int index = mapCitiesIndex.get(destinationComboBox1.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (destinationComboBox2.isVisible() && !destinationComboBox2.getValue().isEmpty()){
            int index = mapCitiesIndex.get(destinationComboBox2.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (destinationComboBox3.isVisible() && !destinationComboBox3.getValue().isEmpty()){
            int index = mapCitiesIndex.get(destinationComboBox3.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (destinationComboBox4.isVisible() && !destinationComboBox4.getValue().isEmpty()){
            int index = mapCitiesIndex.get(destinationComboBox4.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (destinationComboBox5.isVisible() && !destinationComboBox5.getValue().isEmpty()){
            int index = mapCitiesIndex.get(destinationComboBox5.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (destinationComboBox6.isVisible() && !destinationComboBox6.getValue().isEmpty()){
            int index = mapCitiesIndex.get(destinationComboBox6.getValue());
            City temp = City.mainCities.get(index);
            goals.add(temp);
            goalInteger.add(index);
        }
        if (!startNodeComboBox.getValue().isEmpty())
            startNode = City.mainCities.get(mapCitiesIndex.get(startNodeComboBox.getValue())).getHeuristicCities().get(mapCitiesIndex.get(startNodeComboBox.getValue()));
        if(goals.size()>0 && startNode != null) {
            pathDepthFirst = DepthFirst.executeDFS(startNode, goals);
            pathAStar = AStar.executeAStar(mapCitiesIndex.get(startNodeComboBox.getValue()),goalInteger);
            pathOptimalTwo = OptimalTwo.executeOptimalTwo(mapCitiesIndex.get(startNodeComboBox.getValue()), goalInteger);
            showDepthFirst();
            showAStar();
            showOptimalTwo();
        }
    }
    public void showDepthFirst(){
        for(int i=0; i<pathDepthFirst.size(); i++){
            labelDepth.setText(labelDepth.getText() + pathDepthFirst.get(i).getCityName() + "->");
        }

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
        chartDepthFirst.getData().add(series);
    }
    public void showAStar(){
        for(int i=0; i<pathAStar.size(); i++){
            labelAStar.setText(labelAStar.getText() + pathAStar.get(i).getCityName() + "->");
        }
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
        chartAStar.getData().add(series);
    }
    public void showOptimalTwo(){
        for(int i=0; i<pathOptimalTwo.size(); i++){
            labelOptimalTwo.setText(labelOptimalTwo.getText() + pathOptimalTwo.get(i).getCityName() + "->");
        }
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
        chartOptimalTwo.getData().add(series);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        destinationComboBox2.setVisible(false);
        destinationComboBox3.setVisible(false);
        destinationComboBox4.setVisible(false);
        destinationComboBox5.setVisible(false);
        destinationComboBox6.setVisible(false);
        FileReader.initializeReader();
        ArrayList<String> cityOptions = new ArrayList<>();
        for (int i=0; i< City.mainCities.size(); i++){
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
    }
}
