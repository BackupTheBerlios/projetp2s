package P2S;

import P2S.Control.*;
import P2S.Control.Bundle.Bundle;
import P2S.Inf.ParserXMLPreferences;
import P2S.UI.View.JFrameP2S;
import java.io.*;
import java.net.URL;

import java.util.Locale;

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
        ParserXMLPreferences parserPref = new ParserXMLPreferences(readFile("P2S/preferences.xml"));
        Bundle.setCurrentLocale(new Locale(parserPref.lireLangue()));
        
        // Fenetre principale
        new JFrameP2S().show();    
    }
    
    
    
    
    public static InputStream readFile(String filename) {
        DataInputStream dis = new DataInputStream(ClassLoader.getSystemResourceAsStream(filename));
        return dis;      
    }
    
    public static URL getRessource(String path){
        return ClassLoader.getSystemResource(path);
    }
    
}
