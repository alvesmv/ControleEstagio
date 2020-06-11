/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.webservice;

import br.edu.femass.controleestagio.dao.EstagioDao;
import br.edu.femass.controleestagio.wsmodel.EstagioWS;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dumas
 */
@Named
@Path("estagio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstagioRest {
    
    
    @EJB
    EstagioDao estagioDao;
    
    @GET
    @Path("buscarestagio")
    public EstagioWS buscarEstagio(Long idAluno){
        return null;
    }
    
}
