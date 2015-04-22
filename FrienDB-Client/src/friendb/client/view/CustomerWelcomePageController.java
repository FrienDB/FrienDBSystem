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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author evanguby
 */
public class CustomerWelcomePageController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private Label welcome;
    @FXML
    private TableView<?> ad;
    @FXML
    private TableView<?> circle;
    @FXML
    private TextField joinCircleName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlePurchase(ActionEvent event) {
    }

    @FXML
    private void handleVisitCircle(ActionEvent event) {
        myController.setScreen(FrienDBClient.YourCirclePageID);
    }

    @FXML
    private void handleNewCircle(ActionEvent event) {
        myController.setScreen(FrienDBClient.NewCirclePageID);
    }

    @FXML
    private void handleJoinCircle(ActionEvent event) {
    }

    @FXML
    private void handleViewBestSellerList(ActionEvent event) {
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        myController.setScreen(FrienDBClient.LoginPageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {

    }
    
}
