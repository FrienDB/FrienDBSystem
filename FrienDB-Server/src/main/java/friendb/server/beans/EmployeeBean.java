/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.Customer;
import friendb.server.entities.Employee;
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
public class EmployeeBean {
     //Logger
    private static final Logger logger =
            Logger.getLogger("friendb.beans.EmployeeBean");

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;
    
    public void addEmployee(int employeeID,
            int ssn,
            String startDate,
            String firstName,
            String lastName,
            String telephone,
            String address,
            String city,
            String curState,
            int zipCode,
            Double hourlyRate,
            String role){
        
         em = DatabaseConnection.getEntityManager();
         Employee e = new Employee(employeeID, ssn, startDate, firstName, lastName, telephone,
                 address, city, curState, zipCode, hourlyRate, role);
         
         try
        {
            //add the school
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            logger.log(Level.INFO, "New employee added to database {0}", e);
        } catch (EntityExistsException eeex)
        {
            //a school with that id already exists in database
            logger.log(Level.WARNING, "Collision on employee ID within database");
            throw eeex;
        } finally
        {
            //close the entity manager
            em.close();
            em = null;
        }
    }
    
    public List<Employee> getAllEmployees(){
        List<Employee> employees = null;

        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Employee> query =
                em.createNamedQuery("Employee.findAll", Employee.class);
        try
        {
            employees = query.getResultList();
            logger.log(Level.INFO, "Retrieving all employees in DB", employees);
        } finally
        {
            //Close the entity manager
            em.close();
            em = null;
        }
        return employees;
    }
}
