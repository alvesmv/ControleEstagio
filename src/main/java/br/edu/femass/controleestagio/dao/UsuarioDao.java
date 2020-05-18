/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.dao;

import br.edu.femass.controleestagio.model.Usuario;
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
public class UsuarioDao {
    
    @PersistenceContext
    EntityManager em;

    public void inserir(Usuario usuario) {
        em.persist(usuario);
    }

    public void alterar(Usuario usuario) {
        em.merge(usuario);
    }

    public void excluir(Usuario usuario) {
        em.remove(em.merge(usuario));
    }

    public List<Usuario> getUsuarios() {
        Query q = em.createQuery("select u from Usuario u order by u.login");
        return q.getResultList();
    }

    public List<Usuario> getUsuarios(String nome) {
        Query q = em.createQuery("select u from Usuario u where u.login = :n");
        q.setParameter("n", nome);
        return q.getResultList();
    }
    
    public Usuario getUsuario(String login) {
        Query q = em.createQuery("select u from Usuario u where u.login = :l");
        q.setParameter("l", login);
        return (Usuario) q.getSingleResult();
    }
    
}
