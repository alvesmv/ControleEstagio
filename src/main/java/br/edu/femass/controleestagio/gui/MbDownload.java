package br.edu.femass.controleestagio.gui;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import br.edu.femass.controleestagio.model.Documento;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gxrj
 */
@Named(value = "mbDownload")
@Dependent
public class MbDownload {
    
    public void download(Documento doc) throws FileNotFoundException, IOException{
        //Captura o response
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        //Preapara o conteúdo do response
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition","attachment;filename=" + doc.getNome());
        //Escreve o conteudo de doc no response
        response.getOutputStream().write(doc.getArquivo());
        FacesContext.getCurrentInstance().responseComplete(); 
    }    
}
