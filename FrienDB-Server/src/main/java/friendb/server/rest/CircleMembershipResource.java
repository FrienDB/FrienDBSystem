/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.rest;

import friendb.server.beans.AuthenticationBean;
import friendb.server.beans.CircleMembershipBean;
import friendb.server.entities.Circle;
import friendb.server.entities.Customer;
import friendb.shared.SimpleCircle;
import friendb.shared.SimpleCircleMembership;
import friendb.shared.SimpleCustomer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author evanguby
 */
@Path("circleMembership")
@RequestScoped
public class CircleMembershipResource {
    
    //Logger
    private static final Logger logger = Logger.getLogger(CircleMembershipResource.class.getName());

    @Context
    private UriInfo context;

    @Inject
    private CircleMembershipBean circleMembershipBean;

    public CircleMembershipResource() {
    }
    
    @POST
    @Path("/getCircleMembers")
    @Consumes("application/json")
    public Response getCircleMembers(SimpleCircleMembership scm) {
        List<Customer> customers = circleMembershipBean.getCircleMembers(scm);
        ArrayList<SimpleCustomer> simpleCustomers = new ArrayList<>();
        SimpleCustomer sc;
        for (Customer customer : customers)
        {
            sc = new SimpleCustomer();
            sc.CustomerID = customer.getCustomerID();
            sc.firstName = customer.getFirstName();
            sc.lastName = customer.getLastName();
            simpleCustomers.add(sc);
        }
        GenericEntity<List<SimpleCustomer>> wrapper =
                new GenericEntity<List<SimpleCustomer>>(simpleCustomers)
                {
                };
        return Response.ok(wrapper).build();
    }
    
}
