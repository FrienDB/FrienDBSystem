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
import friendb.shared.SimpleSales;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author evanguby
 */
public class MakePurchasePageController implements Initializable, ControlledScreen {

    ScreensController myController;

    @FXML
    private Label welcome;
    @FXML
    private ComboBox<String> creditCard;
    @FXML
    private Label quantity;
    @FXML
    private TextField amount;

    private final ServerAccessPoint getCustomersAccounts
            = new ServerAccessPoint(ServerResources.GET_CUSTOMERS_ACCOUNTS_URL);
    
    private final ServerAccessPoint makeSale =
            new ServerAccessPoint(ServerResources.MAKE_SALE_URL);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleBack(ActionEvent event) {
        myController.loadScreen(FrienDBClient.CustomerWelcomePageID, FrienDBClient.CustomerWelcomePage);
        myController.setScreen(FrienDBClient.CustomerWelcomePageID);
    }

    @FXML
    private void handleMakePurchase(ActionEvent event) {
        CustomerSession cs = (CustomerSession) myController.getSession();
        SimpleSales sale = new SimpleSales();

        sale.accountNum = cs.getAccounts().get(creditCard.getSelectionModel().getSelectedIndex()).accountNum;
        sale.adID = cs.getAdvertisement().adID;
        sale.numUnits = Integer.parseInt(amount.getText());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String value = dateFormat.format(date);
        String change = value.replaceAll("/", "-");
        sale.dateSold = change;
        
        makeSale.request(sale);
        
        
        
        myController.loadScreen(FrienDBClient.CustomerWelcomePageID, FrienDBClient.CustomerWelcomePage);
        myController.setScreen(FrienDBClient.CustomerWelcomePageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {
        CustomerSession cs = (CustomerSession) myController.getSession();
        quantity.setText("Quantity (" + cs.getAdvertisement().numOfUnits + ")");

        SimpleCustomer c = cs.getCustomerAccount();

        Response rsp = getCustomersAccounts.request(c);
        GenericType<List<SimpleAccount>> gtlc = new GenericType<List<SimpleAccount>>() {
        };
        ObservableList<String> account = FXCollections.observableArrayList();
        ArrayList<SimpleAccount> saA = new ArrayList<>();
        List<SimpleAccount> saList = rsp.readEntity(gtlc);
        for (SimpleAccount sa : saList) {
            account.add(sa.creditCard);
        }
        creditCard.getItems().setAll(account);
        cs.setAccounts(saList);
    }

}
