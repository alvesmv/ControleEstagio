/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author dumas
 */
@Entity
public class Orientador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrientador;
    private String cpf;
    private String nomeOrientador;
    private Usuario usuario;

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nomeOrientador
     */
    public String getNomeOrientador() {
        return nomeOrientador;
    }

    /**
     * @param nomeOrientador the nomeOrientador to set
     */
    public void setNomeOrientador(String nomeOrientador) {
        this.nomeOrientador = nomeOrientador;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return nomeOrientador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrientador != null ? idOrientador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idAluno fields are not set
        if (!(object instanceof Aluno)) {
            return false;
        }
        Orientador other = (Orientador) object;
        return this.getCpf().equalsIgnoreCase(other.getCpf());
    }

}
