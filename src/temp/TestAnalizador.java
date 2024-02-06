package temp;

import Domain.Analizador.AnalizadorURL;
import Domain.Analizador.Solicitud;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestAnalizador {

    public static void main(String[] args) {
        try {
            test();
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
            Logger.getLogger(TestAnalizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void test() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        String url = "https://www.last.fm/es/user/Al_fe_45";
        Solicitud solicitud = new Solicitud(url, true,
                false, 
                false, 
                false, 
                false);
        
        AnalizadorURL analizadorURL = new AnalizadorURL();
        analizadorURL.analizarSolicitud(solicitud, 1, 1);
        
    }

}//class
