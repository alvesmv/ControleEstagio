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
 * @author Souza
 */
@Named(value = "MbDownload")
@Dependent
public class MbDownload {
    
    public void download(Documento doc) throws FileNotFoundException, IOException{
        byte[] blob = doc.getArquivo();
        
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition","attachment;filename=" + doc.getNome());
        response.getOutputStream().write(blob);
        FacesContext.getCurrentInstance().responseComplete(); 
    }    
}
