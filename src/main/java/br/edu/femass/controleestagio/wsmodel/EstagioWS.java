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

    public Long getIdEstagio() {
        return idEstagio;
    }

    public void setIdEstagio(Long idEstagio) {
        this.idEstagio = idEstagio;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getNomeOrientador() {
        return nomeOrientador;
    }

    public void setNomeOrientador(String nomeOrientador) {
        this.nomeOrientador = nomeOrientador;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Status getStatusEstagio() {
        return statusEstagio;
    }

    public void setStatusEstagio(Status statusEstagio) {
        this.statusEstagio = statusEstagio;
    }
}
