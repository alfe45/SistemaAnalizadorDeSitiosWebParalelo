package Business;

import Data.SolicitudData;
import Domain.Analizador.Solicitud;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.jdom.Element;
import org.jdom.JDOMException;

public class SolicitudBusiness {

    private SolicitudData solicitudData;

    public SolicitudBusiness() throws JDOMException, IOException, NoSuchAlgorithmException {
        this.solicitudData = new SolicitudData();
    }

    public boolean saveNewSolicitud(Solicitud solicitud) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        return this.solicitudData.saveNewSolicitud(solicitud);
    }//saveNewSolicitud

    public boolean overrideSolicitud(Solicitud solicitud) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        return this.solicitudData.overrideSolicitud(solicitud);
    }//overrideSolicitud

    public List<Solicitud> loadSolicitudes(String username, String rol) {
        return this.solicitudData.loadSolicitudes(username, rol);
    }//loadSolicitudes

    public boolean agregarResultado(Solicitud solicitud, Element resultado) throws IOException {
       return this.solicitudData.agregarResultado(solicitud,resultado);
    }//agregarResultado

}//class
