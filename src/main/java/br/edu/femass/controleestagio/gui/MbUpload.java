/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.gui;
import br.edu.femass.controleestagio.model.Documento;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Souza
 */
@Named(value = "guiDocumento")
@Dependent
public class MbUpload {

    /**
     * Creates a new instance of MbUpload
     */
    Documento doc;
    public MbUpload() {
        doc = new Documento();   
    }
 
    public void upload(FileUploadEvent event){
        this.doc.setArquivo(event.getFile()); 
        this.doc.setNome(this.doc.getArquivo().getFileName());
        this.doc.setTamanho(this.doc.getArquivo().getSize());
        
    }
    
}
