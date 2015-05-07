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
import friendb.shared.SimpleAdvertisement;
import friendb.shared.SimpleEmployee;
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
    
    public void addAdvertisement(SimpleAdvertisement ad){
        
         em = DatabaseConnection.getEntityManager();
         Advertisement a = new Advertisement(ad.adID, ad.employeeID, ad.adType, ad.postDate, ad.company, ad.item, ad.content, ad.price, ad.numUnits);
    
         
         try
        {
            //add the school
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            logger.log(Level.INFO, "New Advertisement added to database {0}", a);
        } catch (EntityExistsException eeex)
        {
            //a school with that id already exists in database
            logger.log(Level.WARNING, "Collision on Advertisement ID within database");
            throw eeex;
        } finally
        {
            //close the entity manager
            em.close();
            em = null;
        }
    }
    
    public List<Advertisement> getAllAdvertisements(){
        List<Advertisement> advertisements = null;

        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Advertisement> query =
                em.createNamedQuery("Advertisement.findAll", Advertisement.class);
        try
        {
            advertisements = query.getResultList();
            logger.log(Level.INFO, "Retrieving all advertisement in DB", advertisements);
        } finally
        {
            //Close the entity manager
            em.close();
            em = null;
        }
        return advertisements;
    }
    public List<Advertisement> getAdvertisementByEmployee(int employeeId){
        List<Advertisement> advertisements = null;

        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Advertisement> query =
                em.createNamedQuery("Advertisement.findByEmployeeID", Advertisement.class);
        query.setParameter("employeeID", employeeId);
        try
        {
            advertisements = query.getResultList();
            logger.log(Level.INFO, "Retrieving all advertisement in DB", advertisements);
        } finally
        {
            //Close the entity manager
            em.close();
            em = null;
        }
        return advertisements;
    
    }
    public List<Advertisement> getEmployeeAds(SimpleEmployee employee){
        
                    
      List<Advertisement> empAds = null;
        try
        {
            
                em = DatabaseConnection.getEntityManager();
                TypedQuery<Advertisement> query =
                em.createNamedQuery("Advertisement.findByEmployeeID", Advertisement.class);
                    query.setParameter("empId", employee.employeeID);
            empAds  = query.getResultList();
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
        return empAds;
    }
                 
}
