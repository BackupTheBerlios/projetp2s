/*
 * FiltreXml.java
 *
 * Created on 13 mai 2004, 10:57
 */

package P2S.UI.View;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * Cette classe représente un filtre pour fichiers
 */
public class FiltreFichier extends FileFilter 
{
    /**
     * Tableau des suffixes autorisés
     */    
    String []lesSuffixes;
    /**
     * La description du filtre
     */    
    String  laDescription;

    /**
     * Crée une instance de FiltreFichier
     * @param lesSuffixes Les extensions a autoriser
     * @param laDescription La description du filtre
     */    
    public FiltreFichier(String[] lesSuffixes, String laDescription){
        this.lesSuffixes = lesSuffixes;
        this.laDescription = laDescription;
    }

    /**
     * Renvoie si l'extension est valide
     * @param suffixe extension
     * @return true si l'extension est valide
     */    
    boolean appartient( String suffixe )
    {
        for( int i = 0; i<lesSuffixes.length; ++i){
            if(suffixe.equals(lesSuffixes[i])) return true;
        }
        return false;
    }

    /**
     * Renvoie si le fichier est valide
     * @param f le fichier
     * @return true si l'extension du fichier est valide
     */    
    public boolean accept(File f) 
    {
        if (f.isDirectory()) {
            return true;
    }

    String suffixe = null;
    String s = f.getName();
    
    int i = s.lastIndexOf('.');
    if (i > 0 &&  i < s.length() - 1) 
    {
        suffixe = s.substring(i+1).toLowerCase();
     }

     return suffixe != null && appartient(suffixe);
    }

   
    /**
     * Renvoie la description du filtre
     * @return Description du filtre
     */    
    public String getDescription() {
        return laDescription;
   }
}
