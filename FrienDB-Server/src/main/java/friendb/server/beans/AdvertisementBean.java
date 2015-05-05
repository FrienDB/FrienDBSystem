/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.Advertisement;
import friendb.server.entities.Customer;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.persistence.EntityExistsException;
import friendb.server.util.DatabaseConnection;
/**
 *
 * @author evanguby
 */
@Stateful
public class AdvertisementBean {
     //Logger
    private static final Logger logger =
            Logger.getLogger("friendb.beans.CustomerBean");

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;
    
    public void addAdvertisement(int adId,
            int employeeID,
            String adType,
            String postDate,
            String company,
            String item,
            Double price,
            String content,
            int numUnits){
        
         em = DatabaseConnection.getEntityManager();
         Advertisement a = new Advertisement(adId, employeeID, adType, postDate, company, item, content, price, numUnits);
    }
         /*
         try
        {
            //add the school
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            logger.log(Level.INFO, "New customer added to database {0}", c);
        } catch (EntityExistsException eeex)
        {
            //a school with that id already exists in database
            logger.log(Level.WARNING, "Collision on customer ID within database");
            throw eeex;
        } finally
        {
            //close the entity manager
            em.close();
            em = null;
        }
    }
    
    public List<Customer> getAllCustomers(){
        List<Customer> customers = null;

        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Customer> query =
                em.createNamedQuery("Customer.findAll", Customer.class);
        try
        {
            customers = query.getResultList();
            logger.log(Level.INFO, "Retrieving all customers in DB", customers);
        } finally
        {
            //Close the entity manager
            em.close();
            em = null;
        }
        return customers;
    }
                 */
}