/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author evanguby
 */
@NamedQueries({
    @NamedQuery(name = "Circle.findAll", 
            query = "SELECT c FROM Circle c")
})
@Entity
public class Circle implements Serializable{
    
    @Id
    private int circleID;
    private String  circleName;
    private String circleType;
    private int circleOwner;
    


    public Circle() {
    }
    
    public Circle(int circleID, String circleName, String circleType,
            int circleOwner){
        this.circleID = circleID;
        this.circleName = circleName;
        this.circleType = circleType;
        this.circleOwner = circleOwner;
        }
     public int getCircleID() {
    return circleID;
    }

    public void setCircleID(int circleID) {
        this.circleID = circleID;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }
     public String getCircleType() {
    return circleType;
    }

    public void setCircleType(String circleType) {
        this.circleType = circleType;
    }

    public int getCircleOwner() {
        return circleOwner;
    }

    public void setCircleOwner(int circleOwner) {
        this.circleOwner = circleOwner;
    }
    
    
    }
    
    
    
   