package P2S.Control.Bundle;

import java.util.*;

/**
 * Classe permettant de recuperer le texte dans la langue de la Locale
 * @author Fabien
 */
public class Bundle {

    final private static String BASENAME = "P2S/Control/Bundle/label"; // Chemin des fichiers properties
    private static ResourceBundle bundle = ResourceBundle.getBundle(BASENAME,Locale.getDefault());
    
    /**
     * Constructeur de la classe Bundle
     */
    public Bundle() {
    }
    
    /**
     * Modifie la locale avec la langue voulue
     * @param locale contient la langue
     */    
    public static void setCurrentLocale(Locale locale)
    {
        bundle = ResourceBundle.getBundle(BASENAME,locale);
        Locale.setDefault(locale);
    }
    
    /**
     * Renvoie la valeur(String) associee a la cle
     * @param id cle
     * @return la valeur associee a la cle
     */    
    public static String getText(String id)
    {
        try
        {
             return (bundle.getString(id)) ;
        }
        catch (MissingResourceException e)
        {
             return id ;
        }
    }
    
    /**
     * Renvoie la premiere lettre de la valeur associee a la cle
     * @param id cle
     * @return premiere lettre de la valeur associee a la cle
     */    
    public static char getChar(String id)
    {
        return bundle.getString(id).charAt(0);
    }
    
    /**
     * Renvoie le code(Integer) de la cle
     * @params cle
     * @return code de la cle
     */    
    public static Integer getCode(String s)
    {
        return new Integer((int)getChar(s));
    }
}
