package Data;

import Domain.Analizador.Solicitud;
import Utility.Utility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
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
            this.root = new Element(Utility.SOLICITUDES);
            this.jdomDocument = new Document(this.root);
            saveXML();
        }//if
    }

    //METODOS PRIVADOS
    private void saveXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.jdomDocument, new PrintWriter(Utility.RUTA_XML_SOLICITUDES_FILE));
    }//saveXML

    public boolean saveNewSolicitud(Solicitud solicitud) throws IOException {
        if (!exists(solicitud)) {
            return saveSolicitud(solicitud);
        } else {
            return false;
        }
    }//saveNewSolicitud

    public boolean overrideSolicitud(Solicitud solicitud) throws IOException {
        if (exists(solicitud)) {
            List<Element> eSolicitudes = this.root.getChildren();
            for (Element eSolicitud : eSolicitudes) {
                if (eSolicitud.getAttributeValue(Utility.URL).equals(solicitud.data_getUrl())) {
                    this.root.removeContent(eSolicitud);
                    break;
                }
            }
            saveXML();
            return saveSolicitud(solicitud);
        } else {
            return false;
        }
    }//saveNewSolicitud

    public List<Solicitud> loadSolicitudes(String username, String rol) {
        List<Solicitud> solicitudes = new ArrayList<>();
        List<Element> eSolicitudes = this.root.getChildren();
        if (rol.equals(Utility.GESTOR)) {
            for (Element eSolicitud : eSolicitudes) {
                Solicitud temp = new Solicitud(eSolicitud);
                solicitudes.add(temp);
            }
            return solicitudes;
        } else {
            for (Element eSolicitud : eSolicitudes) {
                if (eSolicitud.getChild(rol).getAttributeValue(Utility.USERNAME).equals(username)) {
                    Solicitud temp = new Solicitud(eSolicitud);
                    solicitudes.add(temp);
                }
            }
            return solicitudes;
        }
    }//loadSolicitudes

    private boolean saveSolicitud(Solicitud solicitud) throws IOException {
        this.root.addContent(solicitud.getData());
        saveXML();
        return true;
    }//saveSolicitud

    private boolean exists(Solicitud solicitud) {
        List<Solicitud> solicitudes = new ArrayList<>();
        List<Element> eSolicitudes = this.root.getChildren();
        for (Element eSolicitud : eSolicitudes) {
            if (eSolicitud.getAttributeValue(Utility.URL).equals(solicitud.data_getUrl())) {
                return true;
            }
        }
        return false;
    }//exists

    public boolean agregarResultado(Solicitud solicitud, Element resultado) throws IOException {
        List<Solicitud> solicitudes = new ArrayList<>();
        List<Element> eSolicitudes = this.root.getChildren();
        for (Element eSolicitud : eSolicitudes) {
            if (eSolicitud.getAttributeValue(Utility.URL).equals(solicitud.data_getUrl())) {
                eSolicitud.removeChild(Utility.RESULTADO);
                eSolicitud.addContent(resultado);
                saveXML();
                return true;
            }
        }
        return false;
    }//agregarResultado

}//class
