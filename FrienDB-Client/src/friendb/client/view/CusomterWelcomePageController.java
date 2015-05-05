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
import friendb.shared.SimpleCircle;
import friendb.shared.SimpleCustomer;
import friendb.client.session.CustomerSession;
import friendb.shared.SimpleCustomer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author evanguby
 */
public class CusomterWelcomePageController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @FXML
    private Label welcome;
    @FXML
    private TableView<?> ad;
    @FXML
    private ListView circles;

    @FXML
    private TableColumn<?,?> company;
    @FXML
    private TableColumn<?, ?> product;
    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TextField joinCircleName;

    private final ServerAccessPoint getCustomersCircles =
            new ServerAccessPoint(ServerResources.GET_CUSTOMERS_CIRCLES_URL);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handlePurchase(ActionEvent event) {
    }

    @FXML
    private void handleVisitCircle(ActionEvent event) {
        int index = circles.getSelectionModel().getSelectedIndex();
        CustomerSession cs = (CustomerSession)myController.getSession();
        SimpleCircle circle = cs.getCircles().get(index);
        cs.setVisitingCircle(circle);
        if(cs.getCustomerAccount().CustomerID == circle.circleOwner){
            myController.loadScreen(FrienDBClient.YourCirclePageID, FrienDBClient.YourCirclePage);
            myController.setScreen(FrienDBClient.YourCirclePageID);
        }else{
            myController.loadScreen(FrienDBClient.CirclePageID, FrienDBClient.CirclePage);
            myController.setScreen(FrienDBClient.CirclePageID);
        }
        
    }

    @FXML
    private void handleNewCircle(ActionEvent event) {
        myController.setScreen(FrienDBClient.NewCirclePageID);
    }

    @FXML
    private void handleJoinCircle(ActionEvent event) {
    }
    @FXML
    private void handleViewBestSellerList(ActionEvent event) {
    }
    
    @FXML
    private void handleManageInfo(ActionEvent event) {
        myController.setScreen(FrienDBClient.ManageCustomerInfoID);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        myController.setScreen(FrienDBClient.LoginPageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {

        CustomerSession cs = (CustomerSession)myController.getSession();
        SimpleCustomer c = cs.getCustomerAccount();
        welcome.setText("Welcome " + c.firstName + "!");

        Response rsp = getCustomersCircles.request(cs.getCustomerAccount());
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
        //circle.setItems(circles);

    }

    @FXML
    }
}
