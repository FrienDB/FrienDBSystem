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
    @NamedQuery(name = "Post.findAll", 
            query = "SELECT p FROM Post p")
})
@Entity
public class Post implements Serializable{
    
    @Id
    private int postID;
    private int pageID;
    private String content;
    private int author;
    private String datePosted;
    private int commentCount;


    public Post() {
    }
    
    public Post(int pageID, int postID, String content, int author,
        String datePosted){
        this.pageID = pageID;
        this.postID = postID;
        this.content=content;
        this.author=author;
        this.datePosted=datePosted;
        this.commentCount=0;
    }
     public int getPageID() {
    return pageID;
    }

    public void setPageID(int pageID) {
        this.pageID = pageID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    } 
    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }
    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
    }
    
    
    
   