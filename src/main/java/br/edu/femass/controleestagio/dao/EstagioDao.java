package br.edu.femass.controleestagio.dao;

import br.edu.femass.controleestagio.model.Aluno;
import br.edu.femass.controleestagio.enums.Disciplina;
import br.edu.femass.controleestagio.model.Estagio;
import br.edu.femass.controleestagio.enums.Status;
import br.edu.femass.controleestagio.wsmodel.EstagioWS;
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

    public Estagio getEstagioAtivoPorAluno(Aluno aluno) {
        Query q = em.createQuery("select e from Estagio e where e.alunoEstagio =:a  and e.statusDoEstagio = :c");
        q.setParameter("c", Status.Cursando);
        q.setParameter("a", aluno);
        return (Estagio) q.getSingleResult();
    }

    public List<Estagio> getEstagiosAtivosPorAluno(String matricula) {
        Query q = em.createQuery("select e from Estagio e where e.alunoEstagio.matricula =:m  and e.statusDoEstagio = :c");
        q.setParameter("c", Status.Cursando);
        q.setParameter("m", matricula);
        return q.getResultList();
    }

    public Long getQtdeEstagiosAtivosPorMatricula(String matricula) {
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e where e.alunoEstagio.matricula = :m and e.statusDoEstagio = :c");
        q.setParameter("m", matricula);
        q.setParameter("c", Status.Cursando);
        return (Long) q.getSingleResult();
    }

    /*
    Método que retorna uma lista de estágios concluídos (aprovados e reporvados)
     */
    public List<Estagio> getListEstagiosConcluidos() {
        Query q = em.createQuery("select e from Estagio e where e.statusDoEstagio = :a  or e.statusDoEstagio = :r");
        q.setParameter("a", Status.Aprovado);
        q.setParameter("r", Status.Reprovado);

        /* O trecho abaixo pode substituir o código acima enquanto houver apenas 3 status (aprovado, reprovado e cursando)
        Query q = em.createQuery("select e from Estagio e where e.statusDoEstagio <> :c");
        q.setParameter("c", Status.Cursando);
         */
        return q.getResultList();
    }

    /*
    Método que retorna uma lista de estágio ativos referentes a disciplina estágio I
     */
    public List<Estagio> getListEstagioI() {
        Query q = em.createQuery("select e from Estagio e where e.disciplina =:d  and e.statusDoEstagio = :c");
        q.setParameter("c", Status.Cursando);
        q.setParameter("d", Disciplina.Estagio_Obrigatorio_I);
        return q.getResultList();
    }

    /*
    Método que retorna uma lista de estágio ativos referentes a disciplina estágio II
     */
    public List<Estagio> getListEstagioII() {
        Query q = em.createQuery("select e from Estagio e where e.disciplina =:d  and e.statusDoEstagio = :c");
        q.setParameter("c", Status.Cursando);
        q.setParameter("d", Disciplina.Estagio_Obrigatorio_II);
        return q.getResultList();
    }

    /*
    Método que retorna uma lista de estágio ativos referentes ao orientador e a disciplina estágio I
     */
    public List<Estagio> getListEstagioIByOrientador(String cpfOrientador) {
        Query q = em.createQuery("select e from Estagio e where e.disciplina = :d and e.statusDoEstagio = :c and e.orientadorEstagio.cpfLogin = :cpf");
        q.setParameter("c", Status.Cursando);
        q.setParameter("d", Disciplina.Estagio_Obrigatorio_I);
        q.setParameter("cpf", cpfOrientador);
        return q.getResultList();
    }

    /*
    Método que retorna uma lista de estágio ativos referentes ao orientador e a disciplina estágio II
     */
    public List<Estagio> getListEstagioIIByOrientador(String cpfOrientador) {
        Query q = em.createQuery("select e from Estagio e where e.disciplina = :d and e.statusDoEstagio = :c and e.orientadorEstagio.cpfLogin = :cpf");
        q.setParameter("c", Status.Cursando);
        q.setParameter("d", Disciplina.Estagio_Obrigatorio_II);
        q.setParameter("cpf", cpfOrientador);
        return q.getResultList();
    }

    /*
    Método que retorna uma lista de estágios concluídos (aprovados e reporvados) pelo orientador
     */
    public List<Estagio> getListEstagiosConcluidosByOrientador(String cpfOrientador) {
        Query q = em.createQuery("select e from Estagio e where e.statusDoEstagio = :a or e.statusDoEstagio = :r and e.orientadorEstagio.cpfLogin = :cpf");
        q.setParameter("a", Status.Aprovado);
        q.setParameter("r", Status.Reprovado);
        q.setParameter("cpf", cpfOrientador);
        return q.getResultList();
    }

    /*
    Método que retorna o número total de alunos estagiando
     */
    public Long getQtdAlunosEstagiando() {
        Query q = em.createQuery("select COUNT(e) from Estagio e where e.statusDoEstagio = :c");
        q.setParameter("c", Status.Cursando);
        return (Long) q.getSingleResult();
    }

    /*
    Método que retorna o número de alunos por curso que estajam com estágio ativo agrupado pelo idCurso
     */
    public List<Number> getQtdAlunoPorCurso() {
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e where e.statusDoEstagio = :status group by e.alunoEstagio.curso.nomeCurso order by e.alunoEstagio.curso.nomeCurso");
        q.setParameter("status", Status.Cursando);
        return q.getResultList();
    }

    /*
    Método que retorna o número de alunos por empresa com estágio ativo
     */
    public List<Number> getQtdAlunosPorEmpresa() {
        Query q = em.createQuery("select COUNT(e) from Estagio e where e.statusDoEstagio = :status group by e.empresaEstagio.idEmpresa order by e.empresaEstagio.idEmpresa");
        q.setParameter("status", Status.Cursando);
        return q.getResultList();
    }

    /*
    Método que retorna para o webservice EstagioRest os dados do EstagioWS
     */
    public List<EstagioWS> getListaDeEstagioWS(Long idAluno) {
        Query q = em.createQuery("select new br.edu.femass.controleestagio.wsmodel.EstagioWS(e.idEstagio, e.alunoEstagio.nome, e.alunoEstagio.matricula, e.orientadorEstagio.nomeOrientador,"
                + " e.empresaEstagio.nomeEmpresa, e.disciplina, e.statusDoEstagio) from Estagio e where e.alunoEstagio.idAluno = :idAluno");
        q.setParameter("idAluno", idAluno);
        return q.getResultList();
    }

    /*
    Método que retorna o número de alunos por curso que estajam com estágio ativo agrupado pelo idCurso
     */
    public List<Number> getQtdAlunoPorTipoEstagio() {
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e where e.statusDoEstagio = :status group by e.tipoEstagio");
        q.setParameter("status", Status.Cursando);
        return q.getResultList();
    }

    /*
    Método que retorna o número de alunos por curso que estajam com estágio ativo agrupado pelo idCurso
     */
    public List<Number> getQtdAlunoPorCursoEmEstagioI() {
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e where e.statusDoEstagio = :status and e.disciplina = :disciplina group by e.alunoEstagio.curso.nomeCurso order by e.alunoEstagio.curso.nomeCurso");
        q.setParameter("status", Status.Cursando);
        q.setParameter("disciplina", Disciplina.Estagio_Obrigatorio_I);
        return q.getResultList();
    }

    /*
    Método que retorna o número de alunos por curso que estajam com estágio ativo agrupado pelo idCurso
     */
    public List<Number> getQtdAlunoPorCursoEmEstagioII() {
        Query q = em.createQuery("select COUNT(e.alunoEstagio) from Estagio e where e.statusDoEstagio = :status and e.disciplina = :disciplina group by e.alunoEstagio.curso.nomeCurso order by e.alunoEstagio.curso.nomeCurso");
        q.setParameter("status", Status.Cursando);
        q.setParameter("disciplina", Disciplina.Estagio_Obrigatorio_II);
        return q.getResultList();
    }

    /*
    Método que retorna para o webservice EstagioRest os dados do EstagioWS
     */
    public EstagioWS getEstagioWS(Long idAluno) {
        Query q = em.createQuery("select new br.edu.femass.controleestagio.wsmodel.EstagioWS(e.idEstagio, e.alunoEstagio.nome, e.alunoEstagio.matricula, e.orientadorEstagio.nomeOrientador,"
                + " e.empresaEstagio.nomeEmpresa, e.disciplina, e.statusDoEstagio) from Estagio e where e.alunoEstagio.idAluno = :idAluno");
        q.setParameter("idAluno", idAluno);
        return (EstagioWS) q.getSingleResult();
    }

}
