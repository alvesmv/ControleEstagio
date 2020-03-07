package br.edu.femass.controleestagio.model;



import java.io.File;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Souza
 */
@Entity
public class Documento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Aluno aluno;
    String nome;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dataEnvio;
    Long tamanho;
    File arquivo;
    DocumentoTipo docTipo;
    DocumentoStatus docStatus;
    

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }
    
    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public DocumentoTipo getDocTipo() {
        return docTipo;
    }

    public void setDocTipo(DocumentoTipo docTipo) {
        this.docTipo = docTipo;
    }

    public DocumentoStatus getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(DocumentoStatus docStatus) {
        this.docStatus = docStatus;
    }
    
    
}
