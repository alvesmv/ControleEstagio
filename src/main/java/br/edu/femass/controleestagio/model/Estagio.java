package br.edu.femass.controleestagio.model;

import br.edu.femass.controleestagio.enums.Disciplina;
import br.edu.femass.controleestagio.enums.Status;
import br.edu.femass.controleestagio.enums.TipoEstagio;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author dumas
 */
@Entity
public class Estagio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstagio;
    @OneToOne
    private Aluno alunoEstagio;
    @ManyToOne
    private Orientador orientadorEstagio;
    @ManyToOne
    private Empresa empresaEstagio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicioEstagio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datafimEstagio;
    @Enumerated(EnumType.STRING)
    private Status statusDoEstagio;
    private String nomeSupervisor;
    private String contatoSupervisor;
    @Enumerated(EnumType.STRING)
    private Disciplina disciplina;
    @Enumerated(EnumType.STRING)
    private TipoEstagio tipoEstagio;

    /**
     * @return the idEstagio
     */
    public Long getIdEstagio() {
        return idEstagio;
    }

    /**
     * @return the alunoEstagio
     */
    public Aluno getAlunoEstagio() {
        return alunoEstagio;
    }

    /**
     * @param alunoEstagio the alunoEstagio to set
     */
    public void setAlunoEstagio(Aluno alunoEstagio) {
        this.alunoEstagio = alunoEstagio;
    }

    /**
     * @return the orientadorEstagio
     */
    public Orientador getOrientadorEstagio() {
        return orientadorEstagio;
    }

    /**
     * @param orientadorEstagio the orientadorEstagio to set
     */
    public void setOrientadorEstagio(Orientador orientadorEstagio) {
        this.orientadorEstagio = orientadorEstagio;
    }

    /**
     * @return the empresaEstagio
     */
    public Empresa getEmpresaEstagio() {
        return empresaEstagio;
    }

    /**
     * @param empresaEstagio the empresaEstagio to set
     */
    public void setEmpresaEstagio(Empresa empresaEstagio) {
        this.empresaEstagio = empresaEstagio;
    }

    /**
     * @return the dataInicioEstagio
     */
    public Date getDataInicioEstagio() {
        return dataInicioEstagio;
    }

    /**
     * @param dataInicioEstagio the dataInicioEstagio to set
     */
    public void setDataInicioEstagio(Date dataInicioEstagio) {
        this.dataInicioEstagio = dataInicioEstagio;
    }

    /**
     * @return the datafimEstagio
     */
    public Date getDatafimEstagio() {
        return datafimEstagio;
    }

    /**
     * @param datafimEstagio the datafimEstagio to set
     */
    public void setDatafimEstagio(Date datafimEstagio) {
        this.datafimEstagio = datafimEstagio;
    }

    /**
     * @return the statusDoEstagio
     */
    public Status getStatusDoEstagio() {
        return statusDoEstagio;
    }

    /**
     * @param statusDoEstagio the statusDoEstagio to set
     */
    public void setStatusDoEstagio(Status statusDoEstagio) {
        this.statusDoEstagio = statusDoEstagio;
    }

    /**
     * @return the nomeSupervisor
     */
    public String getNomeSupervisor() {
        return nomeSupervisor;
    }

    /**
     * @param nomeSupervisor the nomeSupervisor to set
     */
    public void setNomeSupervisor(String nomeSupervisor) {
        this.nomeSupervisor = nomeSupervisor;
    }

    /**
     * @return the contatoSupervisor
     */
    public String getContatoSupervisor() {
        return contatoSupervisor;
    }

    /**
     * @param contatoSupervisor the contatoSupervisor to set
     */
    public void setContatoSupervisor(String contatoSupervisor) {
        this.contatoSupervisor = contatoSupervisor;
    }

    /**
     * @return the disciplina
     */
    public Disciplina getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * @return the tipoEstagio
     */
    public TipoEstagio getTipoEstagio() {
        return tipoEstagio;
    }

    /**
     * @param tipoEstagio the tipoEstagio to set
     */
    public void setTipoEstagio(TipoEstagio tipoEstagio) {
        this.tipoEstagio = tipoEstagio;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstagio != null ? idEstagio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Estagio)) {
            return false;
        }
        Estagio other = (Estagio) object;
        return this.idEstagio.equals(other.idEstagio);
    }

}
