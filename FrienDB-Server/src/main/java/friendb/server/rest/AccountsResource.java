/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.rest;

import friendb.server.beans.AccountsBean;
import friendb.server.beans.AuthenticationBean;
import friendb.server.entities.Account;
import friendb.server.entities.Customer;
import friendb.shared.SimpleAccount;
import friendb.shared.SimpleCustomer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author nathanwong
 */
@Path("accounts")
@RequestScoped
public class AccountsResource {
    
    //Logger
    private static final Logger logger = Logger.getLogger(AccountsResource.class.getName());

    @Context
    private UriInfo context;

    @Inject
    private AccountsBean accountsBean;
    
    @POST
    @Path("/getCustomerAccounts")
    @Consumes("application/json")
    public Response getCustomersAccounts(SimpleCustomer sc){
        List<Account> accounts = accountsBean.getCustomersAccounts(sc);
        ArrayList<SimpleAccount> simpleAccounts = new ArrayList<>();
        SimpleAccount accountTemp;
        for (Account account : accounts)
        {
            accountTemp = new SimpleAccount();
            accountTemp.accountNum = account.getAccountNum();
            accountTemp.creationDate = account.getCreationDate();
            accountTemp.creditCard = account.getCreditCard();
            accountTemp.customerID = account.getCustomerID();
            simpleAccounts.add(accountTemp);
        }
        GenericEntity<List<SimpleAccount>> wrapper =
                new GenericEntity<List<SimpleAccount>>(simpleAccounts)
                {
                };
        return Response.ok(wrapper).build();
    }
    
}
