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
    @NamedQuery(name = "CommentsLiked.findAll", 
            query = "SELECT l FROM CommentsLiked l")
})
@Entity
public class CommentsLiked implements Serializable{
    
    @Id
    private int commentID;
    private int customer_who_likedID;
    


    public CommentsLiked() {
    }
    
    public CommentsLiked(int commentID, int customer_who_likedID){
        this.commentID = commentID;
        this.customer_who_likedID = customer_who_likedID;
        }
     public int getCommentID() {
    return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getCustomer_who_likedID() {
        return customer_who_likedID;
    }

    public void setCustomer_who_likedID(int customer_who_likedID) {
        this.customer_who_likedID = customer_who_likedID;
    }
    
    }
    
    
    
   