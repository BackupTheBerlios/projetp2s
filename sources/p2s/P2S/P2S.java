package P2S;

import P2S.Control.*;
import P2S.Control.Bundle.Bundle;
import P2S.UI.View.JFrameP2S;
import java.io.*;
import java.net.URL;
import java.util.Locale;


import java.util.Properties;

/**
 * Classe contenant le main()
 * @author Fabien
 */
public class P2S {
    
    /**
     * Controller qui permet de changer la langue de l'application
     */
    public static LocaleController ControllerLocale;
    
    public static String FICHIER_PREFERENCES = "preferences.properties";
    public static Properties Preferences;
    
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
        
        // Chargement des propriétés
        try{
            // Recuperation et initialisation de la langue
            ControllerLocale = new LocaleController();
            
            // Crée et charge les propriétés
            Preferences = new Properties();
            //InputStream in = readFile("app");
            InputStream in = new FileInputStream(FICHIER_PREFERENCES);
            Preferences.load(in);
            in.close();
        }catch(FileNotFoundException e){ // Si le fichier n'existe pas
            try{
                // On crée le fichier des préférences
                File fic = new File(FICHIER_PREFERENCES);
                fic.createNewFile();
                
                // On charge les proprietes par defaut
                InputStream in = readFile("P2S/defaultPreferences.properties");
                Preferences.load(in);
                in.close();
            }catch(IOException e2){
                e2.printStackTrace();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
           
        Bundle.setCurrentLocale(new Locale(Preferences.getProperty("langue")));        
        saveProperties();
        // Fenetre principale
        new JFrameP2S().show();
    }
    
    public static void saveProperties(){
        try{
        FileOutputStream out = new FileOutputStream(FICHIER_PREFERENCES);
        Preferences.store(out, "---Fichier preferences de P2S---");
        out.close(); 
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public static InputStream readFile(String filename) {
        DataInputStream dis = new DataInputStream(ClassLoader.getSystemResourceAsStream(filename));
        return dis;
    }
    
    public static URL getRessource(String path){
        return ClassLoader.getSystemResource(path);
    }
    
}
