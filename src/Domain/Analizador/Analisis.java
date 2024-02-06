package Domain.Analizador;

import Domain.Sistema.SistemaSingleton;
import Utility.Utility;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Analisis extends Thread {

    private Solicitud solicitud;
    private String estado;
    private int subprocesos;
    private int esclavos;
    private boolean running;
    private org.jdom.Element data_analisis1;
    private org.jdom.Element data_analisis2;
    private org.jdom.Element data_analisis3;

    public Analisis(Solicitud solicitud, int subprocesos, int esclavos) {
        this.solicitud = solicitud;
        this.estado = Utility.ESTADO_PENDIENTE;
        this.subprocesos = subprocesos;
        this.esclavos = esclavos;
        this.running = false;
        this.data_analisis1 = new org.jdom.Element(Utility.ANALISIS1);
        this.data_analisis2 = new org.jdom.Element(Utility.ANALISIS2);
        this.data_analisis3 = new org.jdom.Element(Utility.ANALISIS3);
    }

    public String getEstado() {
        return estado;
    }

    public synchronized void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public void run() {

        List<Esclavo> esclavos = new ArrayList<>();
        for (int i = 0; i < this.esclavos; i++) {
            esclavos.add(new Esclavo(this.subprocesos));
        }
        
        System.out.println("Analisis iniciado");
        this.estado = Utility.ESTADO_EN_EJECUCION;
        this.running = true;

        boolean analisis1 = this.solicitud.doAnalisis1();
        boolean analisis2_img = this.solicitud.doAnalisis2_extract_img();
        boolean analisis2_links = this.solicitud.doAnalisis2_extract_links();
        boolean analisis3 = this.solicitud.doAnalisis3();

        if (analisis1) {
            try {
                for (int i = 0; i < esclavos.size(); i++) {
                    for (int j = 0; j < analisisDeElementos(solicitud).size(); j++) {
                        if (esclavos.get(i).execute(analisisDeElementos(solicitud).get(j))) {
                            
                        }else{
                            if (i+1<=esclavos.size()) {
                                esclavos.get(i+1).execute(analisisDeElementos(solicitud).get(j));
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.solicitud.getData().getChild(Utility.RESULTADO).addContent(data_analisis1);
                SistemaSingleton.getInstance().agregarSolicitud(solicitud); 
                
            } catch (IOException ex) {
                Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyManagementException ex) {
                Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        estado = Utility.ESTADO_FINALIZADO;
//        SistemaSingleton.getInstance().agregarResultado(solicitud, resultado);
        System.out.println("Analisis finalizado");
    }//run

    public List<Runnable> analisisDeElementos(Solicitud solicitud) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        List<Runnable> tasks = new ArrayList<>();
        Document doc = AnalizadorURL.conectarURL(solicitud.data_getUrl());
        Runnable task_Subtitles = new Runnable() {
            @Override
            public void run() {
                int subtitulos = doc.getElementsByTag(Utility.TAG_H1).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H2).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H3).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H4).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H5).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H6).size();

                org.jdom.Element eSubtitulos = new org.jdom.Element(Utility.SUBTITLES);
                eSubtitulos.setAttribute(Utility.CANTIDAD, subtitulos + "");
                data_analisis1.addContent(eSubtitulos);
                System.out.println("Runnable terminado");
            }
        };
        tasks.add(task_Subtitles);
        
        Runnable task_imagenes = new Runnable() {
            @Override
            public void run() {
                int subtitulos = doc.getElementsByTag(Utility.TAG_H1).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H2).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H3).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H4).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H5).size();
                subtitulos += doc.getElementsByTag(Utility.TAG_H6).size();

                org.jdom.Element eSubtitulos = new org.jdom.Element(Utility.SUBTITLES);
                eSubtitulos.setAttribute(Utility.CANTIDAD, subtitulos + "");
                data_analisis1.addContent(eSubtitulos);
                System.out.println("Runnable terminado");
            }
        };
        tasks.add(task_Subtitles);
        
        

//        org.jdom.Element eAnalisis1 = new org.jdom.Element(Utility.ANALISIS1);
//
//        org.jdom.Element eImagenes = new org.jdom.Element(Utility.IMAGES);
//        eImagenes.setAttribute(Utility.CANTIDAD,
//                doc.getElementsByTag(Utility.TAG_IMG).size() + "");
//        eAnalisis1.addContent(eImagenes);
//
//        org.jdom.Element eA = new org.jdom.Element(Utility.LINKS);
//        eA.setAttribute(Utility.CANTIDAD,
//                doc.getElementsByTag(Utility.TAG_A).size() + "");
//        eAnalisis1.addContent(eA);
//
//        org.jdom.Element eVideos = new org.jdom.Element(Utility.VIDEOS);
//        eVideos.setAttribute(Utility.CANTIDAD,
//                doc.getElementsByTag(Utility.TAG_VIDEO).size() + "");
//        eAnalisis1.addContent(eVideos);
//
//        org.jdom.Element eTitulos = new org.jdom.Element(Utility.TITLES);
//        eTitulos.setAttribute(Utility.CANTIDAD,
//                doc.getElementsByTag(Utility.TAG_TITLE).size() + "");
//        eAnalisis1.addContent(eTitulos);
//
//        org.jdom.Element eTablas = new org.jdom.Element(Utility.TABLES);
//        eTablas.setAttribute(Utility.CANTIDAD,
//                doc.getElementsByTag(Utility.TAG_TABLE).size() + "");
//        eAnalisis1.addContent(eTablas);
//
        return tasks;
    }//analisisDeElementos

    protected void analisisDeElementosyExtraccion(Solicitud solicitud) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Document doc = AnalizadorURL.conectarURL(solicitud.data_getUrl());

        for (Element element : doc.getElementsByTag(Utility.TAG_IMG)) {
            System.out.println(element.attr(Utility.ATTRI_SRC));
//            DescargaArchivo da = new DescargaArchivo(element.attr(Utility.ATTRI_SRC), destino);
        }

    }//analisisDeElementosyExtraccion

    protected void analisisDeExtraccionYComparacion() {

    }//analisisDeExtraccionYComparacion

    protected boolean validarSolicitud(Solicitud solicitud) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        boolean urlValida = urlValida = AnalizadorURL.conectarURL(solicitud.data_getUrl()) != null;
        return (solicitud.esValida() && urlValida);
    }//validarSolicitud

}//class
