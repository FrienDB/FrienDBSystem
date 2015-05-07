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
                    String toReturn[] = new String[14];
                    toReturn[0] = "Customer";
                    toReturn[1] = cust.get(0).getAddress();
                    toReturn[2] = cust.get(0).getCity();
                    toReturn[3] = cust.get(0).getCustState();
                    toReturn[4] = cust.get(0).getDob();
                    toReturn[5] = cust.get(0).getEmailID();
                    toReturn[6] = cust.get(0).getFirstName();
                    toReturn[7] = cust.get(0).getLastName();
                    toReturn[8] = cust.get(0).getPassword();
                    toReturn[9] = cust.get(0).getSex();
                    toReturn[10] = cust.get(0).getTelephone();
                    toReturn[11] = "" + cust.get(0).getRatingOf10();
                    toReturn[12] = ""+cust.get(0).getZipCode();
                    toReturn[13] = ""+cust.get(0).getCustomerID();
                    return toReturn;
                } else
                {
                    logger.log(Level.WARNING, "Incorrect password for Student {0}", email);
                    throw new WrongPasswordException();
                }
            } else //Employee don't have @ symbol in their username
            {
                int empID = Integer.parseInt(email);
                //set up the query
                TypedQuery<Employee> query =
                        em.createNamedQuery("Employee.findByID", Employee.class);
                query.setParameter("employeeID", empID);

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
