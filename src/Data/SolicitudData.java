
package Data;

import Domain.Analizador.Solicitud;
import Utility.Utility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;


public class SolicitudData {
    
    private Document jdomDocument;
    private Element root;

    public SolicitudData() throws JDOMException, IOException, NoSuchAlgorithmException {
        File file = new File(Utility.RUTA_XML_SOLICITUDES_FILE);
        if (file.exists()) {
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            this.jdomDocument = saxBuilder.build(file);
            this.root = this.jdomDocument.getRootElement();
        } else {
            this.root = new Element(Utility.USUARIOS);
            this.jdomDocument = new Document(this.root);
            saveXML();
        }//if
    }

    
    //METODOS PRIVADOS
    private void saveXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.jdomDocument, new PrintWriter(Utility.RUTA_XML_USUARIOS_FILE));
    }//saveXML

   

    
}//class
