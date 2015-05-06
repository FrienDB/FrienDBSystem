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
import friendb.shared.SimpleCircle;
import friendb.shared.SimpleCircleMembership;
import friendb.shared.SimpleCustomer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
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

    public void addCircle(SimpleCircle sc) {
        em = DatabaseConnection.getEntityManager();
        try
        {
            Circle circle = new Circle(sc.circleName,sc.circleType,sc.circleOwner);
            //add the course
            em.getTransaction().begin();
            //@TODO check return of addCourse to see if it worked
            em.persist(circle);
            em.getTransaction().commit();
            TypedQuery<Circle> query = em.createNamedQuery("Circle.findByCustomerID", Circle.class);
            query.setParameter("customerID", sc.circleOwner);
            
            List<Circle> cmList = query.getResultList();
            
            Circle c = cmList.get(cmList.size()-1);
            CircleMembership cm = new CircleMembership(c.getCircleOwner(),c.getCircleID());
            em.getTransaction().begin();
            //@TODO check return of addCourse to see if it worked
            em.persist(cm);
            em.getTransaction().commit();
            logger.log(Level.INFO, "New circlemembership added to database {0}", circle);
        } catch (RollbackException rex)
        {
            //a course with that id already exists in database
            logger.log(Level.WARNING, "Collision on circle ID within database");
            throw rex;
        } finally
        {
            //close the entity manager
            em.close();
            em = null;
        }
    }

    public void deleteCircle(SimpleCircle sc) {
        em = DatabaseConnection.getEntityManager();
        try
        {
            TypedQuery<Circle> query = em.createNamedQuery("Circle.findByID", Circle.class);
            query.setParameter("circleID", sc.circleID);
            TypedQuery<CircleMembership> query2 = em.createNamedQuery("CircleMembership.findByCircleID", CircleMembership.class);
            query2.setParameter("circleID", sc.circleID);
            Circle circle = query.getSingleResult();
            List<CircleMembership> cms = query2.getResultList();
            em.getTransaction().begin();
            for(CircleMembership cm : cms){
                em.remove(cm);
            }
            em.remove(circle);
            em.getTransaction().commit();
        }finally
        {
            //close the entity manager
            em.close();
            em = null;
        }
    }
    
    
}
