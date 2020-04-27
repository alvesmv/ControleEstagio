package br.edu.femass.controleestagio.model;

/**
 *
 * @author gxrj
 */
public enum DocumentoStatus {

    em_analise, indeferido, deferido;

    @Override
    public String toString() {
        switch (this) {
            case em_analise:
                return "em analise";
            case indeferido:
                return "indeferido";
            case deferido:
                return "deferido;";
        }
        return null;
    }
}
