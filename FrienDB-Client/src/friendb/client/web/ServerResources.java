package friendb.client.web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Evan Guby
 */
public final class ServerResources {
    
     private static final String SERVER_BASE_URL =
            "http://localhost:8080/FrienDB-Server/webresources/";
     
    public static final String GET_ALL_CUSTOMERS_URL =
            SERVER_BASE_URL + "customer";
    public static final String GET_ALL_EMPLOYEE_URL =
            SERVER_BASE_URL + "employee";
    
    public static final String ADD_NEW_CUSTOMER_URL =
            SERVER_BASE_URL + "customer/add";
    public static final String GET_ALL_ADVERTISEMENT_URL =
            SERVER_BASE_URL + "advertisement";
    
    public static final String ADD_NEW_ADVERTISEMENT_URL =
            SERVER_BASE_URL + "advertisement/add";

    public static final String AUTHENTICATION_URL = 
            SERVER_BASE_URL + "authentication";
    
    public static final String GET_CUSTOMERS_CIRCLES_URL = 
            SERVER_BASE_URL + "circle";
    public static final String GET_EMPLOYEE_SALES_URL = 
            SERVER_BASE_URL + "sales";
    public static final String GET_SINGLE_EMP_SALES_URL = 
            SERVER_BASE_URL + "sales/employeeSales" ;   
    public static final String ADD_CIRCLE_URL = 
            SERVER_BASE_URL + "circle/add";
    
    public static final String GET_CUSTOMERS_ACCOUNTS_URL =
            SERVER_BASE_URL + "accounts/getCustomerAccounts";

    
    public static final String GET_CIRCLE_MEMBERS_URL =
            SERVER_BASE_URL + "circleMembership/getCircleMembers";

    public static final String GET_CIRCLE_POSTS_URL =
            SERVER_BASE_URL + "post/getCirclePosts";
    public static final String GET_CUSTOMER_SALES_URL = 
            SERVER_BASE_URL + "sales/findCustomer";
    
    public static final String ADD_POST_URL = 
            SERVER_BASE_URL + "post/addPost";
    public static final String REMOVE_POST_URL = 
            SERVER_BASE_URL + "post/removePost";
    
    public static final String DELETE_CIRCLE_URL = 
            SERVER_BASE_URL + "circle/deleteCircle";
    
    public static final String ADD_CUSTOMER_TO_CIRCLE_URL = 
            SERVER_BASE_URL + "circleMembership/addCustomerToCircle";
    
    public static final String REMOVE_CUSTOMER_FROM_CIRCLE_URL = 
            SERVER_BASE_URL + "circleMembership/removeCustomerFromCircle";
    
    public static final String GET_POST_COMMENTS_URL = 
            SERVER_BASE_URL + "comment/getPostComments";
    

    public static final String GET_POST_LIKES = 
            SERVER_BASE_URL + "post/getLikes";

    public static final String ADD_COMMENT_URL = 
            SERVER_BASE_URL + "comment/addComment";
    
    
}
