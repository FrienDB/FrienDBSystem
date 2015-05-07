/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.Advertisement;
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
import friendb.shared.SimpleAdvertisement;
import friendb.shared.SimpleEmployee;
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
    public void editEmployee(SimpleEmployee employee){
        

        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
        
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Employee> query =
                em.createNamedQuery("Employee.findByID", Employee.class);
                query.setParameter("employeeID", employee.employeeID);
        try
        {
            //execute query and update school info
            Employee edit = query.getSingleResult();
            em.getTransaction().begin();
            edit.setFirstName(employee.firstName);
            edit.setLastName(employee.lastName);
            edit.setTelephone(employee.telephone);
            edit.setCity(employee.city);
            edit.setAddress(employee.address);
            edit.setCurState(employee.curState);
            edit.setPassword(employee.password);
            em.getTransaction().commit();
            logger.log(Level.INFO, "Updated properties for customer: {0}", edit);

        } catch (NoResultException nrex)
        {
            //school not found
            logger.log(Level.WARNING,
                    "No customer with name {0} found in database", employee);
            throw nrex;
        } finally
        {
            //close the entity manager
            em.close();
            em = null;
        }
        
    }
    public Employee findEmployee(int empID){
        

        // Create the entity manager and set up the query for all schools
        em = DatabaseConnection.getEntityManager();
        
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Employee> query =
                em.createNamedQuery("Employee.findByID", Employee.class);
                query.setParameter("employeeID", empID);
           Employee employee = query.getSingleResult();
           return employee;
    }            
    public void addAdvertisement(SimpleAdvertisement ad){
        /*
         em = DatabaseConnection.getEntityManager();
         Advertisement = new Advertisement(firstName, lastName, sex, emailID, dob, address, city, state, zipCode, telephone, password);
         
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
                */
    }
}
