/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.dao;

import br.edu.femass.controleestagio.model.Curso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dumas
 */
@Stateless
public class CursoDao {

    @PersistenceContext
    EntityManager em;

    public void inserir(Curso curso) {
        em.persist(curso);
    }

    public void alterar(Curso curso) {
        em.merge(curso);
    }

    public void excluir(Curso curso) {
        em.remove(em.merge(curso));
    }

    public List<Curso> getCursos() {
        Query q = em.createQuery("select c from Curso c order by c.nomeCurso");
        return q.getResultList();
    }

    public List<Curso> getCursos(String nome) {
        Query q = em.createQuery("select c from Curso c where c.nomeCurso = :n");
        q.setParameter("n", nome);
        return q.getResultList();
    }
   
    public Curso getCursoByString(String nome) {
        Query q = em.createQuery("select c from Curso c where c.nomeCurso = :n");
        q.setParameter("n", nome);
        return (Curso) q.getSingleResult();
    }
}
