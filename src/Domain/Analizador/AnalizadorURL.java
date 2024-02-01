package Domain.Analizador;

import Domain.Sistema.UsuarioExaminador;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class AnalizadorURL {

    private UsuarioExaminador analista;
    private ArrayList<Analisis> analisis;

    public AnalizadorURL() {
        this.analisis = new ArrayList<>();
    }

    public void setAnalista(UsuarioExaminador analista) {
        this.analista = analista;
    }
    

    public boolean analizarSolicitud(Solicitud solicitud, int subprocesos, int esclavos) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        if (subprocesos >= 1 && esclavos >= 1) {
            Analisis analisis =  new Analisis(solicitud, subprocesos, esclavos);
            this.analisis.add(analisis);
            analisis.start();
            return true;
        } else {
            return false;
        }
    }//analizarSolicitud

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
