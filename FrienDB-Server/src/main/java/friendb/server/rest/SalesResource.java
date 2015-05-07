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
import friendb.server.beans.SalesBean;
import friendb.server.entities.Customer;
import friendb.server.entities.Sales;
import friendb.shared.SimpleAdvertisement;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import friendb.shared.SimpleCustomer;
import friendb.shared.SimpleEmployee;
import friendb.shared.SimpleSales;
/**
 *
 * @author evanguby
 */

@Path("sales") //the url at which this web service's resources are accessed
@RequestScoped
public class SalesResource {
    
    @Context
    private UriInfo context;

    //link the bean whose functionallity we expose
    @Inject
    private SalesBean salesBean;

    //Logger
    private static final Logger logger =
            Logger.getLogger(SalesResource.class.getName());

    /**
     * Creates a new instance of CustomerREST
     */
    public SalesResource()
    {
        
    }
    
    @GET
    @Produces("application/json")
    public Response getAllSales()
    {
        List<Sales> allSales = salesBean.getAllSales();
        
        //convert the School entities to a stripped down version readable by the client
        List<SimpleSales> simpleSales = new ArrayList<>();
        SimpleSales s;
        for (Sales sale : allSales)
        {
            s = new SimpleSales();
            s.transID = sale.getTransID();
            s.dateSold = sale.getDateSold();
            s.adID = sale.getAdID();
            s.numUnits = sale.getNumUnits();
            s.accountNum = sale.getAccountNum();
            simpleSales.add(s);
        }
        GenericEntity<List<SimpleSales>> wrapper =
                new GenericEntity<List<SimpleSales>>(simpleSales)
                {
                };
        return Response.ok(wrapper).build();
    }
    
    @POST
    @Path("/makeSale")
    @Consumes("application/json")
    public Response makeSale(SimpleSales sale)
    {
        try
        {
            salesBean.addSales(sale.dateSold, sale.adID, sale.numUnits, sale.accountNum);
            logger.log(Level.INFO, "OK response");
            return Response.ok(sale).build();
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
    @POST
    @Path("/findCustomer")
    @Consumes("application/json")
    public Response getCustomerByID(SimpleSales sale)
    {
              Customer simplePosts = salesBean.getCustomerByID(sale);
        
        GenericEntity<Customer> wrapper =
                new GenericEntity<Customer>(simplePosts)
                {
                };
        return Response.ok(wrapper).build();
    
    }
    
    @POST
    @Path("/employeeSales")
    @Consumes("application/json")
    public Response getSalesFromEmployee(SimpleEmployee employee)
    {
      
        List<SimpleSales> simpleSales = new ArrayList();
        List<Sales> allSales = salesBean.getSalesByEmployee(employee);
        SimpleSales s;
        for (Sales sale : allSales)
        {
            s = new SimpleSales();
            s.transID = sale.getTransID();
            s.dateSold = sale.getDateSold();
            s.adID = sale.getAdID();
            //string d = customer.getDob();
            s.numUnits = sale.getNumUnits();
            s.accountNum = sale.getAccountNum();
            simpleSales.add(s);
        }
        GenericEntity<List<SimpleSales>> wrapper =
                new GenericEntity<List<SimpleSales>>(simpleSales)
                {
                };
        return Response.ok(wrapper).build();
    
    }
    @POST
    @Path("/getFromAd")
    @Consumes("application/json")
    public Response getSalesByAd(SimpleAdvertisement ad)
    {
      
        List<SimpleSales> allSales2 = new ArrayList();
        List<Sales> allSales = salesBean.getSalesByAd(ad);
        SimpleSales s;
        for (Sales sale : allSales)
        {
            s = new SimpleSales();
            s.transID = sale.getTransID();
            s.dateSold = sale.getDateSold();
            s.adID = sale.getAdID();
            //string d = customer.getDob();
            s.numUnits = sale.getNumUnits();
            s.accountNum = sale.getAccountNum();
            allSales2.add(s);
        }
        GenericEntity<List<SimpleSales>> wrapper =
                new GenericEntity<List<SimpleSales>>(allSales2)
                {
                };
        return Response.ok(wrapper).build();
    
    }
}
