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

/**
 *
 * @author evanguby
 */
@Stateful
public class SalesBean {

    //Logger

    private static final Logger logger
            = Logger.getLogger("friendb.beans.CustomerBean");

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;

    public void addSales(int transId,
            String dateSold,
            int adId,
            int numUnits,
            int accountNum
            ) 
        {

        em = DatabaseConnection.getEntityManager();
        Sales s = new Sales(transId, dateSold, adId, numUnits, accountNum);

        try {
            //add the school
            em.getTransaction().begin();
            em.persist(s);
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

    public List<Sales> getSalesByEmployee(int employeeId) {
        List<Sales> sales = null;

        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Sales> query
                = em.createNamedQuery("Sales.findByEmployeeID", Sales.class);
        query.setParameter("employeeID", employeeId);
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

}
