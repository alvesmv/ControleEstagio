package br.edu.femass.controleestagio.webservice;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.DocumentoDao;
import br.edu.femass.controleestagio.dao.EstagioDao;
import br.edu.femass.controleestagio.enums.DocumentoStatus;
import br.edu.femass.controleestagio.model.Documento;
import br.edu.femass.controleestagio.wsmodel.EstagioWS;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;
import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Named
@Path("/estagio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstagioService {
    
    @Context
    private UriInfo context;
    
    @EJB
    EstagioDao estagioDao;
    
    @EJB
    DocumentoDao docDao;
    
    @EJB
    AlunoDao alunoDao;
    
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
    
    @PUT
    public Response put(EstagioWS estagio){
        try{
            estagioDao.alterar(estagioDao.getEstagioAtivoPorAluno(alunoDao.getAlunoPorMatricula(estagio.getMatriculaAluno())));       
        }catch(Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).entity("Usuário atualizado com sucesso").build();
    }
    
    @GET
    @Path("ativo/{login}/pdf")
    @Produces("application/pdf")
    public Response download(@QueryParam("filename")String nomeArq) throws IOException{
        
        Documento doc = docDao.getDocumentoByString(nomeArq);

        File f = new File(doc.getNome());
        FileUtils.writeByteArrayToFile(f, doc.getArquivo());
        
        ResponseBuilder res = Response.ok((Object) f);
        res.header("Content-Disposition","attachment; filename="+doc.getNome());
        
        return res.build();
    }
    
    @DELETE
    @Path("ativo/{login}/pdf")
    public Response delete(@QueryParam("filename")String nomeArq, @PathParam("login")String login){
        try
        {
            Documento doc = docDao.getDocumentoByEstagio(nomeArq, estagioDao.getEstagioAtivoPorAluno(alunoDao.getAlunoPorMatricula(login)));
            docDao.excluir(doc);
            return Response.status(200).entity("Arquivo excluido com sucesso").build();
        }
        catch(Exception e){
            
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    
    @POST
    @Path("ativo/{login}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(
            @FormDataParam("file") InputStream fluxoEntrada,
            @FormDataParam("file") FormDataContentDisposition metadados,
            @PathParam("login")String login) throws Exception{
        
            Documento doc = new Documento();
            doc.setAlunoMatricula(login);
            doc.setNome(metadados.getFileName());         
            doc.setArquivo(this.converterParaBinario(fluxoEntrada, metadados));
            doc.setTamanho((long) doc.getArquivo().length);
            doc.setEstagio(estagioDao.getEstagioAtivoPorAluno(alunoDao.getAlunoPorMatricula(login)));
            doc.setDataEnvio(metadados.getModificationDate());
            doc.setDocStatus(DocumentoStatus.em_analise);
            
            docDao.inserir(doc);
            
        return Response.status(200).entity("Arquivo enviado com sucesso").build();
    }
    
    /* O método abaixo traduz um baita malabarismo para salvar o conteúdo de inputStream para um array de bytes */
    public byte[] converterParaBinario(InputStream fluxoEntrada, FormDataContentDisposition meta) throws FileNotFoundException, IOException{
        
        ByteArrayOutputStream outArray = new ByteArrayOutputStream();
        int temp = 0;
        byte[] chunck = new byte[1024];
        
        do{
            outArray.write(chunck, 0, temp); 
            temp = fluxoEntrada.read(chunck, 0, 1024);/* O temp registra o indice do ultimo byte da iteracao lido em fluxoEntrada*/  
            
        }while(temp != -1); /* Para quando a leitura do fluxoEntrada chegar ao fim */
        
        
        byte[] blob = outArray.toByteArray(); 
        
        return blob;
    }
}
