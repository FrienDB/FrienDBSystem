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
    @NamedQuery(name = "Preferences.findAll", 
            query = "SELECT p FROM Post p")
})
@Entity
public class Preferences implements Serializable{
    
    @Id
    private int customerID;
    private String preference;
    


    public Preferences() {
    }
    
    public Preferences(int customerID, String preference){
        this.customerID = customerID;
        this.preference = preference;
    }
     public int getCustomerID() {
    return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }
   
    }
    
    
    
   