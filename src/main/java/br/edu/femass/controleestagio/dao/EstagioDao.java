/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.dao;

import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Estagio;
import br.edu.femass.controleestagio.model.Status;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author souza
 */
@Stateless
public class EstagioDao {

    @PersistenceContext
    EntityManager em;

    public void inserir(Estagio estagio) {
        em.persist(estagio);
    }

    public void alterar(Estagio estagio) {
        em.merge(estagio);
    }

    public void excluir(Estagio estagio) {
        em.remove(em.merge(estagio));
    }

    public List<Estagio> getEstagios() {
        Query q = em.createQuery("select e from Estagio e order by e.idEstagio");
        return q.getResultList();
    }

    public List<Estagio> getEstagios(String id) {
        Query q = em.createQuery("select e from Estagio e where e.idEstagio = :i");
        q.setParameter("i", id);
        return q.getResultList();
    }
    
    public Estagio getEstagioAtivoPorAluno(Aluno aluno){
        Query q = em.createQuery("select e from Estagio e where e.alunoEstagio = :a and e.statusDoEstagio = :c");
        q.setParameter("c", Status.Cursando);
        q.setParameter("a", aluno);
        return (Estagio) q.getSingleResult();
    }
    /*
    Método que retorna o número de alunos estagiando
    */
    public Long getAlunosEstagiando(){
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e");
        return (Long) q.getSingleResult();
    }
    
    /*
    Método que retorna o número de alunos por disciplina que estão estagiando
     */
    public List<Long> getAlunoPorDisciplina() {
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e group by e.disciplina");
        return q.getResultList();
    }
    
    /*
    Método que retorna o número de alunos por curso que estão estagiando
    */
    public List<Long> getAlunoPorCurso(){
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e group by e.alunoEstagio.curso.idCurso order by e.alunoEstagio.curso.idCurso");
        return q.getResultList();
    }
    
    /*
    Método que retorna o número de alunos por empresa
    */
    public List<Long> getAlunosPorEmpresa(){
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e group by e.empresaEstagio.idEmpresa order by e.empresaEstagio.idEmpresa");
        return q.getResultList();
    }
}
