/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import friendb.client.main.FrienDBClient;
import friendb.client.main.ScreensController;
import friendb.shared.SimpleCustomer;
import friendb.shared.SimpleSales;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author Chin
 */
public class ManagerSalesController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML
    private ListView salesList;
    @FXML
    private ComboBox<String> monthCombo;
    @FXML
    private Button selectButton;
    @FXML
    private Button backButton;
    
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
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "January",
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December");
        monthCombo.setItems(options);
        
        
        /*
         Response rsp3 = getAllSalesByMonth.request();

        GenericType<List<SimpleSales>> gtlc3 = new GenericType<List<SimpleSales>>() {
        };
        List<SimpleSales> sales = rsp3.readEntity(gtlc3);
        for(SimpleSales sale: sales) {
            String date = sale.dateSold;
            String adId = ""+sale.adID;
            String numUnits = ""+sale.numUnits;
            String value = date + "\t\t" + adId +"\t\t"+ numUnits;
            salesList.getItems().add(value);
        }
                */
    }

    @FXML
    private void HandleSelect(ActionEvent event) {
        String value = monthCombo.getValue();
    }

    @FXML
    private void HandleBack(ActionEvent event) {
        myController.setScreen(FrienDBClient.ManagerPageID);
    }
    
}
