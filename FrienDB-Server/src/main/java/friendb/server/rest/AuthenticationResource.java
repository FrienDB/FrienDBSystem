/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.rest;

import friendb.server.beans.AuthenticationBean;
import friendb.shared.LoginInfo;
import friendb.shared.SimpleCustomer;
import friendb.shared.SimpleEmployee;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author evanguby
 */
@Path("authentication")
@RequestScoped
public class AuthenticationResource {

    //Logger
    private static final Logger logger = Logger.getLogger(AuthenticationResource.class.getName());

    @Context
    private UriInfo context;

    @Inject
    private AuthenticationBean authenticationBean;

    /**
     * Creates a new instance of AuthenticationResource
     */
    public AuthenticationResource() {
    }

    /**
     * Retrieves representation of an instance of
     * shield.server.rest.AuthenticationResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for validating user credentials
     *
     * @param login The bundle of login information sent from the client
     * @return an HTTP response with the user's account info or an error code.
     */
    @POST
    @Consumes("application/json")
    public Response authenticateUser(LoginInfo login) {
        try {
            String[] s = authenticationBean.authenticate(login.email, login.password);
            logger.log(Level.INFO, "Authentication success, OK response");
            if (s[0].equals("Customer")) {
                SimpleCustomer c = new SimpleCustomer();
                c.CustomerID = Integer.parseInt(s[13]);
                c.emailID = s[5];
                c.address = s[1];
                c.city = s[2];
                c.dob = s[4];
                c.firstName = s[6];
                c.lastName = s[7];
                c.password = s[8];
                c.rating = Integer.parseInt(s[11]);
                c.sex = s[9].charAt(0);
                c.state = s[3];
                c.telephone = s[10];
                c.zipCode = Integer.parseInt(s[12]);
                //CREATE C FINDBYNAME.Customer
                return Response.ok(c).build();
            } else //Employee
            {
                SimpleEmployee e = new SimpleEmployee();
                e.employeeID = Integer.parseInt(s[1]);
                e.ssn = Integer.parseInt(s[2]);
                e.startDate = s[3];
                e.firstName = s[4];
                e.lastName = s[5];
                e.telephone = s[6];
                e.address = s[7];
                e.city = s[8];
                e.curState = s[9];
                e.hourlyRate = Double.parseDouble(s[11]);
                e.role = s[12];
                e.zipCode = Integer.parseInt(s[10]);
                e.password = s[13];
                //CREATE C FINDBYNAME.Customer
                return Response.ok(e).build();
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "UNAUTHORIZED resposne");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
    }
}
