
package Domain;

import Utility.Utility;

public class Solicitud {
    
    private String url;
    private boolean[] tipoAnalisis;
    private int porcentajeAvance;
    private String estado;

    public Solicitud(String url, boolean analisis1, boolean analisis2, boolean analisis3) {
        this.url = url;
        this.tipoAnalisis = new boolean[3];
        this.tipoAnalisis[0] = analisis1;
        this.tipoAnalisis[1] = analisis2;
        this.tipoAnalisis[2] = analisis3;
        this.porcentajeAvance = 0;
        this.estado = Utility.EN_PROCESO;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean[] getTipoAnalisis() {
        return tipoAnalisis;
    }

    public void setTipoAnalisis(boolean[] tipoAnalisis) {
        this.tipoAnalisis = tipoAnalisis;
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

    @Override
    public String toString() {
        return "Solicitud{" + "url=" + url + ", tipoAnalisis=" + tipoAnalisis + ", porcentajeAvance=" + porcentajeAvance + ", estado=" + estado + '}';
    }
    

}//class
