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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import friendb.shared.SimplePost;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author evanguby
 */
public class NewPostPageController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @FXML
    private Label circleName;
    
    private final ServerAccessPoint addNewCircle =
            new ServerAccessPoint(ServerResources.ADD_POST_URL);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBack(ActionEvent event) {
        myController.setScreen(FrienDBClient.YourCirclePageID);
    }

    @FXML
    private void handleMakePost(ActionEvent event) {
        CustomerSession cs = (CustomerSession)myController.getSession();
        SimplePost newPost = new SimplePost();
        
        newPost.pageID = cs.getPageID();
//        newPost.content = 
//        newPost.authorID = Integer.parseInt( (cs.getCustomerAccount().CustomerID) );
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String value = dateFormat.format(date);
        String change = value.replaceAll("/", "-");
        newPost.datePosted = change;

        Response rsp = addNewCircle.request(newPost);
        
        myController.setScreen(FrienDBClient.YourCirclePageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {

    }
    
}
