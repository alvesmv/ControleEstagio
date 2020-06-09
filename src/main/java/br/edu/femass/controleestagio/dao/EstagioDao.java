package br.edu.femass.controleestagio.dao;

import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.model.Disciplina;
import br.edu.femass.controleestagio.model.Estagio;
import br.edu.femass.controleestagio.model.Orientador;
import br.edu.femass.controleestagio.model.Status;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author souza
 */
@Stateless
public class EstagioDao {

    @PersistenceContext
    EntityManager em;

    public void inserir(Estagio estagio) {
        em.persist(estagio);
    }

    public void alterar(Estagio estagio) {
        em.merge(estagio);
    }

    public void excluir(Estagio estagio) {
        em.remove(em.merge(estagio));
    }

    public List<Estagio> getEstagios() {
        Query q = em.createQuery("select e from Estagio e order by e.idEstagio");
        return q.getResultList();
    }

    public List<Estagio> getEstagios(String id) {
        Query q = em.createQuery("select e from Estagio e where e.idEstagio = :i");
        q.setParameter("i", id);
        return q.getResultList();
    }
    
    public Estagio getEstagioAtivoPorAluno(Aluno aluno){
        Query q = em.createQuery("select e from Estagio e where e.alunoEstagio = :a and e.statusDoEstagio = :c");
        q.setParameter("c", Status.Cursando);
        q.setParameter("a", aluno);
        return (Estagio) q.getSingleResult();
    }

    /*
    Método que retorna uma lista de estágios concluídos (aprovados e reporvados)
    */
    public List<Estagio> getListEstagiosConcluidos(){
        Query q = em.createQuery("select e from Estagio where e.statusDoEstagio = :a or e.statusDoEstagio = :r");
        q.setParameter("a", Status.Aprovado);
        q.setParameter("a", Status.Reprovado);
        return q.getResultList();
    }
    
    /*
    Método que retorna uma lista de estágio ativos referentes a disciplina estágio I
    */
    public List<Estagio> getListEstagioI(){
        Query q = em.createQuery("select e from Estagio where e.disciplina = :d and e.statusDoEstagio = :c");
        q.setParameter("c", Status.Cursando);
        q.setParameter("d", Disciplina.Estagio_Obrigatorio_I);
        return q.getResultList();
    }
    
    /*
    Método que retorna uma lista de estágio ativos referentes a disciplina estágio II
    */
    public List<Estagio> getListEstagioII(){
        Query q = em.createQuery("select e from Estagio where e.disciplina = :d and e.statusDoEstagio = :c");
        q.setParameter("c", Status.Cursando);
        q.setParameter("d", Disciplina.Estagio_Obrigatorio_II);
        return q.getResultList();
    }
    
    /*
    Método que retorna uma lista de estágio ativos referentes ao orientador e a disciplina estágio I
    */
    public List<Estagio> getListEstagioIByOrientador(Orientador o){
        Query q = em.createQuery("select e from Estagio where e.disciplina = :d and e.statusDoEstagio = :c and e.orientadorEstagio = :o");
        q.setParameter("c", Status.Cursando);
        q.setParameter("d", Disciplina.Estagio_Obrigatorio_I);
        q.setParameter("o", o);
        return q.getResultList();
    }
    
    /*
    Método que retorna uma lista de estágio ativos referentes ao orientador e a disciplina estágio II
    */
    public List<Estagio> getListEstagioIIByOrientador(Orientador o){
        Query q = em.createQuery("select e from Estagio where e.disciplina = :d and e.statusDoEstagio = :c and e.orientadorEstagio = :o");
        q.setParameter("c", Status.Cursando);
        q.setParameter("d", Disciplina.Estagio_Obrigatorio_II);
        q.setParameter("o", o);
        return q.getResultList();
    }
    
    /*
    Método que retorna uma lista de estágios concluídos (aprovados e reporvados) pelo orientador
    */
    public List<Estagio> getListEstagiosConcluidosByOrientador(Orientador o){
        Query q = em.createQuery("select e from Estagio where e.statusDoEstagio = :a or e.statusDoEstagio = :r and e.orientadorEstagio = :o");
        q.setParameter("a", Status.Aprovado);
        q.setParameter("a", Status.Reprovado);
        q.setParameter("o", o);
        return q.getResultList();
    }
    
    /*
    Método que retorna o número total de alunos estagiando
    */
    /*public Long getAlunosEstagiando(){
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e where e.statusDoEstagio = :c");
        q.setParameter("c", Status.Cursando);
        return (Long) q.getSingleResult();
    } */
    
    /*
    Método que retorna o número de alunos que estão estagiando por curso
    */
    /*public List<Long> getAlunoPorCurso(){
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e group by e.alunoEstagio.curso.idCurso order by e.alunoEstagio.curso.idCurso");
        return q.getResultList();
    }*/
    
    /*
    Método que retorna o número de alunos por empresa
    */
    /*public List<Long> getAlunosPorEmpresa(){
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e group by e.empresaEstagio.idEmpresa order by e.empresaEstagio.idEmpresa");
        return q.getResultList();
    }*/
}
