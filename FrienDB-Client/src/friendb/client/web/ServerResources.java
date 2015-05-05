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
    
    public static final String ADD_NEW_CUSTOMER_URL =
            SERVER_BASE_URL + "customer/add";

    public static final String AUTHENTICATION_URL = 
            SERVER_BASE_URL + "authentication";
    
    public static final String GET_CUSTOMERS_CIRCLES_URL = 
            SERVER_BASE_URL + "circle";
    
    public static final String ADD_CIRCLE_URL = 
            SERVER_BASE_URL + "circle/add";
    
    public static final String GET_CUSTOMERS_ACCOUNTS_URL =
            SERVER_BASE_URL + "circle/add";

     
}
