/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import friendb.client.main.FrienDBClient;
import friendb.client.main.ScreensController;
import friendb.client.web.ServerAccessPoint;
import friendb.client.web.ServerResources;
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
import friendb.shared.SimpleCustomer;
import javafx.scene.control.PasswordField;
import javax.ws.rs.core.Response;

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
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    
    private final ServerAccessPoint newCustomer =
            new ServerAccessPoint(ServerResources.ADD_NEW_CUSTOMER_URL);
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        System.out.println(dob.getValue().toString());
        if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || sex.getValue() == null ||
                tele.getText().isEmpty() || address.getText().isEmpty() || city.getText().isEmpty() ||
                state.getValue() == null || dob.isPressed() || zipCode.getText().isEmpty()
                || email.getText().isEmpty()){
            //MISSING FIELD ERROR
        } else {
            SimpleCustomer c = new SimpleCustomer();
            c.address = address.getText();
            c.city = city.getText();
            c.dob = dob.getPromptText();
            c.emailID = email.getText();
            c.firstName = firstName.getText();
            c.lastName = lastName.getText();
            c.password = password.getText();
            c.sex = sex.getValue().charAt(0);
            c.state = state.getValue();
            c.telephone = tele.getText();
            c.zipCode = Integer.parseInt(zipCode.getText());
            
            //transmit new student form to server
            Response rsp = newCustomer.request(c);
            //check response code
            if (rsp.getStatus() != Response.Status.OK.getStatusCode())
            {
                //@TODO handle error codes
            }
            
            address.clear();
            city.clear();
            dob.setValue(null);
            email.clear();
            firstName.clear();
            lastName.clear();
            password.clear();
            sex.getSelectionModel().clearSelection();
            state.getSelectionModel().clearSelection();
            tele.clear();
            zipCode.clear();
            
            myController.setScreen(FrienDBClient.LoginPageID);
        }
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
                "AL",
                "AK",
                "AZ",
                "AR",
                "CA",
                "CO",
                "CT",
                "DE",
                "FL",
                "GA",
                "HI",
                "ID",
                "IL",
                "IN",
                "IA",
                "KS",
                "KY",
                "LA",
                "ME",
                "MD",
                "MA",
                "MI",
                "MN",
                "MS",
                "MO",
                "MT",
                "NE",
                "NV",
                "NH",
                "NJ",
                "NM",
                "NY",
                "NC",
                "ND",
                "OH",
                "OK",
                "OR",
                "PA",
                "RI",
                "SC",
                "SD",
                "TN",
                "TX",
                "UT",
                "VT",
                "VA",
                "WA",
                "WV",
                "WI",
                "WY"
        );
        state.setItems(options);
    }

}
