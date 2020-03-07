/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.model;

/**
 *
 * @author Souza
 */
public enum DocumentoTipo {
    relatorio{
        String getNome(){
            return "relatorio";
        }
    }, 
    documentoCadastral{
        String getNome(){
            return "documento cadastral";
        }
    };
}
