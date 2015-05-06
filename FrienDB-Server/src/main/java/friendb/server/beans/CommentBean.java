/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.Comments;
import friendb.server.rest.CommentResource;
import friendb.server.util.DatabaseConnection;
import friendb.shared.SimpleComments;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

/**
 *
 * @author nathanwong
 */
public class CommentBean {
    
        //Logger
    private static final Logger logger =
            Logger.getLogger(CommentResource.class.getName());
    
    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;
    
    public void addComment(SimpleComments sc) {
        em = DatabaseConnection.getEntityManager();
        try
        {
            Comments comment = new Comments();
            
            comment.setContent(sc.content);
            comment.setDateCommented(sc.dateCommented);
            comment.setAuthor(sc.author);
            comment.setPostID(sc.postID);
            
            //add the course
            em.getTransaction().begin();
            //@TODO check return of addCourse to see if it worked
            em.persist(comment);
            em.getTransaction().commit();
            
            logger.log(Level.INFO, "New Comment added to database {0}", comment);
        } catch (RollbackException rex)
        {
            //a course with that id already exists in database
            logger.log(Level.WARNING, "Collision on comment ID within database");
            throw rex;
        } finally
        {
            //close the entity manager
            em.close();
            em = null;
        }
    }
    
}
