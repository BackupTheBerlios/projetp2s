/*
 * ParserXMLPreferences.java
 * 
 */

package P2S.Inf;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.*;
import java.io.FileOutputStream;


/**
 * Cette classe permet de lire un fichier XML contenant les preferences pour P2S
 * @author Fabien
 */
public class ParserXMLPreferences {
    
    /**
     * Fichier Xml
     */    
    private String _fichierPreferences;
    /**
     * Document contenant le fichier Xml
     */    
    private Document _document;
    
    /**
     * Crée une instance de ParserXMLPreferences
     * @param Xml Chemin du fichier Xml à lire
     */
    public ParserXMLPreferences(String Xml) {
        this._fichierPreferences = Xml;
        
        try{
            DocumentBuilderFactory usine = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructeur = usine.newDocumentBuilder();
            this._document = constructeur.parse(new FileInputStream(new File(this._fichierPreferences)));
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    
    
    /**
     * Renvoie la langue de P2S
     * @return la langue
     */    
    public String lireLangue()
    {
        Node langue = this._document.getElementsByTagName("langue").item(0);
                
        return langue.getFirstChild().getNodeValue();   
    }
    
    public void changerLangue()
    {
        
    }
}