package Main;

import GUI.JFWindow;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
    C22910 ALEXANDER FERNANDEZ D.
    KEVING VEGA
    FABIO 
 */

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        JFWindow.getInstance().setVisible(true);
    }//class

}//class
