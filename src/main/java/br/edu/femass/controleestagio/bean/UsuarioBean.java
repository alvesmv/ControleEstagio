package br.edu.femass.controleestagio.bean;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.OrientadorDao;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Orientador;
import br.edu.femass.controleestagio.model.Usuario;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author dumas
 */
@Named
@SessionScoped
public class UsuarioBean implements Serializable {

    private List<Aluno> alunos;
    private List<Orientador> orientadores;
    private Usuario usuario;
    private Aluno aluno;
    private Orientador orientador;
    private String nome;
    private String login;
    /*
    Valore para tipo gravar:
        true - aluno
        false - orientador
    */
    private Boolean tipoGravar;

    @EJB
    AlunoDao alunoDao = new AlunoDao();
    @EJB
    OrientadorDao orientadorDao = new OrientadorDao();

    public UsuarioBean() {

    }

    public String iniciar() {
        alunos = alunoDao.getAlunos();
        orientadores = orientadorDao.getOrientadores();
        return "FrmLstUsuario";
    }

    public String alterarSenha(Object objSelecionado) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        usuario = new Usuario();
        nome = new String();
        login = new String();

        if (objSelecionado instanceof Aluno) {
            aluno = new Aluno();
            aluno = (Aluno) objSelecionado;
            usuario = aluno.getUsuario();
            nome = aluno.getNome();
            login = aluno.getUsuario().getLogin();
            tipoGravar = true;
        } else if (objSelecionado instanceof Orientador) {
            orientador = new Orientador();
            orientador = (Orientador) objSelecionado;
            usuario = orientador.getUsuario();
            nome = orientador.getNomeOrientador();
            login = orientador.getUsuario().getLogin();
            tipoGravar = false;
        }
        usuario.setSenha("");
        return "FrmAlterarSenha";
    }

    public String voltar() {
        return "FrmLstUsuario";
    }

    public String gravar() {
        if (tipoGravar) {
            aluno.setUsuario(usuario);
            alunoDao.alterar(aluno);
        } else {
            orientador.setUsuario(usuario);
            orientadorDao.alterar(orientador);
        }
        return iniciar();
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Orientador> getOrientadores() {
        return orientadores;
    }

    public void setOrientadores(List<Orientador> orientadores) {
        this.orientadores = orientadores;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
