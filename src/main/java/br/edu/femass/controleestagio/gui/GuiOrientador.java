/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.gui;

import br.edu.femass.controleestagio.dao.OrientadorDao;
import br.edu.femass.controleestagio.dao.UsuarioDao;
import br.edu.femass.controleestagio.model.Orientador;
import br.edu.femass.controleestagio.model.TipoDeAcesso;
import br.edu.femass.controleestagio.model.Usuario;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author dumas
 */
@Named(value = "guiOrientador")
@SessionScoped
public class GuiOrientador implements Serializable {

    private List<Orientador> orientadores;
    private Orientador orientador;
    private Boolean alterando;
    private Usuario usuario;
    private TipoDeAcesso tipoDeAcesso;

    @EJB
    OrientadorDao orientadorDao;
    @EJB
    UsuarioDao usuarioDao = new UsuarioDao();

    public GuiOrientador() {
    }

    public String iniciar() {
        orientadores = orientadorDao.getOrientadores();
        return "FrmLstOrientador";
    }

    public String incluir() {
        orientador = new Orientador();
        alterando = false;
        usuario = new Usuario();
        return "FrmCadOrientador";
    }

    public String alterar(Orientador o) {
        orientador = o;
        alterando = true;
        return "FrmCadOrientador";
    }

    public String excluir(Orientador o) {
        orientadorDao.excluir(o);
        orientadores = orientadorDao.getOrientadores();
        return null;
    }

    public String voltarMenuPrincipal() {
        return "index";
    }

    public String voltar() {
        return "FrmLstOrientador";
    }

    public String gravar() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (alterando) {
            orientadorDao.alterar(orientador);
        } else {
            usuario.setLogin(orientador.getCpf());
            usuario.setSenha(orientador.getCpf());
            usuarioDao.inserir(usuario);
            orientador.setUsuario(usuario);
            orientadorDao.inserir(orientador);
        }
        return iniciar();
    }

    /**
     * @return the orientadores
     */
    public List<Orientador> getOrientadores() {
        return orientadores;
    }

    /**
     * @param orientadores the orientadores to set
     */
    public void setOrientadores(List<Orientador> orientadores) {
        this.orientadores = orientadores;
    }

    /**
     * @return the orientador
     */
    public Orientador getOrientador() {
        return orientador;
    }

    /**
     * @param orientador the orientador to set
     */
    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
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

}
