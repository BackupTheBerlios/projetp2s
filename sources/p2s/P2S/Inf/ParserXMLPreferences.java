/*
 * ParserXMLPreferences.java
 * 
 */

package P2S.Inf;

import javax.xml.parsers.*;
import org.w3c.dom.*;



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
     * Cree une instance de ParserXMLPreferences
     * @param Xml Chemin du fichier Xml a lire
     */
    public ParserXMLPreferences(String Xml) {
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
    public String lireLangue()
    {
        Node langue = this._document.getElementsByTagName("langue").item(0);
                
        return langue.getFirstChild().getNodeValue();   
    }
    
    public void changerLangue()
    {
    /*    Element root = this._document.addElement( "bibliotheque" );
    Element livre = null;
    try {
      livre = root.addElement("livre");
      livre.addElement("titre").addText("titre 1");
      livre.addElement("auteur").addText("auteur 1");
      livre.addElement("editeur").addText("editeur 1");
      livre = root.addElement("livre");
      livre.addElement("titre").addText("titre 2");
      livre.addElement("auteur").addText("auteur 2");
      livre.addElement("editeur").addText("editeur 2");
      livre = root.addElement("livre");
      livre.addElement("titre").addText("titre 3");
      livre.addElement("auteur").addText("auteur 3");
      livre.addElement("editeur").addText("editeur 3");
      OutputFormat format = OutputFormat.createPrettyPrint(); 
      XMLWriter writer = new XMLWriter( System.out, format ); 
      writer.write( document ); 
    } catch (Exception e){
      e.printStackTrace();
    }*/

    }
}