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
            System.out.println("FLUX : " + Xml);
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
    
    public Vector lireMessages() {
        Vector messages = new Vector();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        NodeList listeMessages = this._document.getElementsByTagName("message");
        
        //Recupération des messages un par un
        for(int i = 0 ; i < listeMessages.getLength() ; i++){
            Node messageXML = listeMessages.item(i);
            NodeList attributs = messageXML.getChildNodes();
            
            //Attributs que l'on va récuperer
            String sujet = null;
            Date date = null;
            String message = null;
            
            //Récupération des attributs des messages
            for(int j = 0 ; j < attributs.getLength() ; j++){
                Node attributCourant = attributs.item(j);
                
                //Recuperation du sujet du message
                if(attributCourant.getNodeName().equalsIgnoreCase("sujet"))
                    sujet = attributCourant.getFirstChild().getNodeValue();
                
                //Récupération de la date du message
                if(attributCourant.getNodeName().equalsIgnoreCase("date"))
                    try{
                        if(attributCourant.getFirstChild() != null)
                            date = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());
                    } catch(ParseException e1){
                        System.out.println("Probleme pour parser la date du message");
                    } catch (NullPointerException e){}
                
                
                //Recuperation du corps du message
                if(attributCourant.getNodeName().equalsIgnoreCase("detail"))
                    message = attributCourant.getFirstChild().getNodeValue();
                
                
            }
            Messages mess = new Messages(sujet,message,date);
            messages.add(mess);
        }
        return messages;
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
            String commentaire = null;
            Date dateDebut = null;
            Date dateFin = null;
            IndicateursProjet indicateursProjet = null;
            Vector iterationList = new Vector();
            SeuilsFixes seuilsFixe = new SeuilsFixes();
            // plus
            Vector membresList = new Vector() ;
            Vector risquesList = new Vector() ;
            Vector problemesList = new Vector() ;
            
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
                    if(attributCourant.getFirstChild() != null)
                        description = attributCourant.getFirstChild().getNodeValue();
                
                //Recuperation de la date de debut du projet
                if(attributCourant.getNodeName().equalsIgnoreCase("datedebut"))
                    try{
                        if(attributCourant.getFirstChild() != null)
                            dateDebut = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());
                    } catch(ParseException e1){
                        System.out.println("Probleme pour parser dateDebut");
                    } catch (NullPointerException e){}
                
                //Recuperation de la date de fin du projet
                if(attributCourant.getNodeName().equalsIgnoreCase("datefin"))
                    try{
                        if(attributCourant.getFirstChild() != null)
                            dateFin = dateFormat.parse(attributCourant.getFirstChild().getNodeValue());
                    } catch(ParseException e2){
                        System.out.println("Probleme pour parser dateFin");
                    }
                
                //Recuperation de la description de la tache
                if(attributCourant.getNodeName().equalsIgnoreCase("commentaire"))
                    if(attributCourant.getFirstChild() != null)
                        commentaire = attributCourant.getFirstChild().getNodeValue();
                
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
                            if(indicateurActuel.getFirstChild() != null)
                                totalCharges = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                        
                        //Recuperation du nombre de taches terminees
                        if(indicateurActuel.getNodeName().equalsIgnoreCase("tachesterminees"))
                            if(indicateurActuel.getFirstChild() != null)
                                tachesTerminees = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                        
                        //Recuperation du duree moyenne des taches
                        if(indicateurActuel.getNodeName().equalsIgnoreCase("dureemoyennetache"))
                            if(indicateurActuel.getFirstChild() != null)
                                dureeMoyenneTache = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                        
                        //Recuperation du nombre de participants
                        if(indicateurActuel.getNodeName().equalsIgnoreCase("nombreparticipants"))
                            if(indicateurActuel.getFirstChild() != null)
                                nombreParticipants = Integer.parseInt(indicateurActuel.getFirstChild().getNodeValue());
                        
                        //Recuperation du taux d'avancement
                        if(indicateurActuel.getNodeName().equalsIgnoreCase("avancementprojet"))
                            
                            if(indicateurActuel.getFirstChild() != null)
                                avancementProjet = Float.parseFloat(indicateurActuel.getFirstChild().getNodeValue());
                        
                        
                    }
                    
                    indicateursProjet = new IndicateursProjet(totalCharges , tachesTerminees, dureeMoyenneTache, nombreParticipants, avancementProjet);
                }
                
                //Recuperation des seuils fixe du projet liées aux projets
                if(attributCourant.getNodeName().equalsIgnoreCase("seuilsprojet")){
                    
                    //Les seuils généraux
                    Seuil totalChargesProjet = new Seuil();
                    Seuil tachesTermineesProjet = new Seuil();
                    Seuil dureeMoyenneTache = new Seuil();
                    Seuil nombreParticipants = new Seuil();
                    Seuil avancementProjet = new Seuil();
                    
                    //Les seuils par itération
                    Seuil totalChargesIteration = new Seuil();
                    Seuil tacheTermineesIteration = new Seuil();
                    Seuil dureeMoyenneIteration = new Seuil();
                    Seuil nombreParticipantIteration = new Seuil();
                    Seuil chargeMoyenne = new Seuil();
                    Seuil nombreTacheParticipant = new Seuil();
                    
                    int seuilInt = 0;
                    float seuilFloat = 0;
                    
                    NodeList attributsSeuilFixe = attributCourant.getChildNodes();
                    
                    for(int nbrSeuil=0; nbrSeuil<attributsSeuilFixe.getLength();nbrSeuil++ ){
                        
                        Node seuilActuel = attributsSeuilFixe.item(nbrSeuil);
                        
                        //Recuperation du seuil min du total des charges du projet
                        if(seuilActuel.getNodeName().equalsIgnoreCase("totalChargesProjetMin"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            totalChargesProjet.setSeuilMin(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil max du total des charges du projet
                        if(seuilActuel.getNodeName().equalsIgnoreCase("totalChargesProjetMax"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            totalChargesProjet.setSeuilMax(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil min du total des taches terminees du projet
                        if(seuilActuel.getNodeName().equalsIgnoreCase("tachesTermineesProjetMin"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            tachesTermineesProjet.setSeuilMin(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil max du total des taches terminees du projet
                        if(seuilActuel.getNodeName().equalsIgnoreCase("tachesTermineesProjetMax"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            tachesTermineesProjet.setSeuilMax(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil min de la duree moyenne des taches
                        if(seuilActuel.getNodeName().equalsIgnoreCase("dureeMoyenneTacheMin"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilFloat = Float.parseFloat(seuilActuel.getFirstChild().getNodeValue());
                            dureeMoyenneTache.setSeuilMin(new Float(seuilFloat));
                            }
                        
                        //Recuperation du seuil max de la duree moyenne des taches
                        if(seuilActuel.getNodeName().equalsIgnoreCase("dureeMoyenneTacheMax"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilFloat = Float.parseFloat(seuilActuel.getFirstChild().getNodeValue());
                            dureeMoyenneTache.setSeuilMax(new Float(seuilFloat));
                            }
                        
                        //Recuperation du seuil min pour le nombre de participant
                        if(seuilActuel.getNodeName().equalsIgnoreCase("nombreParticipantsMin"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            nombreParticipants.setSeuilMin(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil max pour le nombre de participant
                        if(seuilActuel.getNodeName().equalsIgnoreCase("nombreParticipantsMax"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            nombreParticipants.setSeuilMax(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil min pour l'avancement du projet
                        if(seuilActuel.getNodeName().equalsIgnoreCase("avancementProjetMin"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            avancementProjet.setSeuilMin(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil max pour l'avancement du projet
                        if(seuilActuel.getNodeName().equalsIgnoreCase("avancementProjetMax"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            avancementProjet.setSeuilMax(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil min pour le total des charges dans l'itération
                        if(seuilActuel.getNodeName().equalsIgnoreCase("totalChargesIterationMin"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            totalChargesIteration.setSeuilMin(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil max pour le total des charges dans l'itération
                        if(seuilActuel.getNodeName().equalsIgnoreCase("totalChargesIterationMax"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            totalChargesIteration.setSeuilMax(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil min pour le total des taches terminees dans l'itération
                        if(seuilActuel.getNodeName().equalsIgnoreCase("tacheTermineesIterationMin"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            tacheTermineesIteration.setSeuilMin(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil max pour le total des taches terminees dans l'itération
                        if(seuilActuel.getNodeName().equalsIgnoreCase("tacheTermineesIterationMax"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            tacheTermineesIteration.setSeuilMax(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil min de la duree moyenne des taches dans l'iteration
                        if(seuilActuel.getNodeName().equalsIgnoreCase("dureeMoyenneIterationMin"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilFloat = Float.parseFloat(seuilActuel.getFirstChild().getNodeValue());
                            dureeMoyenneIteration.setSeuilMin(new Float(seuilFloat));
                            }
                        
                        //Recuperation du seuil max de la duree moyenne des taches dans l'iteration
                        if(seuilActuel.getNodeName().equalsIgnoreCase("dureeMoyenneIterationMax"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilFloat = Float.parseFloat(seuilActuel.getFirstChild().getNodeValue());
                            dureeMoyenneIteration.setSeuilMax(new Float(seuilFloat));
                            }
                        
                        //Recuperation du seuil min pour le nombre de participant dans l'iteration
                        if(seuilActuel.getNodeName().equalsIgnoreCase("nombreParticipantIterationMin"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            nombreParticipantIteration.setSeuilMin(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil max pour le nombre de participant dans l'iteration
                        if(seuilActuel.getNodeName().equalsIgnoreCase("nombreParticipantIterationMax"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilInt = Integer.parseInt(seuilActuel.getFirstChild().getNodeValue());
                            nombreParticipantIteration.setSeuilMax(new Integer(seuilInt));
                            }
                        
                        //Recuperation du seuil min de la charge moyenne des participants l'iteration
                        if(seuilActuel.getNodeName().equalsIgnoreCase("chargeMoyenneMin"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilFloat = Float.parseFloat(seuilActuel.getFirstChild().getNodeValue());
                            chargeMoyenne.setSeuilMin(new Float(seuilFloat));
                            }
                        
                        //Recuperation du seuil max de la charge moyenne des participants l'iteration
                        if(seuilActuel.getNodeName().equalsIgnoreCase("chargeMoyenneMax"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilFloat = Float.parseFloat(seuilActuel.getFirstChild().getNodeValue());
                            chargeMoyenne.setSeuilMax(new Float(seuilFloat));
                            }
                        
                        //Recuperation du seuil min pour le nombre de tache par participant dans l'iteration
                        if(seuilActuel.getNodeName().equalsIgnoreCase("nombreTacheParticipantMin"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilFloat = Float.parseFloat(seuilActuel.getFirstChild().getNodeValue());
                            nombreTacheParticipant.setSeuilMin(new Float(seuilFloat));
                            }
                        
                        //Recuperation du seuil max pour le nombre de tache par participant dans l'iteration
                        if(seuilActuel.getNodeName().equalsIgnoreCase("nombreTacheParticipantMax"))
                            if(seuilActuel.getFirstChild() != null){
                            seuilFloat = Float.parseFloat(seuilActuel.getFirstChild().getNodeValue());
                            nombreTacheParticipant.setSeuilMax(new Float(seuilFloat));
                            }
                        
                    }
                    
                    seuilsFixe.setTotalChargesProjet(totalChargesProjet);
                    seuilsFixe.setTachesTermineesProjet(tachesTermineesProjet);
                    seuilsFixe.setAvancementProjet(avancementProjet);
                    seuilsFixe.setChargeMoyenne(chargeMoyenne);
                    seuilsFixe.setDureeMoyenneIteration(dureeMoyenneIteration);
                    seuilsFixe.setDureeMoyenneTache(dureeMoyenneTache);
                    seuilsFixe.setNombreParticipantIteration(nombreParticipantIteration);
                    seuilsFixe.setNombreParticipants(nombreParticipants);
                    seuilsFixe.setNombreTacheParticipant(nombreTacheParticipant);
                    seuilsFixe.setTacheTermineesIteration(tacheTermineesIteration);
                    seuilsFixe.setTotalChargesIteration(totalChargesIteration);
                    
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
                                if(attributIterationCourant.getFirstChild() != null)
                                    idIteration = Integer.parseInt(attributIterationCourant.getFirstChild().getNodeValue());
                            
                            //Recuperation du numero de l'iteration
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("numero"))
                                if(attributIterationCourant.getFirstChild() != null)
                                    numero = Integer.parseInt(attributIterationCourant.getFirstChild().getNodeValue());
                            
                            //Recuperation de la date de debut prevue de l'iteration
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("datedebutprevue"))
                                try{
                                    if(attributIterationCourant.getFirstChild() != null)
                                        dateDebutPrevue = dateFormat.parse(attributIterationCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateDebutPrevue");
                                }
                            
                            //Recuperation de la date de debut reelle de l'iteration
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("datedebutreelle"))
                                try{
                                    if(attributIterationCourant.getFirstChild() != null)
                                        dateDebutReelle = dateFormat.parse(attributIterationCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateDebutReelle");
                                }
                            
                            //Recuperation de la date de debut reelle de l'iteration
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("datefinprevue"))
                                try{
                                    if(attributIterationCourant.getFirstChild() != null)
                                        dateFinPrevue = dateFormat.parse(attributIterationCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateFinPrevue");
                                }
                            
                            //Recuperation de la date de debut reelle de l'iteration
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("datefinreelle"))
                                try{
                                    if(attributIterationCourant.getFirstChild() != null)
                                        dateFinReelle = dateFormat.parse(attributIterationCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser dateFinReelle");
                                }
                            
                            //Recuperation des indicateurs liées aux projets
                            if(attributIterationCourant.getNodeName().equalsIgnoreCase("indicateursiteration")){
                                
                                int totalChargesIte = 0;
                                int tachesTermineesIte = 0;
                                float dureeMoyenneTacheIte = 0;
                                int nombreParticipantsIte = 0;
                                float chargeMoyenneParticipants = 0;
                                float nombreMoyenTachesParticipants = 0;
                                
                                NodeList attributsIndicateurIte = attributIterationCourant.getChildNodes();
                                
                                for(int nbrIndicateursIte=0; nbrIndicateursIte<attributsIndicateurIte.getLength();nbrIndicateursIte++ ){
                                    
                                    Node indicateurActuelIte = attributsIndicateurIte.item(nbrIndicateursIte);
                                    //Recuperation du total des charges
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("totalcharges")){
                                        if(indicateurActuelIte.getFirstChild() != null)
                                            totalChargesIte = Integer.parseInt(indicateurActuelIte.getFirstChild().getNodeValue());
                                        
                                        System.out.println(totalChargesIte);
                                    }
                                    
                                    //Recuperation du nombre de taches terminees
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("nombretachesterminees"))
                                        if(indicateurActuelIte.getFirstChild() != null)
                                            tachesTermineesIte = Integer.parseInt(indicateurActuelIte.getFirstChild().getNodeValue());
                                    
                                    //Recuperation du duree moyenne des taches
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("dureemoyennetache"))
                                        if(indicateurActuelIte.getFirstChild() != null)
                                            dureeMoyenneTacheIte = Float.parseFloat(indicateurActuelIte.getFirstChild().getNodeValue());
                                    
                                    //Recuperation du nombre de participants
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("nombreparticipants"))
                                        if(indicateurActuelIte.getFirstChild() != null)
                                            nombreParticipantsIte = Integer.parseInt(indicateurActuelIte.getFirstChild().getNodeValue());
                                    
                                    //Recuperation de la charge moyenne des participants
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("chargemoyenneparticipants"))
                                        if(indicateurActuelIte.getFirstChild() != null)
                                            chargeMoyenneParticipants = Float.parseFloat(indicateurActuelIte.getFirstChild().getNodeValue());
                                    
                                    //Recuperation de la charge moyenne des participants
                                    if(indicateurActuelIte.getNodeName().equalsIgnoreCase("nombremoyentachesparticipants"))
                                        if(indicateurActuelIte.getFirstChild() != null)
                                            nombreMoyenTachesParticipants = Float.parseFloat(indicateurActuelIte.getFirstChild().getNodeValue());
                                    
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
                                    float tempsPasse = 0;
                                    float resteAPasser = 0;
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
                                            if(attributTacheCourant.getFirstChild() != null)
                                                idTache = Integer.parseInt(attributTacheCourant.getFirstChild().getNodeValue());
                                        
                                        //Recuperation du nom de la tache
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("nom"))
                                            if(attributTacheCourant.getFirstChild() != null)
                                                nomTache = attributTacheCourant.getFirstChild().getNodeValue();
                                        
                                        //Recuperation de la description de la tache
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("description"))
                                            if(attributTacheCourant.getFirstChild() != null)
                                                descriptionTache = attributTacheCourant.getFirstChild().getNodeValue();
                                        
                                        //Recuperation de l'etat de la tache
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("etat"))
                                            if(attributTacheCourant.getFirstChild() != null)
                                                etat = attributTacheCourant.getFirstChild().getNodeValue();
                                        
                                        //Recuperation de la charge prevue de la tache
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("chargeprevue"))
                                            if(attributTacheCourant.getFirstChild() != null)
                                                chargePrevue = Integer.parseInt(attributTacheCourant.getFirstChild().getNodeValue());
                                        
                                        //Recuperation du temps passe de la tache
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("tempspasse"))
                                            if(attributTacheCourant.getFirstChild() != null)
                                                tempsPasse = Float.parseFloat(attributTacheCourant.getFirstChild().getNodeValue());
                                        
                                        //Recuperation du reste a passer de la tache
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("tempsrestant"))
                                            if(attributTacheCourant.getFirstChild() != null)
                                                resteAPasser = Float.parseFloat(attributTacheCourant.getFirstChild().getNodeValue());
                                        
                                        //Recuperation de la date de debut prevue de l'iteration
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("datedebutprevue"))
                                            try{
                                                if(attributTacheCourant.getFirstChild() != null)
                                                    dateDebutPrevueTache = dateFormat.parse(attributTacheCourant.getFirstChild().getNodeValue());
                                            } catch(ParseException e1){
                                                System.out.println("Probleme pour parser dateDebutPrevue");}
                                        
                                        
                                        //Recuperation de la date de debut reelle de l'iteration
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("datedebutreelle"))
                                            try{
                                                if(attributTacheCourant.getFirstChild() != null)
                                                    dateDebutReelleTache = dateFormat.parse(attributTacheCourant.getFirstChild().getNodeValue());
                                            } catch(ParseException e1){
                                                System.out.println("Probleme pour parser dateDebutReelle");}
                                        
                                        
                                        //Recuperation de la date de debut reelle de l'iteration
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("datefinprevue"))
                                            try{
                                                if(attributTacheCourant.getFirstChild() != null)
                                                    dateFinPrevueTache = dateFormat.parse(attributTacheCourant.getFirstChild().getNodeValue());
                                            } catch(ParseException e1){
                                                System.out.println("Probleme pour parser dateFinPrevue");}
                                        
                                        //Recuperation de la date de debut reelle de l'iteration
                                        if(attributTacheCourant.getNodeName().equalsIgnoreCase("datefinreelle"))
                                            try{
                                                if(attributTacheCourant.getFirstChild() != null)
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
                
                // risques
                if(attributCourant.getNodeName().equalsIgnoreCase("risques")){
                    NodeList risqueNodeList = attributCourant.getChildNodes();
                    
                    for(int riskCounter = 0 ; riskCounter < risqueNodeList.getLength() ; riskCounter++){
                        
                        
                        //int idRisque;
                        String nomRisque = null ;
                        String descriptionRisque = null ;
                        int priorite = 0 ;
                        int impact = 0 ;
                        int etatRisque = 0 ;
                        
                        Node risqueCourant = risqueNodeList.item(riskCounter);
                        NodeList attributsRisqueCourant = risqueCourant.getChildNodes();
                        
                        for(int riskCounter1 = 0 ; riskCounter1 < attributsRisqueCourant.getLength() ; riskCounter1++){
                            
                            Node attributRisqueCourant = attributsRisqueCourant.item(riskCounter1);
                            //Recuperation du nom du risque
                            if(attributRisqueCourant.getNodeName().equalsIgnoreCase("nom")) {
                                if(attributRisqueCourant.getFirstChild() != null)
                                    nomRisque = attributRisqueCourant.getFirstChild().getNodeValue();
                            }
                            
                            //Recuperation de la description
                            if(attributRisqueCourant.getNodeName().equalsIgnoreCase("description")) {
                                if(attributRisqueCourant.getFirstChild() != null)
                                    descriptionRisque = attributRisqueCourant.getFirstChild().getNodeValue();
                            }
                            
                            
                            //Recuperation de la priorite
                            if(attributRisqueCourant.getNodeName().equalsIgnoreCase("priorite")) {
                                if(attributRisqueCourant.getFirstChild() != null)
                                    priorite = Integer.parseInt(attributRisqueCourant.getFirstChild().getNodeValue());
                            }
                            
                            //Recuperation de l'impact
                            if(attributRisqueCourant.getNodeName().equalsIgnoreCase("impact")) {
                                if(attributRisqueCourant.getFirstChild() != null)
                                    impact = Integer.parseInt(attributRisqueCourant.getFirstChild().getNodeValue());
                            }
                            
                            //Recuperation de l'etat
                            if(attributRisqueCourant.getNodeName().equalsIgnoreCase("etat")) {
                                if(attributRisqueCourant.getFirstChild() != null)
                                    etatRisque = Integer.parseInt(attributRisqueCourant.getFirstChild().getNodeValue());
                            }
                        }
                        Risque risque = new Risque(nomRisque, descriptionRisque, priorite, impact, etatRisque) ;
                        risquesList.add(risque) ;
                    }
                }
                
                //problemes
                if(attributCourant.getNodeName().equalsIgnoreCase("problemes")){
                    NodeList problemeNodeList = attributCourant.getChildNodes();
                    
                    for(int pbCounter = 0 ; pbCounter < problemeNodeList.getLength() ; pbCounter++){
                        
                        
                        
                        String nomProbleme = null ;
                        String causeProbleme = null ;
                        Date debutProbleme = null ;
                        Date finProbleme = null ;
                        
                        
                        
                        Node problemeCourant = problemeNodeList.item(pbCounter);
                        NodeList attributsProblemeCourant = problemeCourant.getChildNodes();
                        
                        for(int pbCounter1 = 0 ; pbCounter1 < attributsProblemeCourant.getLength() ; pbCounter1++){
                            
                            Node attributProblemeCourant = attributsProblemeCourant.item(pbCounter1);
                            //Recuperation du nom du probleme
                            if(attributProblemeCourant.getNodeName().equalsIgnoreCase("nom")) {
                                if(attributProblemeCourant.getFirstChild() != null)
                                    nomProbleme = attributProblemeCourant.getFirstChild().getNodeValue();
                            }
                            
                            //Recuperation de la cause
                            if(attributProblemeCourant.getNodeName().equalsIgnoreCase("cause")) {
                                if(attributProblemeCourant.getFirstChild() != null)
                                    causeProbleme = attributProblemeCourant.getFirstChild().getNodeValue();
                            }
                            
                            
                            //Recuperation de la date de debut
                            if(attributProblemeCourant.getNodeName().equalsIgnoreCase("dateDebut")) {
                                try{
                                    if(attributProblemeCourant.getFirstChild() != null)
                                        debutProbleme = dateFormat.parse(attributProblemeCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser debut du probleme");}
                            }
                            
                            //Recuperation de la date de fin
                            if(attributProblemeCourant.getNodeName().equalsIgnoreCase("dateFin")) {
                                try{
                                    if(attributProblemeCourant.getFirstChild() != null)
                                        finProbleme = dateFormat.parse(attributProblemeCourant.getFirstChild().getNodeValue());
                                } catch(ParseException e1){
                                    System.out.println("Probleme pour parser fin du probleme");}
                            }
                            
                        }
                        Probleme probleme = new Probleme(nomProbleme, causeProbleme, debutProbleme, finProbleme) ;
                        problemesList.add(probleme) ;
                    }
                }
            }
            Projet projetCourant = new Projet(nom,description, dateDebut, dateFin);
            projetCourant.setCommentaire(commentaire);
            projetCourant.setListeIt(iterationList);
            projetCourant.setIndicateursProjet(indicateursProjet);
            projetCourant.setListeRisques(risquesList) ;
            projetCourant.setListeProblemes(problemesList) ;
            projetCourant.setSeuilFixes(seuilsFixe);
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
                    if(attributCourant.getFirstChild() != null)
                        nom = attributCourant.getFirstChild().getNodeValue();
                
                //Recuperation du prenom du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("prenom"))
                    if(attributCourant.getFirstChild() != null)
                        prenom = attributCourant.getFirstChild().getNodeValue();
                
                //Recuperation de l'adresse du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("adresse"))
                    if(attributCourant.getFirstChild() != null)
                        adresse = attributCourant.getFirstChild().getNodeValue();
                
                //Recuperation du telephone du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("telephone"))
                    if(attributCourant.getFirstChild() != null)
                        telephone = attributCourant.getFirstChild().getNodeValue();
                
                //Recup?ration du mail du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("email"))
                    if(attributCourant.getFirstChild() != null)
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
                                if(indicateurActuel.getFirstChild() != null)
                                    designation = indicateurActuel.getFirstChild().getNodeValue();
                            
                            //Recuperation de la description du role
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("description"))
                                if(indicateurActuel.getFirstChild() != null)
                                    description = indicateurActuel.getFirstChild().getNodeValue();
                        }
                        
                        listeRoles.add(new Role(designation,description));
                    }
                    
                }
                
                //Recuperation des indicateurs liées aux projets du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("indicateursProjets")){
                    
                    String nomProjet = null;
                    float charge = -1;
                    float tempsTravail = -1;
                    
                    NodeList listeIndicateursProjetsMembre = attributCourant.getChildNodes();
                    
                    for(int nbrIndicateursProjets=0; nbrIndicateursProjets<listeIndicateursProjetsMembre.getLength();nbrIndicateursProjets++ ){
                        
                        Node indicateursProjetsMembreActuel = listeIndicateursProjetsMembre.item(nbrIndicateursProjets);
                        NodeList attributIndicateursProjetsMembre = indicateursProjetsMembreActuel.getChildNodes();
                        
                        for(int nbrAttributs=0; nbrAttributs<attributIndicateursProjetsMembre.getLength();nbrAttributs++ ){
                            
                            Node indicateurActuel = attributIndicateursProjetsMembre.item(nbrAttributs);
                            
                            //Recuperation du nom du projet
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("nom"))
                                if(indicateurActuel.getFirstChild() != null)
                                    nomProjet = indicateurActuel.getFirstChild().getNodeValue();
                            
                            //Recuperation des charges dans ce projet
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("charges"))
                                if(indicateurActuel.getFirstChild() != null)
                                    charge = Float.parseFloat(indicateurActuel.getFirstChild().getNodeValue());
                            
                            //Recuperation du temps de travail dans ce projet
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("tempstravail"))
                                if(indicateurActuel.getFirstChild() != null)
                                    tempsTravail = Float.parseFloat(indicateurActuel.getFirstChild().getNodeValue());
                            
                        }
                        
                        listeIndicateursProjet.add(new IndicateursProjetMembre(nomProjet,charge,tempsTravail));
                    }
                    
                }
                
                
                
                //Recuperation des indicateurs liées aux taches du membre
                if(attributCourant.getNodeName().equalsIgnoreCase("indicateursTaches")){
                    
                    String nomTache = null;
                    String nomProjet = null;
                    float charges = -1;
                    
                    NodeList listeIndicateursTachesMembre = attributCourant.getChildNodes();
                    
                    for(int nbrIndicateursTaches=0; nbrIndicateursTaches<listeIndicateursTachesMembre.getLength();nbrIndicateursTaches++ ){
                        
                        Node indicateursTachesMembreActuel = listeIndicateursTachesMembre.item(nbrIndicateursTaches);
                        NodeList attributIndicateursTachesMembre = indicateursTachesMembreActuel.getChildNodes();
                        
                        for(int nbrAttributs=0; nbrAttributs<attributIndicateursTachesMembre.getLength();nbrAttributs++ ){
                            
                            Node indicateurActuel = attributIndicateursTachesMembre.item(nbrAttributs);
                            
                            //Recuperation du nom de la tache
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("nom"))
                                if(indicateurActuel.getFirstChild() != null)
                                    nomTache = indicateurActuel.getFirstChild().getNodeValue();
                            
                            //Recuperation du nom du projet associé
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("nomprojet"))
                                if(indicateurActuel.getFirstChild() != null)
                                    nomProjet = indicateurActuel.getFirstChild().getNodeValue();
                            
                            //Recuperation des charges dans cette tache
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("charges"))
                                if(indicateurActuel.getFirstChild() != null)
                                    charges = Float.parseFloat(indicateurActuel.getFirstChild().getNodeValue());
                            
                        }
                        listeIndicateursTache.add(new IndicateursTacheMembre(nomTache,nomProjet,charges));
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
                                if(indicateurActuel.getFirstChild() != null)
                                    nomProduit = indicateurActuel.getFirstChild().getNodeValue();
                            
                            //Recuperation de l'etat du produit
                            if(indicateurActuel.getNodeName().equalsIgnoreCase("etat"))
                                if(indicateurActuel.getFirstChild() != null)
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
