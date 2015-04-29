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
<<<<<<< HEAD
=======
import friendb.client.session.CustomerSession;
=======
>>>>>>> parent of 861b97e... Revert 780e2f5..dc40f98
import friendb.client.web.ServerAccessPoint;
import friendb.client.web.ServerResources;
import friendb.shared.LoginInfo;
import friendb.shared.SimpleCustomer;
import friendb.shared.SimpleEmployee;
<<<<<<< HEAD
>>>>>>> parent of 5c8da0d... Revert "Pretty much done showing circles can't get last step"
=======
>>>>>>> parent of 861b97e... Revert 780e2f5..dc40f98
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
<<<<<<< HEAD
<<<<<<< HEAD
        if(true){ //Logged in as student only for now
            //myController.loadStudentPages(); 
            myController.setScreen(FrienDBClient.CustomerWelcomePageID);
=======
=======
>>>>>>> parent of 861b97e... Revert 780e2f5..dc40f98
        //create a login credentials structure
        LoginInfo login = new LoginInfo();
        login.email = email.getText();
        login.password = password.getText();

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
<<<<<<< HEAD
                
=======
>>>>>>> parent of 861b97e... Revert 780e2f5..dc40f98
                myController.loadScreen(FrienDBClient.CustomerWelcomePageID, FrienDBClient.CustomerWelcomePage);
                myController.loadScreen(FrienDBClient.CirclePageID, FrienDBClient.CirclePage); //this will be moved once we know what circle page we have to load
                myController.loadScreen(FrienDBClient.YourCirclePageID, FrienDBClient.YourCirclePage); //this will too
                myController.loadScreen(FrienDBClient.NewCirclePageID, FrienDBClient.NewCirclePage);
                myController.loadScreen(FrienDBClient.NewCommentPageID, FrienDBClient.NewCommentPage);
                myController.loadScreen(FrienDBClient.CommentsPageID, FrienDBClient.CommentsPage);
                myController.loadScreen(FrienDBClient.NewPostPageID, FrienDBClient.NewPostPage);
                myController.setScreen(FrienDBClient.CustomerWelcomePageID);

            } //employees have no @ symbols in their username
            else {
                SimpleEmployee emp = rsp.readEntity(SimpleEmployee.class);
                //myController.createEmployeeSession(emp);
                //myController.setScreen(FrienDBClient.EmployeeWelcomePage);
            }

<<<<<<< HEAD
>>>>>>> parent of 5c8da0d... Revert "Pretty much done showing circles can't get last step"
=======
>>>>>>> parent of 861b97e... Revert 780e2f5..dc40f98
        }
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
