/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.rest;

import friendb.server.beans.CustomerBean;
import friendb.server.beans.PostBean;
import friendb.server.entities.Post;
import friendb.shared.SimpleCircle;
import friendb.shared.SimpleCircleMembership;
import friendb.shared.SimplePost;
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
@Path("post") //the url at which this web service's resources are accessed
@RequestScoped
public class PostResource {
    
    @Context
    private UriInfo context;

    //link the bean whose functionallity we expose
    @Inject
    private PostBean postBean;

    //Logger
    private static final Logger logger =
            Logger.getLogger(PostResource.class.getName());

    public PostResource() {
        
    }
    
    @POST
    @Path("/getCirclePosts")
    @Consumes("application/json")
    public Response getCirclePosts(SimpleCircle sc) {
        List<SimplePost> simplePosts = postBean.getCirclePost(sc);
        
        GenericEntity<List<SimplePost>> wrapper =
                new GenericEntity<List<SimplePost>>(simplePosts)
                {
                };
        return Response.ok(wrapper).build();
    }
    
}
