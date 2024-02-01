package temp;

import Domain.Analizador.Solicitud;
import Domain.Analizador.AnalizadorURL;
import Domain.Sistema.UsuarioExaminador;
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
        System.out.println("Test Modulo Analizador\n");

        AnalizadorURL moduloAnalizador = new AnalizadorURL();

        UsuarioExaminador usuarioAnalista = new UsuarioExaminador("Alex", "1234");
        moduloAnalizador.setAnalista(usuarioAnalista);

        String url1 = "https://es.wikipedia.org/wiki/Marte_(planeta)";
        Solicitud solicitud1 = new Solicitud(url1, true, false, false);
        String url2 = "https://es.wikipedia.org/wiki/Marco_Aurelio";
        Solicitud solicitud2 = new Solicitud(url2, true, false, false);

        moduloAnalizador.analizarSolicitud(solicitud1, 1, 1);

    }

}//class
