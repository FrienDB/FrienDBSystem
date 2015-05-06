/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.rest;

import friendb.server.beans.AdvertisementBean;
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
import friendb.server.entities.Advertisement;
import friendb.server.entities.Customer;
import friendb.shared.SimpleAdvertisement;
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

@Path("advertisement") //the url at which this web service's resources are accessed
@RequestScoped
public class AdvertisementResource {
    
    @Context
    private UriInfo context;

    //link the bean whose functionallity we expose
    @Inject
    private AdvertisementBean advertisementBean;

    //Logger
    private static final Logger logger =
            Logger.getLogger(CustomerResource.class.getName());

    /**
     * Creates a new instance of CustomerREST
     */
    public AdvertisementResource()
    {
        
    }
    
    @GET
    @Produces("application/json")
    public Response getAllAdvertisements()
    {
        List<Advertisement> allAdvertisements = advertisementBean.getAllAdvertisements();
        
        //convert the School entities to a stripped down version readable by the client
        List<SimpleAdvertisement> simpleAdvertisement = new ArrayList<>();
        SimpleAdvertisement a;
        for (Advertisement advertisement : allAdvertisements)
        {
            a = new SimpleAdvertisement();
            a.adID = advertisement.getAdID();
            a.employeeID = advertisement.getEmployeeID();
            a.adType = advertisement.getAdType();
            a.postDate = advertisement.getPostDate();
            a.company = advertisement.getCompany();
            a.item = advertisement.getItem();
            a.content = advertisement.getContent();
            a.price = advertisement.getPrice();
            a.numOfUnits = advertisement.getNumOfUnits();
            simpleAdvertisement.add(a);
        }
        GenericEntity<List<SimpleAdvertisement>> wrapper =
                new GenericEntity<List<SimpleAdvertisement>>(simpleAdvertisement)
                {
                };
        return Response.ok(wrapper).build();
    }
    
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addAdvertisement(SimpleAdvertisement advertisement)
    {
        try
        {
            //add the school
           // customerBean.addCustomer(customer.firstName,customer.lastName,customer.sex,customer.emailID,
             //       customer.dob,customer.address,customer.city,customer.state,customer.zipCode,customer.telephone, customer.password);
            logger.log(Level.INFO, "OK response");
            return Response.ok(advertisement).build();
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
