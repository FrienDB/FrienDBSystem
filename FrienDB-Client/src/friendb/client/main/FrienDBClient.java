/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.main;

import friendb.client.main.ScreensController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    public static String NewCommentPage = "NewCommentPage.fxml";
    public static String NewCommentPageID = "NewCommentPage";
    public static String CommentsPage = "CommentsPage.fxml";
    public static String CommentsPageID = "CommentsPage";
    public static String NewPostPage = "NewPostPage.fxml";
    public static String NewPostPageID = "NewPostPage";
    public static String EmployeePageID = "EmployeeLogin";
    public static String EmployeePage = "EmployeeLogin.fxml";
    public static String AdsPageID = "AdsScreen";
    public static String AdsPage = "AdsScreen.fxml";
    public static String SalesPageID = "SalesScreen";
    public static String SalesPage = "SalesScreen.fxml";
    public static String ManageCustomerInfoID = "ManageCustomerInfo";
    public static String ManageCustomerInfo = "ManageCustomerInfo.fxml";
    public static String NewAdvertisementID = "NewAdvertisement";
    public static String NewAdvertisement = "NewAdvertisement.fxml";
    public static String AccountPageID = "AccountPage";
    public static String AccountPage = "AccountPage.fxml";
    public static String MessagesPageID = "MessagesPage";
    public static String MessagesPage = "MessagesPage.fxml";
    public static String ManagerSalesReportPageID = "SalesReportPage";
    public static String ManagerSalesReportPage = "SalesReportPage.fxml";
    public static String ManagerAdsPageID = "ManagerAdsPage";
    public static String ManagerAdsPage = "ManagerAdsPage.fxml";
    public static String ManagerEditEmployeePageID = "EditEmployeePage";
    public static String ManagerEditEmployeePage = "EditEmployeePage.fxml";
    public static String ManagerSalesPageID = "ManagerSales";
    public static String ManagerSalesPage = "ManagerSales.fxml";
    public static String MailingListPage = "MailingList.fxml";
    public static String MailingListPageID = "MailingList";
    public static String ManagerPage = "ManagerPage.fxml";
    public static String ManagerPageID = "ManagerPage";
     public static String MakePurchasePage = "MakePurchasePage.fxml";
    public static String MakePurchasePageID = "MakePurchasePage";
    public static String EmployeeEditPage = "EmployeeEdit.fxml";
    public static String EmployeeEditPageID = "EmployeeEdit";
    
    
    
    
    private Stage primaryStage;
    private AnchorPane rootLayout;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        ScreensController mainContainer = new ScreensController();
        
        mainContainer.loadScreen(FrienDBClient.LoginPageID, FrienDBClient.LoginPage);
        mainContainer.loadScreen(FrienDBClient.RegisterPageID, FrienDBClient.RegisterPage);
        
        
        
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
