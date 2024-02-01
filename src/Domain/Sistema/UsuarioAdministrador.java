
package Domain.Sistema;

import Domain.Sistema.UsuarioExaminador;
import Utility.Utility;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class UsuarioAdministrador extends Usuario{

    public UsuarioAdministrador(String username, String password) {
        super(username, password);
    }
    
    public boolean darUsuarioDeBaja(String idUsuario) throws IOException{
        return SistemaSingleton.getInstance().getUsuarioBusiness().deleteUsuario(idUsuario);
    }//darUsuarioDeBaja
    
    public boolean darUsuarioDeAlta(Usuario usuario) throws IOException, NoSuchAlgorithmException{
        return SistemaSingleton.getInstance().getUsuarioBusiness().saveNewUsuario(usuario);
    }//darUsuarioDeAlta
    
    public boolean asignarRol(UsuarioExaminador usuario, String rol){
        usuario.setRol(rol);
        return true;
    }//asignarRol

    @Override
    public String tipo() {
        return Utility.ADMIN;
    }//tipo

}//class
