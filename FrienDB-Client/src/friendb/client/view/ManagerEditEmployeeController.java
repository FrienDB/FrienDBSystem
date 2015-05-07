/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.view;

import friendb.client.main.ControlledScreen;
import friendb.client.main.ScreensController;
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
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author Chin
 */
public class ManagerEditEmployeeController implements Initializable, ControlledScreen {
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
    private PasswordField passwordText;
    @FXML
    private Button confirmButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField hourlyText;
    @FXML
    private TextField roleText;
    @FXML
    private TextField EmployeeIDText;
  private final ServerAccessPoint findEmp =
            new ServerAccessPoint(ServerResources.FIND_EMPLOYEE_URL);
       private final ServerAccessPoint employeeEdit =
            new ServerAccessPoint(ServerResources.GET_EMPLOYEE_EDIT_URL);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleConfirm(ActionEvent event) {
    if(EmployeeIDText.getText().isEmpty())
        {
            return;
        }
        else
        {
        Response rsp = findEmp.request(EmployeeIDText.getText());
        GenericType<SimpleEmployee> gtlc = new GenericType<SimpleEmployee>() {
        };
        SimpleEmployee oldEmp = rsp.readEntity(gtlc);
        SimpleEmployee newEmp = new SimpleEmployee();
        newEmp.employeeID = oldEmp.employeeID;
        if(!firstNameText.getText().isEmpty())
        {
            newEmp.firstName= firstNameText.getText();
        }
        else
        {
            newEmp.firstName = oldEmp.firstName;
        }
        if(!lastNameText.getText().isEmpty())
        {
            newEmp.lastName= lastNameText.getText();
        }
        else
        {
            newEmp.lastName = oldEmp.lastName;
        }
        if(!telephoneText.getText().isEmpty())
        {
            newEmp.telephone= telephoneText.getText();
        }
        else
        {
            newEmp.telephone = oldEmp.telephone;
        }
        if(!addressText.getText().isEmpty())
        {
            newEmp.address= addressText.getText();
        }
        else
        {
            newEmp.address = oldEmp.address;
        }
        if(!cityText.getText().isEmpty())
        {
            newEmp.city= cityText.getText();
        }
        else
        {
            newEmp.city = oldEmp.city;
        }
        if(stateText.getValue()!=null)
        {
            newEmp.curState= stateText.getValue();
        }
        else
        {
            newEmp.curState = oldEmp.curState;
        }
        if(passwordText.getText().isEmpty())
        {
            newEmp.password= passwordText.getText();
        }
        else
        {
            newEmp.password = oldEmp.password;
        }
        if(!hourlyText.getText().isEmpty())
        {
            newEmp.hourlyRate= Double.parseDouble(hourlyText.getText());
        }
        else
        {
            newEmp.hourlyRate = oldEmp.hourlyRate;
        }
        if(!roleText.getText().isEmpty())
        {
            newEmp.role= roleText.getText();
        }
        else
        {
            newEmp.role = oldEmp.role;
        }
        
        Response rsp2 = employeeEdit.request(newEmp);
        }
    
    }

    @FXML
    private void HandleBack(ActionEvent event) {
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {
        
    }
    
}
