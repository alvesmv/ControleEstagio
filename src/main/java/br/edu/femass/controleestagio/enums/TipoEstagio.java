/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.enums;

/**
 *
 * @author dumas
 */
public enum TipoEstagio {

    trabalho, trabalho_voluntario, mei, estagio;

    @Override
    public String toString() {
        switch (this) {
            case trabalho:
                return "Trabalho";
            case trabalho_voluntario:
                return "Trabalho voluntario";
            case mei:
                return "MEI";
            case estagio:
                return "Estágio";
        }
        return null;
    }
}
