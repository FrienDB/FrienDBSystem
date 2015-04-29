/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.Circle;
import friendb.server.entities.CircleMembership;
import friendb.server.entities.Customer;
import friendb.server.rest.CircleResource;
import friendb.server.util.DatabaseConnection;
import friendb.shared.SimpleCustomer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author evanguby
 */
@Stateless
public class CircleBean {

        //Logger
    private static final Logger logger =
            Logger.getLogger(CircleResource.class.getName());

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;
    
    public ArrayList<Circle> getCustomersCircles(SimpleCustomer c) {
        //Create the entity manager
        em = DatabaseConnection.getEntityManager();
        logger.log(Level.INFO, "User attempts log in with name {0}", c);
        ArrayList<Circle> circles = new ArrayList<Circle>();
        try
        {
            TypedQuery<CircleMembership> query = em.createNamedQuery("CircleMembership.findByCustomer", CircleMembership.class);
            query.setParameter("customerID", c.CustomerID);
       
            List<CircleMembership> cmList = query.getResultList();
            for(CircleMembership cm : cmList){
                TypedQuery<Circle> query2 = em.createNamedQuery("Circle.findByID", Circle.class);
                query2.setParameter("circleID", cm.getCircleID());
                Circle circle = query2.getSingleResult();
                circles.add(circle);
            }
        } catch (NoResultException nrex)
        {
            //no matching account
            logger.log(Level.WARNING, "No such account with email {0} found in database", c);
            throw nrex;
        } finally
        {
            //close the entity manager
            em.close();
            em = null;
        }
        return circles;
    }
    
}
