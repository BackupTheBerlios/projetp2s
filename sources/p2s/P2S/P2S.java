package P2S;

import P2S.UI.View.*;
import P2S.Control.*;
import P2S.Control.Bundle.*;
import P2S.Inf.*;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 * Classe contenant le main()
 * @author Fabien
 */
public class P2S {
    
        /**
     * Controller qui permet de changer la langue de l'application
     */    
    public static LocaleController ControllerLocale;
    
    /**
     * Constructeur de la classe P2S
     */
    public P2S(){
    }
    
    /**
     * M�thode main
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Recuperation et initialisation de la langue
        ControllerLocale = new LocaleController();
        //ParserXMLPreferences parserPref = new ParserXMLPreferences("E:\\Documents and Settings\\Fabien\\Bureau\\BE\\sources\\sources\\P2S\\P2S\\preferences.xml");
        //Bundle.setCurrentLocale(new Locale(parserPref.lireLangue()));
        Bundle.setCurrentLocale(Locale.FRENCH);
        new JFrameP2S().show();
    }
    
}