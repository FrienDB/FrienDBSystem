/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import friendb.client.main.ScreensController;
import friendb.client.session.EmployeeSession;
import friendb.client.web.ServerAccessPoint;
import friendb.client.web.ServerResources;
import friendb.shared.SimpleAdvertisement;
import friendb.shared.SimpleEmployee;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author Chin
 */
public class ManagerAdsController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML
    private ListView<String> adList;

    private final ServerAccessPoint getAdvertisement
            = new ServerAccessPoint(ServerResources.GET_ALL_ADVERTISEMENT_URL);
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {
       
         Response rsp3 = getAdvertisement.request();

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
