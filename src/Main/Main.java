package Main;

import GUI.JFVentanaPrincipalSingleton;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
    C22910 ALEXANDER FERNANDEZ D.
 */
public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        JFVentanaPrincipalSingleton.getInstance().setVisible(true);
    }//main

}//class
