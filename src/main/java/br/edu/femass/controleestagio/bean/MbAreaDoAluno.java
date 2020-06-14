package br.edu.femass.controleestagio.bean;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author souza
 */
@Named
@Dependent
public class MbAreaDoAluno implements Serializable {

    public String irAbaInicial() {
        return "FrmAreaDoAluno";
    }

    public String irAbaDadosDoEstagio() {
        return "FrmAbaDadosDoEstagio";
    }

    public String irAbaRelatorios() {
        return "FrmAbaEnviarRelatorios";
    }
}
