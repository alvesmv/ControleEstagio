package br.edu.femass.controleestagio.bean;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.OrientadorDao;
import br.edu.femass.controleestagio.enums.TipoDeAcesso;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Orientador;
import br.edu.femass.controleestagio.model.Usuario;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author dumas
 */
@Named
@SessionScoped
public class MbUsuario implements Serializable {

    private List<Aluno> alunos;
    private List<Orientador> orientadores;
    private Usuario usuario;
    private Aluno aluno;
    private Orientador orientador;
    private String nome;
    private String login;
    private TipoDeAcesso tipoGravar;
    /*
    true - painel do admin
    false - barra de menu
     */
    private Boolean origem;

    @EJB
    AlunoDao alunoDao = new AlunoDao();
    @EJB
    OrientadorDao orientadorDao = new OrientadorDao();

    public MbUsuario() {

    }

    public String iniciar() {
        alunos = alunoDao.getAlunos();
        orientadores = orientadorDao.getOrientadores();
        return "FrmLstUsuario";
    }

    /*
    Método para alterar senha pelo painel do coordenador ou admin
     */
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
            tipoGravar = TipoDeAcesso.aluno;
        } else if (objSelecionado instanceof Orientador) {
            orientador = new Orientador();
            orientador = (Orientador) objSelecionado;
            usuario = orientador.getUsuario();
            nome = orientador.getNomeOrientador();
            login = orientador.getUsuario().getLogin();
            switch (usuario.getTipoDeAcesso()) {
                case orientador:
                    tipoGravar = TipoDeAcesso.orientador;
                    break;
                case coordenador:
                    tipoGravar = TipoDeAcesso.coordenador;
                    break;
            }
        }
        origem = true;
        usuario.setSenha("");
        return "FrmAlterarSenha";
    }

    /*
    Método para alterar senha pela barra de menu
     */
    public String alteraSenhaMenu() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        switch (usuario.getTipoDeAcesso()) {
            case aluno:
                aluno = alunoDao.getAluno(usuario.getIdUsuario());
                nome = aluno.getNome();
                login = aluno.getUsuario().getLogin();
                tipoGravar = TipoDeAcesso.aluno;
                break;
            case orientador:
                orientador = orientadorDao.getOrientador(usuario.getIdUsuario());
                nome = orientador.getNomeOrientador();
                login = orientador.getUsuario().getLogin();
                tipoGravar = TipoDeAcesso.orientador;
                break;
            case coordenador:
                orientador = orientadorDao.getOrientador(usuario.getIdUsuario());
                nome = orientador.getNomeOrientador();
                login = orientador.getUsuario().getLogin();
                tipoGravar = TipoDeAcesso.coordenador;
                break;
            case admin:
                nome = usuario.getNomeDeExibicao();
                login = usuario.getLogin();
                tipoGravar = TipoDeAcesso.admin;
                break;
        }
        origem = false;
        usuario.setSenha("");
        return "FrmAlterarSenha";
    }

    public String voltar() {
        return "FrmLstUsuario";
    }

    public String gravar() {
        switch (tipoGravar) {
            case aluno:
                aluno.setUsuario(usuario);
                alunoDao.alterar(aluno);
                break;
            case orientador:
                orientador.setUsuario(usuario);
                orientadorDao.alterar(orientador);
                break;
            case coordenador:
                orientador.setUsuario(usuario);
                orientadorDao.alterar(orientador);
                break;
            case admin:
                break;
        }
        if (origem) {
            return iniciar();
        } else {
            return home();
        }
    }

    /*
    Método que leva o usuário para sua página inicial
     */
    private String home() {

        switch (usuario.getTipoDeAcesso()) {
            case aluno:
                return "FrmAreaDoAluno";
            case orientador:
                return "FrmAreaDoOrientador";
            case coordenador:
                return "FrmAreaDoCoordenador";
            case admin:
                return "FrmAreaDoCoordenador";
        }
        return null;
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
