package temp;

import Domain.Analizador.AnalizadorURL;
import Domain.Analizador.Solicitud;
import Utility.Encryptor;
import Utility.Utility;
import java.io.File;
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
        String url = "https://www.bjjtime.com/Identity/Account/Login?ReturnUrl=%2F";
        Solicitud solicitud = new Solicitud(url, true,
                false,
                true,
                true,
                true);
        AnalizadorURL analizadorURL = new AnalizadorURL();
        analizadorURL.analizarSolicitud(solicitud, 100, 1);

//        File dir = new File("./downloads");
//        if (!dir.exists()) {
//            if (dir.mkdirs()) {
//                System.out.println("Directorio creado " + dir.getAbsolutePath());
//            } else {
//                System.out.println("Error al crear directorio " + dir.getAbsolutePath());
//            }
//        }
//
//        String url = "https://decodigo.com/wp-content/uploads/2019/05/java_crear_txt_01.jpg";
//
//        String rute = dir.getPath() + "/" + Encryptor.encrypt(url, Encryptor.MD2);
//        File dir2 = new File(rute);
//        if (!dir2.exists()) {
//            if (dir2.mkdirs()) {
//                System.out.println("Directorio creado " + dir2.getAbsolutePath());
//            } else {
//                System.out.println("Error al crear directorio " + dir2.getAbsolutePath());
//            }
//        }
//
//        int id = 0;
//        String id2 = Encryptor.encrypt(url, Encryptor.MD2);
//
//        Thread h1 = new Thread(new Download(url, rute, 1));
//        h1.start();
    }//test

}//class
