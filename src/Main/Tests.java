package Main;

import Data.UsuarioData;
import Domain.AnalizadorURL;
import Domain.Solicitud;
import Domain.UsuarioAdministrador;
import Domain.UsuarioExaminador;
import Utility.Utility;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import org.jdom.JDOMException;

public class Tests {

    public Tests() {
    }
    
    public static void test() throws JDOMException, IOException, NoSuchAlgorithmException, KeyManagementException{
//        testUsuarioData();
        testAnalizadorURL();
    }

    private static void testUsuarioData() throws JDOMException, IOException {
        UsuarioData usuarioData = new UsuarioData();

        UsuarioAdministrador kevinAdmin = new UsuarioAdministrador("Kevin", "1234");
        UsuarioExaminador alexExaminador = new UsuarioExaminador("Alex", "1234");
        System.out.println("Kevin y alex creados");
        kevinAdmin.asignarRol(alexExaminador, Utility.ROL_ANALISTA);
        System.out.println(kevinAdmin.getUsername()
                + " asigno a " + alexExaminador.getUsername() + " el rol de " + Utility.ROL_ANALISTA);

        System.out.println(kevinAdmin.getUsername() + " guardado: "
                + usuarioData.saveNewUsuario(kevinAdmin));

        System.out.println(alexExaminador.getUsername() + " guardado: "
                + usuarioData.saveNewUsuario(alexExaminador));

        alexExaminador.setUsername("Alex");
        alexExaminador.setPassword("12345678");
        alexExaminador.setRol(Utility.ROL_GESTOR);

        System.out.println(alexExaminador.getUsername() + " sobreescrito: "
                + usuarioData.overrideUsuario(alexExaminador));

        System.out.println(usuarioData.getUsuario("alex"));

    }//test

    private static void testAnalizadorURL() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        AnalizadorURL analizadorURL = new AnalizadorURL();
        String url = "https://es.wikipedia.org/wiki/%C3%81rbol";
        Solicitud solicitud = new Solicitud(url, true, false, false);

        analizadorURL.analisisDeElementos(solicitud);
//        analizadorURL.analisisDeElementosyExtraccion(solicitud);

    }//testAnalizadorURL

}//class
