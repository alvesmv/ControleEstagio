
package br.edu.femass.controleestagio.gui;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.DocumentoDao;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Documento;
import br.edu.femass.controleestagio.model.Usuario;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author gxrj
 */
@Named(value = "mbRelatorios")
@SessionScoped

public class MbRelatorios implements Serializable{

    private List<Documento> docList;
    private List<Aluno> alunoList;
    private Documento doc;
    private String login;
    private Aluno aluno;
    private StreamedContent conteudoTransmitido;
    
    
    @EJB
    DocumentoDao docDao = new DocumentoDao();
    @EJB
    AlunoDao alunoDao = new AlunoDao();
    
    public MbRelatorios() {
    }
    
    public String iniciar() {
        //Obtem o objeto usuario instanciado no durante o login
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        login = user.getLogin();
        
        switch (user.getTipoDeAcesso()) {
            case aluno:
                docList = docDao.getListaDocumentosByMatricula(login); 
                return "FrmAbaEnviarRelatorio";
            case orientador:
                alunoList = alunoDao.getAlunosByOrientador(login);
                break;
            default:
                alunoList = alunoDao.getAlunosComEstagio();
                break;
        }
        
        return "FrmAbaAvaliarRelatorio";
    }
    
    public String acessarFichaDeRelatorios(Aluno a){
        this.aluno = a;
        docList = docDao.getListaDocumentosByMatricula(a.getMatricula());
        return "FrmFichaRelatorios";
    }
    
    public String voltarParaListaDeAlunos(){
        return "FrmAbaAvaliarRelatorio";
    }
    
    public String voltarParaFichaRelatorios(){
        return "FrmFichaRelatorios";
    }
    
    public String excluir(Documento d) {
        docDao.excluir(d);
        docList = docDao.getListaDocumentosByMatricula(login);
        return null;
    }
    
    public String salvarAlteracoes(){
        docDao.alterar(doc);
        return voltarParaFichaRelatorios();
    }
   
    public String editAvaliacao(Documento d){
        this.doc = d;
        this.conteudoTransmitido = new DefaultStreamedContent(new ByteArrayInputStream(d.getArquivo()),"application/pdf");
        return "FrmEditRelatorio";
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public StreamedContent getConteudoTransmitido() {
        return conteudoTransmitido;
    }

    public void setConteudoTransmitido(StreamedContent conteudoTransmitido) {
        this.conteudoTransmitido = conteudoTransmitido;
    }
}
