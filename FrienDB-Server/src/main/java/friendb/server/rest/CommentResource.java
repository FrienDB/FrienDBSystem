/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.rest;

import friendb.server.beans.CommentBean;
import friendb.shared.SimpleComments;
import friendb.shared.SimplePost;
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
 * @author nathanwong
 */
@Path("comment") //the url at which this web service's resources are accessed
@RequestScoped
public class CommentResource {
    
    @Context
    private UriInfo context;

    //link the bean whose functionallity we expose
    @Inject
    private CommentBean commentBean;

    //Logger
    private static final Logger logger =
            Logger.getLogger(CommentResource.class.getName());
    
    @POST
    @Path("/getPostComments")
    @Consumes("application/json")
    public Response getPostComments(SimplePost sp) {
        List<SimpleComments> simpleComments = commentBean.getPostComments(sp);
        
        GenericEntity<List<SimpleComments>> wrapper =
                new GenericEntity<List<SimpleComments>>(simpleComments)
                {
                };
        return Response.ok(wrapper).build();
    }
    
}
