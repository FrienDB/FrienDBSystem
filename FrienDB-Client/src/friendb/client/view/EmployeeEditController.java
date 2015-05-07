/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import friendb.client.main.FrienDBClient;
import static friendb.client.main.FrienDBClient.EmployeePageID;
import friendb.client.main.ScreensController;
import friendb.client.session.EmployeeSession;
import friendb.client.web.ServerAccessPoint;
import friendb.client.web.ServerResources;
import friendb.shared.SimpleEmployee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Chin
 */
public class EmployeeEditController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField telephoneText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField cityText;
    @FXML
    private ComboBox<String> stateText;
    @FXML
    private Button confirmButton;
    @FXML
    private Button backButton;
      private final ServerAccessPoint employeeEdit =
            new ServerAccessPoint(ServerResources.GET_EMPLOYEE_EDIT_URL);
    @FXML
    private PasswordField passwordText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleConfirm(ActionEvent event) {
        EmployeeSession es = (EmployeeSession)myController.getSession();
        SimpleEmployee e = es.getEmployeeAccount();
        SimpleEmployee newEmp = new SimpleEmployee();
        newEmp.employeeID = e.employeeID;
        newEmp.firstName=firstNameText.getText();
        newEmp.lastName=lastNameText.getText();
        newEmp.telephone=telephoneText.getText();
        newEmp.address=addressText.getText();
        newEmp.city=cityText.getText();
        if(stateText.getValue()!=null)
        {
            newEmp.curState= stateText.getValue();
        }
        else
        {
            newEmp.curState = e.curState;
        }
        if(!passwordText.getText().isEmpty())
        {
            newEmp.password= passwordText.getText();
        }
        else
        {
            newEmp.password = e.password;
        }
        employeeEdit.request(newEmp);
                    myController.createEmployeeSession(newEmp);

         myController.loadScreen(FrienDBClient.EmployeePageID, FrienDBClient.EmployeePage);
        myController.loadScreen(FrienDBClient.SalesPageID, FrienDBClient.SalesPage);
        myController.loadScreen(FrienDBClient.AdsPageID, FrienDBClient.AdsPage);
        myController.loadScreen(FrienDBClient.EmployeeEditPageID, FrienDBClient.EmployeeEditPage);
        myController.loadScreen(FrienDBClient.MailingListPageID, FrienDBClient.MailingListPage);
        myController.setScreen(EmployeePageID);
        
        
        
    }

    @FXML
    private void HandleBack(ActionEvent event) {
        myController.setScreen(EmployeePageID);
        
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
 
        
        
    }

    @Override
    public void populatePage() {
        EmployeeSession es = (EmployeeSession)myController.getSession();
        SimpleEmployee e = es.getEmployeeAccount();
        firstNameText.setText(e.firstName);
        lastNameText.setText(e.lastName);
        telephoneText.setText(e.telephone);
        addressText.setText(e.address);
        cityText.setText(e.city);
        
        
    }
    
}
