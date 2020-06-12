/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.bean;

import br.edu.femass.controleestagio.dao.CursoDao;
import br.edu.femass.controleestagio.model.Curso;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author dumas
 */
@Named(value = "guiCurso")
@SessionScoped
public class MbCurso implements Serializable {

    private List<Curso> cursos;
    private Curso curso;
    private Boolean alterando;

    @EJB
    CursoDao cursoDao;

    public MbCurso() {

    }

    public String iniciar() {
        cursos = cursoDao.getCursos();
        return "FrmLstCurso";
    }

    public String incluir() {
        curso = new Curso();
        alterando = false;
        return "FrmCadCurso";
    }

    public String alterar(Curso c) {
        curso = c;
        alterando = true;
        return "FrmCadCurso";
    }

    public String excluir(Curso c) {
        cursoDao.excluir(c);
        cursos = cursoDao.getCursos();
        return null;
    }

    public String voltarMenuPrincipal() {
        return "/index";
    }

    public String voltar() {
        return "FrmLstCurso";
    }

    public String gravar() {
        if (alterando) {
            cursoDao.alterar(curso);
        } else {
            cursoDao.inserir(curso);
        }
        return iniciar();
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

    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
