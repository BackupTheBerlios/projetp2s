/*
 * ParserXMLPreferences.java
 *
 */

package P2S.Inf;

import java.io.File;
import java.io.InputStream;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;



/**
 * Cette classe permet de lire un fichier XML contenant les preferences pour P2S
 * @author Fabien
 */
public class ParserXMLPreferences {
    
    /**
     * Fichier Xml
     */
    private InputStream _fichierPreferences;
    /**
     * Document contenant le fichier Xml
     */
    private Document _document;
    
    /**
     * Cree une instance de ParserXMLPreferences
     * @param Xml Chemin du fichier Xml a lire
     */
    public ParserXMLPreferences(InputStream Xml) {
        this._fichierPreferences = Xml;
        
        try{
            DocumentBuilderFactory usine = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructeur = usine.newDocumentBuilder();
            //this._document = constructeur.parse(new FileInputStream(new File(this._fichierPreferences)));
            this._document = constructeur.parse(this._fichierPreferences);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * Renvoie la langue de P2S
     * @return la langue
     */
    public String lireLangue() {
        Node langue = this._document.getElementsByTagName("langue").item(0);
        return langue.getFirstChild().getNodeValue();
    }
    
    public String lirePortServeur(){
        Node port = this._document.getElementsByTagName("port").item(0);
        return port.getFirstChild().getNodeValue();
    }
    
    public String lireAdresseServeur(){
        Node adresse = this._document.getElementsByTagName("adresse").item(0);
        return adresse.getFirstChild().getNodeValue();
    }
    
    public void changerLangue(String l) {
        Node langue = this._document.getElementsByTagName("langue").item(0);
        langue.setNodeValue(l);
    }
    
    public void editerFichier(){
        P2S.P2S.getRessource("/P2S/preferences.xml");       
    }
}