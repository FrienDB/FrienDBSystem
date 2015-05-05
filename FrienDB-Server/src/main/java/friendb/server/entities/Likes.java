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
    @NamedQuery(name = "Likes.findAll", 
            query = "SELECT l FROM Likes l")
})
@Entity
public class Likes implements Serializable{
    
    @Id
    private int postID;
    private int customer_who_likedID;
    


    public Likes() {
    }
    
    public Likes(int postID, int customer_who_likedID){
        this.postID = postID;
        this.customer_who_likedID = customer_who_likedID;
        }
     public int getpostID() {
    return postID;
    }

    public void setpostID(int postID) {
        this.postID = postID;
    }

    public int getCustomer_who_likedID() {
        return customer_who_likedID;
    }

    public void setCustomer_who_likedID(int customer_who_likedID) {
        this.customer_who_likedID = customer_who_likedID;
    }
    
    }

    
    
    
   