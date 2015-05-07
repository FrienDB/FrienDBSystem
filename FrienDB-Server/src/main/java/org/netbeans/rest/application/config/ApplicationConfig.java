/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author evanguby
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(friendb.server.rest.AccountsResource.class);
        resources.add(friendb.server.rest.AdvertisementResource.class);
        resources.add(friendb.server.rest.AuthenticationResource.class);
        resources.add(friendb.server.rest.CircleMembershipResource.class);
        resources.add(friendb.server.rest.CircleResource.class);
        resources.add(friendb.server.rest.CommentResource.class);
        resources.add(friendb.server.rest.CustomerResource.class);
        resources.add(friendb.server.rest.EmployeeResource.class);
        resources.add(friendb.server.rest.MessagesResource.class);
        resources.add(friendb.server.rest.PostResource.class);
        resources.add(friendb.server.rest.SalesResource.class);

    }
    
}
