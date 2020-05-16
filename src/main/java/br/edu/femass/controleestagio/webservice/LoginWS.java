package br.edu.femass.controleestagio.webservice;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

//Dependencias dos atributos
import br.edu.femass.controleestagio.dao.UsuarioDao;
import javax.ejb.EJB;

/**
 * REST Web Service
 *
 * @author Souza
 */
@Path("login")
public class LoginWS {

    @Context
    private UriInfo context;
    
    @EJB
    UsuarioDao userDao;

   
    public LoginWS() {
    }
    
    //Metodo para obter usuario atraves do login e senha
  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of LoginWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
