/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import static friendb.client.main.FrienDBClient.EmployeePageID;
import friendb.client.main.ScreensController;
import friendb.client.web.ServerAccessPoint;
import friendb.client.web.ServerResources;
import friendb.shared.SimpleCustomer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author Chin
 */
public class MailingListController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML
    private ListView<String> mailingList;
    @FXML
    private Button backButton;
    private final ServerAccessPoint getAllCustomers
            = new ServerAccessPoint(ServerResources.GET_ALL_CUSTOMERS_URL);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleBack(ActionEvent event) {
        myController.setScreen(EmployeePageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {
         Response rsp3 = getAllCustomers.request();

        GenericType<List<SimpleCustomer>> gtlc3 = new GenericType<List<SimpleCustomer>>() {
        };
        List<SimpleCustomer> customers = rsp3.readEntity(gtlc3);
        for(SimpleCustomer customer: customers) {
            String first = customer.firstName;
            String last = customer.lastName;
            String email = customer.emailID;
            String value = first + " " + last +" "+ email;
            mailingList.getItems().add(value);
        }
    }
    
}
