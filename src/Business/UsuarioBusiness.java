
package Business;

import Data.UsuarioData;
import Domain.Usuario;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.jdom.JDOMException;

public class UsuarioBusiness {
    private UsuarioData usuarioData;

    public UsuarioBusiness() throws JDOMException, IOException, NoSuchAlgorithmException {
        this.usuarioData = new UsuarioData();
    }
    
    public boolean overrideUsuario(Usuario usuario) throws IOException, NoSuchAlgorithmException {
        return this.usuarioData.overrideUsuario(usuario);
    }//overrideUsuario

    public boolean saveNewUsuario(Usuario usuario) throws IOException, NoSuchAlgorithmException {
        return this.usuarioData.saveNewUsuario(usuario);
    }//saveNewUsuario

    public boolean deleteUsuario(String username) throws IOException {
        return this.usuarioData.deleteUsuario(username);
    }//saveNewUsuario

    public boolean exists(String username) {
        return this.usuarioData.exists(username);
    }//exists

    public Usuario getUsuario(String username) {
        return this.usuarioData.getUsuario(username);
    }//exists

    public List<Usuario> loadUsuarios() {
        return this.usuarioData.loadUsuarios();
    }//loadUsuarios

    public List<String> loadNombresUsuarios() {
        return this.usuarioData.loadNombresUsuarios();
    }//loadUsuarios
    
}//class
