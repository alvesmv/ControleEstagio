package br.edu.femass.controleestagio.bean;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.EmpresaDao;
import br.edu.femass.controleestagio.dao.EstagioDao;
import br.edu.femass.controleestagio.dao.OrientadorDao;
import br.edu.femass.controleestagio.dao.DocumentoDao;
import br.edu.femass.controleestagio.enums.Status;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Empresa;
import br.edu.femass.controleestagio.model.Estagio;
import br.edu.femass.controleestagio.model.Orientador;
import br.edu.femass.controleestagio.model.Documento;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author souza
 */

@Named
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
    @EJB
    DocumentoDao docDao = new DocumentoDao();

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
        estagio.setStatusDoEstagio(Status.Cursando);
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
        List<Documento> docList = docDao.getListaDocumentosByEstagio(e);
        
        for(Documento doc : docList)
        {
            docDao.excluir(doc);
        }
        
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
        
        Long qtdeEstagiosAtivos = daoEstagio.getQtdeEstagiosAtivosPorMatricula(estagio.getAlunoEstagio().getMatricula());
        if(estagio.getIdEstagio() != daoEstagio.getEstagioAtivoPorAluno(estagio.getAlunoEstagio()).getIdEstagio() 
                && estagio.getStatusDoEstagio().equals(Status.Cursando))
             qtdeEstagiosAtivos++;
        //Garante que aluno esteja cursando apenas um estágio por vez
        if( qtdeEstagiosAtivos >= 2) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro","Não é possível mudar o status desse estágio pois aluno já possuí outro estágio ativo") );
            return null;
        }else{
            if (alterando) {
                daoEstagio.alterar(estagio);
            } else {
                estagio.setStatusDoEstagio(Status.Cursando);
                daoEstagio.inserir(estagio);
            }
        }
        
        return iniciar();
    }

    public String avaliarEstagio(Estagio e) {
        estagio = e;
        alterando = true;
        campoNomeOrientador = estagio.getOrientadorEstagio().getNomeOrientador();
        campoNomeAluno = estagio.getAlunoEstagio().getNome();
        campoNomeEmpresa = estagio.getEmpresaEstagio().getNomeEmpresa();
        return "FrmAvaliarEstagio";
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
