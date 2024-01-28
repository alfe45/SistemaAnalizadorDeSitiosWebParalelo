package Domain;

import java.util.ArrayList;

public class SistemaSingleton {

    //SISTEMA ANALIZADOR DE SITIOS WEB PARALELO
    private static SistemaSingleton sistema;

    private ArrayList<Usuario> usuarios;
    
    private SistemaSingleton() {
        this.usuarios = new ArrayList<>();
    }

    public static SistemaSingleton getInstance() {
        if (sistema == null) {
            sistema = new SistemaSingleton();
        }
        return sistema;
    }//getInstance

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    

}//class