package Domain.Analizador;

import Utility.Utility;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Analisis extends Thread{

    private Solicitud solicitud;
    private String estado;
    private String resultado;
    private int subprocesos;
    private int esclavos;

    public Analisis(Solicitud solicitud, int subprocesos, int esclavos) {
        this.solicitud = solicitud;
        this.estado = Utility.ESTADO_PENDIENTE;
        this.resultado = "";
        this.subprocesos = subprocesos;
        this.esclavos = esclavos;
    }

    public String getEstado() {
        return estado;
    }

    public synchronized void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public void run() {
        System.out.println("Analisis iniciado");
        while (true) {            
            if (this.estado.equals(Utility.FINALIZADO)) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//while
        System.out.println("Analisis finalizado");
    }//run

    
    
    private void analizar() {
        for (int i = 0; i < esclavos; i++) {
            Esclavo esclavo = new Esclavo(this.subprocesos);
        }
        
        
        if (this.solicitud.doAnalisis1()) {
            
        }
        if (this.solicitud.doAnalisis2()) {
            
        }
        if (this.solicitud.doAnalisis3()) {
            
        }

    }

    protected boolean validarSolicitud(Solicitud solicitud) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        boolean urlValida = urlValida = AnalizadorURL.conectarURL(solicitud.getUrl()) != null;
        return (solicitud.doAnalisis1()
                || solicitud.doAnalisis2()
                || solicitud.doAnalisis3())
                && urlValida;
    }//validarSolicitud

    protected static org.jdom.Document analisisDeElementos(Solicitud solicitud) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Document doc = AnalizadorURL.conectarURL(solicitud.getUrl());
        //int img

        int subtitulos = doc.getElementsByTag(Utility.TAG_H1).size();
        subtitulos += doc.getElementsByTag(Utility.TAG_H2).size();
        subtitulos += doc.getElementsByTag(Utility.TAG_H3).size();
        subtitulos += doc.getElementsByTag(Utility.TAG_H4).size();
        subtitulos += doc.getElementsByTag(Utility.TAG_H5).size();
        subtitulos += doc.getElementsByTag(Utility.TAG_H6).size();

        org.jdom.Element root = new org.jdom.Element(Utility.ANALISIS_ELEMENTOS);
        org.jdom.Document documentoXML = new org.jdom.Document(root);

        org.jdom.Element eImagenes = new org.jdom.Element(Utility.IMAGES);
        eImagenes.setAttribute(Utility.CANTIDAD,
                doc.getElementsByTag(Utility.TAG_IMG).size() + "");
        root.addContent(eImagenes);

        org.jdom.Element eA = new org.jdom.Element(Utility.LINKS);
        eA.setAttribute(Utility.CANTIDAD,
                doc.getElementsByTag(Utility.TAG_A).size() + "");
        root.addContent(eA);

        org.jdom.Element eVideos = new org.jdom.Element(Utility.VIDEOS);
        eVideos.setAttribute(Utility.CANTIDAD,
                doc.getElementsByTag(Utility.TAG_VIDEO) + "");
        root.addContent(eVideos);

        org.jdom.Element eTitulos = new org.jdom.Element(Utility.TITLES);
        eTitulos.setAttribute(Utility.CANTIDAD,
                doc.getElementsByTag(Utility.TAG_TITLE).size() + "");
        root.addContent(eTitulos);

        org.jdom.Element eSubtitulos = new org.jdom.Element(Utility.SUBTITLES);
        eSubtitulos.setAttribute(Utility.CANTIDAD, subtitulos + "");
        root.addContent(eSubtitulos);

        org.jdom.Element eTablas = new org.jdom.Element(Utility.TABLES);
        eTablas.setAttribute(Utility.CANTIDAD,
                doc.getElementsByTag(Utility.TAG_TABLE).size() + "");
        root.addContent(eTablas);

        return documentoXML;
    }//analisisDeElementos

    protected void analisisDeElementosyExtraccion(Solicitud solicitud) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Document doc = AnalizadorURL.conectarURL(solicitud.getUrl());

        for (Element element : doc.getElementsByTag(Utility.TAG_IMG)) {
            System.out.println(element.attr(Utility.ATTRI_SRC));
//            DescargaArchivo da = new DescargaArchivo(element.attr(Utility.ATTRI_SRC), destino);
        }

    }//analisisDeElementosyExtraccion

    protected void analisisDeExtraccionYComparacion() {

    }//analisisDeExtraccionYComparacion

}//class
