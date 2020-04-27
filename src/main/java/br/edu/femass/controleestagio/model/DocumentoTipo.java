package br.edu.femass.controleestagio.model;

/**
 *
 * @author Souza
 */
public enum DocumentoTipo {
    relatorio, documentoCadastral;
    
    @Override
    public String toString(){
        switch(this){
                case relatorio:
                                return "relatorio";
                default:
                                return "documento cadastral";
            }
    }    
}
