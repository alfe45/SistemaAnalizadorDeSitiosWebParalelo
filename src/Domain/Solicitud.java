
package Domain;

import Utility.Utility;
import java.util.ArrayList;

public class Solicitud {
    
    private String url;
    private ArrayList<Analisis> analisis;
    private int porcentajeAvance;
    private String estado;

    public Solicitud(String url, boolean analisis1, boolean analisis2, boolean analisis3) {
        this.url = url;
        this.porcentajeAvance = 0;
        this.estado = Utility.EN_PROCESO;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(int porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}//class
