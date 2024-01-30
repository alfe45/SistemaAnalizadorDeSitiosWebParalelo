package Main;

import Data.UsuarioData;
import Domain.Usuario;
import Domain.UsuarioAdministrador;
import Domain.UsuarioExaminador;
import GUI.JFVentanaPrincipalSingleton;
import Utility.Utility;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdom.JDOMException;

/*
    C22910 ALEXANDER FERNANDEZ D.
 */
public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {

//        UIManager.setLookAndFeel(new FlatMacLightLaf());
//        JFVentanaPrincipalSingleton.getInstance().setVisible(true);
        try {
            test();
        } catch (JDOMException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//class

    public static void test() throws JDOMException, IOException {
        UsuarioData usuarioData = new UsuarioData();
//        
//        UsuarioAdministrador kevinAdmin = new UsuarioAdministrador("Kevin", "1234");
//        UsuarioExaminador alexExaminador = new UsuarioExaminador("Alex", "1234");
//        System.out.println("Kevin y alex creados");
//        kevinAdmin.asignarRol(alexExaminador, Utility.ROL_ANALISTA);
//        System.out.println(kevinAdmin.getUsername()
//                + " asigno a " + alexExaminador.getUsername() + " el rol de " + Utility.ROL_ANALISTA);
//
//        System.out.println(kevinAdmin.getUsername() + " guardado: "
//                + usuarioData.saveNewUsuario(kevinAdmin));
//
//        System.out.println(alexExaminador.getUsername() + " guardado: "
//                + usuarioData.saveNewUsuario(alexExaminador));
//        
//        alexExaminador.setUsername("Alex");
//        alexExaminador.setPassword("12345678");
//        alexExaminador.setRol(Utility.ROL_GESTOR);
//        
//        System.out.println(alexExaminador.getUsername() + " sobreescrito: "
//                + usuarioData.overrideUsuario(alexExaminador));

        System.out.println(usuarioData.getUsuario("alex"));

    }//test

}//class
