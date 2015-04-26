/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import friendb.client.main.FrienDBClient;
import friendb.client.main.ScreensController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author evanguby
 */
public class RegisterPageController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private ComboBox<String> sex;
    @FXML
    private TextField tele;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private ComboBox<String> state;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField zipCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        myController.setScreen(FrienDBClient.LoginPageID);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        myController.setScreen(FrienDBClient.LoginPageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "Male",
                        "Female"
                );
        sex.setItems(options);
        options = FXCollections.observableArrayList(
                "Alabama",
                "Alaska",
                "Arizona",
                "Arkansas",
                "California",
                "Colorado",
                "Connecticut",
                "Delaware",
                "Florida",
                "Georgia",
                "Hawaii",
                "Idaho",
                "Illinois",
                "Indiana",
                "Iowa",
                "Kansas",
                "Kentucky",
                "Louisiana",
                "Maine",
                "Maryland",
                "Massachusetts",
                "Michigan",
                "Minnesota",
                "Mississippi",
                "Missouri",
                "Montana",
                "Nebraska",
                "Nevada",
                "New Hampshire",
                "New Jersey",
                "New Mexico",
                "New York",
                "North Carolina",
                "North Dakota",
                "Ohio",
                "Oklahoma",
                "Oregon",
                "Pennsylvania",
                "Rhode Island",
                "South Carolina",
                "South Dakota",
                "Tennessee",
                "Texas",
                "Utah",
                "Vermont",
                "Virginia",
                "Washington",
                "West Virginia",
                "Wisconsin",
                "Wyoming"
        );
        state.setItems(options);
    }

}
