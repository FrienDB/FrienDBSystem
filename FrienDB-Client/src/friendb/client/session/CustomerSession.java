/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.client.session;

import friendb.shared.SimpleAccount;
import friendb.shared.SimpleCircle;
import friendb.shared.SimpleComments;
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
    private List<SimpleCustomer> allCustomers;
    private List<SimpleComments> postComments;
    private SimplePost visitingPost;
    private SimpleCustomer postAuthor;

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

    public List<SimpleCustomer> getAllCustomers() {
        return allCustomers;
    }

    public void setAllCustomers(List<SimpleCustomer> allCustomers) {
        this.allCustomers = allCustomers;
    }

    public List<SimpleComments> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<SimpleComments> postComments) {
        this.postComments = postComments;
    }

    public SimplePost getVisitingPost() {
        return visitingPost;
    }

    public void setVisitingPost(SimplePost visitingPost) {
        this.visitingPost = visitingPost;
    }

    public SimpleCustomer getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(SimpleCustomer postAuthor) {
        this.postAuthor = postAuthor;
    }

    public void setCustomer(SimpleCustomer customer) {
        this.customer = customer;
    }
    
    

}
