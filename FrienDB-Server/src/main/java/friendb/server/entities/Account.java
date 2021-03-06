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
    @NamedQuery(name = "Account.findAll", 
            query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByCustomerID", 
            query = "SELECT a FROM Account a WHERE a.customerID = :customerID")
})
@Entity
public class Account implements Serializable{
    
    @Id
    private int customerID;
    private int  accountNum;
    private String creationDate;
    private String creditCard;
    


    public Account() {
    }
    
    public Account(int customerID, int accountNum, String creationDate,
            String creditCard){
        this.customerID = customerID;
        this.accountNum = accountNum;
        this.creationDate = creationDate;
        this.creditCard = creditCard;
        }
     public int getCustomerID() {
    return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }
     public String getCreationDate() {
    return creationDate;
    }

    public void setCreationDate(String CreationDate) {
        this.creationDate = creationDate;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String CreditCard) {
        this.creditCard = creditCard;
    }
    
    
    }
    
    
    
   