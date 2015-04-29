/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.shared;

<<<<<<< HEAD
=======
import javafx.beans.property.SimpleStringProperty;

>>>>>>> parent of 861b97e... Revert 780e2f5..dc40f98
/**
 *
 * @author nathanwong
 */
public class SimpleCircle {
    
    public int circleID;
    public String  circleName;
    public String circleType;
    public int circleOwner;
    
<<<<<<< HEAD
=======
    private final SimpleStringProperty ownerColumnProperty = new SimpleStringProperty("");
    private final SimpleStringProperty nameColumnProperty = new SimpleStringProperty("");
    private final SimpleStringProperty typeColumnProperty = new SimpleStringProperty("");

    public SimpleStringProperty getCircleName() {
        return nameColumnProperty;
    }

    public void setCircleName(String circleName) {
        nameColumnProperty.set(circleName);
    }

    public SimpleStringProperty getCircleType() {
        return typeColumnProperty;
    }

    public void setCircleType(String circleType) {
        typeColumnProperty.set(circleType);
    }

    public SimpleStringProperty getCircleOwner() {
        return ownerColumnProperty;
    }

    public void setCircleOwner(String circleOwner) {
        typeColumnProperty.set(circleOwner);
    }
    
    
>>>>>>> parent of 861b97e... Revert 780e2f5..dc40f98
}
