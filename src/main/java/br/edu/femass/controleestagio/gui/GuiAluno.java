/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.gui;

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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author dumas
 */
@Named(value = "guiAluno")
@SessionScoped
public class GuiAluno implements Serializable {
    
    private List<Aluno> alunos;
    private Aluno aluno;
    private Boolean alterando;
    
    private List<String> listaDeCursos;
    private List<Curso> cursos;
    private String campoCursoNome;
    private Usuario usuario;
    
    @EJB
    AlunoDao alunoDao;
    @EJB
    CursoDao daoCurso = new CursoDao();
    @EJB
    UsuarioDao usuarioDao = new UsuarioDao();
    
    public GuiAluno() {
    }
    
    public String iniciar() {
        alunos = alunoDao.getAlunos();
        return "FrmLstAluno";
    }
    
    public String incluir() {
        aluno = new Aluno();
        alterando = false;
        inicializaListaDeCurso();
        usuario = new Usuario();
        campoCursoNome = new String();
        return "FrmCadAluno";
    }
    
    public String alterar(Aluno a) {
        aluno = a;
        alterando = true;
        inicializaListaDeCurso();
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
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the listaDeCursos
     */
    public List<String> getListaDeCursos() {
        return listaDeCursos;
    }

    /**
     * @param listaDeCursos the listaDeCursos to set
     */
    public void setListaDeCursos(List<String> listaDeCursos) {
        this.listaDeCursos = listaDeCursos;
    }

    /**
     * @return the campoCursoNome
     */
    public String getCampoCursoNome() {
        return campoCursoNome;
    }

    /**
     * @param campoCursoNome the campoCursoNome to set
     */
    public void setCampoCursoNome(String campoCursoNome) {
        this.campoCursoNome = campoCursoNome;
    }

    /**
     * @return the cursos
     */
    public List<Curso> getCursos() {
        return cursos;
    }

    /**
     * @param cursos the cursos to set
     */
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
    
    private void inicializaListaDeCurso() {
        listaDeCursos = new ArrayList<>();
        //Retorna a lista de itens para a selecao do combobox do FrmCadAluno
        try {
            cursos = daoCurso.getCursos();
            for (Curso c : cursos) {
                listaDeCursos.add(c.getNomeCurso());
            }
        } catch (Exception e) {
            listaDeCursos = null;
        }
    }
    
    private Curso getCursoSelecionado() {
        for (Curso c : cursos) {
            if (c.getNomeCurso().equals(campoCursoNome)) {
                return c;
            }
        }
        return null;
    }
    
}
