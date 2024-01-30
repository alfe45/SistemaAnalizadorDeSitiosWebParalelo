
package Domain;

public class UsuarioAdministrador extends Usuario{

    public UsuarioAdministrador(String username, String password) {
        super(username, password);
    }
    
    public boolean darUsuarioDeBaja(String idUsuario){
        
        return false;
    }//darUsuarioDeBaja
    
    public boolean darUsuarioDeAlta(Usuario usuario){
        return false;
    }//darUsuarioDeAlta
    
    public boolean asignarRol(UsuarioExaminador usuario, String rol){
        usuario.setRol(rol);
        return true;
    }//asignarRol

    
}//class
