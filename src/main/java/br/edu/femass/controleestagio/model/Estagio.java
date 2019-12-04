/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author dumas
 */
@Entity
public class Estagio implements Serializable{

     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private boolean categoriaObrigatoria;
    private Aluno alunoEstagio;
    private Orientador orientadorEstagio;
    private Empresa empresaEstagio;
    private Date dataInicioEstagio;
    private Date datafimEstagio;
    private Status statusDoEstagio;
    private String nomeSupervisor;
    private String contatoSupervisor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCategoriaObrigatoria() {
        return categoriaObrigatoria;
    }

    public void setCategoriaObrigatoria(boolean categoriaObrigatoria) {
        this.categoriaObrigatoria = categoriaObrigatoria;
    }
    
    public Aluno getAlunoEstagio() {
        return alunoEstagio;
    }

    public void setAlunoEstagio(Aluno alunoEstagio) {
        this.alunoEstagio = alunoEstagio;
    }

    public Orientador getOrientadorEstagio() {
        return orientadorEstagio;
    }

    public void setOrientadorEstagio(Orientador orientadorEstagio) {
        this.orientadorEstagio = orientadorEstagio;
    }

    public Empresa getEmpresaEstagio() {
        return empresaEstagio;
    }

    public void setEmpresaEstagio(Empresa empresaEstagio) {
        this.empresaEstagio = empresaEstagio;
    }    
    
    public Date getDataInicioEstagio() {
        return dataInicioEstagio;
    }

    public void setDataInicioEstagio(Date dataInicioEstagio) {
        this.dataInicioEstagio = dataInicioEstagio;
    }

    public Date getDatafimEstagio() {
        return datafimEstagio;
    }

    public void setDatafimEstagio(Date datafimEstagio) {
        this.datafimEstagio = datafimEstagio;
    }
    
    public Status getStatusDoEstagio() {
        return statusDoEstagio;
    }

    public void setStatusDoEstagio(Status statusDoEstagio) {
        this.statusDoEstagio = statusDoEstagio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Estagio)) {
            return false;
        }
        Estagio other = (Estagio) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id;
    }

    /**
     * @return the nomeSupervisor
     */
    public String getNomeSupervisor() {
        return nomeSupervisor;
    }

    /**
     * @param nomeSupervisor the nomeSupervisor to set
     */
    public void setNomeSupervisor(String nomeSupervisor) {
        this.nomeSupervisor = nomeSupervisor;
    }

    /**
     * @return the contatoSupervisor
     */
    public String getContatoSupervisor() {
        return contatoSupervisor;
    }

    /**
     * @param contatoSupervisor the contatoSupervisor to set
     */
    public void setContatoSupervisor(String contatoSupervisor) {
        this.contatoSupervisor = contatoSupervisor;
    }

    
}
