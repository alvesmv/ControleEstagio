package br.edu.femass.controleestagio.gui;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.DocumentoDao;
import br.edu.femass.controleestagio.dao.EstagioDao;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Documento;
import br.edu.femass.controleestagio.model.TipoDeAcesso;
import br.edu.femass.controleestagio.model.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;

/**
 *
 * @author gxrj
 */
@Named(value = "mbAvaliarRelatorios")
@Dependent
public class MbAvaliarRelatorios {

    private List<Aluno> alunoList;
    private List<Documento> docList;
    private Aluno aluno;
    private Documento doc;
    private String userId;
    
    @EJB
    DocumentoDao docDao = new DocumentoDao();
    @EJB
    AlunoDao alunoDao = new AlunoDao();
    @EJB
    EstagioDao estagioDao = new EstagioDao();
    
    public MbAvaliarRelatorios() {
        
    }
    
    public String iniciar() {
        //Obtem o objeto usuario instanciado no durante o login
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        userId = user.toString();
        
        if(user.getTipoDeAcesso().equals(TipoDeAcesso.orientador))
        {
            alunoList = alunoDao.getAlunosByOrientador(userId);
            
        }
        else
        {
            alunoList = alunoDao.getAlunos();    
        }
        
        
        return "FrmAbaAvaliarRelatorio";
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }

    public List<Documento> getDocList() {
        return docList;
    }

    public void setDocList(List<Documento> docList) {
        this.docList = docList;
    } 

    public Documento getDoc() {
        return doc;
    }

    public void setDoc(Documento doc) {
        this.doc = doc;
    }
    
    
}
