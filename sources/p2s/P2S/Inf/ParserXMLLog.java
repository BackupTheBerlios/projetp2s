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
            /*
             ** Ce try catch permet d'attraper des exceptions dans le cas ou
             ** le mot de passe ou un autre champ dans le fichier xml est incorrect
             */
            try {
                this._document= constructeur.parse(new java.io.ByteArrayInputStream(Xml.getBytes()));
            } catch (Exception e) {
                e.printStackTrace() ;
            }
            
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
        try {
            Node fonction = this._document.getElementsByTagName("fonction").item(0);
            return fonction.getFirstChild().getNodeValue();
        } catch (Exception e) {
            e.printStackTrace() ;
            return "error" ; // seuls valeurs reconnues : dir et sup
        }
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
            IndicateursProjet indicateursProjet = null;
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
                
                //Recuperation des indicateurs liées aux projets
                if(attributCourant.getNodeName().equalsIgnoreCase("indicateursprojet")){
                    
                    int totalCharges = 0;
                    int tachesTerminees = 0;
                    int dureeMoyenneTache = 0;
                    int nombreParticipants = 0;
                    float avancementProjet = 0;
                    
                    NodeList attributsIndicateurProjet = attributCourant.getChildNodes();
                    
                    for(int nbrIndicateurs=0; nbrIndicateurs<attributsIndicateurProjet.getLength();nbrIndicateurs++ ){
                        
                        Node indicateurActuel = attributsIndicateurProjet.item(nbrIndicateurs);
                        
                        //Recuperation du total des charges
                        if(indicateurActuel.getNodeName().equalsIgnoreCase("totalcharges"))
                            totalCharges = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                        
                        //Recuperation du nombre de taches terminees
                        if(indicateurActuel.getNodeName().equalsIgnoreCase("tachesterminees"))
                            tachesTerminees = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                        
                        //Recuperation du duree moyenne des taches
                        if(indicateurActuel.getNodeName().equalsIgnoreCase("dureemoyennetache"))
                            dureeMoyenneTache = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                        
                        //Recuperation du nombre de participants
                        if(indicateurActuel.getNodeName().equalsIgnoreCase("nombreparticipants"))
                            nombreParticipants = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                        
                        //Recuperation du taux d'avancement
                        if(indicateurActuel.getNodeName().equalsIgnoreCase("avancementprojet"))
                            avancementProjet = Float.parseFloat(indicateurActuel.getFirstChild().getNodeValue());
                        
                    }
                    indicateursProjet = new IndicateursProjet(totalCharges , tachesTerminees, dureeMoyenneTache, nombreParticipants, avancementProjet);
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
                        IndicateursIteration indicateursIteration = null;
                        Vector tacheListe = new Vector();
                        
                        Node attributIteration = iterationNodeList.item(tailleIterationNodeList);
                        NodeList attributsIterationCourant = attributIteration.getChildNodes();
                        
                        //Recuperation des attributs de l'itération
                        for(int k = 0 ; k < attributsIterationCourant.getLength() ; k++){
                            
                            Node attributIterationCourant = attributsIterationCourant.item(k);
                            
                            //Recuperation de l'id de l'iteration
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("id"))
                                idIteration = Integer.parseInt(attributIterationCourant.getFirstChild().getNodeValue());
                            
                            //Recuperation du numero de l'iteration
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("numero"))
                                numero = Integer.parseInt(attributIterationCourant.getFirstChild().getNodeValue());
                            
                            //Recuperation de la date de debut prevue de l'iteration
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("datedebutprevue"))
                                try{
                                    dateDebutPrevue = dateFormat.parse(attributIterationCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateDebutPrevue");
                                }
                            
                            //Recuperation de la date de debut reelle de l'iteration
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("datedebutreelle"))
                                try{
                                    dateDebutReelle = dateFormat.parse(attributIterationCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateDebutReelle");
                                }
                            
                            //Recuperation de la date de debut reelle de l'iteration
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("datefinprevue"))
                                try{
                                    dateFinPrevue = dateFormat.parse(attributIterationCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateFinPrevue");
                                }
                            
                            
                            
                            //Recuperation de la date de debut reelle de l'iteration
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("datefinreelle"))
                                try{
                                    dateFinReelle = dateFormat.parse(attributIterationCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateFinReelle");
                                }
                            
                            //Recuperation des indicateurs liées aux projets
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("indicateursiteration")){
                                
                                int totalChargesIte = 0;
                                int tachesTermineesIte = 0;
                                int dureeMoyenneTacheIte = 0;
                                int nombreParticipantsIte = 0;
                                int chargeMoyenneParticipants = 0;
                                int nombreMoyenTachesParticipants = 0;
                                
                                NodeList attributsIndicateurIte = attributIterationCourant.getChildNodes();
                                
                                for(int nbrIndicateursIte=0; nbrIndicateursIte<attributsIndicateurIte.getLength();nbrIndicateursIte++ ){
                                    
                                    Node indicateurActuelIte = attributsIndicateurIte.item(nbrIndicateursIte);
                                    //Recuperation du total des charges
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("totalcharges")){
                                        totalChargesIte = Integer.parseInt(indicateurActuelIte.getFirstChild().getNodeValue());
                                        
                                        System.out.println(totalChargesIte);
                                    }
                                    
                                    //Recuperation du nombre de taches terminees
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("nombretachesterminees"))
                                        tachesTermineesIte = Integer.parseInt(indicateurActuelIte.getFirstChild().getNodeValue());
                                    
                                    //Recuperation du duree moyenne des taches
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("dureemoyennetache"))
                                        dureeMoyenneTacheIte = Integer.parseInt(indicateurActuelIte.getFirstChild().getNodeValue());
                                    
                                    //Recuperation du nombre de participants
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("nombreparticipants"))
                                        nombreParticipantsIte = Integer.parseInt(indicateurActuelIte.getFirstChild().getNodeValue());
                                    
                                    //Recuperation de la charge moyenne des participants
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("chargemoyenneparticipants"))
                                        chargeMoyenneParticipants = Integer.parseInt(indicateurActuelIte.getFirstChild().getNodeValue());
                                    
                                    //Recuperation de la charge moyenne des participants
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("nombremoyentachesparticipants"))
                                        nombreMoyenTachesParticipants = Integer.parseInt(indicateurActuelIte.getFirstChild().getNodeValue());
                                    
                                }
                                indicateursIteration = new IndicateursIteration(totalChargesIte, tachesTermineesIte, dureeMoyenneTacheIte, nombreParticipantsIte, chargeMoyenneParticipants, nombreMoyenTachesParticipants);
                            }
                            
                            //Recuperation des taches
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("taches")){
                                
                                NodeList tacheNodeList = attributIterationCourant.getChildNodes();
                                
                                
                                for(int tailleTacheNodeList = 0 ; tailleTacheNodeList < tacheNodeList.getLength() ; tailleTacheNodeList++){
                                    
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
                                    
                                    Node attributTache = tacheNodeList.item(tailleTacheNodeList);
                                    NodeList attributsTacheCourant = attributTache.getChildNodes();
                                    
                                    //Recuperation des attributs de la tache
                                    for(int l = 0 ; l < attributsTacheCourant.getLength() ; l++){
                                        
                                        Node attributTacheCourant = attributsTacheCourant.item(l);
                                        
                                        //Recuperation de l'id de la tache
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("id"))
                                            idTache = Integer.parseInt(attributTacheCourant.getFirstChild().getNodeValue());
                                        
                                        //Recuperation du nom de la tache
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("nom"))
                                            nomTache = attributTacheCourant.getFirstChild().getNodeValue();
                                        
                                        //Recuperation de la description de la tache
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("description"))
                                            descriptionTache = attributTacheCourant.getFirstChild().getNodeValue();
                                        
                                        //Recuperation de l'etat de la tache
                                        if(attributCourant.getNodeName().equalsIgnoreCase("etat"))
                                            etat = attributCourant.getFirstChild().getNodeValue();
                                        
                                        //Recuperation de la charge prevue de la tache
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("chargeprevue"))
                                            chargePrevue = Integer.parseInt(attributTacheCourant.getFirstChild().getNodeValue());
                                        
                                        //Recuperation du temps passe de la tache
                                        if(attributCourant.getNodeName().equalsIgnoreCase("tempspasse"))
                                            tempsPasse = Integer.parseInt(attributCourant.getFirstChild().getNodeValue());
                                        
                                        //Recuperation du reste a passer de la tache
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("resteapasser"))
                                            resteAPasser = Integer.parseInt(attributTacheCourant.getFirstChild().getNodeValue());
                                        
                                        //Recuperation de la date de debut prevue de l'iteration
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("datedebutprevue"))
                                            try{
                                                dateDebutPrevueTache = dateFormat.parse(attributTacheCourant.getFirstChild().getNodeValue());
                                            } catch(ParseException e1){
                                                System.out.println("Probleme pour parser dateDebutPrevue");}
                                        
                                        
                                        //Recuperation de la date de debut reelle de l'iteration
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("datedebutreelle"))
                                            try{
                                                dateDebutReelleTache = dateFormat.parse(attributTacheCourant.getFirstChild().getNodeValue());
                                            } catch(ParseException e1){
                                                System.out.println("Probleme pour parser dateDebutReelle");}
                                        
                                        
                                        //Recuperation de la date de debut reelle de l'iteration
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("datefinprevue"))
                                            try{
                                                dateFinPrevueTache = dateFormat.parse(attributTacheCourant.getFirstChild().getNodeValue());
                                            } catch(ParseException e1){
                                                System.out.println("Probleme pour parser dateFinPrevue");}
                                        
                                        //Recuperation de la date de debut reelle de l'iteration
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("datefinreelle"))
                                            try{
                                                dateFinReelleTache = dateFormat.parse(attributTacheCourant.getFirstChild().getNodeValue());
                                            } catch(ParseException e1){
                                                System.out.println("Probleme pour parser dateFinReelle");
                                            }
                                        
                                    }
                                    Tache tache = new Tache(nomTache, descriptionTache, etat, chargePrevue, tempsPasse, resteAPasser, dateDebutPrevueTache, dateDebutReelleTache, dateFinPrevueTache, dateFinReelleTache);
                                    tacheListe.add(tache);
                                }
                            }
                        }
                        
                        Iteration iteration = new Iteration(numero, dateDebutPrevue, dateDebutReelle, dateFinPrevue, dateFinReelle, tacheListe, indicateursIteration);
                        iterationList.add(iteration);
                    }
                }
                
                //IL FAUT REGARDER COMMENT CA SE PASSE POUR LES MESURES !!
            }
            Projet projetCourant = new Projet(nom,description, dateDebut, dateFin);
            projetCourant.setListeIt(iterationList);
            projetCourant.setIndicateursProjet(indicateursProjet);
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
            Vector listeRoles = new Vector();
            Vector listeIndicateursProjet = new Vector();
            Vector listeIndicateursTache = new Vector();
            Vector listeIndicateursProduit = new Vector();
            
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
                
                //Recuperation des indicateurs liées aux roles du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("roles")){
                    
                    String designation = null;
                    String description = null;
                    
                    NodeList listeRolesMembre = attributCourant.getChildNodes();
                    
                    for(int nbrRoles=0; nbrRoles<listeRolesMembre.getLength();nbrRoles++ ){
                        
                        Node roleActuel = listeRolesMembre.item(nbrRoles);
                        NodeList attributRolesMembres = roleActuel.getChildNodes();
                        
                        for(int nbrAttributs=0; nbrAttributs<attributRolesMembres.getLength();nbrAttributs++ ){
                            
                            Node indicateurActuel = attributRolesMembres.item(nbrAttributs);
                            
                            //Recuperation de la designation du role
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("designation"))
                                designation = indicateurActuel.getFirstChild().getNodeValue();
                            
                            //Recuperation de la description du role
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("description"))
                                description = indicateurActuel.getFirstChild().getNodeValue();
                        }
                        
                        listeRoles.add(new Role(designation,description));
                    }
                    
                }
                
                //Recuperation des indicateurs liées aux projets du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("indicateursProjets")){
                    
                    String nomProjet = null;
                    int charge = -1;
                    int tempsTravail = -1;
                    
                    NodeList listeIndicateursProjetsMembre = attributCourant.getChildNodes();
                    
                    for(int nbrIndicateursProjets=0; nbrIndicateursProjets<listeIndicateursProjetsMembre.getLength();nbrIndicateursProjets++ ){
                        
                        Node indicateursProjetsMembreActuel = listeIndicateursProjetsMembre.item(nbrIndicateursProjets);
                        NodeList attributIndicateursProjetsMembre = indicateursProjetsMembreActuel.getChildNodes();
                        
                        for(int nbrAttributs=0; nbrAttributs<attributIndicateursProjetsMembre.getLength();nbrAttributs++ ){
                            
                            Node indicateurActuel = attributIndicateursProjetsMembre.item(nbrAttributs);
                            
                            //Recuperation du nom du projet
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("nom"))
                                nomProjet = indicateurActuel.getFirstChild().getNodeValue();
                            
                            //Recuperation des charges dans ce projet
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("charges"))
                                charge = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                            
                            //Recuperation du temps de travail dans ce projet
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("tempstravail"))
                                tempsTravail = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                            
                        }
                        
                        listeIndicateursProjet.add(new IndicateursProjetMembre(nomProjet,charge,tempsTravail));
                    }
                    
                }
                
                
                
                //Recuperation des indicateurs liées aux taches du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("indicateursTaches")){
                    
                    String nomTache = null;
                    int tempsPasse = -1;
                    
                    NodeList listeIndicateursTachesMembre = attributCourant.getChildNodes();
                    
                    for(int nbrIndicateursTaches=0; nbrIndicateursTaches<listeIndicateursTachesMembre.getLength();nbrIndicateursTaches++ ){
                        
                        Node indicateursTachesMembreActuel = listeIndicateursTachesMembre.item(nbrIndicateursTaches);
                        NodeList attributIndicateursTachesMembre = indicateursTachesMembreActuel.getChildNodes();
                        
                        for(int nbrAttributs=0; nbrAttributs<attributIndicateursTachesMembre.getLength();nbrAttributs++ ){
                            
                            Node indicateurActuel = attributIndicateursTachesMembre.item(nbrAttributs);
                            
                            //Recuperation du nom de la tache
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("nom"))
                                nomTache = indicateurActuel.getFirstChild().getNodeValue();
                            
                            //Recuperation des charges dans cette tache
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("tempspasse"))
                                tempsPasse = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                            
                        }
                        listeIndicateursTache.add(new IndicateursTacheMembre(nomTache,tempsPasse));
                    }
                }
                
                //Recuperation des indicateurs liées aux produits du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("indicateursArtefacts")){
                    
                    String nomProduit = null;
                    int etat = -1;
                    
                    NodeList listeIndicateursProduitsMembre = attributCourant.getChildNodes();
                    
                    for(int nbrIndicateursProduits=0; nbrIndicateursProduits<listeIndicateursProduitsMembre.getLength();nbrIndicateursProduits++ ){
                        
                        Node indicateursProduitsMembreActuel = listeIndicateursProduitsMembre.item(nbrIndicateursProduits);
                        NodeList attributIndicateursProduitsMembre = indicateursProduitsMembreActuel.getChildNodes();
                        
                        for(int nbrAttributs=0; nbrAttributs<attributIndicateursProduitsMembre.getLength();nbrAttributs++ ){
                            
                            Node indicateurActuel = attributIndicateursProduitsMembre.item(nbrAttributs);
                            
                            //Recuperation du nom du produit
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("nom"))
                                nomProduit = indicateurActuel.getFirstChild().getNodeValue();
                            
                            //Recuperation de l'etat du produit
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("etat"))
                                etat = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                            
                        }
                        listeIndicateursProduit.add(new IndicateursProduitMembre(nomProduit,etat));
                    }
                }
                
            }
            
            Membre membreCourant = new Membre(nom,prenom,adresse,telephone,email,listeRoles, listeIndicateursTache, listeIndicateursProjet, listeIndicateursProduit);
            membres.add(membreCourant);
        }
        
        return membres;
    }
}
