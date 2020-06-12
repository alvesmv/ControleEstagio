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
public enum Disciplina {

    Estagio_Obrigatorio_I, Estagio_Obrigatorio_II;

    @Override
    public String toString() {
        switch (this) {
            case Estagio_Obrigatorio_I: {
                return "Estagio Obrigatorio I";
            }
            case Estagio_Obrigatorio_II: {
                return "Estagio Obrigatorio II";
            }
        }
        return null;
    }
}
