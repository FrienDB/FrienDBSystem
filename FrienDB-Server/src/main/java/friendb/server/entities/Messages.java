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
    @NamedQuery(name = "Messages.findAll", 
            query = "SELECT m FROM Messages m")
})
@Entity
public class Messages implements Serializable{
    
    @Id
    private int messageID;
    private String subj;
    private String content;
    private String dateSent;
    private String sender;
    private String receiver;
    


    public Messages() {
    }
    
    public Messages(int messageID, String subj, String content,
            String dateSent, String sender,
            String receiver){
        this.messageID = messageID;
        this.subj = subj;
        this.content = content;
        this.dateSent = dateSent;
        this.sender = sender;
        this.receiver = receiver;
        }
     public int getMessageID() {
    return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }    
    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }    
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }    
    }

    
