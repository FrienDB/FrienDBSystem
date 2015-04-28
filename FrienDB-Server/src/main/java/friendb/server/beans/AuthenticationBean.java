/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.exceptions.WrongPasswordException;
import friendb.server.entities.Customer;
import friendb.server.entities.Employee;
import friendb.server.util.DatabaseConnection;
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
public class AuthenticationBean
{

    //Logger
    private static final Logger logger =
            Logger.getLogger("sss.ejb.AuthenticationBean");

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;

    /**
     * Checks validity of the supplied log-in credentails.
     * 
     * @param username The username for the account
     * @param password The password for the account
     * @return The account matching the supplied credentials.
     * @throws AccountActiveException When the Student account is already logged in somewhere.
     * @throws AccountPendingException When the Student account is not yet approved.
     * @throws WrongPasswordException When the supplied password does not match the one on file.
     * @throws NoResultException When there is no account with the supplied username.
     */
    public String[] authenticate(String email,String password) throws WrongPasswordException
    {
        //Create the entity manager
        em = DatabaseConnection.getEntityManager();
        logger.log(Level.INFO, "User attempts log in with name {0}", email);

        try
        {
            if (email.indexOf('@') != -1) //Student users log in using their email
            {
                //set up the query
                TypedQuery<Customer> query = em.createNamedQuery("Customer.findByEmail", Customer.class);
                query.setParameter("emailID", email);

                //find the right account with that email
                List<Customer> cust = query.getResultList();
                if (cust.get(0).getPassword().equals(password))
                {
                    logger.log(Level.INFO, "Student {0} logged in", email);
                    String toReturn[] = {"Customer",cust.get(0).getEmailID()};
                    return toReturn;
                } else
                {
                    logger.log(Level.WARNING, "Incorrect password for Student {0}", email);
                    throw new WrongPasswordException();
                }
            } else //Employee don't have @ symbol in their username
            {
                //set up the query
                TypedQuery<Employee> query =
                        em.createNamedQuery("Employee.findByID", Employee.class);
                query.setParameter("employeeID", email);

                //find the right account with that email
                Employee e = query.getSingleResult();

                    logger.log(Level.INFO, "Emplyee {0} logged in", email);
                    String toReturn[] = {"Employee",email};
                    return toReturn;

            }
        } catch (NoResultException nrex)
        {
            //no matching account
            logger.log(Level.WARNING, "No such account with email {0} found in database", email);
            throw nrex;
        } finally
        {
            //close the entity manager
            em.close();
            em = null;
        }
    }
}
