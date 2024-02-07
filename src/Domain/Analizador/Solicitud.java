package Domain.Analizador;

import Utility.Utility;
import org.jdom.Element;

public class Solicitud {

    private Element data;

    //usar en SolicitudData -> loadSolicitudes()
    public Solicitud(Element data) {
        this.data = data;
    }

    //usar en JIFDigitador
    public Solicitud(String url, boolean analisis1, boolean analisis2, boolean analisis2_img, boolean analisis2_links, boolean analisis3) {

        this.data = new Element(Utility.SOLICITUD);
        data.setAttribute(Utility.ESTADO, Utility.ESTADO_PENDIENTE);
        data.setAttribute(Utility.URL, url);

        Element eDigitador = new Element(Utility.DIGITADOR);
        eDigitador.setAttribute(Utility.USERNAME, Utility.NO_ASIGNADO);
        data.addContent(eDigitador);

        Element eGestor = new Element(Utility.GESTOR);
        eGestor.setAttribute(Utility.USERNAME, Utility.NO_ASIGNADO);
        data.addContent(eGestor);

        Element eAnalista = new Element(Utility.ANALISTA);
        eAnalista.setAttribute(Utility.USERNAME, Utility.NO_ASIGNADO);
        data.addContent(eAnalista);

        Element eAnalisis1 = new Element(Utility.ANALISIS1);
        eAnalisis1.setAttribute(Utility.HACER, analisis1 + "");
        data.addContent(eAnalisis1);

        Element eAnalisis2 = new Element(Utility.ANALISIS2);
        eAnalisis2.setAttribute(Utility.HACER, analisis2 + "");
        eAnalisis2.setAttribute(Utility.ANALISIS2_IMG, analisis2_img + "");
        eAnalisis2.setAttribute(Utility.ANALISIS2_LINKS, analisis2_links + "");
        data.addContent(eAnalisis2);

        Element eAnalisis3 = new Element(Utility.ANALISIS3);
        eAnalisis3.setAttribute(Utility.HACER, analisis3 + "");
        data.addContent(eAnalisis3);

        Element eResultado = new Element(Utility.RESULTADO);
        data.addContent(eResultado);

    }

    public Element getData() {
        return data;
    }

    public void setData(Element data) {
        this.data = data;
    }

    public boolean doAnalisis1() {
        return Boolean.parseBoolean(this.data.getChild(Utility.ANALISIS1).getAttributeValue(Utility.HACER));
    }

    public boolean doAnalisis2() {
        return Boolean.parseBoolean(this.data.getChild(Utility.ANALISIS2).getAttributeValue(Utility.HACER));
    }

    public boolean doAnalisis2_extract_img() {
        return Boolean.parseBoolean(this.data.getChild(Utility.ANALISIS2).getAttributeValue(Utility.ANALISIS2_IMG));
    }

    public boolean doAnalisis2_extract_links() {
        return Boolean.parseBoolean(this.data.getChild(Utility.ANALISIS2).getAttributeValue(Utility.ANALISIS2_LINKS));
    }

    public boolean doAnalisis3() {
        return Boolean.parseBoolean(this.data.getChild(Utility.ANALISIS3).getAttributeValue(Utility.HACER));
    }

    public String data_getUrl() {
        return this.data.getAttributeValue(Utility.URL);
    }

    public String data_getDigitador() {
        return this.data.getChild(Utility.DIGITADOR).getAttributeValue(Utility.USERNAME);
    }

    public void data_setDigitador(String digitador) {
        this.data.getChild(Utility.DIGITADOR).setAttribute(Utility.USERNAME, digitador);
    }

    public String data_getGestor() {
        return this.data.getChild(Utility.GESTOR).getAttributeValue(Utility.USERNAME);
    }

    public void data_setGestor(String gestor) {
        this.data.getChild(Utility.GESTOR).setAttribute(Utility.USERNAME, gestor);
    }

    public String data_getAnalista() {
        return this.data.getChild(Utility.ANALISTA).getAttributeValue(Utility.USERNAME);
    }

    public void data_setAnalista(String analista) {
        this.data.getChild(Utility.ANALISTA).setAttribute(Utility.USERNAME, analista);
    }

    public String data_getEstado() {
        return this.data.getAttributeValue(Utility.ESTADO);
    }

    public void data_setEstado(String estado) {
        this.data.setAttribute(Utility.ESTADO, estado);
    }

    public boolean esValida() {
        return doAnalisis1() || (doAnalisis2_extract_img() || doAnalisis2_extract_links()) || doAnalisis3();
    }//esValida
//
//    public String data() {
//        return this.url + " " + this.analisis[0] + " " + this.analisis[1] + " " + this.analisis[2];
//    }//data

}//class
