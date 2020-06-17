package br.edu.femass.controleestagio.dao;

import br.edu.femass.controleestagio.enums.Status;
import br.edu.femass.controleestagio.model.Aluno;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Rodrigo
 */
@Stateless
public class AlunoDao {

    @PersistenceContext
    EntityManager em;

    public void inserir(Aluno aluno) {
        em.persist(aluno);
    }

    public void alterar(Aluno aluno) {
        em.merge(aluno);
    }

    public void excluir(Aluno aluno) {
        em.remove(em.merge(aluno));
    }

    public List<Aluno> getAlunos() {
        Query q = em.createQuery("select a from Aluno a order by a.nome");
        return q.getResultList();
    }

    public List<String> getListaAlunos() {
        Query q = em.createQuery("select a.nome from Aluno a order by a.nome");
        return q.getResultList();
    }

    public List<Aluno> getAlunos(String nome) {
        Query q = em.createQuery("select a from Aluno a where a.nome = :n");
        q.setParameter("n", nome);
        return q.getResultList();
    }

    public Aluno getAlunoPorMatricula(String matricula) {
        Query q = em.createQuery("select a from Aluno a where a.matricula = :m");
        q.setParameter("m", matricula);
        return (Aluno) q.getSingleResult();
    }

    public Aluno getAlunoByString(String nome) {
        Query q = em.createQuery("select a from Aluno a where a.nome = :n");
        q.setParameter("n", nome);
        return (Aluno) q.getSingleResult();
    }

    public List<Aluno> getAlunosByOrientador(String orientadorCpf) {
        Query q = em.createQuery("select e.alunoEstagio from Estagio e where e.orientadorEstagio.cpfLogin = :cpf");
        q.setParameter("cpf", orientadorCpf);
        return q.getResultList();
    }

   
    public List<Aluno> getAlunosComEstagio() {
        Query q = em.createQuery("select e.alunoEstagio from Estagio e where e is not null");
        return q.getResultList();
    }
    /*
    public List<Aluno> getAlunosComEstagioAtivo() {
        Query q = em.createQuery("select e.alunoEstagio from Estagio e and e.statusDoEstagio = :status where e is not null");
        q.setParameter("status", Status.Cursando);
        return q.getResultList();
    }
    
    public List<Aluno> getAlunosSemEstagioAtivo() {
        Query q = em.createQuery("select e.alunoEstagio from Estagio e and e.statusDoEstagio <> :status where e is not null");
        q.setParameter("status", Status.Cursando);
        return q.getResultList();
    }

    /*
    Método que retorna o número de alunos cadastrado no sistema
    */
    public Long getNumeroDeAlunos() {
        Query q = em.createQuery("select COUNT(a) from Aluno a");
        return (Long) q.getSingleResult();
    }
    
    /*
    Método que retorna o Aluno com base na id do usuário do Aluno
    */
    public Aluno getAluno(Long idAluno){
        Query q = em.createQuery("Select a from Aluno a where a.usuario.idUsuario = :idAluno");
        q.setParameter("idAluno", idAluno);
        return (Aluno) q.getSingleResult();
    }

}
