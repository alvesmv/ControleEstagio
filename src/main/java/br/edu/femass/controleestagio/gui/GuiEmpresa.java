/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.gui;

import br.edu.femass.controleestagio.dao.EmpresaDao;
import br.edu.femass.controleestagio.model.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author dumas
 */
@Named(value = "guiEmpresa")
@SessionScoped
public class GuiEmpresa implements Serializable {

    private List<Empresa> empresas;
    private Empresa empresa;
    private Boolean alterando;

    @EJB
    EmpresaDao empresaDao;

    public GuiEmpresa() {

    }

    public String iniciar() {
        empresas = empresaDao.getEmpresas();
        return "FrmLstEmpresa";
    }

    public String incluir() {
        empresa = new Empresa();
        alterando = false;
        return "FrmCadEmpresa";
    }

    public String alterar(Empresa e) {
        empresa = e;
        alterando = true;
        return "FrmCadEmpresa";
    }

    public String excluir(Empresa e) {
        empresaDao.excluir(e);
        empresas = empresaDao.getEmpresas();
        return null;
    }

    public String voltarMenuPrincipal() {
        return "/index";
    }

    public String voltar() {
        return "FrmLstEmpresa";
    }

    public String gravar() {
        if (alterando) {
            empresaDao.alterar(empresa);
        } else {
            empresaDao.inserir(empresa);
        }

        return iniciar();
    }

    /**
     * @return the empresas
     */
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    /**
     * @param empresas the empresas to set
     */
    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
