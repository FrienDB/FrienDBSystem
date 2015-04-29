/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.rest;

import friendb.server.beans.CircleBean;
import friendb.server.beans.CustomerBean;
import friendb.server.entities.Circle;
import friendb.shared.LoginInfo;
import friendb.shared.SimpleCircle;
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
@Path("circle") //the url at which this web service's resources are accessed
@RequestScoped
public class CircleResource {
    
    @Context
    private UriInfo context;

    //link the bean whose functionallity we expose
    @Inject
    private CircleBean circleBean;

    //Logger
    private static final Logger logger =
            Logger.getLogger(CustomerResource.class.getName());

    public CircleResource() {
    }
    
    @POST
    @Consumes("application/json")
    public Response getCustomersCircles(SimpleCustomer c) {
        ArrayList<Circle> circles = circleBean.getCustomersCircles(c);
        ArrayList<SimpleCircle> simpleCircles = new ArrayList<>();
        SimpleCircle sc;
        for (Circle circle : circles)
        {
            sc = new SimpleCircle();
            sc.circleID = circle.getCircleID();
            sc.circleName = circle.getCircleName();
            sc.circleOwner = circle.getCircleOwner();
            sc.circleType = circle.getCircleType();
            simpleCircles.add(sc);
        }
        GenericEntity<List<SimpleCircle>> wrapper =
                new GenericEntity<List<SimpleCircle>>(simpleCircles)
                {
                };
        return Response.ok(wrapper).build();
    }
    
}
