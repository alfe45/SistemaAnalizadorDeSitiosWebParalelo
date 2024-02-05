package Domain.Analizador;

import Domain.Sistema.SistemaSingleton;
import Utility.Utility;

public class Solicitud {

    private String url;
    private boolean[] analisis;
    private String digitador;
    private String analista;
    private String estado;

    public Solicitud(String url, boolean analisis1, boolean analisis2, boolean analisis3) {
        this.url = url;
        this.analisis = new boolean[3];
        this.analisis[0] = analisis1;
        this.analisis[1] = analisis2;
        this.analisis[2] = analisis3;
        this.digitador = SistemaSingleton.getInstance().getUsuario().getUsername();
        this.analista = Utility.NO_ASIGNADO;
        this.estado = Utility.ESTADO_PENDIENTE;
    }

    public Solicitud(String estado, String url, String digitador, String analista, boolean analisis1, boolean analisis2, boolean analisis3) {
        this.url = url;
        this.analisis = new boolean[3];
        this.analisis[0] = analisis1;
        this.analisis[1] = analisis2;
        this.analisis[2] = analisis3;
        this.digitador = digitador;
        this.analista = analista;
        this.estado = estado;
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

    public String getDigitador() {
        return digitador;
    }

    public void setDigitador(String digitador) {
        this.digitador = digitador;
    }

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) {
        this.analista = analista;
    }

    public boolean[] getAnalisis() {
        return analisis;
    }

    public void setAnalisis(boolean[] analisis) {
        this.analisis = analisis;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String data() {
        return this.url + " " + this.analisis[0] + " " + this.analisis[1] + " " + this.analisis[2];
    }//data

    public boolean esValida() {
        return doAnalisis1() || doAnalisis1() || doAnalisis1();
    }//esValida

}//class
