import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * ParserXMLFichierWF.java
 *
 * Created on 26 janvier 2005, 23:20
 */

/**
 *
 * @author Fabien
 */
public class ParserXMLFichierWF {
    
    /**
     * Fichier Xml
     */
    private String fichier;
    /**
     * Document contenant le fichier Xml
     */
    private Document document;
    private Connection conn;
    
    /** Creates a new instance of ParserXMLFichierWF */
    public ParserXMLFichierWF(String Xml) {
        this.fichier = Xml;
        
        try{
            DocumentBuilderFactory usine = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructeur = usine.newDocumentBuilder();
            this.document = constructeur.parse(new FileInputStream(new File(this.fichier)));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try{
            // Connexion a la base de donnees
            conn = DriverManager.getConnection("jdbc:mysql://localhost/p2s?user=root&password=rootpass");
        }catch(SQLException e){
            
        }
    }
    
    public int lireIdProjet() {
        NodeList listeNoeud = this.document.getElementsByTagName("projet").item(0).getChildNodes();
        
        int b = 0;
        while(listeNoeud.item(b).getNodeName() != "id") {
            b++;
        }
        return new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
    }
    
    
    public void majProjet(){
        int id = -1;
        String nom = null;
        String dateDebut = null;
        String dateFin = null;
        String description = null;
        int budget = -1;
        
        
        Node NoeudProjet = this.document.getElementsByTagName("projet").item(0);
        NodeList listeNoeud = NoeudProjet.getChildNodes();
        
        
        // on recherche l'id du projet
        int b = 0;
        while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
            b++;
        }
        id = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
        
        // on recherche le nom du projet
        b = 0;
        while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
            b++;
        }
        nom = listeNoeud.item(b).getFirstChild().getNodeValue();
        
        // on recherche la date de debut du projet
        b = 0;
        while(listeNoeud.item(b).getNodeName().compareTo("dateDebut") != 0) {
            b++;
        }
        dateDebut = listeNoeud.item(b).getFirstChild().getNodeValue();
        if(dateDebut.compareTo("null") == 0)
                dateDebut = "0001-01-01";
        
        // on recherche la date de fin du projet
        b = 0;
        while(listeNoeud.item(b).getNodeName().compareTo("dateFin") != 0) {
            b++;
        }
        dateFin = listeNoeud.item(b).getFirstChild().getNodeValue();
        if(dateFin.compareTo("null") == 0)
                dateFin = "0001-01-01";
        
        // on recherche la description
        b = 0;
        while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
            b++;
        }
        description = listeNoeud.item(b).getFirstChild().getNodeValue();
        
        // on recherche le budget du projet
        b = 0;
        while(listeNoeud.item(b).getNodeName().compareTo("budget") != 0) {
            b++;
        }
        budget = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
        
        
        
        try {
            // Requete SQL
            PreparedStatement prepState = conn.prepareStatement("Select * from projets where idprojet="+id);
            ResultSet rsp = prepState.executeQuery(); // Execution de la requete
            
            if(!rsp.next()){
                prepState = conn.prepareStatement("insert into projets values ("+id+",'"+nom+"','"+dateDebut+"','"+dateFin+"','"+description+"',"+budget+")");
                prepState.execute(); // Execution de la requete
                
            }else{
                PreparedStatement updateProjet = conn.prepareStatement(
                        "update projets set nom=?, datedebut=?, datefin=?, description=?, budget=? where idprojet ="+id);
                updateProjet.setString(1,nom);
                updateProjet.setString(2, dateDebut);
                updateProjet.setString(3, dateFin);
                updateProjet.setString(4, description);
                updateProjet.setInt(5, budget);
                
                updateProjet.executeUpdate();
            }
            
            
        }catch (SQLException ex) { // Si une SQLException survient
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
        
        
        
    }
    
    public void majIterations() {
        int id = -1;
        int numero = -1;
        String dateDebutPrevue = null;
        String dateDebutReelle = null;
        String dateFinPrevue = null;
        String dateFinReelle = null;
        
        int idProjet = lireIdProjet();
        
        NodeList listeIteration = this.document.getElementsByTagName("eltIteration");
        NodeList listeNoeud;
        
        for(int i=0;i<listeIteration.getLength();i++){
            listeNoeud = listeIteration.item(i).getChildNodes();
            
            // on recherche l'id de l'iteration
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            id = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche le numero de l'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("numero") != 0) {
                b++;
            }
            numero = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche la date prevue de debut d'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutPrevue") != 0) {
                b++;
            }
            dateDebutPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateDebutPrevue.compareTo("null") == 0)
                dateDebutPrevue = "0001-01-01";
            
            // on recherche la date reelle de debut d'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutReelle") != 0) {
                b++;
            }
            dateDebutReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateDebutReelle.compareTo("null") == 0)
                dateDebutReelle = "0001-01-01";
            
            // on recherche la date prevue de fin d'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinPrevue") != 0) {
                b++;
            }
            dateFinPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateFinPrevue.compareTo("null") == 0)
                dateFinPrevue = "0001-01-01";
            
            // on recherche la date reelle de fin d'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinReelle") != 0) {
                b++;
            }
            dateFinReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateFinReelle.compareTo("null") == 0)
                dateFinReelle = "0001-01-01";
            
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from iterations where iditeration="+id);
                ResultSet rsit = prepState.executeQuery(); // Execution de la requete
                
                if(!rsit.next()){
                    prepState = conn.prepareStatement("insert into iterations values ("+id+","+numero+",'"+dateDebutPrevue+"','"+dateDebutReelle+"','"+dateFinPrevue+"','"+dateFinReelle+"',"+idProjet+")");
                    prepState.execute(); // Execution de la requete
                }else{
                    PreparedStatement updateIt = conn.prepareStatement(
                            "update iterations set numero=?, datedebutprevue=?, datedebutreelle=?, datefinprevue=?, datefinreelle=?, idprojet=? where iditeration ="+id);
                    updateIt.setInt(1,numero);
                    updateIt.setString(2, dateDebutPrevue);
                    updateIt.setString(3, dateDebutReelle);
                    updateIt.setString(4, dateFinPrevue);
                    updateIt.setString(5, dateFinReelle);
                    updateIt.setInt(6,idProjet);
                    
                    updateIt.executeUpdate();
                }
                
                
            }catch (SQLException ex) { // Si une SQLException survient
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
            }
            
        }
    }
    
    
    public void majMembres() {
        int id = -1;
        String nom = null;
        String prenom = null;
        String adresse = null;
        String tel = null;
        String mail = null;
        
        NodeList listeMembre = this.document.getElementsByTagName("eltMembre");
        NodeList listeNoeud;
        
        for(int i=0;i<listeMembre.getLength();i++){
            listeNoeud = listeMembre.item(i).getChildNodes();
            
            // on recherche l'id du membre
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            id = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche le nom du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche le prenom du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("prenom") != 0) {
                b++;
            }            
            prenom = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            
            // on recherche l'adresse du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("adresse") != 0) {
                b++;
            }
            adresse = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche le telephone du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("telephone") != 0) {
                b++;
            }
            tel = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche le mail du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("email") != 0) {
                b++;
            }
            mail = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            try {
                // Connexion a la base de donnees
                //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/p2s?user=root&password=rootpass");
                
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from membres where idmembre="+id);
                ResultSet rsmembre = prepState.executeQuery(); // Execution de la requete
                
                if(!rsmembre.next()){
                    prepState = conn.prepareStatement("insert into membres values ("+id+",'"+nom+"','"+prenom+"','"+adresse+"','"+tel+"','"+mail+"')");
                    prepState.execute(); // Execution de la requete
                } else{
                    PreparedStatement updateMembre = conn.prepareStatement(
                            "update membres set nom=?, prenom=?, adresse=?, telephone=?, email=? where idmembre ="+id);
                    updateMembre.setString(1,nom);
                    updateMembre.setString(2, prenom);
                    updateMembre.setString(3, adresse);
                    updateMembre.setString(4, tel);
                    updateMembre.setString(5, mail);
                    
                    updateMembre.executeUpdate();
                }
                
                
            }catch (SQLException ex) { // Si une SQLException survient
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
            }
            
        }
    }
    
    public void majMesures() {
        int id = -1;
        String nom = null;
        String description = null;
        String valeur = null;
        int type = -1;
        
        
        NodeList listeMesures = this.document.getElementsByTagName("eltMetrique");
        NodeList listeNoeud;
        
        for(int i=0;i<listeMesures.getLength();i++){
            listeNoeud = listeMesures.item(i).getChildNodes();
            
            // on recherche l'id de la mesure
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            id = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche le nom de la mesure
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche la description de la mesure
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
                b++;
            }
            description = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche la valeur de la mesure
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("valeur") != 0) {
                b++;
            }
            valeur = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche le type de la mesure
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("type") != 0) {
                b++;
            }
            type = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from mesures where idmesure="+id);
                ResultSet rsmesure = prepState.executeQuery(); // Execution de la requete
                
                if(!rsmesure.next()){
                    prepState = conn.prepareStatement("insert into mesures values ("+id+",'"+nom+"','"+description+"',"+type+",'"+valeur+"')");
                    prepState.execute(); // Execution de la requete
                } else{
                    PreparedStatement updateMesure = conn.prepareStatement(
                            "update mesures set nom=?, description=?, type=?, valeur=? where idmesure ="+id);
                    updateMesure.setString(1,nom);
                    updateMesure.setString(2,description);
                    updateMesure.setInt(3,type);
                    updateMesure.setString(4,valeur);
                    
                    updateMesure.executeUpdate();
                }
                
                
            }catch (SQLException ex) { // Si une SQLException survient
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
            }
            
        }
    }
    
    public void majRisques() {
        int id = -1;
        String nom = null;
        String description = null;
        int priorite = -1;
        int impact = -1;
        int etat = -1;
        
        int idProjet = lireIdProjet();
        
        NodeList listeRisques = this.document.getElementsByTagName("eltRisque");
        NodeList listeNoeud;
        
        for(int i=0;i<listeRisques.getLength();i++){
            listeNoeud = listeRisques.item(i).getChildNodes();
            
            // on recherche l'id du risque
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            id = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche le nom du risque
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche la description du risque
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
                b++;
            }
            description = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche la priorite du risque
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("priorite") != 0) {
                b++;
            }
            priorite = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche l'impact du risque
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("impact") != 0) {
                b++;
            }
            impact = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche l'etat du risque
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("etat") != 0) {
                b++;
            }
            etat = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from risques where idrisque="+id);
                ResultSet rsrisque = prepState.executeQuery(); // Execution de la requete
                
                if(!rsrisque.next()){
                    prepState = conn.prepareStatement("insert into risques values ("+id+",'"+nom+"',"+priorite+","+impact+","+etat+",'"+description+"',"+idProjet+")");
                    prepState.execute(); // Execution de la requete
                } else{
                    PreparedStatement updateRisque = conn.prepareStatement(
                            "update risques set nom=?, description=?, priorite=?, impact=?, etat=?, idprojet=? where idrisque ="+id);
                    updateRisque.setString(1,nom);
                    updateRisque.setString(2,description);
                    updateRisque.setInt(3,priorite);
                    updateRisque.setInt(4,impact);
                    updateRisque.setInt(5,etat);
                    updateRisque.setInt(6,idProjet);
                    
                    updateRisque.executeUpdate();
                }
                
                
            }catch (SQLException ex) { // Si une SQLException survient
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
            }
            
        }
    }
    
    
    public void majTaches() {
        int id = -1;
        String nom = null;
        String description = null;
        int etat = -1;
        int chargePrevue = -1;
        int tempsPasse = -1;
        int resteAPasser = -1;
        String dateDebutPrevue = null;
        String dateDebutReelle = null;
        String dateFinPrevue = null;
        String dateFinReelle = null;
        int idIteration = -1;
        int idMembre = -1;
        
        NodeList listeTache = this.document.getElementsByTagName("eltTache");
        NodeList listeNoeud;
        
        for(int i=0;i<listeTache.getLength();i++){
            listeNoeud = listeTache.item(i).getChildNodes();
            
            // on recherche l'id de la tache
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            id = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche le nom de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche la description de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
                b++;
            }
            description = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche l'etat de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("etat") != 0) {
                b++;
            }
            etat = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche la charge prevue de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("chargePrevue") != 0) {
                b++;
            }
            chargePrevue = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche le temps passe sur la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("tempsPasse") != 0) {
                b++;
            }
            tempsPasse = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche le temps qu'il reste a passer sur la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("resteAPasser") != 0) {
                b++;
            }
            resteAPasser = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche la date prevue de debut de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutPrevue") != 0) {
                b++;
            }
            dateDebutPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateDebutPrevue.compareTo("null") == 0)
                dateDebutPrevue = "0001-01-01";
            
            // on recherche la date reelle de debut de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutReelle") != 0) {
                b++;
            }
            dateDebutReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateDebutReelle.compareTo("null") == 0)
                dateDebutReelle = "0001-01-01";
            
            // on recherche la date prevue de fin de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinPrevue") != 0) {
                b++;
            }
            dateFinPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateFinPrevue.compareTo("null") == 0)
                dateFinPrevue = "0001-01-01";
            
            // on recherche la date reelle de fin de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinReelle") != 0) {
                b++;
            }
            dateFinReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateFinReelle.compareTo("null") == 0)
                dateFinReelle = "0001-01-01";
            
            // on recherche l'id de l'iteration auquel est rattache la tache
            idIteration = lireIdIteration_Tache(id);
                        
            // on recherche l'id du membre auquel est rattache la tache
            idMembre = lireIdMembre_Tache(id);
                         
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from taches where idtache="+id);
                ResultSet rstache = prepState.executeQuery(); // Execution de la requete
             
                if(!rstache.next()){
                    prepState = conn.prepareStatement("insert into taches values ("+id+",'"+nom+"','"+description+"',"+etat+","+chargePrevue+","+tempsPasse+","+resteAPasser+",'"+dateDebutPrevue+"','"+dateFinPrevue+"','"+dateDebutReelle+"','"+dateFinReelle+"',"+idIteration+","+idMembre+")");
                    prepState.execute(); // Execution de la requete
                }else{
                    PreparedStatement updateTache = conn.prepareStatement(
                            "update taches set nom=?, description=?, etat=?, chargeprevue=?, tempspasse=?, tempsrestant=?, datedebutprevue=?, datefinprevue=?, datedebutreelle=?, datefinreelle=?, iditeration=?, idmembre=? where idtache ="+id);
                    updateTache.setString(1,nom);
                    updateTache.setString(2,description);
                    updateTache.setInt(3,etat);
                    updateTache.setInt(4,chargePrevue);
                    updateTache.setInt(5,tempsPasse);
                    updateTache.setInt(6,resteAPasser);
                    updateTache.setString(7,dateDebutPrevue);
                    updateTache.setString(8,dateFinPrevue);
                    updateTache.setString(9,dateDebutReelle);
                    updateTache.setString(10,dateFinReelle);
                    updateTache.setInt(11,idIteration);
                    updateTache.setInt(12,idMembre);
             
                    updateTache.executeUpdate();
                }
             
             
            }catch (SQLException ex) { // Si une SQLException survient
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
            }            
        }
    }
    
    private int lireIdIteration_Tache(int idTache) {
        NodeList listeItTache = this.document.getElementsByTagName("IterationTache");
        NodeList listeIdTache;
        
        int id = -1;
        boolean trouve = false;
        int b = 0;
        int i = 0;
        int j = 0;
        
        while(i<listeItTache.getLength() && !trouve){
            Node NoeudItTache = listeItTache.item(i);
            
            b = 0;
            while(NoeudItTache.getChildNodes().item(b).getNodeName().compareTo("listeIdTache") != 0) {
                b++;
            }
            listeIdTache = NoeudItTache.getChildNodes().item(b).getChildNodes();
            
            j = 0;
            while(j<listeIdTache.getLength() && !trouve){                
                if(listeIdTache.item(j).getNodeName().compareTo("id") == 0){
                    if(new Integer(listeIdTache.item(j).getFirstChild().getNodeValue()).intValue() == idTache){
                        b = 0;
                        while(NoeudItTache.getChildNodes().item(b).getNodeName().compareTo("idIteration") != 0) {
                            b++;
                        }
                        id = new Integer(NoeudItTache.getChildNodes().item(b).getFirstChild().getNodeValue()).intValue();
                        trouve = true;                        
                    }
                }
                j++;
            }
            i++;
        }
        
        if(!trouve){
            // traitement erreur : aucun id iteration n'a etait trouve pour cette tache
            System.out.println("aucun id iteration n'a etait trouve pour cette tache");
        }
        
        return(id);
    }
    
    
    private int lireIdMembre_Tache(int idTache) {
        NodeList listeItTache = this.document.getElementsByTagName("MembreTache");
        NodeList listeIdTache;
        
        int id = -1;
        boolean trouve = false;
        int b = 0;
        int i = 0;
        int j = 0;
        while(i<listeItTache.getLength() && !trouve){
            Node NoeudItTache = listeItTache.item(i);
            
            b = 0;
            while(NoeudItTache.getChildNodes().item(b).getNodeName().compareTo("listeTache") != 0) {
                b++;
            }
            listeIdTache = NoeudItTache.getChildNodes().item(b).getChildNodes();
            
            j = 0;
            while(j<listeIdTache.getLength() && !trouve){                
                if(listeIdTache.item(j).getNodeName().compareTo("id") == 0){
                    if(new Integer(listeIdTache.item(j).getFirstChild().getNodeValue()).intValue() == idTache){
                        b = 0;
                        while(NoeudItTache.getChildNodes().item(b).getNodeName().compareTo("idMembre") != 0) {
                            b++;
                        }
                        id = new Integer(NoeudItTache.getChildNodes().item(b).getFirstChild().getNodeValue()).intValue();
                        trouve = true;                        
                    }
                }
                j++;
            }
            i++;
        }
        
        if(!trouve){
            // traitement erreur : aucun id membre n'a etait trouve pour cette tache
            System.out.println("aucun id membre n'a etait trouve pour cette tache");
        }
        
        return(id);
    }
    
    public void majTachesCollaboratives() {
        int id = -1;
        String nom = null;
        String description = null;
        int etat = -1;
        int chargePrevue = -1;
        int tempsPasse = -1;
        int resteAPasser = -1;
        String dateDebutPrevue = null;
        String dateDebutReelle = null;
        String dateFinPrevue = null;
        String dateFinReelle = null;
        int idIteration = -1;        
        
        NodeList listeTache = this.document.getElementsByTagName("eltTacheCollaborative");
        NodeList listeNoeud;
        
        for(int i=0;i<listeTache.getLength();i++){
            listeNoeud = listeTache.item(i).getChildNodes();
            
            // on recherche l'id de la tache
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            id = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche le nom de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche la description de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
                b++;
            }
            description = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche l'etat de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("etat") != 0) {
                b++;
            }
            etat = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche la charge prevue de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("chargePrevue") != 0) {
                b++;
            }
            chargePrevue = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche le temps passe sur la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("tempsPasse") != 0) {
                b++;
            }
            tempsPasse = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche le temps qu'il reste a passer sur la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("resteAPasser") != 0) {
                b++;
            }
            resteAPasser = new Integer(listeNoeud.item(b).getFirstChild().getNodeValue()).intValue();
            
            // on recherche la date prevue de debut de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutPrevue") != 0) {
                b++;
            }
            dateDebutPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateDebutPrevue.compareTo("null") == 0)
                dateDebutPrevue = "0001-01-01";
            
            // on recherche la date reelle de debut de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutReelle") != 0) {
                b++;
            }
            dateDebutReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateDebutReelle.compareTo("null") == 0)
                dateDebutReelle = "0001-01-01";
            
            // on recherche la date prevue de fin de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinPrevue") != 0) {
                b++;
            }
            dateFinPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateFinPrevue.compareTo("null") == 0)
                dateFinPrevue = "0001-01-01";
            
            // on recherche la date reelle de fin de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinReelle") != 0) {
                b++;
            }
            dateFinReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            if(dateFinReelle.compareTo("null") == 0)
                dateFinReelle = "0001-01-01";
            
            // on recherche l'id de l'iteration auquel est rattache la tache
            idIteration = lireIdIteration_TacheCollaborative(id);
                     
                         
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from tachescollaboratives where idtache="+id);
                ResultSet rstache = prepState.executeQuery(); // Execution de la requete
             
                if(!rstache.next()){
                    prepState = conn.prepareStatement("insert into tachescollaboratives values ("+id+",'"+nom+"','"+description+"',"+etat+","+chargePrevue+","+tempsPasse+","+resteAPasser+",'"+dateDebutPrevue+"','"+dateFinPrevue+"','"+dateDebutReelle+"','"+dateFinReelle+"',"+idIteration+")");
                    prepState.execute(); // Execution de la requete
                }else{
                    PreparedStatement updateTache = conn.prepareStatement(
                            "update tachescollaboratives set nom=?, description=?, etat=?, chargeprevue=?, tempspasse=?, tempsrestant=?, datedebutprevue=?, datefinprevue=?, datedebutreelle=?, datefinreelle=?, iditeration=? where idtache ="+id);
                    updateTache.setString(1,nom);
                    updateTache.setString(2,description);
                    updateTache.setInt(3,etat);
                    updateTache.setInt(4,chargePrevue);
                    updateTache.setInt(5,tempsPasse);
                    updateTache.setInt(6,resteAPasser);
                    updateTache.setString(7,dateDebutPrevue);
                    updateTache.setString(8,dateFinPrevue);
                    updateTache.setString(9,dateDebutReelle);
                    updateTache.setString(10,dateFinReelle);
                    updateTache.setInt(11,idIteration);                    
             
                    updateTache.executeUpdate();
                }
             
             
            }catch (SQLException ex) { // Si une SQLException survient
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
            }            
        }
    }
    
    private int lireIdIteration_TacheCollaborative(int idTache) {
        NodeList listeItTache = this.document.getElementsByTagName("IterationTacheCol");
        NodeList listeIdTache;
        
        int id = -1;
        boolean trouve = false;
        int b = 0;
        int i = 0;
        int j = 0;
        
        while(i<listeItTache.getLength() && !trouve){
            Node NoeudItTache = listeItTache.item(i);
            
            b = 0;
            while(NoeudItTache.getChildNodes().item(b).getNodeName().compareTo("listeIdTacheCol") != 0) {
                b++;
            }
            listeIdTache = NoeudItTache.getChildNodes().item(b).getChildNodes();
            
            j = 0;
            while(j<listeIdTache.getLength() && !trouve){                
                if(listeIdTache.item(j).getNodeName().compareTo("id") == 0){
                    if(new Integer(listeIdTache.item(j).getFirstChild().getNodeValue()).intValue() == idTache){
                        b = 0;
                        while(NoeudItTache.getChildNodes().item(b).getNodeName().compareTo("idIteration") != 0) {
                            b++;
                        }
                        id = new Integer(NoeudItTache.getChildNodes().item(b).getFirstChild().getNodeValue()).intValue();
                        trouve = true;                        
                    }
                }
                j++;
            }
            i++;
        }
        
        if(!trouve){
            // traitement erreur : aucun id iteration n'a etait trouve pour cette tache
            System.out.println("aucun id iteration n'a etait trouve pour cette tache");
        }
        
        return(id);
    }
}
