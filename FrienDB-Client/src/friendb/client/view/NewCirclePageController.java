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
import friendb.shared.SimpleCircle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author evanguby
 */
public class NewCirclePageController implements Initializable, ControlledScreen {

    ScreensController myController;

    @FXML
    private TextField circleName;
    @FXML
    private TextField circleType;

    private final ServerAccessPoint addNewCircle =
            new ServerAccessPoint(ServerResources.ADD_CIRCLE_URL);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleCreateCircle(ActionEvent event) {
        SimpleCircle newCircle = new SimpleCircle();
        newCircle.circleName = circleName.getText();
        newCircle.circleType = circleType.getText();
        CustomerSession cs = (CustomerSession)myController.getSession();
        newCircle.circleOwner = cs.getCustomerAccount().CustomerID;
        
        Response rsp = addNewCircle.request(newCircle);
        myController.loadScreen(FrienDBClient.CustomerWelcomePageID,FrienDBClient.CustomerWelcomePage);
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
