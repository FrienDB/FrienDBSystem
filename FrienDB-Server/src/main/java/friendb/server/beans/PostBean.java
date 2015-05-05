/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.beans;

import friendb.server.entities.Employee;
import friendb.server.entities.Pages;
import friendb.server.entities.Post;
import friendb.server.util.DatabaseConnection;
import friendb.shared.SimpleCircle;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author evanguby
 */
@Stateful
public class PostBean {
    
    private static final Logger logger =
            Logger.getLogger("friendb.beans.PostBean");

    //reference to the perisstence layer
    @PersistenceContext
    private EntityManager em;

    public List<Post> getCirclePost(SimpleCircle sc) {
        em = DatabaseConnection.getEntityManager();
        TypedQuery<Pages> query =
                em.createNamedQuery("Pages.findByCircleID", Pages.class);
        query.setParameter("circleID", sc.circleID);
        List<Post> posts = null;
        try{
            Pages page = query.getSingleResult();
            TypedQuery<Post> query2 =
                em.createNamedQuery("Post.findByPageID", Post.class);
            query2.setParameter("pageID", page.getpageID());
            posts = query2.getResultList();
        }finally
        {
            //Close the entity manager
            em.close();
            em = null;
        }
        return posts;
    }
    
}
