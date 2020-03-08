/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.dao;

import br.edu.femass.controleestagio.model.Documento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Souza
 */
@Stateless
public class DocumentoDao {
    @PersistenceContext
    EntityManager em;
    
    public void inserir(Documento doc){
        em.persist(doc);
    }
    public void alterar(Documento doc){
        em.merge(doc);
    }
    public void excluir(Documento doc){
        em.remove(em.merge(doc));
    }
    
    public List<Documento> getDocumentos() {
        Query q = em.createQuery("select d from Documento d order by d.nome");
        return q.getResultList();
    }
    public List<String> getListaDocumentos() {
        Query q = em.createQuery("select d.nome from Documento d order by d.nome");
        return q.getResultList();
    }

    public List<Documento> getDocumentos(String nome) {
        Query q = em.createQuery("select d from Documento d where d.nome = :n");
        q.setParameter("n", nome);
        return q.getResultList();
    }
    
    public Documento getDocumentoByString(String nome) {
        Query q = em.createQuery("select d from Documento d where d.nome = :n");
        q.setParameter("n", nome);
        return (Documento) q.getSingleResult();
    }
    
}
