/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.util;
import java.util.Map;
import java.util.HashMap;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Utility class for database connections.
 * @author Evan Guby
 */
public final class DatabaseConnection
{

    private static final String PERSISTENCE_UNIT_NAME = "FrienDBT";

    private static Map PROPERTIES = new HashMap();
    private static final String USERNAME = "nwong";
    private static final String PASSWORD = "108857304";

    /**
     * Creates and returns an entity manager connected to the persistence unit.
     * @return 
     */
    public static EntityManager getEntityManager()
    {
        PROPERTIES.put(PersistenceUnitProperties.JDBC_USER, USERNAME);
        PROPERTIES.put(PersistenceUnitProperties.JDBC_PASSWORD, PASSWORD);
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, PROPERTIES);

        return emf.createEntityManager();
    }
}
