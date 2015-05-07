/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.Messages;
import friendb.server.rest.MessagesResource;
import friendb.server.util.DatabaseConnection;
import friendb.shared.SimpleMessages;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author nathanwong
 */
@Stateful
public class MessagesBean {
    
    private static final Logger logger
            = Logger.getLogger(MessagesResource.class.getName());
    
    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;
    
    public List<SimpleMessages> getMessages(int customerID) {
        em = DatabaseConnection.getEntityManager();
        List<SimpleMessages> simpleMessages = null;
        try {
            Query query
                    = em.createQuery("SELECT m.messageID, m.subj, m.content, m.dateSent, m.sender, m.receiver FROM Messages m WHERE m.sender = " + customerID + " OR m.receiver = " + customerID);
            List<Object[]> messages = query.getResultList();
            simpleMessages = new ArrayList<>();
            SimpleMessages sm;
            for (Object[] message : messages) {
                sm = new SimpleMessages();
                sm.messageID = (int) message[0];
                sm.subj = (String) message[1];
                sm.content = (String) message[2];
                sm.dateSent = (String) message[3];
                sm.sender = (int) message[4];
                sm.receiver = (int) message[5];

                simpleMessages.add(sm);
            }
        } finally {
            //Close the entity manager
            em.close();
            em = null;
        }
        return simpleMessages;
    }
    
}
