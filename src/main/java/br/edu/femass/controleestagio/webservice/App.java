package br.edu.femass.controleestagio.webservice;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Souza
 */
@javax.ws.rs.ApplicationPath("ws")
public class App extends Application {

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
        resources.add(br.edu.femass.controleestagio.webservice.Filter.class);
        resources.add(br.edu.femass.controleestagio.webservice.LoginWS.class);
    }
    
}
