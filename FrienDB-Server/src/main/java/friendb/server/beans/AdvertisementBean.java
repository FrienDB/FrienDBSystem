/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.Advertisement;
import friendb.server.entities.Customer;
import friendb.server.entities.Sales;
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
import java.util.ArrayList;
import javax.persistence.Query;

/**
 *
 * @author evanguby
 */
@Stateful
public class AdvertisementBean {

    //Logger
    private static final Logger logger
            = Logger.getLogger("friendb.beans.CustomerBean");

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
            int numUnits) {

        em = DatabaseConnection.getEntityManager();
        Advertisement a = new Advertisement(adId, employeeID, adType, postDate, company, item, content, price, numUnits);

        try {
            //add the school
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            logger.log(Level.INFO, "New Advertisement added to database {0}", a);
        } catch (EntityExistsException eeex) {
            //a school with that id already exists in database
            logger.log(Level.WARNING, "Collision on Advertisement ID within database");
            throw eeex;
        } finally {
            //close the entity manager
            em.close();
            em = null;
        }
    }

    public List<Advertisement> getAllAdvertisements() {
        List<Advertisement> advertisements = null;

        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Advertisement> query
                = em.createNamedQuery("Advertisement.findAll", Advertisement.class);
        try {
            advertisements = query.getResultList();
            logger.log(Level.INFO, "Retrieving all advertisement in DB", advertisements);
        } finally {
            //Close the entity manager
            em.close();
            em = null;
        }
        return advertisements;
    }

    public List<Advertisement> getAdvertisementByEmployee(int employeeId) {
        List<Advertisement> advertisements = null;

        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Advertisement> query
                = em.createNamedQuery("Advertisement.findByEmployeeID", Advertisement.class);
        query.setParameter("employeeID", employeeId);
        try {
            advertisements = query.getResultList();
            logger.log(Level.INFO, "Retrieving all advertisement in DB", advertisements);
        } finally {
            //Close the entity manager
            em.close();
            em = null;
        }
        return advertisements;

    }

    public List<Advertisement> getTopSellingList() {
        List<Advertisement> advertisements = new ArrayList<>();
        TypedQuery<Advertisement> query
                = em.createNamedQuery("Advertisement.findAll", Advertisement.class);
        TypedQuery<Sales> query2
                = em.createNamedQuery("Sales.findAll", Sales.class);
        //Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
//        Query q = em.createQuery("SELECT c from Sales s inner join on Advertisement a where s.adID = a.adID GROUP BY Item ORDER BY s.adID DESC");

        try {

            List<Advertisement> a = query.getResultList();
            List<Sales> s = query2.getResultList();
            int[] numSold = new int[a.size()];
            int counter = 0;
            for (Advertisement ad : a) {
                int numSoldtemp = 0;
                for (Sales sale : s) {

                    if (ad.getAdID() == sale.getAdID()) {
                        numSoldtemp += sale.getNumUnits();
                    }
                    numSold[counter] = numSoldtemp;
                }
                counter++;
            }
            Advertisement[] temp = new Advertisement[a.size()];
            for (int i = 0; i < a.size(); i++) {
                counter = 0;
                for (int j = 0; j < a.size(); j++) {

                    if (numSold[i] < numSold[j] && i != j) {
                        counter++;
                    }
                }
                temp[counter] = a.get(i);
            }
            for (int i = 0; i < temp.length; i++) {
                advertisements.add(temp[i]);
            }
            logger.log(Level.INFO, "Retrieving all advertisement in DB", advertisements);
        } finally {
            //Close the entity manager
            em.close();
            em = null;
        }
        return advertisements;
    }

}
