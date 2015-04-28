/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.rest;

import friendb.server.beans.AuthenticationBean;
import friendb.shared.LoginInfo;
import friendb.shared.SimpleCustomer;
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
                c.emailID = s[1];
                //CREATE C FINDBYNAME.Customer
                return Response.ok(c).build();
            } else //Employee
            {

            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "UNAUTHORIZED resposne");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
