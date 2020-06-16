package br.edu.femass.controleestagio.bean;

import br.edu.femass.controleestagio.dao.CursoDao;
import br.edu.femass.controleestagio.dao.EstagioDao;
import br.edu.femass.controleestagio.enums.TipoEstagio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

/**
 *
 * @author dumas
 */
@Named
@RequestScoped
public class MbEstatisticas implements Serializable {

    private PieChartModel graficoAlunoCurso;
    private PieChartModel graficoAlunoCuroEstagioI;
    private PieChartModel graficoAlunoCuroEstagioII;
    private PieChartModel graficoAlunoTipoDeEstagio;

    @EJB
    EstagioDao estagioDao;
    @EJB
    CursoDao cursoDao;
    
    @PostConstruct
    public void init() {
        criaGraficoAlunoCurso();
        criaGraficoAlunoTipoDeEstagio();
        criaGraficoAlunoCuroEstagioI();
        criaGraficoAlunoCuroEstagioII();
    }

    public String iniciar() { 
        return "FrmEstatisticas";
    }

    private void criaGraficoAlunoCurso() {
        graficoAlunoCurso = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> valores = estagioDao.getQtdAlunoPorCurso();
        dataSet.setData(valores);

        List<String> cores = new ArrayList<>();
        cores.add("rgb(255, 99, 132)");
        cores.add("rgb(54, 162, 235)");
        cores.add("rgb(255, 205, 86)");
        cores.add("rgb(255, 45, 186)");
        dataSet.setBackgroundColor(cores);

        data.addChartDataSet(dataSet);
        List<String> rotulos = cursoDao.getListNomeCurso();
        data.setLabels(rotulos);

        graficoAlunoCurso.setData(data);
    }

    private void criaGraficoAlunoCuroEstagioI() {
        graficoAlunoCuroEstagioI = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> valores = estagioDao.getQtdAlunoPorCursoEmEstagioI();
        dataSet.setData(valores);

        List<String> cores = new ArrayList<>();
        cores.add("rgb(255, 99, 132)");
        cores.add("rgb(54, 162, 235)");
        cores.add("rgb(255, 205, 86)");
        cores.add("rgb(255, 45, 186)");
        dataSet.setBackgroundColor(cores);

        data.addChartDataSet(dataSet);
        List<String> rotulos = cursoDao.getListNomeCurso();
        data.setLabels(rotulos);
        
        graficoAlunoCuroEstagioI.setData(data);
    }

    private void criaGraficoAlunoCuroEstagioII() {
        graficoAlunoCuroEstagioII = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> valores = estagioDao.getQtdAlunoPorCursoEmEstagioII();;
        dataSet.setData(valores);

        List<String> cores = new ArrayList<>();
        cores.add("rgb(255, 99, 132)");
        cores.add("rgb(54, 162, 235)");
        cores.add("rgb(255, 205, 86)");
        cores.add("rgb(255, 45, 186)");
        dataSet.setBackgroundColor(cores);

        data.addChartDataSet(dataSet);
        List<String> rotulos = cursoDao.getListNomeCurso();
        data.setLabels(rotulos);
        
        graficoAlunoCuroEstagioII.setData(data);
    }

    private void criaGraficoAlunoTipoDeEstagio() {
        graficoAlunoTipoDeEstagio = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> valores = estagioDao.getQtdAlunoPorTipoEstagio();
        dataSet.setData(valores);

        List<String> cores = new ArrayList<>();
        cores.add("rgb(255, 99, 132)");
        cores.add("rgb(54, 162, 235)");
        cores.add("rgb(255, 205, 86)");
        cores.add("rgb(255, 45, 186)");
        dataSet.setBackgroundColor(cores);

        data.addChartDataSet(dataSet);
        List<String> rotulos = new ArrayList<>();
        rotulos.add(TipoEstagio.trabalho.toString());
        rotulos.add(TipoEstagio.trabalho_voluntario.toString());
        rotulos.add(TipoEstagio.mei.toString());
        rotulos.add(TipoEstagio.estagio.toString());
        data.setLabels(rotulos);
        
        graficoAlunoTipoDeEstagio.setData(data);
    }

    public PieChartModel getGraficoAlunoCurso() {
        return graficoAlunoCurso;
    }

    public PieChartModel getGraficoAlunoCuroEstagioI() {
        return graficoAlunoCuroEstagioI;
    }

    public PieChartModel getGraficoAlunoCuroEstagioII() {
        return graficoAlunoCuroEstagioII;
    }

    public PieChartModel getGraficoAlunoTipoDeEstagio() {
        return graficoAlunoTipoDeEstagio;
    }

}
