package br.edu.femass.controleestagio.gui;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.EmpresaDao;
import br.edu.femass.controleestagio.dao.EstagioDao;
import br.edu.femass.controleestagio.dao.OrientadorDao;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Empresa;
import br.edu.femass.controleestagio.model.Estagio;
import br.edu.femass.controleestagio.model.Orientador;
import br.edu.femass.controleestagio.model.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
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
    private Orientador orientador;
    private Aluno aluno;
    private Empresa empresa;
    private String campoNomeOrientador, campoNomeAluno, campoNomeEmpresa;

    @EJB
    AlunoDao alunoDao = new AlunoDao();
    @EJB
    OrientadorDao orientadorDao = new OrientadorDao();
    @EJB
    EstagioDao daoEstagio = new EstagioDao();
    @EJB
    EmpresaDao empresaDao = new EmpresaDao();

    public GuiEstagio() {
    }

    public String iniciar() {
        estagios = daoEstagio.getEstagios();
        listaDeOrientadores = new ArrayList<>();
        listaDeAlunos = new ArrayList<>();
        listaDeEmpresas = new ArrayList<>();
       
        return "FrmLstEstagio";
    }

    public String incluir() {
        estagio = new Estagio();
        alterando = false;
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

        return "FrmCadEstagio";
    }

    public String alterar() {
       
        alterando = true;
        return "FrmCadEstagio";
    }

    public String mostraStatus() {
        Status[] s = Status.values();
        return Arrays.toString(s);
    }

    public String excluir(Estagio e) {
        daoEstagio.excluir(e);
        estagios = daoEstagio.getEstagios();
        return null;
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

    public String getCampoNomeOrientador() {
        return campoNomeOrientador;
    }

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

    public void setCampoNomeEmpresa(String campoNomeEmpresa) {
        this.campoNomeEmpresa = campoNomeEmpresa;
    }
    public String voltarMenuPrincipal() {
        return "index";
    }

    public String voltar() {
        return "FrmLstEstagio";
    }
    public List<Estagio> getEstagios() {
        return estagios;
    }

    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }

    public Boolean getAlterando() {
        return alterando;
    }

    public void setAlterando(Boolean alterando) {
        this.alterando = alterando;
    }

    public EstagioDao getDaoEstagio() {
        return daoEstagio;
    }

    public void setDaoEstagio(EstagioDao daoEstagio) {
        this.daoEstagio = daoEstagio;
    }

    public List<Orientador> getOrientadores() {
        return orientadores;
    }

    public void setOrientadores(List<Orientador> orientadores) {
        this.orientadores = orientadores;
    }

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
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
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    
    public Empresa getEmpresaSelecionada() {
        for (Empresa e: empresas) {
            if (e.getNomeEmpresa().equals(campoNomeEmpresa)) {
                return e;
            }
        }
        return null;
    }
    
    public Aluno getAlunoSelecionado() {
        for (Aluno a: alunos) {
            if (a.getNome().equals(campoNomeAluno)) {
                return a;
            }
        }
        return null;
    }
    
    public Orientador getOrientadorSelecionado() {
        for (Orientador o: orientadores) {
            if (o.getNomeOrientador().equals(campoNomeOrientador)) {
                return o;
            }
        }
        return null;
    }
    
    
}
