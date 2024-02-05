package Business;

import Data.SolicitudData;
import Domain.Analizador.Solicitud;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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

    public List<Solicitud> loadSolicitudes(String username) {
        return this.solicitudData.loadSolicitudes(username);
    }//loadSolicitudes

    public List<Solicitud> loadAllSolicitudes() {
        return this.solicitudData.loadAllSolicitudes();
    }//loadAllSolicitudes

}//class
