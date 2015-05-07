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
import friendb.shared.SimpleMessages;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * FXML Controller class
 *
 * @author nathanwong
 */
public class MessagesPageController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private Label Messages;
    @FXML
    private ListView<String> messages;

    private final ServerAccessPoint getMessages
            = new ServerAccessPoint(ServerResources.GET_MESSAGES_URL);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleBack(ActionEvent event) {
        myController.setScreen(FrienDBClient.CustomerWelcomePageID);
    }

    @FXML
    private void handleReply(ActionEvent event) {
    }

    @FXML
    private void handleCompose(ActionEvent event) {
    }

    @FXML
    private void handleDelete(ActionEvent event) {
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void populatePage() {
        CustomerSession cs = (CustomerSession) myController.getSession();

        Response rsp = getMessages.request((cs.getCustomerAccount().CustomerID));

        GenericType<List<SimpleMessages>> gtlm = new GenericType<List<SimpleMessages>>() {
        };
        List<SimpleMessages> simpleMessagesList = null;
        try {
            simpleMessagesList = rsp.readEntity(gtlm);
        } catch (Exception e) {
            return;
        }

        for (SimpleMessages simpleMessages : simpleMessagesList) {
            String listString = "From: " + simpleMessages.sender + " | To: " + simpleMessages.receiver + " | " + simpleMessages.dateSent + " | " + simpleMessages.content;
            messages.getItems().add(listString);
        }

    }

}
