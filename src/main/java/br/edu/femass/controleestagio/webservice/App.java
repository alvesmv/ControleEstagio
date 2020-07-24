package br.edu.femass.controleestagio.webservice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Souza
 */
@javax.ws.rs.ApplicationPath("api")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    /*O metodo getProperties() registra o multiparfeature no servlet*/
    public Map<String, Object> getProperties() {
        final Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.provider.classnames",
                       "org.glassfish.jersey.media.multipart.MultiPartFeature");
        return properties;
    }
    
    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.edu.femass.controleestagio.webservice.EstagioService.class);
        resources.add(br.edu.femass.controleestagio.webservice.Filter.class);
        resources.add(br.edu.femass.controleestagio.webservice.LoginService.class);
    }
    
}
