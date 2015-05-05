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
import friendb.shared.SimpleAccount;
import friendb.shared.SimpleCustomer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javax.ws.rs.core.GenericType;
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
    private ListView<String> accounts;
    
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
        
        CustomerSession cs = (CustomerSession)myController.getSession();
        SimpleCustomer c = cs.getCustomerAccount();

        Response rsp = getCustomersAccounts.request(c);
        GenericType<List<SimpleAccount>> gtlc = new GenericType<List<SimpleAccount>>() {
        };
        ObservableList<String> account = FXCollections.observableArrayList();
        ArrayList<SimpleAccount> saA = new ArrayList<>();
        List<SimpleAccount> saList = rsp.readEntity(gtlc);
        
        //circle.setItems(scList);
        for(SimpleAccount sa : saList){
            account.add("Name: " + sa.accountNum + " | Type: "+ sa.creditCard);
            saA.add(sa);
        }
        cs.setAccounts(saList);
        accounts.setItems(account);
        //circle.setItems(circles);
    }

}
