package Data;

import Domain.Usuario;
import Domain.UsuarioAdministrador;
import Domain.UsuarioExaminador;
import Utility.Encryptor;
import Utility.Utility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class UsuarioData {

    private Document jdomDocument;
    private Element root;

    public UsuarioData() throws JDOMException, IOException, NoSuchAlgorithmException {
        File file = new File(Utility.RUTA_XML_USUARIOS_FILE);
        if (file.exists()) {
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            this.jdomDocument = saxBuilder.build(file);
            this.root = this.jdomDocument.getRootElement();
        } else {
            this.root = new Element(Utility.USUARIOS);
            this.jdomDocument = new Document(this.root);
            saveUsuario(new UsuarioAdministrador("admin", "admin"));
            saveXML();
        }//if
    }

    public boolean overrideUsuario(Usuario usuario) throws IOException, NoSuchAlgorithmException {
        if (exists(usuario.getUsername())) {
            List<Element> usuarios = this.root.getChildren();
            for (Element eUsuario : usuarios) {
                if (eUsuario.getAttributeValue(Utility.USERNAME).equals(usuario.getUsername().toLowerCase())) {
                    this.root.removeContent(eUsuario);
                    return saveUsuario(usuario);
                }
            }
        }
        return false;
    }//overrideUsuario

    public boolean saveNewUsuario(Usuario usuario) throws IOException, NoSuchAlgorithmException {
        if (!exists(usuario.getUsername())) {
            return saveUsuario(usuario);
        }
        return false;
    }//saveNewUsuario

    public boolean deleteUsuario(String username) throws IOException {
        if (exists(username)) {
            List<Element> usuarios = this.root.getChildren();
            for (Element eUsuario : usuarios) {
                if (eUsuario.getAttributeValue(Utility.USERNAME).equals(username)) {
                    this.root.removeContent(eUsuario);
                    saveXML();
                    return true;
                }
            }
        }
        return false;
    }//saveNewUsuario

    public boolean exists(String username) {
        List<Element> usuarios = this.root.getChildren();
        for (Element eUsuario : usuarios) {
            if (eUsuario.getAttributeValue(Utility.USERNAME).equals(username.toLowerCase())) {
                return true;
            }
        }
        return false;
    }//exists

    public Usuario getUsuario(String username) {
        List<Element> usuarios = this.root.getChildren();
        for (Element eUsuario : usuarios) {
            if (eUsuario.getAttributeValue(Utility.USERNAME).equals(username.toLowerCase())) {
                switch (eUsuario.getAttributeValue(Utility.TIPO_USUARIO)) {
                    case Utility.ADMIN:
                        return new UsuarioAdministrador(
                                eUsuario.getAttributeValue(Utility.USERNAME),
                                eUsuario.getAttributeValue(Utility.PASSWORD));
                    case Utility.EXAMINADOR:
                        UsuarioExaminador usuario = new UsuarioExaminador(eUsuario.getAttributeValue(Utility.USERNAME),
                                eUsuario.getAttributeValue(Utility.PASSWORD));
                        usuario.setRol(eUsuario.getChildText(Utility.ROL));
                        return usuario;
                    default:
                        throw new AssertionError();
                }
            }
        }
        return null;
    }//exists
    
    public List<Usuario> loadUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        List<Element> eUsuarios = this.root.getChildren();
        for (Element eUsuario : eUsuarios) {
            switch (eUsuario.getAttributeValue(Utility.TIPO_USUARIO)) {
                case Utility.ADMIN:
                    usuarios.add(new UsuarioAdministrador(
                            eUsuario.getAttributeValue(Utility.USERNAME),
                            eUsuario.getAttributeValue(Utility.PASSWORD)));
                    break;
                case Utility.EXAMINADOR:
                    UsuarioExaminador usuario = new UsuarioExaminador(eUsuario.getAttributeValue(Utility.USERNAME),
                            eUsuario.getAttributeValue(Utility.PASSWORD));
                    usuario.setRol(eUsuario.getChildText(Utility.ROL));
                    usuarios.add(usuario);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        return usuarios;
    }//loadUsuarios

    public List<String> loadNombresUsuarios() {
        List<String> usuarios = new ArrayList<>();
        List<Element> eUsuarios = this.root.getChildren();
        for (Element eUsuario : eUsuarios) {
            usuarios.add(eUsuario.getAttributeValue(Utility.USERNAME));
        }
        return usuarios;
    }//loadUsuarios

    //METODOS PRIVADOS
    private void saveXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.jdomDocument, new PrintWriter(Utility.RUTA_XML_USUARIOS_FILE));
    }//saveXML

    private boolean saveUsuario(Usuario usuario) throws IOException, NoSuchAlgorithmException {
        Element eUsuario = new Element(Utility.USUARIO);
        eUsuario.setAttribute(Utility.USERNAME, usuario.getUsername().toLowerCase());
        String encrytedPassword = Encryptor.encrypt(usuario.getPassword(), Encryptor.SHA256);
        eUsuario.setAttribute(Utility.PASSWORD, encrytedPassword);
        switch (usuario.tipo()) {
            case Utility.ADMIN:
                eUsuario.setAttribute(Utility.TIPO_USUARIO, Utility.ADMIN);
                break;
            case Utility.EXAMINADOR:
                eUsuario.setAttribute(Utility.TIPO_USUARIO, Utility.EXAMINADOR);
                Element eRol = new Element(Utility.ROL);
                eRol.addContent(((UsuarioExaminador) usuario).getRol());
                eUsuario.addContent(eRol);
                break;
            default:
                throw new AssertionError();
        }
        this.root.addContent(eUsuario);
        saveXML();
        return true;
    }//saveUsuario

    

}//class
