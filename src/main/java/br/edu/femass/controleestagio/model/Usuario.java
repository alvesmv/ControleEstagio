/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.model;

import br.edu.femass.controleestagio.enums.TipoDeAcesso;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author dumas
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String login;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoDeAcesso tipoDeAcesso;

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public void setSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.senha = cifra(senha);
    }

    /**
     * @return the tipoDeAcesso
     */
    public TipoDeAcesso getTipoDeAcesso() {
        return tipoDeAcesso;
    }

    /**
     * @param tipoDeAcesso the tipoDeAcesso to set
     */
    public void setTipoDeAcesso(TipoDeAcesso tipoDeAcesso) {
        this.tipoDeAcesso = tipoDeAcesso;
    }

    private String cifra(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String senhaHex;

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        senhaHex = hexString.toString();
        return senhaHex;
    }

    @Override
    public String toString() {
        return this.login;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.login);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Empresa)) {
            return false;
        }
        Usuario other = (Usuario) object;

        return this.getLogin().equals(other.getLogin());

    }

}
