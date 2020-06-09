package br.edu.femass.controleestagio.gui;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.DocumentoDao;
import br.edu.femass.controleestagio.dao.EstagioDao;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Documento;
import br.edu.femass.controleestagio.model.DocumentoStatus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Named(value = "mbUpload")
@Dependent
public class MbUpload implements Serializable{

    Documento doc;
    @EJB
    DocumentoDao docDao;
    @EJB
    AlunoDao alunoDB;
    @EJB
    EstagioDao estagioDao;
    public MbUpload(){
        doc = new Documento();  
        docDao = new DocumentoDao();
        alunoDB = new AlunoDao();
    }
    
    //O metodo abaixo converte o UpFile do primefaces para um array de bytes
    public byte[] convertUpFileToByte(UploadedFile uf){
  
            System.setProperty("file.encoding", "UTF-8");
            File novoArq = new File(uf.getFileName());
            FileInputStream in;
            byte[] blob = uf.getContents();
  
        try{
            in = new FileInputStream(novoArq);
            in.read(blob);      //lê o conteúdo do FileInputStream e joga tudo em blob
            in.close();

        } catch (IOException ex) {
            Logger.getLogger(MbUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blob;
    }
        
    public void upload(FileUploadEvent event){
        UploadedFile upFile = event.getFile();
        //Transforma o FileInputStream em bytes[]; 
        this.doc.setArquivo(convertUpFileToByte(upFile));
        this.doc.setNome(upFile.getFileName());
        this.doc.setTamanho(upFile.getSize());
        this.doc.setDocStatus(DocumentoStatus.em_analise);
        //this.doc.setDocTipo(DocumentoTipo.relatorio);
        //Obtem o objeto usuario instanciado no durante o login
        Object o = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        Aluno aluno = alunoDB.getAlunoPorMatricula(o.toString());
        this.doc.setEstagio(estagioDao.getEstagioAtivoPorAluno(aluno)); // setAluno() alterado para setEstagio()
        //Estabelece o valor do atributo que auxilia na busca de documentos por matricula
        this.doc.setAlunoMatricula(aluno.getMatricula());
        
        if(doc.getArquivo() == null)
            System.out.println("Arquivo nao convertido!!!!!!!!!");
        //Caso contrário, flush no DB
        else{
            docDao.inserir(this.doc);
        }
    }   
}
