import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
/*
 * ParserConnexionBD.java
 *
 * Created on 8 février 2005, 17:06
 */

/**
 *
 * @author Fabien
 */
public class ParserConnexionBD {
    
    /**
     * Document contenant le fichier Xml
     */
    private Document document;
    
    /** Creates a new instance of ParserConnexionBD */
    public ParserConnexionBD(String Xml) {
        try{
            DocumentBuilderFactory usine = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructeur = usine.newDocumentBuilder();
            this.document= constructeur.parse(Xml);
            
            //this._document = constructeur.parse(new InputStream(Xml));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
        
    /**
     * Renvoie l'adresse du serveur
     * @return l'adresse du serveur
     */
    public String lireHost(){
        Node fonction = this.document.getElementsByTagName("host").item(0);
        return fonction.getFirstChild().getNodeValue();
    }
    
    /**
     * Renvoie le nom de la base de donnees
     * @return le nom de la base de donnees
     */
    public String lireBase(){
        Node fonction = this.document.getElementsByTagName("base").item(0);
        return fonction.getFirstChild().getNodeValue();
    }
    
    /**
     * Renvoie le login
     * @return le login
     */
    public String lireLogin(){
        Node fonction = this.document.getElementsByTagName("login").item(0);
        return fonction.getFirstChild().getNodeValue();
    }
    
    /**
     * Renvoie le mot de passe
     * @return le mot de passe
     */
    public String lirePassword(){
        Node fonction = this.document.getElementsByTagName("password").item(0);
        return fonction.getFirstChild().getNodeValue();
    }
}
