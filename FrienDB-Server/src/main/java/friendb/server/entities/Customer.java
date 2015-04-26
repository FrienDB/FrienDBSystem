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
    @NamedQuery(name = "Customer.findAll", 
            query = "SELECT c FROM Customer c")
})
@Entity
public class Customer implements Serializable{
    
    private String firstName;
    private String lastName;
    private char sex;
    private String emailID;
    private String dob;
    private String address;
    private String city;
    private String custState;
    private int zipCode;
    private String telephone;
    private int rating;
    private String password;
    @Id
    private int id;

    public Customer() {
    }
    
    public Customer(String firstName,
            String lastName,
            char sex,
            String emailID,
            String dob,
            String address,
            String city,
            String state,
            int zipCode,
            String telephone,
            String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.emailID = emailID;
        this.dob = dob;
        this.address = address;
        this.city = city;
        this.custState = state;
        this.zipCode = zipCode;
        this.telephone = telephone;
        this.password = password;
        this.rating = 5;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustState() {
        return custState;
    }

    public void setCustState(String state) {
        this.custState = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}