package br.edu.femass.controleestagio.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Usuario usuario;
    private String cpfLogin;

    /* Inicio dos Getters and Setters */
    
    /**
     * @return the idOrientador
     */
    public Long getIdOrientador() {
        return idOrientador;
    }

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

    /**
     * @return the cpfLogin
     */
    public String getCpfLogin() {
        return cpfLogin;
    }

    /**
     * @param cpfLogin the cpfLogin to set
     */
    public void setCpfLogin(String cpfLogin) {
        this.cpfLogin = cpfLogin;
    }

    /* Final dos Getters and Setters */
    
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

    /*
    O Método remove o limpaCpf tem como objetivo limpara a mascara do CPF removendo pontos e hifen
    */
    
    public void limpaCpf(){
        this.cpfLogin = this.cpf;
        //"[^0-9]+" remove qualquer caractere que não esteja entre 0 - 9
        this.cpfLogin = this.cpfLogin.replaceAll("[^0-9]+", "");
    }
}
