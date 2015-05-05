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
    @NamedQuery(name = "CircleMembership.findByCustomer", 
            query = "SELECT m FROM CircleMembership m WHERE m.customerID = :customerID"),
    @NamedQuery(name = "CircleMembership.findByCircleID", 
            query = "SELECT m FROM CircleMembership m WHERE m.circleID = :circleID")
    })
@Entity
public class CircleMembership implements Serializable{
    
    @Id
    private int circleID;
    private int customerID;
    


    public CircleMembership() {
    }
    
    public CircleMembership(int customerID, int circleID){
        this.circleID = circleID;
        this.customerID = customerID;
        }
     public int getCircleID() {
    return circleID;
    }

    public void setCircleID(int circleID) {
        this.circleID = circleID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    }
    
    
    
   