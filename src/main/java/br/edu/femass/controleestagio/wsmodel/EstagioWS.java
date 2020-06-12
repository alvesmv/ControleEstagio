/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.femass.controleestagio.wsmodel;

import br.edu.femass.controleestagio.enums.Disciplina;
import br.edu.femass.controleestagio.enums.Status;

/**
 *
 * @author dumas
 */
public class EstagioWS {
    
    private Long idEstagio;
    private String nomeAluno;
    private String matriculaAluno;
    private String nomeOrientador;
    private String nomeEmpresa;
    private Disciplina disciplina;
    private Status statusEstagio;

    public EstagioWS(Long idEstagio, String nomeAluno, String matriculaAluno, String nomeOrientador, String nomeEmpresa, Disciplina disciplina, Status statusEstagio) {
        this.idEstagio = idEstagio;
        this.nomeAluno = nomeAluno;
        this.matriculaAluno = matriculaAluno;
        this.nomeOrientador = nomeOrientador;
        this.nomeEmpresa = nomeEmpresa;
        this.disciplina = disciplina;
        this.statusEstagio = statusEstagio;
    }
    
}
