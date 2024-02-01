
package Domain;

import java.io.IOException;

public class UsuarioAdministrador extends Usuario{

    public UsuarioAdministrador(String username, String password) {
        super(username, password);
    }
    
    public boolean darUsuarioDeBaja(String idUsuario) throws IOException{
        return SistemaSingleton.getInstance().getUsuarioData().deleteUsuario(idUsuario);
    }//darUsuarioDeBaja
    
    public boolean darUsuarioDeAlta(Usuario usuario) throws IOException{
        return SistemaSingleton.getInstance().getUsuarioData().saveNewUsuario(usuario);
    }//darUsuarioDeAlta
    
    public boolean asignarRol(UsuarioExaminador usuario, String rol){
        usuario.setRol(rol);
        return true;
    }//asignarRol

}//class
