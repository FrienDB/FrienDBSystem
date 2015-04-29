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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private TableView<?> circle;

    @FXML
    private TableColumn<?, ?> company;
    @FXML
    private TableColumn<?, ?> product;
    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<String[], String> circleName;
    @FXML
    private TableColumn<String[], String> circleType;
    @FXML
    private TableColumn<String[], String> circleOwner;

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
        myController.setScreen(FrienDBClient.YourCirclePageID);
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
        ObservableList<SimpleCircle> circles = FXCollections.observableArrayList();
        List<SimpleCircle> scList = rsp.readEntity(gtlc);
        
        circleName.setCellValueFactory(
                new PropertyValueFactory<>("circleName")
        );
        circleType.setCellValueFactory(
                new PropertyValueFactory<>("circleType")
        );
        circleOwner.setCellValueFactory(
                new PropertyValueFactory<>("circleOwner")
        );

        //circle.setItems(scList);
        for(SimpleCircle sc : scList){
            circles.add(sc);
        }
        
        //circle.setItems(circles);
        //circle.
//            String[] toAdd = new String[3];
//            toAdd[0] = sc.circleName;
//            toAdd[1] = sc.circleType;
//            toAdd[2] = ""+sc.circleOwner;
//            circle.getItems().add(toAdd);
//        }
        //ArrayList<SimpleCircle> schoolNames = new ArrayList<>();
        //TableColumn<String,String> tcs = (TableColumn<String,String>) circle.getColumns();
    }

}
