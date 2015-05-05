/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.rest;

import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import friendb.server.beans.CustomerBean;
import friendb.server.beans.EmployeeBean;
import friendb.server.entities.Customer;
import friendb.server.entities.Employee;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import friendb.shared.SimpleCustomer;
import friendb.shared.SimpleEmployee;
/**
 *
 * @author evanguby
 */

@Path("employee") //the url at which this web service's resources are accessed
@RequestScoped
public class EmployeeResource {
    
    @Context
    private UriInfo context;

    //link the bean whose functionallity we expose
    @Inject
    private EmployeeBean employeeBean;

    //Logger
    private static final Logger logger =
            Logger.getLogger(CustomerResource.class.getName());

    /**
     * Creates a new instance of CustomerREST
     */
    public EmployeeResource()
    {
        
    }
    
    @GET
    @Produces("application/json")
    public Response getAllEmployee()
    {
        List<Employee> allEmployees = employeeBean.getAllEmployees();
        
        //convert the School entities to a stripped down version readable by the client
        List<SimpleEmployee> simpleEmployees = new ArrayList<>();
        SimpleEmployee e;
        for (Employee employee : allEmployees)
        {
            e = new SimpleEmployee();
            e.employeeID = employee.getEmployeeID();
            e.ssn = employee.getSSN();
            e.startDate = employee.getStartDate();
            //String d = employee.getStartDate();
            e.firstName = employee.getFirstName();
            e.lastName = employee.getLastName();
            e.telephone = employee.getTelephone();
            //c.sex = customer.getSex();
            e.address = employee.getAddress();
            e.telephone = employee.getTelephone();
            e.city = employee.getCity();
            e.curState = employee.getCurState();
            e.hourlyRate = employee.getHourlyRate();
            e.zipCode = employee.getZipCode();
            e.role = employee.getRole();
            simpleEmployees.add(e);
        }
        GenericEntity<List<SimpleEmployee>> wrapper =
                new GenericEntity<List<SimpleEmployee>>(simpleEmployees)
                {
                };
        return Response.ok(wrapper).build();
    }
    
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addCustomer(SimpleCustomer customer)
    {
        try
        {
            //add the school
           // customerBean.addCustomer(customer.firstName,customer.lastName,customer.sex,customer.emailID,
             //       customer.dob,customer.address,customer.city,customer.state,customer.zipCode,customer.telephone, customer.password);
            logger.log(Level.INFO, "OK response");
            return Response.ok(customer).build();
        } catch (EntityExistsException eeex)
        {
            logger.log(Level.WARNING, "BAD REQUEST response");
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (NoResultException eeex)
        {
            //@TODO disambiguate with previous exception
            logger.log(Level.WARNING, "BAD REQUEST response");
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception ex)
        {
            logger.log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }
}
