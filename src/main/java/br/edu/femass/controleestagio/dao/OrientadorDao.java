/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.dao;

import br.edu.femass.controleestagio.model.Orientador;
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
public class OrientadorDao {

    @PersistenceContext
    EntityManager em;

    public void inserir(Orientador orientador) {
        em.persist(orientador);
    }

    public void alterar(Orientador orientador) {
        em.merge(orientador);
    }

    public void excluir(Orientador orientador) {
        em.remove(em.merge(orientador));
    }

    public List<Orientador> getOrientadores() {
        Query q = em.createQuery("select o from Orientador o order by o.nomeOrientador");
        return q.getResultList();
    }

    public List<Orientador> getOrientadores(String nome) {
        Query q = em.createQuery("select o from Orientador o where o.nomeOrientador = :n");
        q.setParameter("n", nome);
        return q.getResultList();
    }
    
    public Orientador getOrientadorByName(String nome) {
        Query q = em.createQuery("select o from Orientador o where o.nomeOrientador = :n");
        q.setParameter("n", nome);
        return (Orientador) q.getSingleResult();
    }
    
    public List<String> getListaOrientadores() {
        Query q = em.createQuery("select o.nomeOrientador from Orientador o order by o.nomeOrientador");
        return q.getResultList();
    }
}
