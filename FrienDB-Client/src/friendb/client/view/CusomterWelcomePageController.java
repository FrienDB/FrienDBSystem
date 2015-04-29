/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import friendb.client.main.FrienDBClient;
import friendb.client.main.ScreensController;
<<<<<<< HEAD
=======
import friendb.client.session.CustomerSession;
import friendb.client.web.ServerAccessPoint;
import friendb.client.web.ServerResources;
import friendb.shared.SimpleCircle;
import friendb.shared.SimpleCustomer;
>>>>>>> parent of 5c8da0d... Revert "Pretty much done showing circles can't get last step"
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
<<<<<<< HEAD
    @FXML
    private TableView<?> circle;
=======
    @FXML
    private TableColumn<?, ?> company;
    @FXML
    private TableColumn<?, ?> product;
    @FXML
    private TableColumn<?, ?> price;
    @FXML
    private TableView<String[]> circle;
    @FXML
    private TableColumn<String[], String> circleName;
    @FXML
    private TableColumn<String[], String> circleType;
    @FXML
    private TableColumn<String[], String> circleOwner;
>>>>>>> parent of 5c8da0d... Revert "Pretty much done showing circles can't get last step"
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
<<<<<<< HEAD

=======
        CustomerSession cs = (CustomerSession)myController.getSession();
        SimpleCustomer c = cs.getCustomerAccount();
        welcome.setText("Welcome " + c.firstName + "!");
        
        Response rsp = getCustomersCircles.request(cs.getCustomerAccount());
        GenericType<List<SimpleCircle>> gtlc = new GenericType<List<SimpleCircle>>()
        {
        };
        
        List<SimpleCircle> scList = rsp.readEntity(gtlc);
        for(SimpleCircle sc : scList){
            String[] toAdd = new String[3];
            toAdd[0] = sc.circleName;
            toAdd[1] = sc.circleType;
            toAdd[2] = ""+sc.circleOwner;
            circle.getItems().add(toAdd);
        }
        //ArrayList<SimpleCircle> schoolNames = new ArrayList<>();
        //TableColumn<String,String> tcs = (TableColumn<String,String>) circle.getColumns();
        
        
>>>>>>> parent of 5c8da0d... Revert "Pretty much done showing circles can't get last step"
    }
    
}
