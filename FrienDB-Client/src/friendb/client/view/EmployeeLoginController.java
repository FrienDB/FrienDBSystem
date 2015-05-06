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
import friendb.client.session.EmployeeSession;
import friendb.shared.SimpleCustomer;
import friendb.shared.SimpleEmployee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Chin
 */
public class EmployeeLoginController implements Initializable, ControlledScreen {
     ScreensController myController;
    @FXML
    private Label welcomeTitle;
    @FXML
    private Button salesButton;
    @FXML
    private Button adsButton;
    @FXML
    private Button mailingButton;
    @FXML
    private Button suggestMailButton;
    @FXML
    private Button signOutButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
  
    }    

    @FXML
    private void HandleSales(ActionEvent event) {
        myController.setScreen(FrienDBClient.SalesPageID);
    }

    @FXML
    private void HandleAdvertisement(ActionEvent event) {
        myController.setScreen(FrienDBClient.AdsPageID);
    }
    @Override
    public void populatePage() {
        EmployeeSession es = (EmployeeSession)myController.getSession();
        SimpleEmployee e = es.getEmployeeAccount();
        welcomeTitle.setText("Welcome " + e.firstName + "!");
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void HandleMailing(ActionEvent event) {
        myController.setScreen(FrienDBClient.MailingListPageID);
    }

    @FXML
    private void HandleSuggest(ActionEvent event) {
        
    }

    @FXML
    private void HandleSignOut(ActionEvent event) {
        myController.setScreen(FrienDBClient.LoginPageID);
    }
    
}
