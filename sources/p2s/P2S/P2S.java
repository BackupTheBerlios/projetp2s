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
     * Methode main
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        P2S p = new P2S();
        
        // Recuperation et initialisation de la langue
        ControllerLocale = new LocaleController();        
        ParserXMLPreferences parserPref = new ParserXMLPreferences(p.getClassPath());        
        Bundle.setCurrentLocale(new Locale(parserPref.lireLangue()));       
        
        // Fenetre principale
        new JFrameP2S().show();
    }
    
    
    /**
     * Cette methode permet d'avoir le chemin absolu du fichier preference
     * @return le chemin du fichier preferences
     */
    private String getClassPath(){
        return getClass().getResource("/P2S/preferences.xml").getPath();
    }
    
}
