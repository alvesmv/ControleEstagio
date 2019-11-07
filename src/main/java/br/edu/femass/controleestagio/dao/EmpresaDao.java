/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.dao;

import br.edu.femass.controleestagio.model.Empresa;
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
public class EmpresaDao {

    @PersistenceContext
    EntityManager em;

    public void inserir(Empresa empresa) {
        em.persist(empresa);
    }

    public void alterar(Empresa empresa) {
        em.merge(empresa);
    }

    public void excluir(Empresa empresa) {
        em.remove(em.merge(empresa));
    }

    public List<Empresa> getEmpresas() {
        Query q = em.createQuery("select e from Empresa e order by e.nomeEmpresa");
        return q.getResultList();
    }

    public List<Empresa> getEmpresas(String nome) {
        Query q = em.createQuery("select e from Empresa e where e.nomeEmpresa = :n");
        q.setParameter("n", nome);
        return q.getResultList();
    }

    public List<String> getListaEmpresas() {
        Query q = em.createQuery("select e from Empresa e order by e.nomeEmpresa");
        return q.getResultList();
    }
}
