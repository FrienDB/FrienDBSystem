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
    @NamedQuery(name = "Pages.findAll", 
            query = "SELECT p FROM Pages p"),
    @NamedQuery(name = "Pages.findByCircleID", 
            query = "SELECT p FROM Pages p WHERE p.associatedCircle = :circleID")
})
@Entity
public class Pages implements Serializable{
    
    @Id
    private int pageID;
    private int postCount;
    private int associatedCircle;
    


    public Pages() {
    }
    
    public Pages(int pageID, int postCount, int associatedCircle){
        this.pageID = pageID;
        this.postCount = postCount;
        this.associatedCircle=associatedCircle;
        }
     public int getpageID() {
    return pageID;
    }

    public void setpageID(int pageID) {
        this.pageID = pageID;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getAssociatedCircle() {
        return associatedCircle;
    }

    public void setAssociatedCircle(int associatedCircle) {
        this.associatedCircle = associatedCircle;
    }    
    }
    
    