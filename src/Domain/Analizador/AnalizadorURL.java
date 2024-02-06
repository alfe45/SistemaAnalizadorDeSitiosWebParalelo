package Domain.Analizador;

import GUI.JFWindow;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class AnalizadorURL {
    
    private ArrayList<Analisis> analisis;

    public AnalizadorURL() {
        this.analisis = new ArrayList<>();
    }
    
    public boolean analizarSolicitud(Solicitud solicitud, int subprocesos, int esclavos) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        if (subprocesos >= 1 && esclavos >= 1) {
            Analisis analisis = new Analisis(solicitud, subprocesos, esclavos);
            this.analisis.add(analisis);
            analisis.start();
            return true;
        } else {
            return false;
        }
    }//crearAnalisisSolicitud

    public static boolean validarURL(String url) {

        try {
            desactivarCertificado();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AnalizadorURL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (KeyManagementException ex) {
            Logger.getLogger(AnalizadorURL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            Jsoup.connect(url).get();
        } catch (java.lang.IllegalArgumentException ex) {
            return false;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(JFWindow.getInstance(), "Error: 403: página no alcanzable", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }//conectarURL

    //METODOS PROTEGIDOS
    protected static Document conectarURL(String url) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        desactivarCertificado();
        return Jsoup.connect(url).get();
    }//conectarURL

    private static void desactivarCertificado() throws NoSuchAlgorithmException, KeyManagementException {
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
