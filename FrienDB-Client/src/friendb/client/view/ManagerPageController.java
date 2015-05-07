/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import friendb.client.main.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Chin
 */
public class ManagerPageController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML
    private Button salesReportButton;
    @FXML
    private Button advertisementButton;
    @FXML
    private Button employeeStatisticsButton;
    @FXML
    private Button customerStatisticsButton;
    @FXML
    private Button allSalesButton;
    @FXML
    private Button revenueButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleReport(ActionEvent event) {
    }

    @FXML
    private void handleAdvertisements(ActionEvent event) {
    }

    @FXML
    private void handleEmployee(ActionEvent event) {
    }

    @FXML
    private void handleStatistics(ActionEvent event) {
    }

    @FXML
    private void handleSales(ActionEvent event) {
    }

    @FXML
    private void handleRevenue(ActionEvent event) {
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {
        
    }
    
}
