package br.edu.femass.controleestagio.gui;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import br.edu.femass.controleestagio.model.Documento;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Souza
 */
@Named(value = "MbDownload")
@Dependent
public class MbDownload {

    private OutputStream out;
   
    public void convertBlobToFile(Documento doc) throws FileNotFoundException, IOException{
        byte[] blob = doc.getArquivo();
        out = new FileOutputStream(doc.getNome());
        out.write(blob);
        out.close();
    }   

    public OutputStream getOut() {
        return out;
    }
    
}
