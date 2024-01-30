
package Domain;

public class Solicitud {
    
    private String url;
    private boolean[] tipoAnalisis;

    public Solicitud(String url, boolean analisis1, boolean analisis2, boolean analisis3) {
        this.url = url;
        this.tipoAnalisis = new boolean[3];
        this.tipoAnalisis[0] = analisis1;
        this.tipoAnalisis[1] = analisis2;
        this.tipoAnalisis[2] = analisis3;
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

    @Override
    public String toString() {
        return "URL = '"+url+"' ; ANALISIS1= "+tipoAnalisis[0]
                +", ANALISIS2= "+tipoAnalisis[1]
                +", ANALISIS3= "+tipoAnalisis[2];
    }
      
}//class
