/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.session;

import friendb.shared.SimpleAccount;
import friendb.shared.SimpleCircle;
import friendb.shared.SimpleCustomer;
import friendb.shared.SimplePost;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author evanguby
 */
public class CustomerSession implements Session{
    private SimpleCustomer customer;
    private ArrayList<SimpleCircle> circles;
    private SimpleCircle visitingCircle;
    private List<SimpleCustomer> customersInCircle;
    private List<SimpleAccount> accounts;
    private List<SimplePost> circlePosts;
    private int pageID;

    public CustomerSession(SimpleCustomer acct)
    {
        customer = acct;
    }
    
    public SimpleCustomer getCustomerAccount()
    {
        return customer;
    }
    
    public ArrayList<SimpleCircle> getCircles() {
        return circles;
    }

    public void setCircles(ArrayList<SimpleCircle> scA) {
        circles = scA;
    }

    public SimpleCircle getVisitingCircle() {
        return visitingCircle;
    }

    public void setVisitingCircle(SimpleCircle visitingCircle) {
        this.visitingCircle = visitingCircle;
    }

    public List<SimpleCustomer> getCustomersInCircle() {
        return customersInCircle;
    }

    public void setCustomersInCircle(List<SimpleCustomer> customersInCircle) {
        this.customersInCircle = customersInCircle;
    }

    public List<SimpleAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<SimpleAccount> accounts) {
        this.accounts = accounts;
    }

    public List<SimplePost> getCirclePosts() {
        return circlePosts;
    }

    public void setCirclePosts(List<SimplePost> circlePosts) {
        this.circlePosts = circlePosts;
    }

    public int getPageID() {
        return pageID;
    }

    public void setPageID(int pageID) {
        this.pageID = pageID;
    }
    
    

}
