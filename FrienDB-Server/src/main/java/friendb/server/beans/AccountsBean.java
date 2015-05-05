/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.Account;
import friendb.server.entities.CircleMembership;
import friendb.server.entities.Customer;
import friendb.server.util.DatabaseConnection;
import friendb.shared.SimpleAccount;
import friendb.shared.SimpleCustomer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nathanwong
 */
public class AccountsBean {
    
    private static final Logger logger =
            Logger.getLogger("friendb.beans.AccountsBean");
    
    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;
    
    public List<Account> getCustomersAccounts(SimpleCustomer sc){
        List<Account> accounts = null;
        
         // Create the entity manager and set up the query
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Account> query =
                em.createNamedQuery("Account.findByCustomerID", Account.class);
        
        try
        {
            query.setParameter("customerID", sc.CustomerID);
            accounts = query.getResultList();
            
            logger.log(Level.INFO, "Retrieving all accounts for specified customer", accounts);
        } finally
        {
            //Close the entity manager
            em.close();
            em = null;
        }
        return accounts;
        
        
    }
    
}
