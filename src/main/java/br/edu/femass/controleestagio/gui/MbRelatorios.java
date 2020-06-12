
package br.edu.femass.controleestagio.gui;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.DocumentoDao;
import br.edu.femass.controleestagio.dao.EstagioDao;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Documento;
import br.edu.femass.controleestagio.model.Estagio;
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
    private List <Estagio> listaEstagioI;
    private List <Estagio> listaEstagioII;
    private List <Estagio> listaEstagiosConcluidos;
    private List <Estagio> listaEstagioNaoObrigatorio;
    private List <Estagio> listaEstagiosAtivosPorAluno;
    private Documento doc;
    private String login;

    private Estagio estagio;
    private StreamedContent conteudoTransmitido;
    
    
    @EJB
    DocumentoDao docDao = new DocumentoDao();
    //Alterar Aluno para estágio
    @EJB
    AlunoDao alunoDao = new AlunoDao();
    
    @EJB
    EstagioDao estagioDao = new EstagioDao();
    
    public MbRelatorios() {
    }
    
    public String iniciar() {
        //Obtem o objeto usuario instanciado no durante o login
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        login = user.getLogin();
        
        switch (user.getTipoDeAcesso()) {
            case aluno:
                /*Possivelmente será alterado após trocar a relação Documento-Aluno por Documento-Estagio*/
                listaEstagiosAtivosPorAluno = estagioDao.getEstagiosAtivosPorAluno(login);
                
                return "FrmEstagiosDoAluno";
            case orientador:
                alunoList = alunoDao.getAlunosByOrientador(login); // excluir esta linha
                listaEstagioI = estagioDao.getListEstagioIByOrientador(login);
                listaEstagioII = estagioDao.getListEstagioIIByOrientador(login);
                listaEstagiosConcluidos = estagioDao.getListEstagiosConcluidosByOrientador(login);
                break;
            case coordenador:
            case admin:
                alunoList = alunoDao.getAlunosComEstagio();
                listaEstagioI = estagioDao.getListEstagioI();
                listaEstagioII = estagioDao.getListEstagioII();
                listaEstagiosConcluidos = estagioDao.getListEstagiosConcluidos();
                break;
        }
        
        return "FrmAbaAvaliarRelatorio";
    }
   
    public String acessarFichaDeRelatorios(Estagio e){
        this.estagio = e;
        docList = docDao.getListaDocumentosByEstagio(e);
        return "FrmFichaRelatorios";
    }
    
    public String enviarRelatorios(Estagio e){
        docList = docDao.getListaDocumentosByEstagio(e);
        return "FrmEnviarRelatorio";
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
        System.out.println("Buceta");
        docDao.alterar(doc);
        System.out.println("Cabeluda");
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

    public List<Estagio> getListaEstagioI() {
        return listaEstagioI;
    }

    public void setListaEstagioI(List<Estagio> listaEstagioI) {
        this.listaEstagioI = listaEstagioI;
    }

    public List<Estagio> getListaEstagioII() {
        return listaEstagioII;
    }

    public void setListaEstagioII(List<Estagio> listaEstagioII) {
        this.listaEstagioII = listaEstagioII;
    }

    public List<Estagio> getListaEstagiosConcluidos() {
        return listaEstagiosConcluidos;
    }

    public void setListaEstagiosConcluidos(List<Estagio> listaEstagiosConcluidos) {
        this.listaEstagiosConcluidos = listaEstagiosConcluidos;
    }

    public List<Estagio> getListaEstagioNaoObrigatorio() {
        return listaEstagioNaoObrigatorio;
    }

    public void setListaEstagioNaoObrigatorio(List<Estagio> listaEstagioNaoObrigatorio) {
        this.listaEstagioNaoObrigatorio = listaEstagioNaoObrigatorio;
    }

    public List<Estagio> getListaEstagiosAtivosPorAluno() {
        return listaEstagiosAtivosPorAluno;
    }

    public void setListaEstagiosAtivosPorAluno(List<Estagio> listaEstagiosAtivosPorAluno) {
        this.listaEstagiosAtivosPorAluno = listaEstagiosAtivosPorAluno;
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

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }

    public StreamedContent getConteudoTransmitido() {
        return conteudoTransmitido;
    }

    public void setConteudoTransmitido(StreamedContent conteudoTransmitido) {
        this.conteudoTransmitido = conteudoTransmitido;
    }
}
