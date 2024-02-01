package Domain.Sistema;

import Utility.Utility;

public class UsuarioExaminador extends Usuario {

    private String rol;

    public UsuarioExaminador(String username, String password) {
        super(username, password);
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length() - 1) + ", rol=" + rol + '}';
    }
    
    @Override
    public String tipo() {
        return Utility.EXAMINADOR;
    }//tipo

}//class
