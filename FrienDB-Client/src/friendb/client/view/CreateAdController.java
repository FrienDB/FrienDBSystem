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
import friendb.shared.SimpleAdvertisement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author Chin
 */
public class CreateAdController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML
    private Button createButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField itemText;
    @FXML
    private TextField companyText;
    @FXML
    private TextField typeText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField numUnitsText;
    private final ServerAccessPoint newAdvertisement
            = new ServerAccessPoint(ServerResources.ADD_ADVERTISEMENT_URL);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleCreate(ActionEvent event) {
        SimpleAdvertisement s=new SimpleAdvertisement();
        s.adType=typeText.getText();
        s.item=itemText.getText();
        s.company=companyText.getText();
        s.price=Double.parseDouble(priceText.getText());
        s.numUnits=Integer.parseInt(numUnitsText.getText());
        
        Response rsp = newAdvertisement.request(s);
        myController.setScreen(FrienDBClient.AdsPageID);
    }

    @FXML
    private void HandleBack(ActionEvent event) {
        myController.setScreen(FrienDBClient.AdsPageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
    }

    @Override
    public void populatePage() {
        
    }
    
}
