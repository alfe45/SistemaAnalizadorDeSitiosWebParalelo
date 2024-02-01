package Domain;

import Data.UsuarioData;
import Utility.Utility;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.JDOMException;

public class SistemaSingleton {

    //SISTEMA ANALIZADOR DE SITIOS WEB PARALELO
    private static SistemaSingleton sistema;

    private Usuario usuario;

    private AnalizadorURL analizadorURL;

    private UsuarioData usuarioData;

    private SistemaSingleton() {
        this.usuario = null;
        this.analizadorURL = null;
        try {
            this.usuarioData = new UsuarioData();
        } catch (JDOMException | IOException ex) {
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

    public UsuarioData getUsuarioData() {
        return usuarioData;
    }

    public boolean login(String username, String password) {
        boolean login = false;
        Usuario usuario = this.usuarioData.getUsuario(username);
        if (usuario == null) {
            return false;
        } else {
            if (usuario.getPassword().equals(password)) {
                this.usuario = usuario;
                return true;
            } else {
                return false;
            }
        }
    }//login
    
    public void logOut(){
        this.usuario = null;
        this.analizadorURL = null;
    }//logOut
    
    public boolean UserIsLogged(){
        return this.usuario!=null;
    }//isLogged

    @Override
    public String toString() {
        return "SistemaSingleton{" + "usuario=" + usuario + ", analizadorURL=" + analizadorURL + ", usuarioData=" + usuarioData + '}';
    }

    //METODOS PRIVADOS
    private boolean activarAnalizador() {
        if (this.usuario instanceof UsuarioExaminador) {
            if (((UsuarioExaminador) this.usuario).getRol().equals(Utility.ROL_ANALISTA)) {
                this.analizadorURL = new AnalizadorURL();
                return true;
            }
            return false;
        }
        return false;
    }//activarAnalizador

}//class
