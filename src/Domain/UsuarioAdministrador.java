
package Domain;

import Utility.Rol;

public class UsuarioAdministrador extends Usuario{

    public UsuarioAdministrador(String username, String password) {
        super(username, password);
    }
    
    public boolean darUsuarioDeBaja(String idUsuario){
        
        return false;
    }//darUsuarioDeBaja
    
    public boolean darUsuarioDeAlta(Usuario usuario){
        this.asignarRol(usuario, Rol.ANALISTA);
        
        
        return false;
    }//darUsuarioDeAlta
    
    public boolean asignarRol(Usuario usuario, Rol rol){
        
        return false;
    }//asignarRol
    
}//class
