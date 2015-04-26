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
import friendb.server.entities.Customer;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import friendb.shared.SimpleCustomer;
/**
 *
 * @author evanguby
 */

@Path("customer") //the url at which this web service's resources are accessed
@RequestScoped
public class CustomerResource {
    
    @Context
    private UriInfo context;

    //link the bean whose functionallity we expose
    @Inject
    private CustomerBean customerBean;

    //Logger
    private static final Logger logger =
            Logger.getLogger(CustomerResource.class.getName());

    /**
     * Creates a new instance of CustomerREST
     */
    public CustomerResource()
    {
        
    }
    
    @GET
    @Produces("application/json")
    public Response getAllCustomers()
    {
        List<Customer> allCustomers = customerBean.getAllCustomers();
        
        //convert the School entities to a stripped down version readable by the client
        List<SimpleCustomer> simpleCustomers = new ArrayList<>();
        SimpleCustomer c;
        for (Customer customer : allCustomers)
        {
            c = new SimpleCustomer();
            c.CustomerID = customer.getCustomerID();
            c.address = customer.getAddress();
            c.city = customer.getCity();
            String d = customer.getDob();
            c.emailID = customer.getEmailID();
            c.firstName = customer.getFirstName();
            c.lastName = customer.getLastName();
            c.rating = customer.getRatingOf10();
            c.sex = customer.getSex();
            c.state = customer.getCustState();
            c.telephone = customer.getTelephone();
            c.zipCode = customer.getZipCode();
        }
        GenericEntity<List<SimpleCustomer>> wrapper =
                new GenericEntity<List<SimpleCustomer>>(simpleCustomers)
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
            customerBean.addCustomer(customer.firstName,customer.lastName,customer.sex,customer.emailID,
                    customer.dob,customer.address,customer.city,customer.state,customer.zipCode,customer.telephone, customer.password);
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
