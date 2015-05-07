/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import static friendb.client.main.FrienDBClient.EmployeePageID;
import static friendb.client.main.FrienDBClient.NewAdvertisementID;
import friendb.client.main.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Chin
 */
public class AdsScreenController implements Initializable, ControlledScreen{
    ScreensController myController;
    @FXML
    private Label adTitle;
    @FXML
    private ListView<?> adList;
    @FXML
    private Label adInfo;
    @FXML
    private Button createAdButton;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleCreate(ActionEvent event) {
        myController.setScreen(NewAdvertisementID);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        myController.setScreen(EmployeePageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
       myController = screenPage;
    }

    @Override
    public void populatePage() {
        
    }
    
}
