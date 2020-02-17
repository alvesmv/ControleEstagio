/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.gui;
import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.DocumentoDao;
import br.edu.femass.controleestagio.model.Documento;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Souza
 */
@Named(value = "MbUpload")
@Dependent
public class MbUpload implements Serializable{

    /**
     * Creates a new instance of MbUpload
     */
    Documento doc;
    @EJB
    DocumentoDao docDao;
    
    public MbUpload(){
        doc = new Documento();  
        docDao = new DocumentoDao();
    }
 
    public void upload(FileUploadEvent event){
        
        this.doc.setArquivo(event.getFile()); 
        this.doc.setNome(this.doc.getArquivo().getFileName());
        this.doc.setTamanho(this.doc.getArquivo().getSize());
        //Obtem o objeto usuario instanciado no durante o login 
        Object o = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.doc.setAluno(new AlunoDao().getAlunoPorMatricula(o.toString()));
        
        //Flush no DB
        docDao.inserir(this.doc);
     
    }   
}
