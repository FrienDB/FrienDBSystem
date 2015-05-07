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
import friendb.shared.SimpleEmployee;
import javax.persistence.RollbackException;
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
            a.numUnits = advertisement.getNumUnits();
            simpleAdvertisement.add(a);
        }
        GenericEntity<List<SimpleAdvertisement>> wrapper =
                new GenericEntity<List<SimpleAdvertisement>>(simpleAdvertisement)
                {
                };
        return Response.ok(wrapper).build();
    }
    
    @POST
    @Path("/addAdvertisement")
    @Consumes("application/json")
    public Response addAdvertisement(SimpleAdvertisement advertisement)
    {
        try
        {
            advertisementBean.addAdvertisement(advertisement);
            logger.log(Level.INFO, "OK Response");
            return Response.ok(advertisement).build();
        } catch (RollbackException rex)
        {
            logger.log(Level.WARNING, "BAD REQUEST");
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (NoResultException nrex)
        {
            //@TODO disambiguate errors
            logger.log(Level.WARNING, "BAD REQUEST");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @POST
    @Path("/getEmployeeAds")
    @Consumes("application/json")
    public Response getEmployeeAds(SimpleEmployee employee)
    {
        List<Advertisement> allCustomers = advertisementBean.getEmployeeAds(employee);
        
        //convert the School entities to a stripped down version readable by the client
        List<SimpleAdvertisement> simpleAdvertisements = new ArrayList<>();
        SimpleAdvertisement c;
        for (Advertisement ad : allCustomers)
        {
               
            c = new SimpleAdvertisement();
            c.adID = ad.getAdID();
            
            c.employeeID = ad.getEmployeeID();
            c.adType = ad.getAdType();
            c.postDate = ad.getPostDate();
            c.company = ad.getCompany();
            c.item = ad.getItem();
            c.content = ad.getContent();
            c.price = ad.getPrice();
            //c.sex = customer.getSex();
            c.numUnits = ad.getNumUnits();
            simpleAdvertisements.add(c);
        }
        GenericEntity<List<SimpleAdvertisement>> wrapper =
                new GenericEntity<List<SimpleAdvertisement>>(simpleAdvertisements)
                {
                };
        return Response.ok(wrapper).build();
    }
    
    @GET
    @Path("/getTopSellingList")
    @Consumes("application/json")
    public Response getTopSellingList(){
        List<Advertisement> allAdvertisements = advertisementBean.getTopSellingList();
        
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
            a.numUnits = advertisement.getNumUnits();
            simpleAdvertisement.add(a);
        }
        GenericEntity<List<SimpleAdvertisement>> wrapper =
                new GenericEntity<List<SimpleAdvertisement>>(simpleAdvertisement)
                {
                };
        return Response.ok(wrapper).build();
    }
}
