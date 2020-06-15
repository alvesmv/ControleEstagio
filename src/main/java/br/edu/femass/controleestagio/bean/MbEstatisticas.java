package br.edu.femass.controleestagio.bean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gxrj
 */
@Named
@SessionScoped

public class MbEstatisticas implements Serializable {

    MbEstatisticas(){  
    }
    
    public String iniciar(){
        return "FrmEstatisticas";
    }    
}

