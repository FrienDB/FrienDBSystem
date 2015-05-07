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
import friendb.client.session.EmployeeSession;
import friendb.client.web.ServerAccessPoint;
import friendb.client.web.ServerResources;
import friendb.shared.SimpleAdvertisement;
import friendb.shared.SimpleCustomer;
import friendb.shared.SimpleEmployee;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

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
    private ListView adList;
    @FXML
    private Label adInfo;
    @FXML
    private Button createAdButton;
    @FXML
    private Button backButton;
    private final ServerAccessPoint getEmployeeAdvertisements
            = new ServerAccessPoint(ServerResources.GET_EMPLOYEE_ADS_URL);
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
        
         EmployeeSession es = (EmployeeSession) myController.getSession();
        SimpleEmployee e = es.getEmployeeAccount();
        System.out.print("e");
        
         Response rsp3 = getEmployeeAdvertisements.request(e);

        GenericType<List<SimpleAdvertisement>> gtlc3 = new GenericType<List<SimpleAdvertisement>>() {
        };
        List<SimpleAdvertisement> advertisements = rsp3.readEntity(gtlc3);
        for(SimpleAdvertisement ad: advertisements) {
            String item = ad.item;
            String company = ad.company;
            String post = ad.postDate;
            String content = ad.content;
            String type = ad.adType;
            String value = item + "\t\t"+"\t\t"+type+"\t\t" + company +"\t\t"+ post + "\t\t"+content;
            adList.getItems().add(value);
          
        }
                
    }
    
}
