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
import friendb.client.session.EmployeeSession;
import friendb.client.web.ServerAccessPoint;
import friendb.client.web.ServerResources;
import friendb.shared.SimpleCircle;
import friendb.shared.SimpleCustomer;
import friendb.shared.SimpleEmployee;
import friendb.shared.SimpleSales;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.persistence.PersistenceContext;

/**
 * FXML Controller class
 *
 * @author Chin
 */
public class SalesScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private ListView salesList;
    @FXML
    private Label salesInfo;
    @FXML
    private Label salesTitle;
    @FXML
    private Button backButton;
    @FXML
    private Button selectButton;
    
    private final ServerAccessPoint getEmployeeSales
            = new ServerAccessPoint(ServerResources.GET_EMPLOYEE_SALES_URL);
     private final ServerAccessPoint getCustomerById =
            new ServerAccessPoint(ServerResources.GET_CUSTOMER_SALES_URL);
    private static final Logger logger =
            Logger.getLogger("friendb.beans.EmployeeBean");

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void populatePage() {
        EmployeeSession es = (EmployeeSession) myController.getSession();
        SimpleEmployee e = es.getEmployeeAccount();

        Response rsp = getEmployeeSales.request(es.getEmployeeSales());
        GenericType<List<SimpleSales>> gtlc = new GenericType<List<SimpleSales>>() {
        };
        ObservableList<String> sale = FXCollections.observableArrayList();
        ArrayList<SimpleSales> seA = new ArrayList<>();
        List<SimpleSales> seList = rsp.readEntity(gtlc);

        //circle.setItems(scList);
        for (SimpleSales ss : seList) {
            sale.add("TransID: " + ss.transID + " | Date Sold: " + ss.dateSold);
            seA.add(ss);
        }
        es.setEmployeeSales(seA);
        salesList.setItems(sale);
        //circle.setItems(circles);
    }

    @FXML
    private void HandleBack(ActionEvent event) {
        myController.setScreen(FrienDBClient.EmployeePageID);
    }

    @FXML
    private void HandleSelect(ActionEvent event) {
        int index = salesList.getSelectionModel().getSelectedIndex();
        EmployeeSession es = (EmployeeSession) myController.getSession();
        SimpleSales sale = es.getEmployeeSales().get(index);
        Response customer = getCustomerById.request(sale);
        
        GenericType<SimpleCustomer> gtlc = new GenericType<SimpleCustomer>() {
        };
        SimpleCustomer customersTo = customer.readEntity(gtlc);
        
        
        salesInfo.setText("TransID: " + sale.transID + "\n\n" + "AdID: "
                + sale.adID + "\n\n" + "Date Sold: " + sale.dateSold + "\n\n"
                + "Number of units sold: " + sale.numUnits + "\n\n" + "Account Sold to: "
                + sale.accountNum + "\n\n" + "TransID: " + sale.transID+
                "Customer First Name: " + customersTo.firstName + "\n\n" +
                "Customer Last Name: " + customersTo.lastName);

    }

}
