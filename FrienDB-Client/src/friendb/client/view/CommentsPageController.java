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
import friendb.shared.SimpleCustomer;
import friendb.shared.SimplePost;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author evanguby
 */
public class CommentsPageController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private Label circleName;
    @FXML
    private ListView<String> post;
    @FXML
    private ListView<String> comment;

    private final ServerAccessPoint getPostComments
            = new ServerAccessPoint(ServerResources.GET_POST_COMMENTS_URL);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLikeComment(ActionEvent event) {
    }

    @FXML
    private void handleRemoveComment(ActionEvent event) {
    }

    @FXML
    private void handleNewComment(ActionEvent event) {
        myController.setScreen(FrienDBClient.NewCommentPageID);
    }

    @FXML
    private void handleModifyComment(ActionEvent event) {
    }

    @FXML
    private void handleBack(ActionEvent event) {
        myController.setScreen(FrienDBClient.YourCirclePageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {
        CustomerSession cs = (CustomerSession)myController.getSession();
        
        
        List<SimpleComments> comments2 = cs.getPostComments();
        SimplePost sp = cs.getVisitingPost();
        SimpleCustomer authorC = cs.getPostAuthor();
        String author = authorC.firstName + " " + authorC.lastName;
        post.getItems().add(author + ": " + sp.content + " (" + sp.datePosted + ")");
        List<SimpleCustomer> circleMems = cs.getAllCustomers();
        for(SimpleComments c : comments2){
            String commentAuthor = "";
            for(SimpleCustomer cus : circleMems){
                if(cus.CustomerID == c.author){
                    commentAuthor = cus.firstName + " " + cus.lastName;
                }
            }
            comment.getItems().add(commentAuthor + ": " + c.content + " (" + c.dateCommented + ")");
        }
    }
    
}
