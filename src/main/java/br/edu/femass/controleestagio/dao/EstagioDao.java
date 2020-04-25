/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.dao;

import br.edu.femass.controleestagio.model.Estagio;
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

    /*
    Método que retorna o número de alunos por disciplina
     */
    public Long getAlunoPorDisciplina() {
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e group by e.disciplina");
        return (Long) q.getSingleResult();
    }
}
