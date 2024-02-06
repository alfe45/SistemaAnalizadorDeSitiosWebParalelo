package Domain.Sistema;

import Business.SolicitudBusiness;
import Business.UsuarioBusiness;
import Utility.Encryptor;
import Utility.Utility;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.JDOMException;
import Domain.Analizador.AnalizadorURL;
import Domain.Analizador.Solicitud;
import java.security.KeyManagementException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Element;

public class SistemaSingleton {

    //SISTEMA ANALIZADOR DE SITIOS WEB PARALELO
    private static SistemaSingleton sistema;

    private Usuario usuario;

    private AnalizadorURL analizadorURL;

    private UsuarioBusiness usuarioBusiness;
    private SolicitudBusiness solicitudBusiness;

    private Thread hiloSolicitudes;

    private SistemaSingleton() {
        this.usuario = null;
        this.analizadorURL = null;
        try {
            this.usuarioBusiness = new UsuarioBusiness();
            this.solicitudBusiness = new SolicitudBusiness();
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
                activarAnalizadorURL();
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

    public boolean userIsLogged() {
        return this.usuario != null;
    }//isLogged

    //METODOS PRIVADOS
    private boolean activarAnalizadorURL() {
        if (this.usuario.tipo().contains(Utility.EXAMINADOR)) {
            if (this.usuario.tipo().contains(Utility.ROL_ANALISTA)) {
                this.analizadorURL = new AnalizadorURL();
                return true;
            }
            return false;
        }
        return false;
    }//activarAnalizador

    public boolean agregarSolicitud(Solicitud solicitud) {
        if (solicitud.esValida()) {
            try {
                return this.solicitudBusiness.saveNewSolicitud(solicitud);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(SistemaSingleton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyManagementException ex) {
                Logger.getLogger(SistemaSingleton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SistemaSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }//agregarSolicitud

    public boolean urlValida(String url) {
        return AnalizadorURL.validarURL(url);
    }//urlValida

    public List<Solicitud> misDatos(String username) {
        Usuario usuario = this.usuarioBusiness.getUsuario(username);
        List<Solicitud> datos = new ArrayList<>();
        if (usuario != null) {
            if (this.usuario.tipo().contains(Utility.DIGITADOR)) {
                datos = this.solicitudBusiness.loadSolicitudes(username, Utility.ROL_DIGITADOR);
                return datos;
            }
            if (this.usuario.tipo().contains(Utility.ROL_GESTOR)) {
                datos = this.solicitudBusiness.loadSolicitudes(username, Utility.ROL_GESTOR);
                return datos;
            }
            if (this.usuario.tipo().contains(Utility.ROL_ANALISTA)) {
                datos = this.solicitudBusiness.loadSolicitudes(username, Utility.ROL_ANALISTA);
                return datos;
            }
        }
        return null;
    }//misDatos

    public boolean asignarSolicitud(Solicitud solicitud, String analistaUsername) {
        if (!analistaUsername.isBlank()) {
            solicitud.data_setAnalista(analistaUsername);
            solicitud.data_setGestor(this.usuario.getUsername());
            try {
                return this.solicitudBusiness.overrideSolicitud(solicitud);
            } catch (IOException ex) {
                Logger.getLogger(SistemaSingleton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(SistemaSingleton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyManagementException ex) {
                Logger.getLogger(SistemaSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }//asignarSolicitud;

    public boolean agregarResultado(Solicitud solicitud, Element resultado){
        try {
            return this.solicitudBusiness.agregarResultado(solicitud, resultado);
        } catch (IOException ex) {
            Logger.getLogger(SistemaSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }//agregarResultado;

    public boolean analizarSolicitud(Solicitud solicitudSeleccionada, int subprocesos, int esclavos) {
        if (this.analizadorURL != null) {
            try {
                return this.analizadorURL.analizarSolicitud(solicitudSeleccionada, subprocesos, esclavos);
            } catch (IOException ex) {
                Logger.getLogger(SistemaSingleton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(SistemaSingleton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyManagementException ex) {
                Logger.getLogger(SistemaSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }//crearAnalisisSolicitud

    public void agregarSolicitud2(Solicitud solicitud, boolean enlaces, boolean imagenes) {

    }

}//class
