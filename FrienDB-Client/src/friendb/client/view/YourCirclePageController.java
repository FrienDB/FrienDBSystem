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
import friendb.shared.SimpleComments;
import friendb.shared.SimpleCustomer;
import friendb.shared.SimplePost;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    @FXML
    private ComboBox<String> customerToAdd;

    private final ServerAccessPoint getCircleMembers
            = new ServerAccessPoint(ServerResources.GET_CIRCLE_MEMBERS_URL);

    private final ServerAccessPoint getCirclePosts
            = new ServerAccessPoint(ServerResources.GET_CIRCLE_POSTS_URL);

    private final ServerAccessPoint getAllCustomers
            = new ServerAccessPoint(ServerResources.GET_ALL_CUSTOMERS_URL);

    private final ServerAccessPoint deleteCircle
            = new ServerAccessPoint(ServerResources.DELETE_CIRCLE_URL);

    private final ServerAccessPoint addCustomerToCircle
            = new ServerAccessPoint(ServerResources.ADD_CUSTOMER_TO_CIRCLE_URL);

   
    private final ServerAccessPoint removeCustomerFromCircle
            = new ServerAccessPoint(ServerResources.REMOVE_CUSTOMER_FROM_CIRCLE_URL);

    private final ServerAccessPoint removePost 
            = new ServerAccessPoint(ServerResources.REMOVE_POST_URL);
    

    private final ServerAccessPoint getPostComments 
            = new ServerAccessPoint(ServerResources.GET_POST_COMMENTS_URL);

    private final ServerAccessPoint getPostLikes
            = new ServerAccessPoint(ServerResources.GET_POST_LIKES);



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleRemoveSelected(ActionEvent event) {
        int index = circleMember.getSelectionModel().getSelectedIndex();
        CustomerSession cs = (CustomerSession)myController.getSession();
        SimpleCustomer sc = cs.getCustomersInCircle().get(index);
        SimpleCircleMembership scm = new SimpleCircleMembership();
        scm.circleID = cs.getVisitingCircle().circleID;
        scm.customerID = sc.CustomerID;
        if(cs.getVisitingCircle().circleOwner != scm.customerID){
            Response rsp = removeCustomerFromCircle.request(scm);
        }
        myController.loadScreen(FrienDBClient.YourCirclePageID, FrienDBClient.YourCirclePage);
        myController.setScreen(FrienDBClient.YourCirclePageID);
    }

    @FXML
    private void handleLikePost(ActionEvent event) {
    }

    @FXML
    private void handleRenameCircle(ActionEvent event) {
    }

    @FXML
    private void handleDeleteCircle(ActionEvent event) {
        CustomerSession cs = (CustomerSession) myController.getSession();
        SimpleCircle toDelete = cs.getVisitingCircle();

        Response rsp = deleteCircle.request(toDelete);

        myController.loadScreen(FrienDBClient.CustomerWelcomePageID, FrienDBClient.CustomerWelcomePage);
        myController.setScreen(FrienDBClient.CustomerWelcomePageID);
    }

    @FXML
    private void handleAddMember(ActionEvent event) {
        CustomerSession cs = (CustomerSession) myController.getSession();
        String toAdd = customerToAdd.getValue();
        String[] name = toAdd.split(" ");
        List<SimpleCustomer> allCustomers = cs.getAllCustomers();
        SimpleCustomer customerToAdd = null;

        for(SimpleCustomer c : allCustomers){
            if(c.firstName.equals(name[0]) && c.lastName.equals(name[1])){

                customerToAdd = c;
            }
        }

        SimpleCircleMembership scm = new SimpleCircleMembership();
        scm.circleID = cs.getVisitingCircle().circleID;
        scm.customerID = customerToAdd.CustomerID;

        Response rsp = addCustomerToCircle.request(scm);

        myController.loadScreen(FrienDBClient.YourCirclePageID, FrienDBClient.YourCirclePage);
        myController.setScreen(FrienDBClient.YourCirclePageID);
    }

    @FXML
    private void handleRemovePost(ActionEvent event) {
        CustomerSession cs = (CustomerSession) myController.getSession();

        // Get the index in reference to the post ListView of the post to delete
        int postToDelete = post.getSelectionModel().getSelectedIndex();
        int actualIndex = -1;

        // Need to search for the proper post index in cs.GetCirclePosts();
        for (int i = 0; i < cs.getCirclePosts().size(); i++) {
            if (post.getItems().get(postToDelete).contains(cs.getCirclePosts().get(i).content)) {
                actualIndex = i;
                break;
            }
        }
        // If the post is not found, then there is an error
        if (actualIndex == -1) {

        } // else, proceed to delete post
        else {
            SimplePost simplePost = new SimplePost();
            simplePost.authorID = cs.getCirclePosts().get(actualIndex).authorID;
            simplePost.circleID = cs.getCirclePosts().get(actualIndex).circleID;
            simplePost.commentCount = cs.getCirclePosts().get(actualIndex).commentCount;
            simplePost.datePosted = cs.getCirclePosts().get(actualIndex).datePosted;
            simplePost.content = cs.getCirclePosts().get(actualIndex).content;
            simplePost.pageID = cs.getCirclePosts().get(actualIndex).pageID;

            Response rsp = removePost.request(simplePost);
            post.getItems().remove(postToDelete);
        }
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
        int index = post.getSelectionModel().getSelectedIndex();
        CustomerSession cs = (CustomerSession)myController.getSession();
        SimplePost post = cs.getCirclePosts().get(index);
        Response rsp = getPostComments.request(post);
        
        GenericType<List<SimpleComments>> gtlc = new GenericType<List<SimpleComments>>() {
        };

        List<SimpleComments> comments = rsp.readEntity(gtlc);
        
        cs.setPostComments(comments);
        cs.setVisitingPost(post);
        List<SimpleCustomer> scList = cs.getAllCustomers();
        for(SimpleCustomer cust : scList){
            if(cust.CustomerID == post.authorID)
                cs.setPostAuthor(cust);
        }
        
        myController.loadScreen(FrienDBClient.CommentsPageID, FrienDBClient.CommentsPage);
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
        CustomerSession cs = (CustomerSession) myController.getSession();
        circleName.setText(cs.getVisitingCircle().circleName);
        SimpleCircleMembership scm = new SimpleCircleMembership();
        scm.circleID = cs.getVisitingCircle().circleID;

        Response rsp = getCircleMembers.request(scm);

        GenericType<List<SimpleCustomer>> gtlc = new GenericType<List<SimpleCustomer>>() {
        };

        List<SimpleCustomer> customers = rsp.readEntity(gtlc);
        cs.setCustomersInCircle(customers);
        for (SimpleCustomer c : customers) {
            String add = c.firstName + " " + c.lastName;
            circleMember.getItems().add(add);
        }

        Response rsp2 = getCirclePosts.request(scm);

        GenericType<List<SimplePost>> gtlc2 = new GenericType<List<SimplePost>>() {
        };
        List<SimplePost> posts = null;
        try {
            posts = rsp2.readEntity(gtlc2);
        } catch (Exception e) {
            return;
        }
        Response rsp3 = getAllCustomers.request();

        GenericType<List<SimpleCustomer>> gtlc3 = new GenericType<List<SimpleCustomer>>() {
        };
        boolean addToList = true;
        List<SimpleCustomer> customersTo = rsp3.readEntity(gtlc3);
        ArrayList<SimpleCustomer> customersNotInCircle = new ArrayList<>();
        for (SimpleCustomer c : customersTo) {
            for (SimpleCustomer cus : customers) {

                if (c.CustomerID == cus.CustomerID) {
                    addToList = false;
                }

            }
            if (addToList) {
                customerToAdd.getItems().add(c.firstName + " " + c.lastName);
                customersNotInCircle.add(c);
            }
            addToList = true;
        }
        cs.setAllCustomers(customersTo);
        customers = customersTo;
        cs.setCirclePosts(posts);
        cs.setPageID(posts.get(0).pageID);
        for (SimplePost p : posts) {
            String author = "";
            for (SimpleCustomer c : customers) {
                if (c.CustomerID == p.authorID) {
                    author = c.firstName + " " + c.lastName;
                    break;
                }
            }
            
            /*Response rsp4 = getPostLikes.request();
            GenericType<List<Integer>> gtl4 = new GenericType<List<Integer>>() {};
            List<Integer> theLike = rsp4.readEntity(gtl4);*/
            String add = "(" + p.datePosted + ") " + author + ": " + p.content + " Likes: "/* + theLike.get(0)*/;
            post.getItems().add(add);
        }

    }

}
