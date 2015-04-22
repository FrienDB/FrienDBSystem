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
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author evanguby
 */
public class YourCirclePageController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private Label circleName;
    @FXML
    private TableView<?> post;
    @FXML
    private ListView<?> circleMember;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleRemoveSelected(ActionEvent event) {
    }

    @FXML
    private void handleLikePost(ActionEvent event) {
    }

    @FXML
    private void handleRenameCircle(ActionEvent event) {
    }

    @FXML
    private void handleDeleteCircle(ActionEvent event) {
    }

    @FXML
    private void handleAddMember(ActionEvent event) {
    }

    @FXML
    private void handleRemovePost(ActionEvent event) {
    }

    @FXML
    private void handleNewPost(ActionEvent event) {
    }

    @FXML
    private void handleModifyPost(ActionEvent event) {
    }

    @FXML
    private void handleViewComments(ActionEvent event) {
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

    }
    
}
