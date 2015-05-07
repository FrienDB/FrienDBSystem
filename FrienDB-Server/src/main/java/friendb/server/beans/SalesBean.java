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
import friendb.shared.SimpleCustomer;
import friendb.shared.SimpleEmployee;
import friendb.shared.SimpleSales;
import javax.inject.Inject;

/**
 *
 * @author evanguby
 */
@Stateful
public class SalesBean {

    //Logger

    private static final Logger logger
            = Logger.getLogger("friendb.beans.SalesBean");

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;
    
    public void addSales(
            String dateSold,
            int adId,
            int numUnits,
            int accountNum
            ) 
        {

        em = DatabaseConnection.getEntityManager();
        Sales s = new Sales(dateSold, adId, numUnits, accountNum);
        TypedQuery<Advertisement> query
                = em.createNamedQuery("Advertisement.findAll", Advertisement.class);
        
        try {
            //add the school
            Advertisement ad = query.getSingleResult();
            em.getTransaction().begin();
            em.persist(s);
            ad.setNumOfUnits(ad.getNumOfUnits() - numUnits);
            em.getTransaction().commit();
            logger.log(Level.INFO, "New Sale added to database {0}", s);
        } catch (EntityExistsException eeex) {
            //a school with that id already exists in database
            logger.log(Level.WARNING, "Collision on Sale ID within database");
            throw eeex;
        } finally {
            //close the entity manager
            em.close();
            em = null;
        }
    }

    public List<Sales> getAllSales() {
        List<Sales> sales = null;

        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Sales> query
                = em.createNamedQuery("Sales.findAll", Sales.class);
        try {
            sales = query.getResultList();
            logger.log(Level.INFO, "Retrieving all advertisement in DB", sales);
        } finally {
            //Close the entity manager
            em.close();
            em = null;
        }
        return sales;
    }

    public List<Sales> getSalesByEmployee(SimpleEmployee employee) {
        List<Sales> sales = null;

        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Sales> query
                = em.createNamedQuery("Sales.findByEmployeeID", Sales.class);
        query.setParameter("employeeId", employee.employeeID);
        try {
            sales = query.getResultList();
            logger.log(Level.INFO, "Retrieving all advertisement in DB", sales);
        } finally {
            //Close the entity manager
            em.close();
            em = null;
        }
        return sales;

    }
    public Customer getCustomerByID(SimpleSales sale){
        Customer customer= null; 
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByID", Customer.class);
                query.setParameter("customerID", sale.accountNum);
                customer = query.getSingleResult();
                return customer;
    }
     public List<Sales> getSalesFromEmployee(SimpleEmployee emp){
        List<Advertisement> allAds= null;
        List<Sales> allSales = null;
        TypedQuery<Advertisement> query1 = em.createNamedQuery("Advertisement.findByEmployeeID", Advertisement.class);
                query1.setParameter("employeeID", emp.employeeID);
                allAds = query1.getResultList();
                
                for(Advertisement ad: allAds)
                {
                    TypedQuery<Sales> query = em.createNamedQuery("Sales.findByAdId", Sales.class);
                    query.setParameter("adID", ad.getAdID());
                    allSales.add(query.getSingleResult());
                }
                    
                return allSales;
    }

}
