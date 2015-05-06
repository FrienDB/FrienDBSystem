/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import friendb.client.main.FrienDBClient;
import static friendb.client.main.FrienDBClient.EmployeePageID;
import friendb.client.main.ScreensController;
import friendb.client.web.ServerAccessPoint;
import friendb.client.web.ServerResources;
import friendb.shared.SimpleAdvertisement;
import friendb.shared.SimpleCustomer;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author Chin
 */
public class NewAdvertisementController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML
    private TextField itemText;
    @FXML
    private TextField typeText;
    @FXML
    private TextField companyText;
    @FXML
    private TextField priceText;
    @FXML
    private TextArea descriptionText;
    @FXML
    private Button createButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField numUnitsText;
    private final ServerAccessPoint newAdvertisement =
            new ServerAccessPoint(ServerResources.ADD_NEW_ADVERTISEMENT_URL);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleCreate(ActionEvent event) {
        if(itemText.getText().isEmpty() || typeText.getText().isEmpty() || companyText.getText() == null ||
             priceText.getText().isEmpty() || descriptionText.getText().isEmpty() ||numUnitsText.getText().isEmpty())
        {
            //MISSING FIELD ERROR
        } else {
            SimpleAdvertisement a = new SimpleAdvertisement();
            
            a.adType=typeText.getText();
            a.company = companyText.getText();
            a.price = Double.parseDouble(priceText.getText());
            a.content=descriptionText.getText();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String value = dateFormat.format(date);
            String change = value.replaceAll("/","-");
            a.postDate = change;
            a.numOfUnits=Integer.parseInt(numUnitsText.getText());
            
            //transmit new ad form to server
        
            Response rsp = newAdvertisement.request(a);
            //check response code
            if (rsp.getStatus() != Response.Status.OK.getStatusCode())
            {
                //@TODO handle error codes
            }
            
            typeText.clear();
            companyText.clear();
            priceText.clear();
            descriptionText.clear();
            
            myController.setScreen(FrienDBClient.LoginPageID);
                
    }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        myController.setScreen(EmployeePageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void populatePage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
