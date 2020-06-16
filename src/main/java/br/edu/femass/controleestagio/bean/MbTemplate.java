package br.edu.femass.controleestagio.bean;

import br.edu.femass.controleestagio.model.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author dumas
 */
@Named
@SessionScoped
public class MbTemplate implements Serializable {

    /*
    Método para exibir o nome do usuário na barra de menu
     */
    public String exibiUsuario() {

        Usuario usr;
        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return usr.getNomeDeExibicao();
    }

    /*
    Método que leva o usuário para sua página inicial
     */
    public String home() {

        //Obtem o objeto usuario instanciado no durante o login
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        switch (user.getTipoDeAcesso()) {
            case aluno:
                return "FrmAreaDoAluno";
            case orientador:
                return "FrmAreaDoOrientador";
            case coordenador:
                return "FrmAreaDoCoordenador";
            case admin:
                return "FrmAreaDoCoordenador";
        }
        return null;
    }

    /*
    Método que define quais os usuários podem visualizar o menu aluno
     */
    public boolean menuAluno() {

        //Obtem o objeto usuario instanciado no durante o login
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        switch (user.getTipoDeAcesso()) {
            case aluno:
                return true;
            /*case admin:
                return true;*/
            default:
                return false;
        }
    }

    /*
    Método que define quais os usuários podem visualizar o menu cadastro
     */
    public boolean menuCadastro() {

        //Obtem o objeto usuario instanciado no durante o login
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        switch (user.getTipoDeAcesso()) {
            case coordenador:
                return true;
            case admin:
                return true;
            default:
                return false;
        }
    }

    /*
    Método que define quais os usuários podem visualizar o menu Academico
     */
    public boolean menuAcademico() {

        //Obtem o objeto usuario instanciado no durante o login
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        switch (user.getTipoDeAcesso()) {
            case orientador:
                return true;
            case coordenador:
                return true;
            case admin:
                return true;
            default:
                return false;
        }
    }

    /*
    Método que define quais os usuários podem visualizar o menu Estatísticas
     */
    public boolean menuEstatistica() {

        //Obtem o objeto usuario instanciado no durante o login
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        switch (user.getTipoDeAcesso()) {
            case orientador:
                return true;
            case coordenador:
                return true;
            case admin:
                return true;
            default:
                return false;
        }

    }
}
