package Data;

import Domain.Usuario;
import Domain.UsuarioAdministrador;
import Domain.UsuarioExaminador;
import Utility.Utility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class UsuarioData {

    private Document jdomDocument;
    private Element root;

    public UsuarioData() throws JDOMException, IOException {
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

    public boolean overrideUsuario(Usuario usuario) throws IOException {
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

    public boolean saveNewUsuario(Usuario usuario) throws IOException {
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

    //METODOS PRIVADOS
    private void saveXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.jdomDocument, new PrintWriter(Utility.RUTA_XML_USUARIOS_FILE));
    }//saveXML

    private boolean saveUsuario(Usuario usuario) throws IOException {
        Element eUsuario = new Element(Utility.USUARIO);
        eUsuario.setAttribute(Utility.USERNAME, usuario.getUsername().toLowerCase());
        eUsuario.setAttribute(Utility.PASSWORD, usuario.getPassword().toLowerCase());
        String s = "";
        if (usuario instanceof UsuarioAdministrador) {
            s = Utility.ADMIN;
        } else {
            s = Utility.EXAMINADOR;
            Element eRol = new Element(Utility.ROL);
            eRol.addContent(((UsuarioExaminador) usuario).getRol());
            eUsuario.addContent(eRol);
        }
        eUsuario.setAttribute(Utility.TIPO_USUARIO, s);

        this.root.addContent(eUsuario);

        saveXML();
        return true;
    }//saveUsuario

}//class
