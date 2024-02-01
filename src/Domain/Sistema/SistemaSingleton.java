package Domain.Sistema;

import Business.UsuarioBusiness;
import Utility.Encryptor;
import Utility.Utility;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.JDOMException;
import Domain.Analizador.AnalizadorURL;

public class SistemaSingleton {

    //SISTEMA ANALIZADOR DE SITIOS WEB PARALELO
    private static SistemaSingleton sistema;

    private Usuario usuario;

    private AnalizadorURL analizadorURL;

    private UsuarioBusiness usuarioBusiness;

    private SistemaSingleton() {
        this.usuario = null;
        this.analizadorURL = null;
        try {
            this.usuarioBusiness = new UsuarioBusiness();
        } catch (JDOMException | IOException | NoSuchAlgorithmException ex) {
            Logger.getLogger(SistemaSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static SistemaSingleton getInstance() {
        if (sistema == null) {
            sistema = new SistemaSingleton();
        }
        return sistema;
    }//getInstance

    public Usuario getUsuario() {
        return usuario;
    }

    public AnalizadorURL getAnalizadorURL() {
        return analizadorURL;
    }

    public UsuarioBusiness getUsuarioBusiness() {
        return usuarioBusiness;
    }

    public boolean login(String username, String password) throws NoSuchAlgorithmException {
        boolean login = false;
        Usuario usuario = this.usuarioBusiness.getUsuario(username);
        if (usuario == null) {
            return false;
        } else {
            if (usuario.getPassword().equals(Encryptor.encrypt(password, Encryptor.SHA256))) {
                this.usuario = usuario;
                System.out.println("Sistema analizador activado: "+activarAnalizadorURL());
                return true;
            } else {
                return false;
            }
        }
    }//login

    public void logOut() {
        this.usuario = null;
        this.analizadorURL = null;
    }//logOut

    public boolean UserIsLogged() {
        return this.usuario != null;
    }//isLogged

    @Override
    public String toString() {
        return "SistemaSingleton{" + "usuario=" + usuario + ", analizadorURL=" + analizadorURL + ", usuarioData=" + usuarioBusiness + '}';
    }

    //METODOS PRIVADOS
    private boolean activarAnalizadorURL() {
        if (this.usuario.tipo().equals(Utility.EXAMINADOR)) {
            if (((UsuarioExaminador) this.usuario).getRol().equals(Utility.ROL_ANALISTA)) {
                this.analizadorURL = new AnalizadorURL();
                this.analizadorURL.setAnalista((UsuarioExaminador) this.usuario);
                return true;
            }
            return false;
        }
        return false;
    }//activarAnalizador

}//class
