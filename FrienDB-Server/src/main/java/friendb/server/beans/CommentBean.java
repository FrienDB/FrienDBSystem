/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.Comments;
import friendb.server.entities.Pages;
import friendb.server.entities.Post;
import friendb.server.rest.CommentResource;
import friendb.server.util.DatabaseConnection;
import friendb.shared.SimpleComments;
import friendb.shared.SimplePost;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.xml.stream.events.Comment;

/**
 *
 * @author nathanwong
 */
@Stateful
public class CommentBean {

    //Logger
    private static final Logger logger
            = Logger.getLogger(CommentResource.class.getName());

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;

    public void addComment(SimpleComments sc) {
        em = DatabaseConnection.getEntityManager();
        try {
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
        } catch (RollbackException rex) {
            //a course with that id already exists in database
            logger.log(Level.WARNING, "Collision on comment ID within database");
            throw rex;
        } finally {
            //close the entity manager
            em.close();
            em = null;
        }
    }

    public List<SimpleComments> getPostComments(SimplePost sp) {
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Comments> query
                = em.createNamedQuery("Comments.findByPostID", Comments.class);
        query.setParameter("postID", sp.postID);
        List<SimpleComments> simpleComments = new ArrayList<>();
        try {
            List<Comments> comments = query.getResultList();
            for (Comments c : comments) {
                SimpleComments sc = new SimpleComments();
                sc.author = c.getAuthor();
                sc.commentID = c.getCommentID();
                sc.content = c.getContent();
                sc.dateCommented = c.getDateCommented();
                sc.postID = c.getPostID();
                simpleComments.add(sc);
            }
        } finally {
            //close the entity manager
            em.close();
            em = null;
        }
        return simpleComments;
    }

    public void addPostComment(SimpleComments sc) {
        em = DatabaseConnection.getEntityManager();
        try {
            TypedQuery<Post> query = em.createNamedQuery("Post.findByID", Post.class);
            query.setParameter("postID", sc.postID);
            Post c = query.getSingleResult();
            Comments comment = new Comments(sc.content,sc.dateCommented,sc.author,c.getPostID());
            //add the course
            em.getTransaction().begin();
            //@TODO check return of addCourse to see if it worked
            em.persist(comment);
            em.getTransaction().commit();

            logger.log(Level.INFO, "New Post added to database {0}", comment);
        } catch (RollbackException rex) {
            //a course with that id already exists in database
            logger.log(Level.WARNING, "Collision on post ID within database");
            throw rex;
        } finally {
            //close the entity manager
            em.close();
            em = null;
        }
    }

}
