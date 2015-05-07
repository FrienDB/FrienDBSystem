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
import friendb.shared.LoginInfo;
import friendb.shared.SimpleCustomer;
import friendb.shared.SimpleEmployee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author evanguby
 */
public class LoginPageController implements Initializable, ControlledScreen {

    ScreensController myController;

    private final ServerAccessPoint AUTHENTICATE
            = new ServerAccessPoint(ServerResources.AUTHENTICATION_URL);

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLogin(ActionEvent event) {

        if (true) { //Logged in as student only for now
            //myController.loadStudentPages(); 
            //myController.setScreen(FrienDBClient.CustomerWelcomePageID);

            //create a login credentials structure
            LoginInfo login = new LoginInfo();
            login.email = email.getText();
            login.password = password.getText();
            if(((login.email).toUpperCase()=="ADMIN")&&((login.password).toUpperCase()=="ADMIN"))
            {
                myController.loadScreen(FrienDBClient.CustomerWelcomePageID, FrienDBClient.CustomerWelcomePage);
            }
            //transmit the login credentials to the server
            Response rsp = AUTHENTICATE.request(login);

            //if response codei indicates error then inform the client and return
            if (rsp.getStatus() != Response.Status.OK.getStatusCode()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Error");
                alert.setHeaderText("Incorrect Username");
                alert.setContentText("Username does not exist");
                alert.show();

                return;
            } //successful response
            else {
                //customer authenticate with an email
                if (login.email.indexOf('@') != -1) {
                    SimpleCustomer cust = rsp.readEntity(SimpleCustomer.class);
                    myController.createStudentSession(cust);

                    myController.loadScreen(FrienDBClient.CustomerWelcomePageID, FrienDBClient.CustomerWelcomePage);
                     //this will be moved once we know what circle page we have to load
                     //this will too MOVED
                    myController.loadScreen(FrienDBClient.NewCirclePageID, FrienDBClient.NewCirclePage);
                    myController.loadScreen(FrienDBClient.NewCommentPageID, FrienDBClient.NewCommentPage);
                    
                    myController.loadScreen(FrienDBClient.NewPostPageID, FrienDBClient.NewPostPage);
                    myController.loadScreen(FrienDBClient.ManageCustomerInfoID, FrienDBClient.ManageCustomerInfo);
                    myController.loadScreen(FrienDBClient.AccountPageID, FrienDBClient.AccountPage);
                    //myController.loadScreen(FrienDBClient.MessagesPageID, FrienDBClient.MessagesPage);
                    myController.setScreen(FrienDBClient.CustomerWelcomePageID);

                } //employees have no @ symbols in their username
                else {
                    SimpleEmployee emp = rsp.readEntity(SimpleEmployee.class);
                    String upperRole = emp.role.toUpperCase();
                    if(upperRole=="MANAGER")
                    {
                        myController.createEmployeeSession(emp);
                        myController.loadScreen(FrienDBClient.EmployeePageID, FrienDBClient.EmployeePage);
                        myController.loadScreen(FrienDBClient.SalesPageID, FrienDBClient.SalesPage);
                        myController.loadScreen(FrienDBClient.AdsPageID, FrienDBClient.AdsPage);
                        myController.setScreen(FrienDBClient.EmployeePageID);

                    }
                    else
                    {
                    myController.createEmployeeSession(emp);
                    if(emp.role.equalsIgnoreCase("Manager")){
                        
                    }else{
                        myController.loadScreen(FrienDBClient.EmployeePageID, FrienDBClient.EmployeePage);
                        myController.loadScreen(FrienDBClient.SalesPageID, FrienDBClient.SalesPage);
                        myController.loadScreen(FrienDBClient.AdsPageID, FrienDBClient.AdsPage);
                        myController.loadScreen(FrienDBClient.MailingListPageID, FrienDBClient.MailingListPage);
                        myController.setScreen(FrienDBClient.EmployeePageID);
                    }

                    }

                }
//>>>>>>> origin/master
            }
        }
        
        // CLEAR THE TEXT FIELDS SO THE INFO ISN'T THERE AFTER LOGOUT
        email.clear();
        password.clear();
    }

    @FXML
    private void handleNewUser(ActionEvent event) {
        myController.setScreen(FrienDBClient.RegisterPageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {

    }

}
