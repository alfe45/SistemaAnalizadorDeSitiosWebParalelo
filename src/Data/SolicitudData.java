package Data;

import Domain.Analizador.Solicitud;
import Domain.Sistema.SistemaSingleton;
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
        }else{
            return false;
        }
    }//saveNewSolicitud
    
    public boolean overrideSolicitud(Solicitud solicitud) throws IOException {
        if (exists(solicitud)) {
            List<Element> eSolicitudes = this.root.getChildren();
            for (Element eSolicitud : eSolicitudes) {
                if (eSolicitud.getAttributeValue(Utility.URL).equals(solicitud.getUrl())) {
                    this.root.removeContent(eSolicitud);
                    break;
                }
            }
            return saveSolicitud(solicitud);
        }else{
            return false;
        }
    }//saveNewSolicitud

    public List<Solicitud> loadSolicitudes(String username) {
        List<Solicitud> solicitudes = new ArrayList<>();
        List<Element> eSolicitudes = this.root.getChildren();
        for (Element eSolicitud : eSolicitudes) {
            if (eSolicitud.getChild(Utility.DIGITADOR).getAttributeValue(Utility.USERNAME).equals(username)) {
                Solicitud temp = new Solicitud(
                        eSolicitud.getAttributeValue(Utility.ESTADO),
                        eSolicitud.getAttributeValue(Utility.URL),
                        eSolicitud.getChild(Utility.DIGITADOR).getAttributeValue(Utility.USERNAME),
                        eSolicitud.getChild(Utility.ANALISTA).getAttributeValue(Utility.USERNAME),
                        Boolean.parseBoolean(eSolicitud.getChild(Utility.ANALISIS1).getAttributeValue(Utility.HACER)),
                        Boolean.parseBoolean(eSolicitud.getChild(Utility.ANALISIS2).getAttributeValue(Utility.HACER)),
                        Boolean.parseBoolean(eSolicitud.getChild(Utility.ANALISIS3).getAttributeValue(Utility.HACER)));
                solicitudes.add(temp);
            }
        }
        return solicitudes;
    }//loadSolicitudeDigitador

    public List<Solicitud> loadAllSolicitudes() {
        List<Solicitud> solicitudes = new ArrayList<>();
        List<Element> eSolicitudes = this.root.getChildren();
        for (Element eSolicitud : eSolicitudes) {
            Solicitud temp = new Solicitud(
                    eSolicitud.getAttributeValue(Utility.ESTADO),
                    eSolicitud.getAttributeValue(Utility.URL),
                    eSolicitud.getChild(Utility.DIGITADOR).getAttributeValue(Utility.USERNAME),
                    eSolicitud.getChild(Utility.ANALISTA).getAttributeValue(Utility.USERNAME),
                    Boolean.parseBoolean(eSolicitud.getChild(Utility.ANALISIS1).getAttributeValue(Utility.HACER)),
                    Boolean.parseBoolean(eSolicitud.getChild(Utility.ANALISIS2).getAttributeValue(Utility.HACER)),
                    Boolean.parseBoolean(eSolicitud.getChild(Utility.ANALISIS3).getAttributeValue(Utility.HACER)));
            solicitudes.add(temp);
        }
        return solicitudes;
    }//loadAllSolicitudes

    private boolean saveSolicitud(Solicitud solicitud) throws IOException {
        Element eSolicitud = new Element(Utility.SOLICITUD);
        eSolicitud.setAttribute(Utility.ESTADO, solicitud.getEstado());
        eSolicitud.setAttribute(Utility.URL, solicitud.getUrl());

        Element eDigitador = new Element(Utility.DIGITADOR);
        eDigitador.setAttribute(Utility.USERNAME, solicitud.getDigitador());
        eSolicitud.addContent(eDigitador);

        Element eAnalista = new Element(Utility.ANALISTA);
        eAnalista.setAttribute(Utility.USERNAME, solicitud.getAnalista());
        eSolicitud.addContent(eAnalista);

        Element eAnalisis1 = new Element(Utility.ANALISIS1);
        eAnalisis1.setAttribute(Utility.HACER, solicitud.doAnalisis1() + "");
        eSolicitud.addContent(eAnalisis1);

        Element eAnalisis2 = new Element(Utility.ANALISIS2);
        eAnalisis2.setAttribute(Utility.HACER, solicitud.doAnalisis2() + "");
        eSolicitud.addContent(eAnalisis2);

        Element eAnalisis3 = new Element(Utility.ANALISIS3);
        eAnalisis3.setAttribute(Utility.HACER, solicitud.doAnalisis3() + "");
        eSolicitud.addContent(eAnalisis3);

        this.root.addContent(eSolicitud);
        saveXML();
        return true;
    }//saveSolicitud

    private boolean exists(Solicitud solicitud) {
        List<Solicitud> solicitudes = new ArrayList<>();
        List<Element> eSolicitudes = this.root.getChildren();
        for (Element eSolicitud : eSolicitudes) {
            if (eSolicitud.getAttributeValue(Utility.URL).equals(solicitud.getUrl())) {
                return true;
            }
        }
        return false;
    }//exists

}//class
