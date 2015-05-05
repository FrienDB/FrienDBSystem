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
import friendb.shared.SimpleCustomer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleSave(ActionEvent event) {
        
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
        zipCode.setText( "" + c.zipCode);
        
    }
    
}
