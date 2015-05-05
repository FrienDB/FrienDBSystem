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
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author nathanwong
 */
public class MessagesPageController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private Label Accounts;
    @FXML
    private ListView<?> accounts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBack(ActionEvent event) {
        myController.setScreen(FrienDBClient.CustomerWelcomePageID);
    }

    @FXML
    private void handleReply(ActionEvent event) {
    }

    @FXML
    private void handleCompose(ActionEvent event) {
    }

    @FXML
    private void handleDelete(ActionEvent event) {
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
    }

    @Override
    public void populatePage() {
    }
    
    
    
}
