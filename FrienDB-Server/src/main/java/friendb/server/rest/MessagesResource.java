/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.rest;

import friendb.server.beans.MessagesBean;
import friendb.server.entities.Messages;
import friendb.shared.SimpleMessages;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author nathanwong
 */
public class MessagesResource {
    
    @Context
    private UriInfo context;

    @Inject
    private MessagesBean messagesBean;

    public MessagesResource() {
    }
    
    @GET
    @Produces("application/json")
    public Response getMessages(int customerID)
    {
        List<SimpleMessages> allMessages = messagesBean.getMessages(customerID);
        
        //convert the School entities to a stripped down version readable by the client
        List<SimpleMessages> simpleMessages = new ArrayList<>();
        SimpleMessages m;
        for (SimpleMessages message : allMessages)
        {
            m = new SimpleMessages();
            m.messageID = message.messageID;
            m.sender = message.sender;
            m.receiver = message.receiver;
            m.subj = message.subj;
            m.dateSent = message.dateSent;
            m.content = message.content;
            
            simpleMessages.add(m);
        }
        GenericEntity<List<SimpleMessages>> wrapper =
                new GenericEntity<List<SimpleMessages>>(simpleMessages)
                {
                };
        return Response.ok(wrapper).build();
    }
    
}
