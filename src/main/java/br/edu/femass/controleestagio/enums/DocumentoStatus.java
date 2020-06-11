package br.edu.femass.controleestagio.enums;

/**
 *
 * @author gxrj
 */
public enum DocumentoStatus {

    em_analise, indeferido, deferido;

    @Override
    public String toString() {
        switch (this) {
            case em_analise: {
                return "Em Analise";
            }
            case indeferido: {
                return "Indeferido";
            }
            case deferido: {
                return "Deferido";
            }
        }
        return null;
    }
}
