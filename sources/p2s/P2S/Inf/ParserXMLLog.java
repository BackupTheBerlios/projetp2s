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
        /*
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
            Vector iterationList = new Vector();
            
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
                
                //Recuperation des iterations
                if(attributCourant.getNodeName().equalsIgnoreCase("iterations")){
                 
                    NodeList iterationNodeList = attributCourant.getChildNodes();
      
                    for(int tailleIterationNodeList = 0 ; tailleIterationNodeList < iterationNodeList.getLength() ; tailleIterationNodeList++){
                 
                        int idIteration;
                        int numero = 0;
                        Date dateDebutPrevue = null;
                        Date dateDebutReelle = null;
                        Date dateFinPrevue = null;
                        Date dateFinReelle = null;
                        Vector tacheListe = new Vector();
                 
                        //Recuperation des attributs de l'itération
                        for(int k = 0 ; k < attributs.getLength() ; k++){
                 
                            Node attributIterationCourant = attributs.item(k);
                 
                            //Recuperation de l'id de l'iteration
                            if(attributCourant.getNodeName().equalsIgnoreCase("id"))
                                idIteration = Integer.parseInt(attributCourant.getFirstChild().getNodeValue());
                 
                            //Recuperation du numero de l'iteration
                            if(attributCourant.getNodeName().equalsIgnoreCase("numero")){
                                idIteration = Integer.parseInt(attributCourant.getFirstChild().getNodeValue());
                                System.out.println(Integer.parseInt(attributCourant.getFirstChild().getNodeValue()));
                            }
                 
                            //Recuperation de la date de debut prevue de l'iteration
                            if(attributCourant.getNodeName().equalsIgnoreCase("datedebutprevue"))
                                try{
                                    dateDebutPrevue = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateDebutPrevue");
                                }
                 
                            //Recuperation de la date de debut reelle de l'iteration
                            if(attributCourant.getNodeName().equalsIgnoreCase("datedebutreelle"))
                                try{
                                    dateDebutReelle = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateDebutReelle");
                                }
                 
                            //Recuperation de la date de debut reelle de l'iteration
                            if(attributCourant.getNodeName().equalsIgnoreCase("datefinprevue"))
                                try{
                                    dateFinPrevue = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateFinPrevue");
                                }
                 
                 
                 
                            //Recuperation de la date de debut reelle de l'iteration
                            if(attributCourant.getNodeName().equalsIgnoreCase("datefinreelle"))
                                try{
                                    dateFinReelle = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateFinReelle");
                                }
                 
                 
                            //Recuperation des taches
                            if(attributCourant.getNodeName().equalsIgnoreCase("taches")){
                 
                                NodeList tacheNodeList = attributCourant.getChildNodes();
                 
                                int idTache;
                                String nomTache = null;
                                String descriptionTache = null;
                                String etat = null;
                                int chargePrevue = 0;
                                int tempsPasse = 0;
                                int resteAPasser = 0;
                                Date dateDebutPrevueTache = null;
                                Date dateDebutReelleTache = null;
                                Date dateFinPrevueTache = null;
                                Date dateFinReelleTache = null;
                 
                                for(int tailleTacheNodeList = 0 ; tailleTacheNodeList < iterationNodeList.getLength() ; tailleTacheNodeList++){
                 
                                    //Recuperation des attributs de la tache
                                    for(int l = 0 ; l < attributs.getLength() ; l++){
                 
                                        Node attributTacheCourant = attributs.item(l);
                 
                                        //Recuperation de l'id de la tache
                                        if(attributCourant.getNodeName().equalsIgnoreCase("id"))
                                            idTache = Integer.parseInt(attributCourant.getFirstChild().getNodeValue());
                 
                                        //Recuperation du nom de la tache
                                        if(attributCourant.getNodeName().equalsIgnoreCase("nom"))
                                            nomTache = attributCourant.getFirstChild().getNodeValue();
                 
                                        //Recuperation de la description de la tache
                                        if(attributCourant.getNodeName().equalsIgnoreCase("description"))
                                            descriptionTache = attributCourant.getFirstChild().getNodeValue();
                 
                                        //Recuperation de l'etat de la tache
                                        if(attributCourant.getNodeName().equalsIgnoreCase("etat"))
                                            etat = attributCourant.getFirstChild().getNodeValue();
                 
                                        //Recuperation de la charge prevue de la tache
                                        if(attributCourant.getNodeName().equalsIgnoreCase("chargeprevue"))
                                            chargePrevue = Integer.parseInt(attributCourant.getFirstChild().getNodeValue());
                 
                                        //Recuperation du temps passe de la tache
                                        if(attributCourant.getNodeName().equalsIgnoreCase("tempspasse"))
                                            tempsPasse = Integer.parseInt(attributCourant.getFirstChild().getNodeValue());
                 
                                        //Recuperation du reste a passer de la tache
                                        if(attributCourant.getNodeName().equalsIgnoreCase("resteapasser"))
                                            resteAPasser = Integer.parseInt(attributCourant.getFirstChild().getNodeValue());
                 
                                        //Recuperation de la date de debut prevue de l'iteration
                                        if(attributCourant.getNodeName().equalsIgnoreCase("datedebutprevue"))
                                            try{
                                                dateDebutPrevueTache = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());
                                            } catch(ParseException e1){
                                                System.out.println("Probleme pour parser dateDebutPrevue");}
                 
                 
                                        //Recuperation de la date de debut reelle de l'iteration
                                        if(attributCourant.getNodeName().equalsIgnoreCase("datedebutreelle"))
                                            try{
                                                dateDebutReelleTache = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());
                                            } catch(ParseException e1){
                                                System.out.println("Probleme pour parser dateDebutReelle");}
                 
                 
                                        //Recuperation de la date de debut reelle de l'iteration
                                        if(attributCourant.getNodeName().equalsIgnoreCase("datefinprevue"))
                                            try{
                                                dateFinPrevueTache = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());
                                            } catch(ParseException e1){
                                                System.out.println("Probleme pour parser dateFinPrevue");}
                 
                                        //Recuperation de la date de debut reelle de l'iteration
                                        if(attributCourant.getNodeName().equalsIgnoreCase("datefinreelle"))
                                            try{
                                                dateFinReelleTache = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());
                                            } catch(ParseException e1){
                                                System.out.println("Probleme pour parser dateFinReelle");
                                            }
                 
                                    }
                                    Tache tache = new Tache(nomTache, descriptionTache, etat, chargePrevue, tempsPasse, resteAPasser, dateDebutPrevueTache, dateDebutReelleTache, dateFinPrevueTache, dateFinReelleTache);
                                    tacheListe.add(tache);
                                }
                            }
                        }
                 
                        Iteration iteration = new Iteration(numero, dateDebutPrevue, dateDebutReelle, dateFinPrevue, dateFinReelle, tacheListe);
                        iterationList.add(iteration);
                    }
                }
                
                //IL FAUT REGARDER COMMENT CA SE PASSE POUR LES MESURES !!
            }
            Projet projetCourant = new Projet(nom,description, dateDebut, dateFin);
            projetCourant.setListeIt(iterationList);
            projets.add(projetCourant);
            
        }
        
        Projet p = (Projet)projets.get(0);
        Iteration i = (Iteration)p.getListeIt().get(0);
        System.out.println(i.getNumero());
        */
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
                if(attributCourant.getNodeName().equalsIgnoreCase("id"))
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
