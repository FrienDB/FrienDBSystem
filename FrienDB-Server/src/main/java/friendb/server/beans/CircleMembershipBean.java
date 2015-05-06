/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.CircleMembership;
import friendb.server.entities.Customer;
import friendb.server.util.DatabaseConnection;
import friendb.shared.SimpleCircleMembership;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author evanguby
 */
@Stateless
public class CircleMembershipBean {
    
    private static final Logger logger =
            Logger.getLogger("friendb.beans.CircleMembershipBean");

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;
    
    public List<Customer> getCircleMembers(SimpleCircleMembership scm){
        List<Customer> customers = new ArrayList<>();
        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();

        try
        {
            TypedQuery<CircleMembership> query = em.createNamedQuery("CircleMembership.findByCircleID", CircleMembership.class);
            query.setParameter("circleID", scm.circleID);
            List<CircleMembership> cmList = query.getResultList();
            
            for(CircleMembership cm : cmList){
                TypedQuery<Customer> query2 =
                    em.createNamedQuery("Customer.findByID", Customer.class);
                query2.setParameter("id", cm.getCustomerID());
                Customer customer = query2.getSingleResult();
                customers.add(customer);
            }
            logger.log(Level.INFO, "Retrieving all customers in DB", customers);
        } finally
        {
            //Close the entity manager
            em.close();
            em = null;
        }
        return customers;
    }
}
