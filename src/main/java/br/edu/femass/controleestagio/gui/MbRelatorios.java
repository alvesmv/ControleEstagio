/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.gui;

import br.edu.femass.controleestagio.dao.DocumentoDao;
import br.edu.femass.controleestagio.model.Documento;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *
 * @author Souza
 */
@Named(value = "mbRelatorios")
@SessionScoped

public class MbRelatorios implements Serializable{

    private List<Documento> docList;
    private Documento doc;
    private String matricula;
    
    @EJB
    DocumentoDao docDao;
    
    public MbRelatorios() {
    }
    
    public String iniciar() {
        //Obtem o objeto usuario instanciado no durante o login
        Object o = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.setMatricula(o.toString());
        docList = docDao.getListaDocumentosByMatricula(this.getMatricula());
        return "FrmAbaRelatorios";
    }
   public String excluir(Documento d) {
        docDao.excluir(d);
        docList = docDao.getListaDocumentosByMatricula(matricula);
        return null;
    }

    public List<Documento> getDocList() {
        return docList;
    }

    public void setDocList(List<Documento> docList) {
        this.docList = docList;
    }

    public Documento getDoc() {
        return doc;
    }

    public void setDoc(Documento doc) {
        this.doc = doc;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
