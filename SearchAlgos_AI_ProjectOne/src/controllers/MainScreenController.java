package controllers;

/**
 * Sample Skeleton for 'MainScreen.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class MainScreenController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tabPane"
    private JFXTabPane tabPane; // Value injected by FXMLLoader

    @FXML // fx:id="tab1"
    private Tab tab1; // Value injected by FXMLLoader

    @FXML // fx:id="startNodeComboBox"
    private JFXComboBox<?> startNodeComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox1"
    private JFXComboBox<?> destinationComboBox1; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox2"
    private JFXComboBox<?> destinationComboBox2; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox3"
    private JFXComboBox<?> destinationComboBox3; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox4"
    private JFXComboBox<?> destinationComboBox4; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox5"
    private JFXComboBox<?> destinationComboBox5; // Value injected by FXMLLoader

    @FXML // fx:id="destinationComboBox6"
    private JFXComboBox<?> destinationComboBox6; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddDestination"
    private JFXButton btnAddDestination; // Value injected by FXMLLoader

    @FXML // fx:id="btnRunAlgorithms"
    private JFXButton btnRunAlgorithms; // Value injected by FXMLLoader

    @FXML // fx:id="tab2"
    private Tab tab2; // Value injected by FXMLLoader

    @FXML // fx:id="taProblem1"
    private JFXTextArea taProblem1; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddProblem"
    private JFXButton btnAddProblem; // Value injected by FXMLLoader

    @FXML // fx:id="labelProblem2"
    private Label labelProblem2; // Value injected by FXMLLoader

    @FXML // fx:id="taProblem2"
    private JFXTextArea taProblem2; // Value injected by FXMLLoader

    @FXML // fx:id="labelProblem3"
    private Label labelProblem3; // Value injected by FXMLLoader

    @FXML // fx:id="taProblem3"
    private JFXTextArea taProblem3; // Value injected by FXMLLoader

    @FXML // fx:id="labelProblem4"
    private Label labelProblem4; // Value injected by FXMLLoader

    @FXML // fx:id="taProblem4"
    private JFXTextArea taProblem4; // Value injected by FXMLLoader

    @FXML // fx:id="btnContinueTab2"
    private JFXButton btnContinueTab2; // Value injected by FXMLLoader

    @FXML // fx:id="hboxPrice1"
    private HBox hboxPrice1; // Value injected by FXMLLoader

    @FXML // fx:id="tfPrice1"
    private JFXTextField tfPrice1; // Value injected by FXMLLoader

    @FXML // fx:id="hboxPrice2"
    private HBox hboxPrice2; // Value injected by FXMLLoader

    @FXML // fx:id="tfPrice2"
    private JFXTextField tfPrice2; // Value injected by FXMLLoader

    @FXML // fx:id="hboxPrice3"
    private HBox hboxPrice3; // Value injected by FXMLLoader

    @FXML // fx:id="tfPrice3"
    private JFXTextField tfPrice3; // Value injected by FXMLLoader

    @FXML // fx:id="hboxPrice4"
    private HBox hboxPrice4; // Value injected by FXMLLoader

    @FXML // fx:id="tfPrice4"
    private JFXTextField tfPrice4; // Value injected by FXMLLoader

    @FXML // fx:id="tab3"
    private Tab tab3; // Value injected by FXMLLoader

    @FXML // fx:id="tfSerialNumber"
    private JFXTextField tfSerialNumber; // Value injected by FXMLLoader

    @FXML // fx:id="tfModelNumer"
    private JFXTextField tfModelNumer; // Value injected by FXMLLoader

    @FXML // fx:id="radioiPhone"
    private JFXRadioButton radioiPhone; // Value injected by FXMLLoader

    @FXML // fx:id="radioiPad"
    private JFXRadioButton radioiPad; // Value injected by FXMLLoader

    @FXML // fx:id="radioMacBook"
    private JFXRadioButton radioMacBook; // Value injected by FXMLLoader

    @FXML // fx:id="radioAppleWatch"
    private JFXRadioButton radioAppleWatch; // Value injected by FXMLLoader

    @FXML // fx:id="tfSpecifications"
    private JFXTextField tfSpecifications; // Value injected by FXMLLoader

    @FXML // fx:id="toggleWarranty"
    private JFXToggleButton toggleWarranty; // Value injected by FXMLLoader

    @FXML // fx:id="tfCondition"
    private JFXTextField tfCondition; // Value injected by FXMLLoader

    @FXML // fx:id="btnContinueTab3"
    private JFXButton btnContinueTab3; // Value injected by FXMLLoader

    @FXML // fx:id="tab4"
    private Tab tab4; // Value injected by FXMLLoader

    @FXML // fx:id="technicianTV"
    private TableView<?> technicianTV; // Value injected by FXMLLoader

    @FXML // fx:id="colID"
    private TableColumn<?, ?> colID; // Value injected by FXMLLoader

    @FXML // fx:id="colName"
    private TableColumn<?, ?> colName; // Value injected by FXMLLoader

    @FXML // fx:id="colNumberOfActiveJobs"
    private TableColumn<?, ?> colNumberOfActiveJobs; // Value injected by FXMLLoader

    @FXML // fx:id="btnDone"
    private JFXButton btnDone; // Value injected by FXMLLoader

    @FXML // fx:id="btnAssign"
    private JFXButton btnAssign; // Value injected by FXMLLoader

    @FXML
    void btnAddDestinationClicked(ActionEvent event) {

    }

    @FXML
    void btnAddProblemClicked(ActionEvent event) {

    }

    @FXML
    void btnAssignClicked(ActionEvent event) {

    }

    @FXML
    void btnContinuetab2Clicked(ActionEvent event) {

    }

    @FXML
    void btnContinuetab3Clicked(ActionEvent event) {

    }

    @FXML
    void btnDoneClicked(ActionEvent event) {

    }

    @FXML
    void btnRunAlgorithmsClicked(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        destinationComboBox2.setVisible(false);
        destinationComboBox3.setVisible(false);
        destinationComboBox4.setVisible(false);
        destinationComboBox5.setVisible(false);
        destinationComboBox6.setVisible(false);
    }
}
