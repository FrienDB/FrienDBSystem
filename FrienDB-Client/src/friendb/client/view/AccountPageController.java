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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author nathanwong
 */
public class AccountPageController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private Label Accounts;
    @FXML
    private ListView<?> accounts;
    
    private final ServerAccessPoint getCustomersAccounts =
            new ServerAccessPoint(ServerResources.GET_CUSTOMERS_ACCOUNTS_URL);

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
    private void handleAddAccount(ActionEvent event) {
    }

    @FXML
    private void handleDeleteAccount(ActionEvent event) {
    }

    @FXML
    private void handleEditAccount(ActionEvent event) {
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {
        
        /*
        CustomerSession cs = (CustomerSession)myController.getSession();
        SimpleAccount c = cs.getCustomerAccount();

        Response rsp = getCustomersAccounts.request(cs.getCustomerAccount());
        GenericType<List<SimpleCircle>> gtlc = new GenericType<List<SimpleCircle>>() {
        };
        ObservableList<String> circle = FXCollections.observableArrayList();
        ArrayList<SimpleCircle> scA = new ArrayList<>();
        List<SimpleCircle> scList = rsp.readEntity(gtlc);
        
        //circle.setItems(scList);
        for(SimpleCircle sc : scList){
            circle.add("Name: " + sc.circleName + " | Type: "+ sc.circleType);
            scA.add(sc);
        }
        cs.setCircles(scA);
        circles.setItems(circle);
        //circle.setItems(circles);*/
    }

}
