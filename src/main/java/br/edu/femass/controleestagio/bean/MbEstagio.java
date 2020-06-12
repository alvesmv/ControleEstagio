package br.edu.femass.controleestagio.bean;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.EmpresaDao;
import br.edu.femass.controleestagio.dao.EstagioDao;
import br.edu.femass.controleestagio.dao.OrientadorDao;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Empresa;
import br.edu.femass.controleestagio.model.Estagio;
import br.edu.femass.controleestagio.model.Orientador;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author souza
 */
@Named(value = "guiEstagio")
@SessionScoped
public class MbEstagio implements Serializable {

    private List<Estagio> estagios;
    private Estagio estagio;
    private Boolean alterando;
    private String campoNomeOrientador;
    private String campoNomeAluno;
    private String campoNomeEmpresa;

    @EJB
    EstagioDao daoEstagio;
    @EJB
    AlunoDao alunoDao = new AlunoDao();
    @EJB
    OrientadorDao orientadorDao = new OrientadorDao();
    @EJB
    EmpresaDao empresaDao = new EmpresaDao();

    public MbEstagio() {
    }

    public String iniciar() {
        estagios = daoEstagio.getEstagios();
        return "FrmLstEstagio";
    }

    public String incluir() {
        estagio = new Estagio();
        alterando = false;
        campoNomeOrientador = new String();
        campoNomeAluno = new String();
        campoNomeEmpresa = new String();
        return "FrmCadEstagio";
    }

    public String alterar(Estagio e) {
        estagio = e;
        alterando = true;
        campoNomeOrientador = estagio.getOrientadorEstagio().getNomeOrientador();
        campoNomeAluno = estagio.getAlunoEstagio().getNome();
        campoNomeEmpresa = estagio.getEmpresaEstagio().getNomeEmpresa();
        return "FrmCadEstagio";
    }

    
    public String excluir(Estagio e) {
        daoEstagio.excluir(e);
        estagios = daoEstagio.getEstagios();
        return null;
    }

    public String voltar() {
        return "FrmLstEstagio";
    }

    public String gravar() {

        estagio.setAlunoEstagio(getAlunoSelecionado());
        estagio.setEmpresaEstagio(getEmpresaSelecionada());
        estagio.setOrientadorEstagio(getOrientadorSelecionado());

        if (alterando) {
            daoEstagio.alterar(estagio);
        } else {
            daoEstagio.inserir(estagio);
        }

        return iniciar();
    }

    public List<Estagio> getEstagios() {
        return estagios;
    }

    /**
     * @param estagios the estagios to set
     */
    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }

    public Estagio getEstagio() {
        return estagio;
    }

    /**
     * @param estagio the estagio to set
     */
    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }

    public List<Orientador> getListaDeOrientadores() {
        List<Orientador> orientadores = orientadorDao.getOrientadores();
        
        return orientadores;
    }

    public List<Aluno> getListaDeAlunos() {
        List<Aluno> alunos = alunoDao.getAlunos();
       
        return alunos; 
    }

    public List<Empresa> getListaDeEmpresas() {
        List<Empresa> empresas = empresaDao.getEmpresas();
       
        return empresas;
    }

    public String getCampoNomeOrientador() {
        return campoNomeOrientador;
    }

    /**
     * @param campoNomeOrientador the campoNomeOrientador to set
     */
    public void setCampoNomeOrientador(String campoNomeOrientador) {
        this.campoNomeOrientador = campoNomeOrientador;
    }

    public String getCampoNomeAluno() {
        return campoNomeAluno;
    }

    public void setCampoNomeAluno(String campoNomeAluno) {
        this.campoNomeAluno = campoNomeAluno;
    }

    public String getCampoNomeEmpresa() {
        return campoNomeEmpresa;
    }

    /**
     * @param campoNomeEmpresa the campoNomeEmpresa to set
     */
    public void setCampoNomeEmpresa(String campoNomeEmpresa) {
        this.campoNomeEmpresa = campoNomeEmpresa;
    }

    private Empresa getEmpresaSelecionada() {
        Empresa e = empresaDao.getEmpresa(campoNomeEmpresa);
            
        return e;    
    }

    private Aluno getAlunoSelecionado() {
        Aluno a = alunoDao.getAlunoByString(campoNomeAluno);
         
        return a;
    }

    private Orientador getOrientadorSelecionado() {
        Orientador o = orientadorDao.getOrientadorByName(campoNomeOrientador);
        
        return o;
    }
}
