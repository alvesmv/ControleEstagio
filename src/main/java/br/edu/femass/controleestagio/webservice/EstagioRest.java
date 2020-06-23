package br.edu.femass.controleestagio.webservice;

import br.edu.femass.controleestagio.dao.EstagioDao;
import br.edu.femass.controleestagio.wsmodel.EstagioWS;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author dumas
 */
@Named
@Path("/estagio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstagioRest {
    
    @Context
    private UriInfo context;
    
    @EJB
    EstagioDao estagioDao;
    
    @GET
    @Path("lista/{login}")
    public List<EstagioWS> buscarListaDeEstagios(@PathParam("login")String login){
        List<EstagioWS> estagios = estagioDao.getListaDeEstagioWS(login);
        
        return estagios;
    }
    
    @GET
    @Path("ativo/{login}")
    public EstagioWS buscarEstagioAtivo(@PathParam("login")String login){
        EstagioWS estagios = estagioDao.getEstagioWS(login);
        
        return estagios;
    } 
}
