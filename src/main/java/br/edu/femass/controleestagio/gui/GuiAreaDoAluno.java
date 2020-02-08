
package br.edu.femass.controleestagio.gui;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author souza
 */
@Named(value = "guiAreaDoAluno")
@Dependent
public class GuiAreaDoAluno implements Serializable {

    public String irAbaInicial(){
        return "FrmAreaDoAluno";
    }
    public String irAbaDadosDoEstagio(){
        return "FrmAbaDadosDoEstagio";
    }
    public String irAbaRelatorios(){
        return "FrmAbaRelatorios";
    }
}
