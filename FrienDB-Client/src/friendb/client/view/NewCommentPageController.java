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
import friendb.shared.SimpleComments;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author evanguby
 */
public class NewCommentPageController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    private final ServerAccessPoint addNewComment =
            new ServerAccessPoint(ServerResources.ADD_COMMENT_URL);
    
    @FXML
    private Label circleName;
    @FXML
    private TextArea content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBack(ActionEvent event) {
         myController.setScreen(FrienDBClient.CommentsPageID);
    }

    @FXML
    private void handleMakeComment(ActionEvent event) {
        CustomerSession cs = (CustomerSession)myController.getSession();
        SimpleComments newComment = new SimpleComments();
        
        newComment.postID = cs.getVisitingPost().postID;
        newComment.content = content.getText();
        newComment.author = (cs.getCustomerAccount().CustomerID);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String value = dateFormat.format(date);
        String change = value.replaceAll("/", "-");
        newComment.dateCommented = change;
        Response rsp = addNewComment.request(newComment);
    
        myController.loadScreen(FrienDBClient.YourCirclePageID, FrienDBClient.YourCirclePage);
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
