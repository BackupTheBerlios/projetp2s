/*
 * ParserXML1.java
 *
 * Created on 13 janvier 2005, 20:18
 */

package P2S.Inf;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import P2S.Model.*;
import java.util.*;
import java.text.*;



/**
 * Cette classe permet de lire un fichier XML contenant les preferences pour P2S
 * @author Fabien
 */
public class ParserXMLLog {
    
    //ATTRIBUTS DE LA CLASSE
    
    /**
     * Document contenant le fichier Xml
     */
    private Document _document;
    
    
    //CONSTRUCTEUR
    
    /**
     * Cree une instance de ParserXMLLog
     * @param Xml Chemin du fichier Xml a lire
     */
    public ParserXMLLog(String Xml) {
        try{
            DocumentBuilderFactory usine = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructeur = usine.newDocumentBuilder();
            this._document= constructeur.parse(new java.io.ByteArrayInputStream(Xml.getBytes()));
            
            //this._document = constructeur.parse(new InputStream(Xml));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //METHODES DE RECUPERATION DES DONNEES
    
    /**
     * Renvoie Le login de l'utilisateur
     * @return Le login de l'utilisateur
     */
    public String lireLogin(){
        Node langue = this._document.getElementsByTagName("login").item(0);
        
        return langue.getFirstChild().getNodeValue();
    }
    
    /**
     * Renvoie la fonction de l'utilisateur
     * @return la fonction de l'utilisateur
     */
    public String lireFonction(){
        Node fonction = this._document.getElementsByTagName("fonction").item(0);
        return fonction.getFirstChild().getNodeValue();
    }
    
    public Vector lireProjets(){
        Vector projets = new Vector();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        NodeList listeProjets = this._document.getElementsByTagName("projet");
        //Recuperation des projets un par un
        for(int i = 0 ; i < listeProjets.getLength() ; i++){
            Node projetXML = listeProjets.item(i);
            NodeList attributs = projetXML.getChildNodes();
            
            //Attributs que l'on va recuperer
            
            int idProjet = -1;
            String nom = null;
            String description = null;
            Date dateDebut = null;
            Date dateFin = null;
            //Recuperation des attributs du projet
            for(int j = 0 ; j < attributs.getLength() ; j++){
                Node attributCourant = attributs.item(j);
                
                //Recuperation de l'id du projet
                if(attributCourant.getNodeName().equalsIgnoreCase("id"))
                    idProjet = Integer.parseInt(attributCourant.getFirstChild().getNodeValue());
                
                //Recuperation du nom du projet
                if(attributCourant.getNodeName().equalsIgnoreCase("nom"))
                    nom = attributCourant.getFirstChild().getNodeValue();
                
                //Recuperation de la description du projet
                if(attributCourant.getNodeName().equalsIgnoreCase("description"))
                    description = attributCourant.getFirstChild().getNodeValue();
                
                //Recuperation de la date de debut du projet
                if(attributCourant.getNodeName().equalsIgnoreCase("datedebut"))
                    try{                        
                        dateDebut = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());                        
                    } catch(ParseException e1){
                        System.out.println("Probleme pour parser dateDebut");
                    }
                
                //Recuperation de la date de fin du projet
                if(attributCourant.getNodeName().equalsIgnoreCase("datefin"))
                    try{                       
                        dateFin = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());                        
                    } catch(ParseException e2){
                        System.out.println("Probleme pour parser dateFin");
                    }
            }
            
            //IL FAUT REGARDER COMMENT CA SE PASSE POUR LES MESURES !!
            
            Projet projetCourant = new Projet(nom,description, dateDebut, dateFin);
            projets.add(projetCourant);
        }
        
        return projets;
    }
    
    public Vector lireMembres(){
        Vector membres = new Vector();
        NodeList listeProjets = this._document.getElementsByTagName("membre");
        //Recuperation des membres un par un
        for(int i = 0 ; i < listeProjets.getLength() ; i++){
            Node membreXML = listeProjets.item(i);
            NodeList attributs = membreXML.getChildNodes();
            
            //Attributs que l'on va recuperer
            
            int idMembre = -1;
            String nom = null;
            String prenom = null;
            String adresse = null;
            String telephone = null;
            String email = null;
            
            //Recuperation des attributs du projet
            for(int j = 0 ; j < attributs.getLength() ; j++){
                Node attributCourant = attributs.item(j);
                
                //Recuperation de l'id du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("idMembre"))
                    idMembre = Integer.parseInt(attributCourant.getFirstChild().getNodeValue());
                
                //Recuperation du nom du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("nom"))
                    nom = attributCourant.getFirstChild().getNodeValue();
                
                //Recuperation du prenom du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("prenom"))
                    prenom = attributCourant.getFirstChild().getNodeValue();
                                                
                //Recuperation de l'adresse du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("adresse"))
                    adresse = attributCourant.getFirstChild().getNodeValue();
                
                //Recuperation du telephone du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("telephone"))
                    telephone = attributCourant.getFirstChild().getNodeValue();
                
                //Recup?ration du mail du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("email"))
                    email = attributCourant.getFirstChild().getNodeValue();
            }
            
            Membre membreCourant = new Membre(nom,prenom,adresse,telephone,email);
            membres.add(membreCourant);
        }
        return membres;
    }
}
