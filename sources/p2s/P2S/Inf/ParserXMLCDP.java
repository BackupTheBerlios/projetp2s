/*
 * ParserXMLCDP.java
 *
 * Created on 5 mars 2005, 17:35
 */

package P2S.Inf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 *
 * @author Guillaume
 */
public class ParserXMLCDP {
    
    private Document _document;
    
    /** Creates a new instance of ParserXMLCDP */
    public ParserXMLCDP(String flux) {
        
        
        try{
            DocumentBuilderFactory usine = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructeur = usine.newDocumentBuilder();
            /*
             ** Ce try catch permet d'attraper des exceptions dans le cas ou
             ** le mot de passe ou un autre champ dans le fichier xml est incorrect
             */
            try {
                this._document= constructeur.parse(new java.io.ByteArrayInputStream(flux.getBytes()));
            } catch (Exception e) {
                e.printStackTrace() ;
            }
            
            //this._document = constructeur.parse(new InputStream(Xml));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void lireChefProjet(HashMap mapCdp, Vector listeProjetsLibres){
        /*mapCdp = new HashMap();
        listeProjetsLibres = new Vector();*/
        HashMap mapProjet = new HashMap();
        try {
            //On récupère tous les projets dans une map
            int idProjet = -1;
            String nomProjet = "";
            NodeList listeProjet = this._document.getElementsByTagName("projet");
            Node projets;
            for (int i=0;i<listeProjet.getLength();i++){
                projets = listeProjet.item(i);
                if(projets.getNodeName().equalsIgnoreCase("projet")){
                    NodeList leProjet = projets.getChildNodes();
                    idProjet = Integer.parseInt(leProjet.item(0).getFirstChild().getNodeValue());
                    nomProjet = leProjet.item(1).getFirstChild().getNodeValue();
                    mapProjet.put(idProjet, nomProjet);
                }
            }
            
            //On récupère tous les chefs de projet dans une map
            Vector listeProjets;
            String nomCdp = "";
            NodeList listeCdp = this._document.getElementsByTagName("cdp");
            Node cdp;
            for (int i=0;i<listeCdp.getLength();i++){
                listeProjets = new Vector();
                cdp = listeCdp.item(i);
                nomCdp = cdp.getFirstChild().getNodeValue();
                NodeList listeLiens = this._document.getElementsByTagName("lien");
                NodeList leLien;
                for (int j=0; j<listeLiens.getLength();j++){
                    leLien = listeLiens.item(j).getChildNodes();
                    String nomCdpLien = leLien.item(0).getFirstChild().getNodeValue();
                    if (nomCdpLien.equalsIgnoreCase(nomCdp)){
                        idProjet = Integer.parseInt(leLien.item(1).getFirstChild().getNodeValue());
                        listeProjets.add((String)mapProjet.get(idProjet));
                        mapProjet.remove(idProjet);
                    }
                }
                mapCdp.put(nomCdp,listeProjets);
            }
            
            Set lesProjetsLibres = mapProjet.keySet( ) ;
            Iterator it = lesProjetsLibres.iterator( ) ;
            while ( it . hasNext( ) ) {
                listeProjetsLibres.add((String)mapProjet.get(it.next()));
            }
            
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }
    
}
