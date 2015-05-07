/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.Circle;
import friendb.server.entities.Employee;
import friendb.server.entities.Pages;
import friendb.server.entities.Post;
import friendb.server.rest.PostResource;
import friendb.server.util.DatabaseConnection;
import friendb.shared.SimpleCircle;
import friendb.shared.SimplePost;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

/**
 *
 * @author evanguby
 */
@Stateful
public class PostBean {

    private static final Logger logger
            = Logger.getLogger(PostResource.class.getName());

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;

    public List<SimplePost> getCirclePost(SimpleCircle sc) {
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Pages> query
                = em.createNamedQuery("Pages.findByCircleID", Pages.class);
        query.setParameter("circleID", sc.circleID);
        List<SimplePost> simplePosts = null;
        try {
            Pages page = query.getSingleResult();
            Query query2
                    = em.createQuery("SELECT p.content, p.author, p.datePosted, p.commentCount, p.pageID FROM Post p WHERE p.pageID = " + page.getpageID());
            List<Object[]> posts = query2.getResultList();
            simplePosts = new ArrayList<>();
            SimplePost sp;
            for (Object[] post : posts) {
                sp = new SimplePost();
                sp.authorID = (int) post[1];
                sp.commentCount = (int) post[3];
                sp.content = (String) post[0];
                sp.datePosted = (String) post[2];
                sp.pageID = (int) post[4];
                simplePosts.add(sp);
            }
        } finally {
            //Close the entity manager
            em.close();
            em = null;
        }
        return simplePosts;
    }

    public void addCirclePost(SimplePost sp) {
        em = DatabaseConnection.getEntityManager();
        try {
            TypedQuery<Pages> query = em.createNamedQuery("Pages.findByCircleID", Pages.class);
            query.setParameter("circleID", sp.circleID);
            Pages p = query.getSingleResult();
            Post post = new Post(p.getpageID(), sp.content, sp.authorID, sp.datePosted);
            //add the course
            em.getTransaction().begin();
            //@TODO check return of addCourse to see if it worked
            em.persist(post);
            em.getTransaction().commit();

            logger.log(Level.INFO, "New Post added to database {0}", post);
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

    public void removeCirclePost(SimplePost sp) {
        em = DatabaseConnection.getEntityManager();
        try {
            Post post = new Post(sp.pageID, sp.content, sp.authorID, sp.datePosted);
            //add the course
            em.getTransaction().begin();
            //@TODO check return of addCourse to see if it worked
            em.remove(post);
            em.getTransaction().commit();

            logger.log(Level.INFO, "Post removed from database", post);
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
