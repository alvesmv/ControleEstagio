/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.gui;

import br.edu.femass.controleestagio.dao.AlunoDao;
import br.edu.femass.controleestagio.dao.CursoDao;
import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Curso;
import java.io.Serializable;
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
    private List <String> listaDeCursos;
    private List <Curso> cursos;
    private String campoCursoNome;
    private Boolean alterando;

    @EJB
    AlunoDao alunoDao;
    @EJB
    CursoDao daoCurso = new CursoDao();

    public GuiAluno() {
    }

    public String iniciar() {
        alunos = alunoDao.getAlunos();
        listaDeCursos = new ArrayList<>();
        return "FrmLstAluno";
    }

    public String incluir() {
        aluno = new Aluno();
        alterando = false;
        
        //Retorna a lista de itens para a selecao do combobox do FrmCadAluno
        try{
            cursos = daoCurso.getCursos();
            for(Curso c : cursos) listaDeCursos.add(c.getNomeCurso());            
        }catch(Exception e){listaDeCursos = null;}
        
        return "FrmCadAluno";
    }

    public String alterar() {
        
        alterando = true;
        
        //Retorna a lista de itens para a selecao do combobox do FrmCadAluno
        try{
            cursos = daoCurso.getCursos();
            for(Curso c : cursos) listaDeCursos.add(c.getNomeCurso());           
        }catch(Exception e){listaDeCursos = null;}
        
        return "FrmCadAluno";
    }
    
    public String voltarMenuPrincipal(){
        return "index";
    }
    
    public String voltar(){
        return "FrmLstAluno";
    }
    public String excluir(Aluno a) {
        alunoDao.excluir(a);
        alunos = alunoDao.getAlunos();
        return null;
    }

    public String gravar() {

        aluno.setCurso(daoCurso.getCursoByString(this.getCampoCursoNome()));
               
        if (alterando) {
            alunoDao.alterar(aluno);
        } else {
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
    
    public List<String> getListaDeCursos() {
        return listaDeCursos;
    }

    public void setListaDeCursos(List<String> listaDeCursos) {
        this.listaDeCursos = listaDeCursos;
    }

    public String getCampoCursoNome() {
        return campoCursoNome;
    }

    public void setCampoCursoNome(String campoCursoNome) {
        this.campoCursoNome = campoCursoNome;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
    
        public Curso getCursoSelecionado() {
        for (Curso c: cursos) {
            if (c.getNomeCurso().equals(campoCursoNome)) {
                return c;
            }
        }
        return null;
    }
    
    
}
