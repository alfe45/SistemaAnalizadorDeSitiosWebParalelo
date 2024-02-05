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
    public String tipo() {
        return Utility.EXAMINADOR + "-" + this.rol;
    }//tipo

}//class
