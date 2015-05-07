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
import friendb.shared.SimpleAdvertisement;
import friendb.shared.SimpleCircleMembership;
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
import javafx.scene.control.ComboBox;
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
    private ListView<String> ads;
    @FXML
    private ListView<String> circles;


    @FXML
    private ComboBox circlesToJoin;

    private final ServerAccessPoint getCustomersCircles =
            new ServerAccessPoint(ServerResources.GET_CUSTOMERS_CIRCLES_URL);
    
    private final ServerAccessPoint getTopSellingList =
            new ServerAccessPoint(ServerResources.GET_TOP_SELLING_LIST_URL);
    
    private final ServerAccessPoint getAllCircles =
            new ServerAccessPoint(ServerResources.GET_ALL_CIRCLES_URL);
    private final ServerAccessPoint joinCircle =
            new ServerAccessPoint(ServerResources.ADD_CUSTOMER_TO_CIRCLE_URL);
   
    @FXML
    private ListView<?> accounts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handlePurchase(ActionEvent event) {
        CustomerSession cs = (CustomerSession)myController.getSession();
        List<SimpleAdvertisement> ads = cs.getAdvertisements();
        int index = this.ads.getSelectionModel().getSelectedIndex();
        SimpleAdvertisement ad = ads.get(index);
        cs.setAdvertisement(ad);
        myController.loadScreen(FrienDBClient.MakePurchasePageID, FrienDBClient.MakePurchasePage);
        myController.setScreen(FrienDBClient.MakePurchasePageID);
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
        CustomerSession cs = (CustomerSession) myController.getSession();
        
        SimpleCircle sc = cs.getCirclesNotIn().get(circlesToJoin.getSelectionModel().getSelectedIndex());
        
        SimpleCircleMembership scm = new SimpleCircleMembership();
        scm.circleID = sc.circleID;
        scm.customerID = cs.getCustomerAccount().CustomerID;
        joinCircle.request(scm);
        
        myController.loadScreen(FrienDBClient.CustomerWelcomePageID, FrienDBClient.CustomerWelcomePage);
        myController.setScreen(FrienDBClient.CustomerWelcomePageID);
    }
    
    @FXML
    private void handleViewBestSellerList(ActionEvent event) {
    }
    
    @FXML
    private void handleManageInfo(ActionEvent event) {
        myController.setScreen(FrienDBClient.ManageCustomerInfoID);
    }
    
    @FXML
    private void handleAccounts(ActionEvent event) {
        myController.setScreen(FrienDBClient.AccountPageID);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        myController.setScreen(FrienDBClient.LoginPageID);
    }
    
    @FXML
    private void handleMessages(ActionEvent event) {
        myController.setScreen(FrienDBClient.MessagesPageID);
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
        /*
        Response rsp2 = getTopSellingList.request();
        GenericType<List<SimpleAdvertisement>> gtlc2 = new GenericType<List<SimpleAdvertisement>>() {
        };
        List<SimpleAdvertisement> ads2 = rsp2.readEntity(gtlc2);
        
        cs.setAdvertisements(ads2);
        for(SimpleAdvertisement ad : ads2){
            ads.getItems().add(ad.company + ": " + ad.item + " for $" + ad.price);
        }
        */
        Response rsp3 = getAllCircles.request();
        GenericType<List<SimpleCircle>> gtlc3 = new GenericType<List<SimpleCircle>>() {
        };
        List<SimpleCircle> allCircles = rsp3.readEntity(gtlc3);
        List<SimpleCircle> circlesNotIn = new ArrayList<>();
        for(SimpleCircle circle3 : allCircles){
            boolean toAdd= true;
            for(SimpleCircle circle2 : scA){
                if(circle3.circleID == circle2.circleID)
                    toAdd = false;
                
            }
            if(toAdd)
                circlesNotIn.add(circle3);
                
        }
        for(SimpleCircle circle4 : circlesNotIn){
            circlesToJoin.getItems().add(circle4.circleName);
        }
        cs.setCirclesNotIn(circlesNotIn);
        //circle.setItems(circles);
    }
    
}
