package br.edu.femass.controleestagio.bean;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.CursoDao;
import br.edu.femass.controleestagio.dao.UsuarioDao;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Curso;
import br.edu.femass.controleestagio.enums.TipoDeAcesso;
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
public class MbAluno implements Serializable {

    private List<Aluno> alunos;
    private Aluno aluno;
    private Boolean alterando;
    private String campoCursoNome;
    private Usuario usuario;

    @EJB
    AlunoDao alunoDao;
    @EJB
    CursoDao daoCurso = new CursoDao();
    @EJB
    UsuarioDao usuarioDao = new UsuarioDao();

    public MbAluno() {
    }

    public String iniciar() {
        alunos = alunoDao.getAlunos();
        return "FrmLstAluno";
    }

    public String incluir() {
        aluno = new Aluno();
        alterando = false;
        usuario = new Usuario();
        campoCursoNome = new String();
        return "FrmCadAluno";
    }

    public String alterar(Aluno a) {
        aluno = a;
        alterando = true;
        this.setCampoCursoNome(aluno.getCurso().getNomeCurso());
        return "FrmCadAluno";
    }

    public String voltarMenuPrincipal() {
        return "/index";
    }

    public String voltar() {
        return "FrmLstAluno";
    }

    public String excluir(Aluno a) {
        alunoDao.excluir(a);
        alunos = alunoDao.getAlunos();
        return null;
    }

    public String gravar() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        aluno.setCurso(getCursoSelecionado());
        if (alterando) {
            alunoDao.alterar(aluno);
        } else {
            usuario.setLogin(aluno.getMatricula());
            usuario.setSenha(aluno.getMatricula());
            usuario.setTipoDeAcesso(TipoDeAcesso.aluno);
            usuarioDao.inserir(usuario);
            aluno.setUsuario(usuario);
            alunoDao.inserir(aluno);
        }
        return iniciar();
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    /**
     * @param alunos the alunos to set
     */
    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getCampoCursoNome() {
        return campoCursoNome;
    }

    /**
     * @param campoCursoNome the campoCursoNome to set
     */
    public void setCampoCursoNome(String campoCursoNome) {
        this.campoCursoNome = campoCursoNome;
    }

    public List<Curso> getListaDeCursos() {
        List<Curso> cursos = daoCurso.getCursos();
        return cursos;
    }

    private Curso getCursoSelecionado() {
        Curso c = daoCurso.getCursoByString(campoCursoNome);
        return c;
    }
}
