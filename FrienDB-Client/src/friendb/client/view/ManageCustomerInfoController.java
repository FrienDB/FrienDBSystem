/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import friendb.client.main.FrienDBClient;
import friendb.client.main.ScreensController;
import friendb.client.session.CustomerSession;
import friendb.client.web.ServerAccessPoint;
import friendb.client.web.ServerResources;
import friendb.shared.SimpleCustomer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author nathanwong
 */
public class ManageCustomerInfoController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
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
    
    private final ServerAccessPoint updateCustomer =
            new ServerAccessPoint(ServerResources.UPDATE_CUSTOMER_URL);
    
            

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleSave(ActionEvent event) {
        CustomerSession cs = (CustomerSession)myController.getSession();
        SimpleCustomer sc = new SimpleCustomer();
        SimpleCustomer oldInfo = cs.getCustomerAccount();
        sc.CustomerID = oldInfo.CustomerID;
        sc.address = address.getText();
        sc.city = city.getText();
        sc.dob = city.getText();
        sc.emailID = email.getText();
        sc.firstName = firstName.getText();
        sc.lastName = lastName.getText();
        sc.password = password.getText();
        sc.sex = sex.getPromptText().charAt(0);
        sc.state = state.getPromptText();
        sc.telephone = tele.getText();
        sc.zipCode = Integer.parseInt(zipCode.getText());
        sc.dob = dob.getPromptText();
        cs.setCustomer(sc);
        Response rsp = updateCustomer.request(sc);
        myController.loadScreen(FrienDBClient.CustomerWelcomePageID, FrienDBClient.CustomerWelcomePage);
        myController.setScreen(FrienDBClient.CustomerWelcomePageID);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        myController.setScreen(FrienDBClient.CustomerWelcomePageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {
        
        CustomerSession cs = (CustomerSession)myController.getSession();
        SimpleCustomer c = cs.getCustomerAccount();
        
        firstName.setText(c.firstName);
        lastName.setText(c.lastName);
        email.setText(c.emailID);
        password.setText(c.password);
        tele.setText(c.telephone);
        address.setText(c.address);
        city.setText(c.city);
        state.setPromptText(c.state);
        sex.setPromptText("" + c.sex);
        zipCode.setText( "" + c.zipCode);
        dob.setPromptText(c.dob);
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "M",
                        "F"
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
