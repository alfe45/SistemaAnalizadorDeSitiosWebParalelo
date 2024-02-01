
package Domain;

import Utility.Utility;

public class Analisis {
    
    private String estado;
    private String resultado;

    public Analisis() {
        this.estado = Utility.ESTADO_PENDIENTE;
        this.resultado = "";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
}//class