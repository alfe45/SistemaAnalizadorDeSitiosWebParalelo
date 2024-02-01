package Domain.Analizador;

import Utility.Utility;

public class Solicitud {

    private String url;
    private int porcentajeAvance;
    private String estado;
    private boolean[] analisis;

    public Solicitud(String url, boolean analisis1, boolean analisis2, boolean analisis3) {
        this.url = url;
        this.porcentajeAvance = 0;
        this.estado = Utility.EN_ESPERA;
        this.analisis = new boolean[3];
        this.analisis[0] = analisis1;
        this.analisis[1] = analisis2;
        this.analisis[2] = analisis3;
    }

    public boolean doAnalisis1() {
        return this.analisis[0];
    }

    public boolean doAnalisis2() {
        return this.analisis[1];
    }

    public boolean doAnalisis3() {
        return this.analisis[2];
    }

    public String getUrl() {
        return url;
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
