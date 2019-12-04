/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.gui;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author souza
 */
@Named(value = "guiLogin")
@SessionScoped
public class guiLogin implements Serializable{
    
    private String login,
                   senha;
    
    private String tipoUsuario;
    
    public String entrar(){
        
        if(tipoUsuario.equals("aluno")){
            return "areaDoAluno";
        }
        if(tipoUsuario.equals("coordenador")){
            return "index";
        }
        return null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    
}
