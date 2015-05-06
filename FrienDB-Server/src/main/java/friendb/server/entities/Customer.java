/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.entities;

import java.io.Serializable;
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
            query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByEmail", 
            query = "SELECT c FROM Customer c WHERE c.emailID = :emailID"),
    @NamedQuery(name = "Customer.findByID", 
            query = "SELECT c FROM Customer c WHERE c.CustomerID = :id")
})
@Entity
public class Customer implements Serializable{
    
    @Id
    private int CustomerID;
    private String firstName;
    private String lastName;
    private String sex;
    private String emailID;
    private String dob;
    private String address;
    private String city;
    private String custState;
    private int zipCode;
    private String telephone;
    private int ratingOf10;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRatingOf10() {
        return ratingOf10;
    }

    public void setRatingOf10(int ratingOf10) {
        this.ratingOf10 = ratingOf10;
    }

    public Customer() {
    }
    
    public Customer(String firstName,
            String lastName,
            String sex,
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
        this.ratingOf10 = 5;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int id) {
        this.CustomerID = id;
    }
}
