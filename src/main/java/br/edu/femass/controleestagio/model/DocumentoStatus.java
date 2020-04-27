package br.edu.femass.controleestagio.model;

/**
 *
 * @author gxrj
 */
public enum DocumentoStatus {
    em_analise, indeferido, deferido;
    
    @Override
    public String toString(){
        switch(this){
            case deferido:
                            return "deferido;";
            case indeferido: 
                            return "indeferido";
            default:
                            return "em analise";
        }
    }
}
