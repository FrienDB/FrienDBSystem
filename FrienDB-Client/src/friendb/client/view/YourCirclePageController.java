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
import friendb.shared.SimpleCircleMembership;
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
public class YourCirclePageController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private Label circleName;
    @FXML
    private ListView<String> post;
    @FXML
    private ListView<String> circleMember;
    
    private final ServerAccessPoint getCircleMembers =
            new ServerAccessPoint(ServerResources.GET_CIRCLE_MEMBERS_URL);
    
    private final ServerAccessPoint getCirclePosts =
            new ServerAccessPoint(ServerResources.GET_CIRCLE_POSTS_URL);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleRemoveSelected(ActionEvent event) {
    }

    @FXML
    private void handleLikePost(ActionEvent event) {
    }

    @FXML
    private void handleRenameCircle(ActionEvent event) {
    }

    @FXML
    private void handleDeleteCircle(ActionEvent event) {
    }

    @FXML
    private void handleAddMember(ActionEvent event) {
    }

    @FXML
    private void handleRemovePost(ActionEvent event) {
    }

    @FXML
    private void handleNewPost(ActionEvent event) {
        myController.setScreen(FrienDBClient.NewPostPageID);
    }

    @FXML
    private void handleModifyPost(ActionEvent event) {
    }

    @FXML
    private void handleViewComments(ActionEvent event) {
        myController.setScreen(FrienDBClient.CommentsPageID);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        myController.setScreen(FrienDBClient.CustomerWelcomePageID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {
        CustomerSession cs = (CustomerSession)myController.getSession();
        circleName.setText(cs.getVisitingCircle().circleName);
        SimpleCircleMembership scm = new SimpleCircleMembership();
        scm.circleID = cs.getVisitingCircle().circleID;
        
        Response rsp = getCircleMembers.request(scm);
        
        GenericType<List<SimpleCustomer>> gtlc = new GenericType<List<SimpleCustomer>>() {
        };
        
        List<SimpleCustomer> customers = rsp.readEntity(gtlc);
        cs.setCustomersInCircle(customers);
        for(SimpleCustomer c : customers){
            String add = c.firstName + " " + c.lastName;
            circleMember.getItems().add(add);
        }
        
        Response rsp2 = getCirclePosts.request(scm);
        
        GenericType<List<SimplePost>> gtlc2 = new GenericType<List<SimplePost>>() {
        };
        
        List<SimplePost> posts = rsp2.readEntity(gtlc2);
        cs.setCirclePosts(posts);
        cs.setPageID(posts.get(0).pageID);
        for(SimplePost p : posts){
            String author = "";
            for(SimpleCustomer c : customers){
                if(c.CustomerID == p.authorID)
                    author = c.firstName + " " + c.lastName;
            }
            String add = author + p.content + "(" + p.datePosted + ")";
            post.getItems().add(add);
        }
        
    }
    
}
