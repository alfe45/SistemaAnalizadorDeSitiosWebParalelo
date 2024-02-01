package Domain;

import Utility.Utility;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import org.jsoup.nodes.Element;

public class AnalizadorURL {

    public AnalizadorURL() {
    }

    public boolean analizar(Solicitud solicitud, int subprocesos, int esclavos) {

        return true;
    }//analizar

    public org.jdom.Document analisisDeElementos(Solicitud solicitud) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Document doc = conectarURL(solicitud.getUrl());
        
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
                doc.getElementsByTag(Utility.TAG_A).size()+ "");
        root.addContent(eA);

        org.jdom.Element eVideos = new org.jdom.Element(Utility.VIDEOS);
        eVideos.setAttribute(Utility.CANTIDAD,
                doc.getElementsByTag(Utility.TAG_VIDEO)+ "");
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
                doc.getElementsByTag(Utility.TAG_TABLE).size()+ "");
        root.addContent(eTablas);
        
        return documentoXML;
        
//        System.out.println("imagenes = " + imgs);
//        System.out.println("links = " + a);
//        System.out.println("videos = " + videos);
//        System.out.println("titulos = " + titulos);
//        System.out.println("subtitulos = " + subtitulos);
//        System.out.println("tablas = " + tablas);
//        System.out.println(root.getChildren().size());
//        for (Object element : root.getChildren()) {
//            System.out.print(((Element)element).toString()+" "+((Element)element).getAttributeValue(Utility.CANTIDAD));
//            System.out.println("");
//        }
    }//analisisDeElementos

    public void analisisDeElementosyExtraccion(Solicitud solicitud) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Document doc = conectarURL(solicitud.getUrl());
        
        for (Element element : doc.getElementsByTag(Utility.TAG_IMG)) {
            System.out.println(element.attr(Utility.ATTRI_SRC));
//            DescargaArchivo da = new DescargaArchivo(element.attr(Utility.ATTRI_SRC), destino);
        }

    }//analisisDeElementosyExtraccion

    public void analisisDeExtraccionYComparacion() {

    }//analisisDeExtraccionYComparacion

    private Document conectarURL(String url) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        desactivarCertificado();
        return Jsoup.connect(url).get();
    }//conectarURL

    private void desactivarCertificado() throws NoSuchAlgorithmException, KeyManagementException {
        // Crear un administrador de confianza que no realice ninguna validación del certificado
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };

        // Configurar la conexión SSL para desactivar la validación del certificado
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        // Desactivar la verificación del host
        HostnameVerifier allHostsValid = (hostname, session) -> true;
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }//desactivarCertificado
    
    
    
    
    
}//class
