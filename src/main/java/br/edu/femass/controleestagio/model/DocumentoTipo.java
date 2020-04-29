package br.edu.femass.controleestagio.model;

/**
 *
 * @author gxrj
 */
public enum DocumentoTipo {

    relatorio, documentoCadastral;

    @Override
    public String toString() {
        switch (this) {
            case relatorio: {
                return "Relatorio";
            }
            case documentoCadastral: {
                return "Documento Cadastral";
            }
        }
        return null;
    }
}
