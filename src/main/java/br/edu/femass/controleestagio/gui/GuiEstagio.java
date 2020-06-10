package br.edu.femass.controleestagio.gui;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.EmpresaDao;
import br.edu.femass.controleestagio.dao.EstagioDao;
import br.edu.femass.controleestagio.dao.OrientadorDao;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Empresa;
import br.edu.femass.controleestagio.model.Estagio;
import br.edu.femass.controleestagio.model.Orientador;
import java.io.Serializable;
import java.util.ArrayList;
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
public class GuiEstagio implements Serializable {

    private List<Estagio> estagios;
    private Estagio estagio;
    private Boolean alterando;

    private List<Orientador> orientadores;
    private List<Aluno> alunos;
    private List<Empresa> empresas;
    private List<String> listaDeOrientadores;
    private List<String> listaDeAlunos;
    private List<String> listaDeEmpresas;
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

    public GuiEstagio() {
    }

    public String iniciar() {
        estagios = daoEstagio.getEstagios();
        return "FrmLstEstagio";
    }

    public String incluir() {
        estagio = new Estagio();
        alterando = false;
        inicializaListas();
        campoNomeOrientador = new String();
        campoNomeAluno = new String();
        campoNomeEmpresa = new String();
        return "FrmCadEstagio";
    }

    public String alterar(Estagio e) {
        estagio = e;
        alterando = true;
        inicializaListas();
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

    /**
     * @return the estagios
     */
    public List<Estagio> getEstagios() {
        return estagios;
    }

    /**
     * @param estagios the estagios to set
     */
    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }

    /**
     * @return the estagio
     */
    public Estagio getEstagio() {
        return estagio;
    }

    /**
     * @param estagio the estagio to set
     */
    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }

    /**
     * @return the orientadores
     */
    public List<Orientador> getOrientadores() {
        return orientadores;
    }

    /**
     * @param orientadores the orientadores to set
     */
    public void setOrientadores(List<Orientador> orientadores) {
        this.orientadores = orientadores;
    }

    /**
     * @return the alunos
     */
    public List<Aluno> getAlunos() {
        return alunos;
    }

    /**
     * @param alunos the alunos to set
     */
    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    /**
     * @return the empresas
     */
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    /**
     * @param empresas the empresas to set
     */
    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    /**
     * @return the listaDeOrientadores
     */
    public List<String> getListaDeOrientadores() {
        return listaDeOrientadores;
    }

    /**
     * @param listaDeOrientadores the listaDeOrientadores to set
     */
    public void setListaDeOrientadores(List<String> listaDeOrientadores) {
        this.listaDeOrientadores = listaDeOrientadores;
    }

    /**
     * @return the listaDeAlunos
     */
    public List<String> getListaDeAlunos() {
        return listaDeAlunos;
    }

    /**
     * @param listaDeAlunos the listaDeAlunos to set
     */
    public void setListaDeAlunos(List<String> listaDeAlunos) {
        this.listaDeAlunos = listaDeAlunos;
    }

    /**
     * @return the listaDeEmpresas
     */
    public List<String> getListaDeEmpresas() {
        return listaDeEmpresas;
    }

    /**
     * @param listaDeEmpresas the listaDeEmpresas to set
     */
    public void setListaDeEmpresas(List<String> listaDeEmpresas) {
        this.listaDeEmpresas = listaDeEmpresas;
    }

    /**
     * @return the campoNomeOrientador
     */
    public String getCampoNomeOrientador() {
        return campoNomeOrientador;
    }

    /**
     * @param campoNomeOrientador the campoNomeOrientador to set
     */
    public void setCampoNomeOrientador(String campoNomeOrientador) {
        this.campoNomeOrientador = campoNomeOrientador;
    }

    /**
     * @return the campoNomeAluno
     */
    public String getCampoNomeAluno() {
        return campoNomeAluno;
    }

    /**
     * @param campoNomeAluno the campoNomeAluno to set
     */
    public void setCampoNomeAluno(String campoNomeAluno) {
        this.campoNomeAluno = campoNomeAluno;
    }

    /**
     * @return the campoNomeAluno
     */
    public String getCampoNomeEmpresa() {
        return campoNomeEmpresa;
    }

    /**
     * @param campoNomeEmpresa the campoNomeEmpresa to set
     */
    public void setCampoNomeEmpresa(String campoNomeEmpresa) {
        this.campoNomeEmpresa = campoNomeEmpresa;
    }

    private void inicializaListas() {
        listaDeOrientadores = new ArrayList<>();
        listaDeAlunos = new ArrayList<>();
        listaDeEmpresas = new ArrayList<>();
        //Retorna a lista de itens para a selecao do combobox do FrmCadEstagio
        try {
            orientadores = orientadorDao.getOrientadores();
            listaDeOrientadores = orientadorDao.getListaOrientadores();
        } catch (Exception e) {
            listaDeOrientadores = null;
        }
        try {
            alunos = alunoDao.getAlunos();
            listaDeAlunos = alunoDao.getListaAlunos();
        } catch (Exception e) {
            listaDeAlunos = null;
        }
        try {
            empresas = empresaDao.getEmpresas();
            listaDeEmpresas = empresaDao.getListaEmpresas();
        } catch (Exception e) {
            listaDeEmpresas = null;
        }
    }

    private Empresa getEmpresaSelecionada() {
        for (Empresa e : empresas) {
            if (e.getNomeEmpresa().equals(campoNomeEmpresa)) {
                return e;
            }
        }
        return null;
    }

    private Aluno getAlunoSelecionado() {
        for (Aluno a : alunos) {
            if (a.getNome().equals(campoNomeAluno)) {
                return a;
            }
        }
        return null;
    }

    private Orientador getOrientadorSelecionado() {
        for (Orientador o : orientadores) {
            if (o.getNomeOrientador().equals(campoNomeOrientador)) {
                return o;
            }
        }
        return null;
    }

}
