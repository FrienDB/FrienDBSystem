/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.main;

import friendb.client.main.ScreensController;
import java.awt.Color;
import java.awt.Dimension;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;

/**
 *
 * @author evanguby
 */
public class FrienDBClient extends Application {
    
    public static String LoginPage = "LoginPage.fxml";
    public static String LoginPageID = "LoginPage";
    public static String CirclePage = "CirclePage.fxml";
    public static String CirclePageID = "CirclePage";
    public static String NewCirclePage = "NewCirclePage.fxml";
    public static String NewCirclePageID = "NewCirclePage";
    public static String CustomerWelcomePage = "CustomerWelcomePage.fxml";
    public static String CustomerWelcomePageID = "CustomerWelcomePage";
    public static String RegisterPage = "RegisterPage.fxml";
    public static String RegisterPageID = "RegisterPage";
    public static String YourCirclePage = "YourCirclePage.fxml";
    public static String YourCirclePageID = "YourCirclePage";
    public static String CommentsPage = "CommentsPage.fxml";
    public static String CommentsPageID = "CommentsPage";
    public static String NewCommentPage = "NewCommentPage.fxml";
    public static String NewCommentPageID = "NewCommentPage";
    public static String NewPostPage = "NewPostPage.fxml";
    public static String NewPostPageID = "NewPostPage";
    
    private Stage primaryStage;
    private AnchorPane rootLayout;

    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        ScreensController mainContainer = new ScreensController();
        
        mainContainer.loadScreen(FrienDBClient.LoginPageID, FrienDBClient.LoginPage);
        mainContainer.loadScreen(FrienDBClient.RegisterPageID, FrienDBClient.RegisterPage);
        
        mainContainer.loadScreen(FrienDBClient.CustomerWelcomePageID, FrienDBClient.CustomerWelcomePage);
        mainContainer.loadScreen(FrienDBClient.CirclePageID, FrienDBClient.CirclePage); //this will be moved once we know what circle page we have to load
        mainContainer.loadScreen(FrienDBClient.YourCirclePageID, FrienDBClient.YourCirclePage); //this will too
        mainContainer.loadScreen(FrienDBClient.NewCirclePageID, FrienDBClient.NewCirclePage); 
        mainContainer.loadScreen(FrienDBClient.CommentsPageID, FrienDBClient.CommentsPage); 
        mainContainer.loadScreen(FrienDBClient.NewPostPageID, FrienDBClient.NewPostPage); 
        mainContainer.loadScreen(FrienDBClient.NewCommentPageID, FrienDBClient.NewCommentPage); 
        
        mainContainer.setScreen(FrienDBClient.LoginPageID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
}