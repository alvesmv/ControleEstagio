package br.edu.femass.controleestagio.webservice;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

//Dependencias dos atributos
import br.edu.femass.controleestagio.dao.UsuarioDao;
import br.edu.femass.controleestagio.model.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 * REST Web Service
 *
 * @author Souza
 */
@Path("/login")
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{login}/{senha}")
    public Usuario autenticar(@PathParam("login") String login, @PathParam("senha") String senha){
        Usuario user = userDao.getUsuario(login);
        
        if(user != null)
        {
            try {
                
                if(this.cifra(senha).equals(user.getSenha()))
                    switch(user.getTipoDeAcesso())
                    {
                        case aluno: 
                            return user; /* Retorna o obj usuario caso quem logue seja aluno*/
                        default:
                            return null;
                    }
                    
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(LoginWS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return null;
            
        } else
            return null;
       
    }
    
    private String cifra(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String senhaHex;

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        senhaHex = hexString.toString();
        return senhaHex;
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
