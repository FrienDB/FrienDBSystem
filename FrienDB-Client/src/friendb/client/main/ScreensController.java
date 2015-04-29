package friendb.client.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import friendb.client.main.FrienDBClient;


/**
 *
 * @author Evan Guby
 */
public class ScreensController extends StackPane
{
    private HashMap<String, Node> screens = new HashMap<>();
    
    public ScreensController()
    {
        super();
    }
    
    public void addScreen(String name,
            Node screen)
    {
        screens.put(name, screen);
    }

    public Node getScreen(String name)
    {
        return screens.get(name);
    }

    public boolean loadScreen(String name, String resource)
    {
        try
        {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/friendb/client/view/" + resource));
            System.out.println("/friendb/client/view/" + resource);
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            myScreenController.populatePage();
            addScreen(name, loadScreen);
            return true;
        } catch (IOException ex)
        {
            System.out.println("Screen not loaded:" + name + ex);
            return false;
        }
    }
    /**
     * This method will handle loading a welcome page unique to the customer
     */
    public void loadStudentPages() {
        
    }
    
    public boolean setScreen(final String name)
    {
        if (screens.get(name) != null)
        {
            final DoubleProperty opacity = opacityProperty();
            if (!getChildren().isEmpty())
            {

                getChildren().remove(0);
                getChildren().add(0, screens.get(name));

            } else
            {

                getChildren().add(screens.get(name));

            }
            return true;
        } else
        {
            System.out.println("Screen has not loaded!");
            return false;
        }
    }
    
    public boolean unloadScreen(String name)
    {
        if (screens.remove(name) == null)
        {
            System.out.println("Screen doesn't exist");
            return false;

        } else
        {
            return true;
        }
    }

    
}
