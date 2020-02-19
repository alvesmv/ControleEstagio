/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.gui;
import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.DocumentoDao;
import br.edu.femass.controleestagio.model.Documento;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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
    @EJB
    AlunoDao alunoDB;
    public MbUpload(){
        doc = new Documento();  
        docDao = new DocumentoDao();
        alunoDB = new AlunoDao();
    }
    
    public File convertUpFileToFile(UploadedFile uf){
        InputStream in = null;
        OutputStream out = null;
        System.setProperty("file.encoding", "UTF-8");
        File novoArq = new File(uf.getFileName());
        
        try{
            in = uf.getInputstream();
            out = new FileOutputStream(novoArq);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        }catch(IOException e){System.out.println("Falha na converção de arquivos");}
        
        return novoArq;
    }
 
    public void upload(FileUploadEvent event){
        UploadedFile upFile = event.getFile();
        this.doc.setArquivo(convertUpFileToFile(upFile)); 
        this.doc.setNome(upFile.getFileName());
        this.doc.setTamanho(upFile.getSize());
        //Obtem o objeto usuario instanciado no durante o login 
        Object o = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.doc.setAluno(alunoDB.getAlunoPorMatricula(o.toString()));
        
        if(doc.getArquivo() == null)
            System.out.println("NULL!!!!!!!!!");
        //Não é possível serializar abaixo (ERRO)
        //Flush no DB
        docDao.inserir(this.doc);
    }   
}
