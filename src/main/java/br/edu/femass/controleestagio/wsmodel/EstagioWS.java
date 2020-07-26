package br.edu.femass.controleestagio.wsmodel;

import br.edu.femass.controleestagio.enums.Disciplina;
import br.edu.femass.controleestagio.enums.Status;
import br.edu.femass.controleestagio.enums.TipoEstagio;
import java.util.Date;

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
    private TipoEstagio tipoEstagio;
    private Date dataInicioEstagio;

    public EstagioWS(Long idEstagio, String nomeAluno, String matriculaAluno,
            String nomeOrientador, String nomeEmpresa, Disciplina disciplina,
            Status statusEstagio, TipoEstagio tipoEstagio, Date dataInicioEstagio) {
        this.idEstagio = idEstagio;
        this.nomeAluno = nomeAluno;
        this.matriculaAluno = matriculaAluno;
        this.nomeOrientador = nomeOrientador;
        this.nomeEmpresa = nomeEmpresa;
        this.disciplina = disciplina;
        this.statusEstagio = statusEstagio;
        this.tipoEstagio = tipoEstagio;
        this.dataInicioEstagio = dataInicioEstagio;
    }

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

    public TipoEstagio getTipoEstagio() {
        return tipoEstagio;
    }

    public void setTipoEstagio(TipoEstagio tipoEstagio) {
        this.tipoEstagio = tipoEstagio;
    }

    public Date getDataInicioEstagio() {
        return dataInicioEstagio;
    }

    public void setDataInicioEstagio(Date dataInicioEstagio) {
        this.dataInicioEstagio = dataInicioEstagio;
    }
}
