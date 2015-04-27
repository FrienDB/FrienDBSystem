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
    @NamedQuery(name = "Sales.findAll", 
            query = "SELECT s FROM Sales s")
})
@Entity
public class Sales implements Serializable{
    
    @Id
    private int transID;
    private String dateSold;
    private int adID;
    private int numUnits;
    private int accountNum;
    


    public Sales() {
    }
    
    public Sales(int transID, String dateSold,
            int adID, int numUnits, int accountNum){
        this.transID = transID;
        this.dateSold = dateSold;
        this.adID = adID;
        this.numUnits = numUnits;
        this.accountNum = accountNum;
    }
     public int getTransID() {
    return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public String getDateSold() {
        return dateSold;
    }

    public void setDateSold(String dateSold) {
        this.dateSold = dateSold;
    }
     public int getAdID() {
    return adID;
    }

    public void setAdID(int adID) {
        this.adID = adID;
    }
     public int getNumUnits() {
    return numUnits;
    }

    public void setNumUnits(int numUnits) {
        this.numUnits = numUnits;
    }
    public int getAccountNum() {
    return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }
    }
    
    
    
   