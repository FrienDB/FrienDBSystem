/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

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
import friendb.shared.SimpleCustomer;
/**
 *
 * @author evanguby
 */
@Stateful
public class CustomerBean {
     //Logger
    private static final Logger logger =
            Logger.getLogger("friendb.beans.CustomerBean");

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;
    
    public void addCustomer(String firstName,
            String lastName,
            String sex,
            String emailID,
            String dob,
            String address,
            String city,
            String state,
            int zipCode,
            String telephone,
            String password){
        
         em = DatabaseConnection.getEntityManager();
         Customer c = new Customer(firstName, lastName, sex, emailID, dob, address, city, state, zipCode, telephone, password);
         
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

    public void updateCustomer(SimpleCustomer sc) {
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Customer> query =
                em.createNamedQuery("Customer.findByID", Customer.class);
        query.setParameter("id", sc.CustomerID);
        try
        {
            //execute query and update school info
            Customer customer = query.getSingleResult();
            em.getTransaction().begin();
            customer.setAddress(sc.address);
            customer.setCity(sc.city);
            customer.setCustState(sc.state);
            customer.setDob(sc.dob);
            customer.setEmailID(sc.emailID);
            customer.setFirstName(sc.firstName);
            customer.setLastName(sc.lastName);
            customer.setPassword(sc.password);
            customer.setSex("" + sc.sex);
            customer.setTelephone(sc.telephone);
            customer.setZipCode(sc.zipCode);
            em.getTransaction().commit();
            logger.log(Level.INFO, "Updated properties for customer: {0}", customer);

        } catch (NoResultException nrex)
        {
            //school not found
            logger.log(Level.WARNING,
                    "No customer with name {0} found in database", sc);
            throw nrex;
        } finally
        {
            //close the entity manager
            em.close();
            em = null;
        }
    }
 
}
