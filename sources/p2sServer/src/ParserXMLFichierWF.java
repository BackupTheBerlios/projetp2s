import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
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
    private String login;
    
    /** Creates a new instance of ParserXMLFichierWF */
    public ParserXMLFichierWF(String Xml, String cheminBD, String _login) throws FileNotFoundException{
        this.fichier = Xml;
        this.login = _login;
        
        try{
            DocumentBuilderFactory usine = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructeur = usine.newDocumentBuilder();
            
            this.document = constructeur.parse(Xml);
        }catch(FileNotFoundException e){
            e.printStackTrace();
            throw e;
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
            conn = DriverManager.getConnection(cheminBD);
        }catch(SQLException e){
            
        }
    }
    
    public void majBase() throws NullValueXMLException{
        
        majProjet();
       
        majLienSuperviseurProjet();
        majIterations();
        majMembres();
        majRoles();
        majMesures();
        majRisques();
        majTaches();
        majTachesCollaboratives();
        majArtefacts();
        
        // Mise a jour des liens
        majLiensMembres_TachesCollaboratives();
        majLiensMembres_Projets();
        majLiensArtefacts_Entrees_Taches();
        majLiensArtefacts_Sorties_Taches();
        majLiensArtefacts_Entrees_TachesCollaboratives();
        majLiensArtefacts_Sorties_TachesCollaboratives();
        majLiensMembres_Roles();
        
        // Mise a jour des indicateurs
        majIndicateurIteration();
        majIndicateurProjet();
    }
    
    private String insertString(String s){
        if(s == null)
            return null;
        else
            return "'"+s+"'";
    }
    
    private int updateInt(String s){
        if(s == null)
            return -1;
        else
            return Integer.parseInt(s);
    }
    
    public String lireIdProjet() throws NullPointerException{
        NodeList listeNoeud = this.document.getElementsByTagName("projet").item(0).getChildNodes();
        String id;
        int b = 0;
        while(listeNoeud.item(b).getNodeName() != "id") {
            b++;
        }
        id = listeNoeud.item(b).getFirstChild().getNodeValue();
        
        return id;
    }
    
    
    public void majProjet() throws NullValueXMLException, NullPointerException{
        String id = null;
        String nom = null;
        String dateDebut = null;
        String dateFin = null;
        String description = null;
        String budget = null;
        
        Node NoeudProjet = this.document.getElementsByTagName("projet").item(0);
        NodeList listeNoeud = NoeudProjet.getChildNodes();
        
        int b = 0;
        // on recherche l'id du projet
        try{
            id = lireIdProjet();
        }catch(NullPointerException e){            
            throw new NullValueXMLException();
        }
        
        // on recherche le nom du projet
        b = 0;
        while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
            b++;
        }
        try{
            nom = listeNoeud.item(b).getFirstChild().getNodeValue();
        }catch(NullPointerException e){            
            throw new NullValueXMLException();
        }
        
        // on recherche la date de debut du projet
        b = 0;
        while(listeNoeud.item(b).getNodeName().compareTo("dateDebut") != 0) {
            b++;
        }
        try{
            dateDebut = listeNoeud.item(b).getFirstChild().getNodeValue();
        }catch(NullPointerException e){}    
        
        
        // on recherche la date de fin du projet
        b = 0;
        while(listeNoeud.item(b).getNodeName().compareTo("dateFin") != 0) {
            b++;
        }
        try{
            dateFin = listeNoeud.item(b).getFirstChild().getNodeValue();
        }catch(NullPointerException e){}        
        
        // on recherche la description
        b = 0;
        while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
            b++;
        }
        try{    
            description = listeNoeud.item(b).getFirstChild().getNodeValue();
        }catch(NullPointerException e){}
        
        // on recherche le budget du projet
        b = 0;
        while(listeNoeud.item(b).getNodeName().compareTo("budget") != 0) {
            b++;
        }
        try{
            budget = listeNoeud.item(b).getFirstChild().getNodeValue();
        }catch(NullPointerException e){}    
        
        
        
        try {
            // Requete SQL
            PreparedStatement prepState = conn.prepareStatement("Select * from projets where idprojet="+id);
            ResultSet rsp = prepState.executeQuery(); // Execution de la requete
            
            if(!rsp.next()){
                prepState = conn.prepareStatement("insert into projets values ("+id+","+insertString(nom)+","+insertString(dateDebut)+","+insertString(dateFin)+","+insertString(description)+","+budget+")");
                prepState.execute(); // Execution de la requete
                
            }else{
                PreparedStatement updateProjet = conn.prepareStatement(
                        "update projets set nom=?, datedebut=?, datefin=?, description=?, budget=? where idprojet ="+id);
                updateProjet.setString(1,nom);
                updateProjet.setString(2,dateDebut);
                updateProjet.setString(3,dateFin);
                updateProjet.setString(4,description);                
                updateProjet.setInt(5,updateInt(budget));
                
                updateProjet.executeUpdate();
            }
            
            
        }catch (SQLException ex) { // Si une SQLException survient
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }
    
    public void majLienSuperviseurProjet() throws NullValueXMLException{
        try {
            // Requete SQL
            PreparedStatement prepState = conn.prepareStatement("Select * from superviseur_projets where login='"+this.login+"' and idprojet="+lireIdProjet());
            ResultSet rs = prepState.executeQuery(); // Execution de la requete
            
            if(!rs.next()){
                prepState = conn.prepareStatement("insert into superviseur_projets values ('"+login+"',"+lireIdProjet()+")");
                prepState.execute(); // Execution de la requete
            }
            
        }catch (SQLException ex) { // Si une SQLException survient
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
        
    }
    
    public void majIterations() throws NullValueXMLException{
        String id = null;
        String numero = null;
        String dateDebutPrevue = null;
        String dateDebutReelle = null;
        String dateFinPrevue = null;
        String dateFinReelle = null;
        String idProjet = null;
        
        try{
            idProjet = lireIdProjet();
        }catch(NullPointerException e){
             throw new NullValueXMLException();
         }
        
        NodeList listeIteration = this.document.getElementsByTagName("eltIteration");
        NodeList listeNoeud;
        
        for(int i=0;i<listeIteration.getLength();i++){
            listeNoeud = listeIteration.item(i).getChildNodes();
            
            // on recherche l'id de l'iteration
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            try{
                id = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche le numero de l'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("numero") != 0) {
                b++;
            }
            try{
                numero = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche la date prevue de debut d'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutPrevue") != 0) {
                b++;
            }
            try{
                dateDebutPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}            
            
            // on recherche la date reelle de debut d'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutReelle") != 0) {
                b++;
            }
            try{
                dateDebutReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            
            // on recherche la date prevue de fin d'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinPrevue") != 0) {
                b++;
            }
            try{
                dateFinPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            
            // on recherche la date reelle de fin d'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinReelle") != 0) {
                b++;
            }
            try{
                dateFinReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from iterations where iditeration="+id);
                ResultSet rsit = prepState.executeQuery(); // Execution de la requete
                
                if(!rsit.next()){
                    prepState = conn.prepareStatement("insert into iterations values ("+id+","+numero+","+insertString(dateDebutPrevue)+","+insertString(dateDebutReelle)+","+insertString(dateFinPrevue)+","+insertString(dateFinReelle)+","+idProjet+")");
                    prepState.execute(); // Execution de la requete
                }else{
                    PreparedStatement updateIt = conn.prepareStatement(
                            "update iterations set numero=?, datedebutprevue=?, datedebutreelle=?, datefinprevue=?, datefinreelle=?, idprojet=? where iditeration ="+id);
                    updateIt.setInt(1,updateInt(numero));
                    updateIt.setString(2, dateDebutPrevue);
                    updateIt.setString(3, dateDebutReelle);
                    updateIt.setString(4, dateFinPrevue);
                    updateIt.setString(5, dateFinReelle);
                    updateIt.setInt(6,updateInt(idProjet));
                    
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
    
    
    public void majMembres() throws NullValueXMLException{
        String id = null;
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
            try{
                id = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche le nom du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            try{
                nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche le prenom du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("prenom") != 0) {
                b++;
            }
            try{
                prenom = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            
            // on recherche l'adresse du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("adresse") != 0) {
                b++;
            }
            try{
                adresse = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche le telephone du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("telephone") != 0) {
                b++;
            }
            try{
                tel = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche le mail du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("email") != 0) {
                b++;
            }
            try{
                mail = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            try {               
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from membres where idmembre="+id);
                ResultSet rsmembre = prepState.executeQuery(); // Execution de la requete
                
                if(!rsmembre.next()){
                    prepState = conn.prepareStatement("insert into membres values ("+id+","+insertString(nom)+","+insertString(prenom)+","+insertString(adresse)+","+insertString(tel)+","+insertString(mail)+")");
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
    
    public void majRoles() throws NullValueXMLException{
        String id = null;
        String nom = null;
        String description = "description role manquant dans dpe";
        
        
        NodeList listeRole = this.document.getElementsByTagName("role");
        NodeList listeNoeud;
        
        for(int i=0;i<listeRole.getLength();i++){
            listeNoeud = listeRole.item(i).getChildNodes();
            
            // on recherche l'id du role
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            try{
                id = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche le nom du role
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            try{
                nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // PAS DE DESCRIPTIN DANS LE DPE
            // on recherche la description du role
            /*b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
                b++;
            }
            description = listeNoeud.item(b).getFirstChild().getNodeValue();*/
            
            try {
                // Connexion a la base de donnees
                //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/p2s?user=root&password=rootpass");
                
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from roles where idrole='"+id+"'");
                ResultSet rsRole = prepState.executeQuery(); // Execution de la requete
                
                if(!rsRole.next()){
                    prepState = conn.prepareStatement("insert into roles values ("+insertString(id)+","+insertString(nom)+","+insertString(description)+")");
                    prepState.execute(); // Execution de la requete
                } else{
                    PreparedStatement updateRole = conn.prepareStatement(
                            "update roles set nom=?, description=? where idrole ='"+id+"'");
                    updateRole.setString(1,nom);
                    updateRole.setString(2,description);
                    
                    updateRole.executeUpdate();
                }
                                
            }catch (SQLException ex) { // Si une SQLException survient
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
            }
            
        }
    }
    
    public void majMesures() throws NullValueXMLException{
        String id = null;
        String nom = null;
        String description = null;
        String valeur = null;
        String type = null;
        
        
        NodeList listeMesures = this.document.getElementsByTagName("eltMetrique");
        NodeList listeNoeud;
        
        for(int i=0;i<listeMesures.getLength();i++){
            listeNoeud = listeMesures.item(i).getChildNodes();
            
            // on recherche l'id de la mesure
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            try{
                id = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche le nom de la mesure
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            try{
                nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche la description de la mesure
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
                b++;
            }
            try{
                description = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche la valeur de la mesure
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("valeur") != 0) {
                b++;
            }
            try{
                valeur = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche le type de la mesure
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("type") != 0) {
                b++;
            }
            try{
                type = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from mesures where idmesure="+id);
                ResultSet rsmesure = prepState.executeQuery(); // Execution de la requete
                
                if(!rsmesure.next()){
                    prepState = conn.prepareStatement("insert into mesures values ("+id+","+insertString(nom)+","+insertString(description)+","+type+","+insertString(valeur)+")");
                    prepState.execute(); // Execution de la requete
                } else{
                    PreparedStatement updateMesure = conn.prepareStatement(
                            "update mesures set nom=?, description=?, type=?, valeur=? where idmesure ="+id);
                    updateMesure.setString(1,nom);
                    updateMesure.setString(2,description);
                    updateMesure.setInt(3,updateInt(type));
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
    
    public void majRisques() throws NullValueXMLException{
        String id = null;
        String nom = null;
        String description = null;
        String priorite = null;
        String impact = null;
        String etat = null;
        String idProjet = null;
        
        try{
            idProjet = lireIdProjet();
        }catch(NullPointerException e){
            throw new NullValueXMLException();
        }        
        
        NodeList listeRisques = this.document.getElementsByTagName("eltRisque");
        NodeList listeNoeud;
        
        for(int i=0;i<listeRisques.getLength();i++){
            listeNoeud = listeRisques.item(i).getChildNodes();
            
            // on recherche l'id du risque
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            try{
                id = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche le nom du risque
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            try{
                nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche la description du risque
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
                b++;
            }
            try{
                description = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche la priorite du risque
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("priorite") != 0) {
                b++;
            }
            try{
                priorite = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche l'impact du risque
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("impact") != 0) {
                b++;
            }
            try{
                impact = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche l'etat du risque
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("etat") != 0) {
                b++;
            }
            try{
                etat = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from risques where idrisque="+id);
                ResultSet rsrisque = prepState.executeQuery(); // Execution de la requete
                
                if(!rsrisque.next()){
                    prepState = conn.prepareStatement("insert into risques values ("+id+","+insertString(nom)+","+priorite+","+impact+","+etat+","+insertString(description)+","+idProjet+")");
                    prepState.execute(); // Execution de la requete
                } else{
                    PreparedStatement updateRisque = conn.prepareStatement(
                            "update risques set nom=?, description=?, priorite=?, impact=?, etat=?, idprojet=? where idrisque ="+id);
                    updateRisque.setString(1,nom);
                    updateRisque.setString(2,description);
                    updateRisque.setInt(3,updateInt(priorite));
                    updateRisque.setInt(4,updateInt(impact));
                    updateRisque.setInt(5,updateInt(etat));
                    updateRisque.setInt(6,updateInt(idProjet));
                    
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
    
    
    public void majTaches() throws NullValueXMLException{
        String id = null;
        String nom = null;
        String description = null;
        String etat = null;
        String chargePrevue = null;
        String tempsPasse = null;
        String resteAPasser = null;
        String dateDebutPrevue = null;
        String dateDebutReelle = null;
        String dateFinPrevue = null;
        String dateFinReelle = null;
        String idIteration = null;
        String idMembre = null;
        
        NodeList listeTache = this.document.getElementsByTagName("eltTache");
        NodeList listeNoeud;
        
        for(int i=0;i<listeTache.getLength();i++){
            listeNoeud = listeTache.item(i).getChildNodes();
            
            // on recherche l'id de la tache
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            try{
                id = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche le nom de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            try{
                nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche la description de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
                b++;
            }
            try{
                description = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche l'etat de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("etat") != 0) {
                b++;
            }
            try{
                etat = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche la charge prevue de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("chargePrevue") != 0) {
                b++;
            }
            try{
                chargePrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche le temps passe sur la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("tempsPasse") != 0) {
                b++;
            }
            try{
                tempsPasse = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche le temps qu'il reste a passer sur la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("resteAPasser") != 0) {
                b++;
            }
            try{
                resteAPasser = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche la date prevue de debut de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutPrevue") != 0) {
                b++;
            }
            try{
                dateDebutPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche la date reelle de debut de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutReelle") != 0) {
                b++;
            }
            try{
                dateDebutReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            
            // on recherche la date prevue de fin de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinPrevue") != 0) {
                b++;
            }
            try{
                dateFinPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            
            // on recherche la date reelle de fin de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinReelle") != 0) {
                b++;
            }
            try{
                dateFinReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche l'id de l'iteration auquel est rattache la tache
            try{
                idIteration = lireIdIteration_Tache(id);
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche l'id du membre auquel est rattache la tache
            try{
                idMembre = lireIdMembre_Tache(id);
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from taches where idtache="+id);
                ResultSet rstache = prepState.executeQuery(); // Execution de la requete
                
                if(!rstache.next()){
                    prepState = conn.prepareStatement("insert into taches values ("+id+","+insertString(nom)+","+insertString(description)+","+etat+","+chargePrevue+","+tempsPasse+","+resteAPasser+","+insertString(dateDebutPrevue)+","+insertString(dateFinPrevue)+","+insertString(dateDebutReelle)+","+insertString(dateFinReelle)+","+idIteration+","+idMembre+")");
                    prepState.execute(); // Execution de la requete
                }else{
                    PreparedStatement updateTache = conn.prepareStatement(
                            "update taches set nom=?, description=?, etat=?, chargeprevue=?, tempspasse=?, tempsrestant=?, datedebutprevue=?, datefinprevue=?, datedebutreelle=?, datefinreelle=?, iditeration=?, idmembre=? where idtache ="+id);
                    updateTache.setString(1,nom);
                    updateTache.setString(2,description);
                    updateTache.setInt(3,updateInt(etat));
                    updateTache.setInt(4,updateInt(chargePrevue));
                    updateTache.setInt(5,updateInt(tempsPasse));
                    updateTache.setInt(6,updateInt(resteAPasser));
                    updateTache.setString(7,dateDebutPrevue);
                    updateTache.setString(8,dateFinPrevue);
                    updateTache.setString(9,dateDebutReelle);
                    updateTache.setString(10,dateFinReelle);
                    updateTache.setInt(11,updateInt(idIteration));
                    updateTache.setInt(12,updateInt(idMembre));
                    
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
    
    private String lireIdIteration_Tache(String idTache) throws NullPointerException{
        NodeList listeItTache = this.document.getElementsByTagName("IterationTache");
        NodeList listeIdTache;
        
        String id = null;
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
                    if(listeIdTache.item(j).getFirstChild().getNodeValue().compareTo(idTache) == 0){
                        b = 0;
                        while(NoeudItTache.getChildNodes().item(b).getNodeName().compareTo("idIteration") != 0) {
                            b++;
                        }
                        id = NoeudItTache.getChildNodes().item(b).getFirstChild().getNodeValue();
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
    
    
    private String lireIdMembre_Tache(String idTache) throws NullPointerException{
        NodeList listeItTache = this.document.getElementsByTagName("MembreTache");
        NodeList listeIdTache;
        
        String id = null;
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
                    if(listeIdTache.item(j).getFirstChild().getNodeValue().compareTo(idTache) == 0){
                        b = 0;
                        while(NoeudItTache.getChildNodes().item(b).getNodeName().compareTo("idMembre") != 0) {
                            b++;
                        }
                        id = NoeudItTache.getChildNodes().item(b).getFirstChild().getNodeValue();
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
    
    public void majTachesCollaboratives() throws NullValueXMLException{
        String id = null;
        String nom = null;
        String description = null;
        String etat = null;
        String chargePrevue = null;
        String tempsPasse = null;
        String resteAPasser = null;
        String dateDebutPrevue = null;
        String dateDebutReelle = null;
        String dateFinPrevue = null;
        String dateFinReelle = null;
        String idIteration = null;
        String idResponsable = null;
        
        NodeList listeTache = this.document.getElementsByTagName("eltTacheCollaborative");
        NodeList listeNoeud;
        
        for(int i=0;i<listeTache.getLength();i++){
            listeNoeud = listeTache.item(i).getChildNodes();
            
            // on recherche l'id de la tache
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            try{
                id = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche le nom de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            try{
                nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche la description de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
                b++;
            }
            try{
                description = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche l'etat de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("etat") != 0) {
                b++;
            }
            try{
                etat = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche la charge prevue de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("chargePrevue") != 0) {
                b++;
            }
            try{
                chargePrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche le temps passe sur la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("tempsPasse") != 0) {
                b++;
            }
            try{
                tempsPasse = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche le temps qu'il reste a passer sur la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("resteAPasser") != 0) {
                b++;
            }
            try{
                resteAPasser = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche la date prevue de debut de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutPrevue") != 0) {
                b++;
            }
            try{
                dateDebutPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche la date reelle de debut de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutReelle") != 0) {
                b++;
            }
            try{
                dateDebutReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            
            // on recherche la date prevue de fin de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinPrevue") != 0) {
                b++;
            }
            try{
                dateFinPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            
            // on recherche la date reelle de fin de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinReelle") != 0) {
                b++;
            }
            try{
                dateFinReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            
            // on recherche l'id de l'iteration auquel est rattache la tache
            try{
                idIteration = lireIdIteration_TacheCollaborative(id);
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche l'id du responsable de la tache
            try{
                idResponsable = lireIdResponsable_TacheCollaborative(id);
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from tachescollaboratives where idtache="+id);
                ResultSet rstache = prepState.executeQuery(); // Execution de la requete
                
                if(!rstache.next()){
                    prepState = conn.prepareStatement("insert into tachescollaboratives values ("+id+","+insertString(nom)+","+insertString(description)+","+etat+","+chargePrevue+","+tempsPasse+","+resteAPasser+","+insertString(dateDebutPrevue)+","+insertString(dateFinPrevue)+","+insertString(dateDebutReelle)+","+insertString(dateFinReelle)+","+idIteration+","+idResponsable+")");
                    prepState.execute(); // Execution de la requete
                }else{
                    PreparedStatement updateTache = conn.prepareStatement(
                            "update tachescollaboratives set nom=?, description=?, etat=?, chargeprevue=?, tempspasse=?, tempsrestant=?, datedebutprevue=?, datefinprevue=?, datedebutreelle=?, datefinreelle=?, iditeration=?, idresponsable=? where idtache ="+id);
                    updateTache.setString(1,nom);
                    updateTache.setString(2,description);
                    updateTache.setInt(3,updateInt(etat));
                    updateTache.setInt(4,updateInt(chargePrevue));
                    updateTache.setInt(5,updateInt(tempsPasse));
                    updateTache.setInt(6,updateInt(resteAPasser));
                    updateTache.setString(7,dateDebutPrevue);
                    updateTache.setString(8,dateFinPrevue);
                    updateTache.setString(9,dateDebutReelle);
                    updateTache.setString(10,dateFinReelle);
                    updateTache.setInt(11,updateInt(idIteration));
                    updateTache.setInt(12,updateInt(idResponsable));
                    
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
    
    private String lireIdIteration_TacheCollaborative(String idTache) throws NullValueXMLException{
        NodeList listeItTache = this.document.getElementsByTagName("IterationTacheCol");
        NodeList listeIdTache;
        
        String id = null;
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
                    if(listeIdTache.item(j).getFirstChild().getNodeValue().compareTo(idTache) == 0){
                        b = 0;
                        while(NoeudItTache.getChildNodes().item(b).getNodeName().compareTo("idIteration") != 0) {
                            b++;
                        }
                        id = NoeudItTache.getChildNodes().item(b).getFirstChild().getNodeValue();
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
    
    
    private String lireIdResponsable_TacheCollaborative(String idTache) throws NullValueXMLException{
        NodeList listeMembreTache = this.document.getElementsByTagName("MembreTacheCollaborative_Responsable");
        NodeList listeIdTache;
        
        String id = null;
        boolean trouve = false;
        int b = 0;
        int i = 0;
        int j = 0;
        
        while(i<listeMembreTache.getLength() && !trouve){
            Node NoeudMembreTache = listeMembreTache.item(i);
            
            b = 0;
            while(NoeudMembreTache.getChildNodes().item(b).getNodeName().compareTo("listeTacheCollaborative") != 0) {
                b++;
            }
            listeIdTache = NoeudMembreTache.getChildNodes().item(b).getChildNodes();
            
            j = 0;
            while(j<listeIdTache.getLength() && !trouve){
                if(listeIdTache.item(j).getNodeName().compareTo("id") == 0){
                    if(listeIdTache.item(j).getFirstChild().getNodeValue().compareTo(idTache) == 0){
                        b = 0;
                        while(NoeudMembreTache.getChildNodes().item(b).getNodeName().compareTo("idMembre") != 0) {
                            b++;
                        }
                        id = NoeudMembreTache.getChildNodes().item(b).getFirstChild().getNodeValue();
                        trouve = true;
                    }
                }
                j++;
            }
            i++;
        }
        
        if(!trouve){
            // traitement erreur : aucun id membre n'a etait trouve pour cette tache
            System.out.println("aucun id iteration n'a etait trouve pour cette tache");
        }
        
        return(id);
    }
    
    
    public void majArtefacts() throws NullValueXMLException{
        String id = null;
        String nom = null;
        String description = null;
        String livrable = null;
        String etat = null;
        String idResponsable = null;
        
        NodeList listeArtefact = this.document.getElementsByTagName("eltArtefact");
        NodeList listeNoeud;
        
        for(int i=0;i<listeArtefact.getLength();i++){
            listeNoeud = listeArtefact.item(i).getChildNodes();
            
            // on recherche l'id de l'artefact
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            try{
                id = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche le nom de l'artefact
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("nom") != 0) {
                b++;
            }
            try{
                nom = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche la description de l'artefact
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
                b++;
            }
            try{
                description = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche si l'artefact est livrable
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("livrable") != 0) {
                b++;
            }
            try{
                livrable = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche l'etat de l'artefact
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("etat") != 0) {
                b++;
            }
            try{
                etat = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){}
            
            // on recherche l'id du membre responsable de l'artefact
            try{
                idResponsable = lireIdMembre_Artefact(id);
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from artefacts where idartefact="+id);
                ResultSet rstache = prepState.executeQuery(); // Execution de la requete
                
                if(!rstache.next()){
                    prepState = conn.prepareStatement("insert into artefacts values ("+id+","+insertString(livrable)+","+etat+","+insertString(nom)+","+insertString(description)+","+idResponsable+")");
                    prepState.execute(); // Execution de la requete
                }else{
                    PreparedStatement updateTache = conn.prepareStatement(
                            "update artefacts set nom=?, description=?, etat=?, livrable=?, idresponsable=? where idartefact ="+id);
                    updateTache.setString(1,nom);
                    updateTache.setString(2,description);
                    updateTache.setInt(3,updateInt(etat));
                    updateTache.setString(4,livrable);
                    updateTache.setInt(5,updateInt(idResponsable));
                    
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
    
    private String lireIdMembre_Artefact(String idArtefact) throws NullValueXMLException{
        NodeList listeMembreArtefact = this.document.getElementsByTagName("MembreArtefact");
        NodeList listeIdArtefact;
        
        String id = null;
        boolean trouve = false;
        int b = 0;
        int i = 0;
        int j = 0;
        
        while(i<listeMembreArtefact.getLength() && !trouve){
            Node NoeudMembreArtefact = listeMembreArtefact.item(i);
            
            b = 0;
            while(NoeudMembreArtefact.getChildNodes().item(b).getNodeName().compareTo("listeArtefact") != 0) {
                b++;
            }
            listeIdArtefact = NoeudMembreArtefact.getChildNodes().item(b).getChildNodes();
            
            j = 0;
            while(j<listeIdArtefact.getLength() && !trouve){
                if(listeIdArtefact.item(j).getNodeName().compareTo("id") == 0){
                    if(listeIdArtefact.item(j).getFirstChild().getNodeValue().compareTo(idArtefact) == 0){
                        b = 0;
                        while(NoeudMembreArtefact.getChildNodes().item(b).getNodeName().compareTo("idMembre") != 0) {
                            b++;
                        }
                        id = NoeudMembreArtefact.getChildNodes().item(b).getFirstChild().getNodeValue();
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
    
    
    public void majLiensMembres_TachesCollaboratives() throws NullValueXMLException{
        String idMembre = null;
        Vector listeIdTache = new Vector();
        Vector listeMembreTache = new Vector();
        
        NodeList listeNoeud;
        NodeList listeNoeudIdTache;
        
        NodeList listeNoeudTemp = this.document.getElementsByTagName("MembreTacheCollaborative_Realise").item(0).getChildNodes();
        // on recherche le noeud listeMembre
        int b = 0;
        while(listeNoeudTemp.item(b).getNodeName().compareTo("listeMembre") != 0) {
            b++;
        }
        listeNoeudTemp = listeNoeudTemp.item(b).getChildNodes();
        
        // on recherche les noeuds Membre
        for(int n=0;n<listeNoeudTemp.getLength();n++){
            if(listeNoeudTemp.item(n).getNodeName().compareTo("Membre") == 0){
                listeMembreTache.add(listeNoeudTemp.item(n));
            }
        }
        
        // on parcourt tous les noeuds Membre
        for(int i=0;i<listeMembreTache.size();i++){
            listeNoeud = ((Node)listeMembreTache.get(i)).getChildNodes();
            
            // on recherche l'id du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            try{
                idMembre = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche les taches auquelles il participe
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("listeTacheCollaborative") != 0) {
                b++;
            }
            listeNoeudIdTache = listeNoeud.item(b).getChildNodes();
            for(int j=0;j<listeNoeudIdTache.getLength();j++){
                if(listeNoeudIdTache.item(j).getNodeName().compareTo("id") == 0){
                    listeIdTache.add(listeNoeudIdTache.item(j).getFirstChild().getNodeValue());
                }
            }
            
            for(int k=0;k<listeIdTache.size();k++){
                try {
                    // Requete SQL
                    PreparedStatement prepState = conn.prepareStatement("Select * from membres_tachescollaboratives where idmembre="+idMembre+" and idtache="+listeIdTache.get(k));
                    ResultSet rs = prepState.executeQuery(); // Execution de la requete
                    
                    if(!rs.next()){
                        prepState = conn.prepareStatement("insert into membres_tachescollaboratives values ("+idMembre+","+listeIdTache.get(k)+")");
                        prepState.execute(); // Execution de la requete
                    }
                    
                }catch (SQLException ex) { // Si une SQLException survient
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                    ex.printStackTrace();
                }
            }
            listeIdTache.removeAllElements();
        }
    }
    
    
    
    public void majLiensMembres_Projets() throws NullValueXMLException{
        String idProjet = null;
        Vector listeIdMembre = new Vector();
        try{
            idProjet = lireIdProjet();
        }catch(NullPointerException e){
            throw new NullValueXMLException();
        }
        
        NodeList listeMembre = this.document.getElementsByTagName("eltMembre");
        NodeList listeNoeud;
        
        for(int i=0;i<listeMembre.getLength();i++){
            listeNoeud = listeMembre.item(i).getChildNodes();
            
            // on recherche l'id des membres
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            listeIdMembre.add(listeNoeud.item(b).getFirstChild().getNodeValue());
        }
        
        for(int k=0;k<listeIdMembre.size();k++){
            try {
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from membres_projets where idprojet="+idProjet+" and idmembre="+listeIdMembre.get(k));
                ResultSet rs = prepState.executeQuery(); // Execution de la requete
                
                if(!rs.next()){
                    prepState = conn.prepareStatement("insert into membres_projets values ("+listeIdMembre.get(k)+","+idProjet+")");
                    prepState.execute(); // Execution de la requete
                }
                
            }catch (SQLException ex) { // Si une SQLException survient
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
            }
        }
        listeIdMembre.removeAllElements();
    }
    
    public void majLiensArtefacts_Entrees_Taches() throws NullValueXMLException{
        String idTache = null;
        Vector listeIdArtefacts = new Vector();
        
        NodeList listeArtefactTache = this.document.getElementsByTagName("TacheArtefact_Entree");
        NodeList listeNoeud;
        NodeList listeNoeudIdArtefacts;
        
        for(int i=0;i<listeArtefactTache.getLength();i++){
            listeNoeud = listeArtefactTache.item(i).getChildNodes();
            
            // on recherche l'id de la tache
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("idTache") != 0) {
                b++;
            }
            try{
                idTache = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche les artefacts en entree de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("listeArtefact") != 0) {
                b++;
            }
            listeNoeudIdArtefacts = listeNoeud.item(b).getChildNodes();
            for(int j=0;j<listeNoeudIdArtefacts.getLength();j++){
                if(listeNoeudIdArtefacts.item(j).getNodeName().compareTo("id") == 0){
                    listeIdArtefacts.add(listeNoeudIdArtefacts.item(j).getFirstChild().getNodeValue());
                }
            }
            
            for(int k=0;k<listeIdArtefacts.size();k++){
                try {
                    // Requete SQL
                    PreparedStatement prepState = conn.prepareStatement("Select * from artefacts_entrees_taches where idtache="+idTache+" and idartefact="+listeIdArtefacts.get(k));
                    ResultSet rs = prepState.executeQuery(); // Execution de la requete
                    
                    if(!rs.next()){
                        prepState = conn.prepareStatement("insert into artefacts_entrees_taches values ("+listeIdArtefacts.get(k)+","+idTache+")");
                        prepState.execute(); // Execution de la requete
                    }
                    
                }catch (SQLException ex) { // Si une SQLException survient
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                    ex.printStackTrace();
                }
            }
            listeIdArtefacts.removeAllElements();
        }
    }
    
     public void majLiensArtefacts_Sorties_Taches() throws NullValueXMLException{
        String idTache = null;
        Vector listeIdArtefacts = new Vector();
        
        NodeList listeArtefactTache = this.document.getElementsByTagName("TacheArtefact_Sortie");
        NodeList listeNoeud;
        NodeList listeNoeudIdArtefacts;
        
        for(int i=0;i<listeArtefactTache.getLength();i++){
            listeNoeud = listeArtefactTache.item(i).getChildNodes();
            
            // on recherche l'id de la tache
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("idTache") != 0) {
                b++;
            }
            try{
                idTache = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche les artefacts en sortie de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("listeArtefact") != 0) {
                b++;
            }
            listeNoeudIdArtefacts = listeNoeud.item(b).getChildNodes();
            for(int j=0;j<listeNoeudIdArtefacts.getLength();j++){
                if(listeNoeudIdArtefacts.item(j).getNodeName().compareTo("id") == 0){
                    listeIdArtefacts.add(listeNoeudIdArtefacts.item(j).getFirstChild().getNodeValue());
                }
            }
            
            for(int k=0;k<listeIdArtefacts.size();k++){
                try {
                    // Requete SQL
                    PreparedStatement prepState = conn.prepareStatement("Select * from artefacts_sorties_taches where idtache="+idTache+" and idartefact="+listeIdArtefacts.get(k));
                    ResultSet rs = prepState.executeQuery(); // Execution de la requete
                    
                    if(!rs.next()){
                        prepState = conn.prepareStatement("insert into artefacts_sorties_taches values ("+listeIdArtefacts.get(k)+","+idTache+")");
                        prepState.execute(); // Execution de la requete
                    }
                    
                }catch (SQLException ex) { // Si une SQLException survient
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                    ex.printStackTrace();
                }
            }
            listeIdArtefacts.removeAllElements();
        }
    }
    
    
    
    public void majLiensArtefacts_Entrees_TachesCollaboratives() throws NullValueXMLException{
        String idTache = null;
        Vector listeIdArtefacts = new Vector();
        
        NodeList listeArtefactTache = this.document.getElementsByTagName("TacheColArtefact_Entree");
        NodeList listeNoeud;
        NodeList listeNoeudIdArtefacts;
        
        for(int i=0;i<listeArtefactTache.getLength();i++){
            listeNoeud = listeArtefactTache.item(i).getChildNodes();
            
            // on recherche l'id de la tache
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("idTacheCol") != 0) {
                b++;
            }
            try{
                idTache = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche les artefacts en entree de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("listeArtefact") != 0) {
                b++;
            }
            listeNoeudIdArtefacts = listeNoeud.item(b).getChildNodes();
            for(int j=0;j<listeNoeudIdArtefacts.getLength();j++){
                if(listeNoeudIdArtefacts.item(j).getNodeName().compareTo("id") == 0){
                    listeIdArtefacts.add(listeNoeudIdArtefacts.item(j).getFirstChild().getNodeValue());
                }
            }
            
            for(int k=0;k<listeIdArtefacts.size();k++){
                try {
                    // Requete SQL
                    PreparedStatement prepState = conn.prepareStatement("Select * from artefacts_entrees_tachescollaboratives where idtache="+idTache+" and idartefact="+listeIdArtefacts.get(k));
                    ResultSet rs = prepState.executeQuery(); // Execution de la requete
                    
                    if(!rs.next()){
                        prepState = conn.prepareStatement("insert into artefacts_entrees_tachescollaboratives values ("+listeIdArtefacts.get(k)+","+idTache+")");
                        prepState.execute(); // Execution de la requete
                    }
                    
                }catch (SQLException ex) { // Si une SQLException survient
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                    ex.printStackTrace();
                }
            }
            listeIdArtefacts.removeAllElements();
        }
    }
    
    
    public void majLiensArtefacts_Sorties_TachesCollaboratives() throws NullValueXMLException{
        String idTache = null;
        Vector listeIdArtefacts = new Vector();
        
        NodeList listeArtefactTache = this.document.getElementsByTagName("TacheColArtefact_Sortie");
        NodeList listeNoeud;
        NodeList listeNoeudIdArtefacts;
        
        for(int i=0;i<listeArtefactTache.getLength();i++){
            listeNoeud = listeArtefactTache.item(i).getChildNodes();
            
            // on recherche l'id de la tache
            int b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("idTacheCol") != 0) {
                b++;
            }
            try{
                idTache = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche les artefacts en sortie de la tache
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("listeArtefact") != 0) {
                b++;
            }
            listeNoeudIdArtefacts = listeNoeud.item(b).getChildNodes();
            for(int j=0;j<listeNoeudIdArtefacts.getLength();j++){
                if(listeNoeudIdArtefacts.item(j).getNodeName().compareTo("id") == 0){
                    listeIdArtefacts.add(listeNoeudIdArtefacts.item(j).getFirstChild().getNodeValue());
                }
            }
            
            for(int k=0;k<listeIdArtefacts.size();k++){
                try {
                    // Requete SQL
                    PreparedStatement prepState = conn.prepareStatement("Select * from artefacts_sorties_tachescollaboratives where idtache="+idTache+" and idartefact="+listeIdArtefacts.get(k));
                    ResultSet rs = prepState.executeQuery(); // Execution de la requete
                    
                    if(!rs.next()){
                        prepState = conn.prepareStatement("insert into artefacts_sorties_tachescollaboratives values ("+listeIdArtefacts.get(k)+","+idTache+")");
                        prepState.execute(); // Execution de la requete
                    }
                    
                }catch (SQLException ex) { // Si une SQLException survient
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                    ex.printStackTrace();
                }
            }
            listeIdArtefacts.removeAllElements();
        }
    }
    
    
    public void majLiensMembres_Roles() throws NullValueXMLException{
        String idMembre = null;
        Vector listeIdRole = new Vector();
        Vector listeMembreRole = new Vector();
        
        NodeList listeNoeud;
        NodeList listeNoeudIdRole;
        
        NodeList listeNoeudTemp = this.document.getElementsByTagName("MembreRole").item(0).getChildNodes();
        // on recherche le noeud listeMembre
        int b = 0;
        while(listeNoeudTemp.item(b).getNodeName().compareTo("listeMembre") != 0) {
            b++;
        }
        listeNoeudTemp = listeNoeudTemp.item(b).getChildNodes();
        
        // on recherche les noeuds Membre
        for(int n=0;n<listeNoeudTemp.getLength();n++){
            if(listeNoeudTemp.item(n).getNodeName().compareTo("Membre") == 0){
                listeMembreRole.add(listeNoeudTemp.item(n));
            }
        }
        
        // on parcourt tous les noeuds Membre
        for(int i=0;i<listeMembreRole.size();i++){
            listeNoeud = ((Node)listeMembreRole.get(i)).getChildNodes();
            
            // on recherche l'id du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("id") != 0) {
                b++;
            }
            try{
                idMembre = listeNoeud.item(b).getFirstChild().getNodeValue();
            }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
            
            // on recherche les roles qu'il exerce
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("listeRole") != 0) {
                b++;
            }
            listeNoeudIdRole = listeNoeud.item(b).getChildNodes();
            for(int j=0;j<listeNoeudIdRole.getLength();j++){
                if(listeNoeudIdRole.item(j).getNodeName().compareTo("id") == 0){
                    listeIdRole.add(listeNoeudIdRole.item(j).getFirstChild().getNodeValue());
                }
            }
            
            for(int k=0;k<listeIdRole.size();k++){
                try {
                    // Requete SQL
                    PreparedStatement prepState = conn.prepareStatement("Select * from roles_membres where idmembre="+idMembre+" and idrole='"+listeIdRole.get(k)+"'");
                    ResultSet rs = prepState.executeQuery(); // Execution de la requete
                    
                    if(!rs.next()){
                        prepState = conn.prepareStatement("insert into roles_membres values ('"+listeIdRole.get(k)+"',"+idMembre+")");
                        prepState.execute(); // Execution de la requete
                    }
                    
                }catch (SQLException ex) { // Si une SQLException survient
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                    ex.printStackTrace();
                }
            }
            listeIdRole.removeAllElements();
        }
    }
    
    
    public void majIndicateurIteration() throws NullValueXMLException{
        int idIt = 0;
        int chargeTotal = 0;
        int nbTachesTerminees = 0;
        float dureeMoyTache = 0;
        int nbParticipants = 0;
        float chargeMoyMembre = 0;
        float nbMoyTacheMembre = 0;
        
        try {
            // On recupere toutes les iterations du projet
            PreparedStatement prepState = conn.prepareStatement("select * from iterations where idprojet="+lireIdProjet());
            ResultSet rs = prepState.executeQuery(); // Execution de la requete
            while(rs.next()) {
                // remise a 0 des indicateurs
                chargeTotal = 0;
                nbTachesTerminees = 0;
                dureeMoyTache = 0;
                nbParticipants = 0;
                chargeMoyMembre = 0;
                nbMoyTacheMembre = 0;
                
                idIt = rs.getInt("iditeration");
                // Charges Totales
                chargeTotal = calculerChargesTotal_Iteration(idIt);
                // Nombre de taches terminees
                nbTachesTerminees = nbTachesTerminees_Iteration(idIt);
                // Duree Moyenne des taches
                if(nbTachesTerminees == 0)
                    dureeMoyTache = 0;
                else
                    dureeMoyTache = (float)calculerChargesTotalTachesTerminees_Iteration(idIt)/(float)nbTachesTerminees;
                // Nombre de participants
                nbParticipants = nbParticipants_Iteration(idIt);
                // Charge moyenne par membre
                if(nbParticipants == 0)
                    chargeMoyMembre = 0;
                else
                    chargeMoyMembre = (float)chargeTotal/(float)nbParticipants;
                // Nombre moyen de tache par membre
                if(nbParticipants == 0)
                    nbMoyTacheMembre = 0;
                else
                    nbMoyTacheMembre = (float)nbTaches_Iteration(idIt)/(float)nbParticipants;
                
                // Requete SQL
                prepState = conn.prepareStatement("Select * from indicateurs_iteration where iditeration="+idIt);
                ResultSet rsSelect = prepState.executeQuery(); // Execution de la requete
                
                if(!rsSelect.next()){
                    prepState = conn.prepareStatement("insert into indicateurs_iteration values ("+idIt+","+chargeTotal+","+nbTachesTerminees+","+dureeMoyTache+","+nbParticipants+","+chargeMoyMembre+","+nbMoyTacheMembre+")");
                    prepState.execute(); // Execution de la requete
                }else{
                    PreparedStatement updateIndicateur = conn.prepareStatement(
                            "update indicateurs_iteration set totalcharges=?, nombretachesterminees=?, dureemoyennetache=?, nombreparticipants=?, chargemoyenneparticipants=?, nombremoyentachesparticipants=? where iditeration ="+idIt);
                    updateIndicateur.setInt(1,chargeTotal);
                    updateIndicateur.setInt(2,nbTachesTerminees);
                    updateIndicateur.setFloat(3,dureeMoyTache);
                    updateIndicateur.setInt(4,nbParticipants);
                    updateIndicateur.setFloat(5,chargeMoyMembre);
                    updateIndicateur.setFloat(6,nbMoyTacheMembre);
                    
                    updateIndicateur.executeUpdate();
                }
            }
        }catch (SQLException ex) { // Si une SQLException survient
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }
    
    private int calculerChargesTotal_Iteration(int idIt){
        int Somme = 0;
        
        try {
            // On recupere le total des charges pour les taches
            PreparedStatement prepState = conn.prepareStatement("select SUM(tempspasse) from taches where iditeration="+idIt);
            ResultSet rsSomme = prepState.executeQuery(); // Execution de la requete
            if(rsSomme.next())
                Somme += rsSomme.getInt(1);
            
            // On recupere le total des charges pour les taches collaboratives
            prepState = conn.prepareStatement("select SUM(tempspasse) from tachescollaboratives where iditeration="+idIt);
            rsSomme = prepState.executeQuery(); // Execution de la requete
            if(rsSomme.next())
                Somme += rsSomme.getInt(1);
            
        }catch (SQLException ex) { // Si une SQLException survient
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
        return Somme;
    }
    
    
    private int calculerChargesTotalTachesTerminees_Iteration(int idIt){
        int Somme = 0;
        
        try {
            // On recupere le total des charges pour les taches terminees
            PreparedStatement prepState = conn.prepareStatement("select SUM(tempspasse) from taches where iditeration="+idIt+" and datefinreelle!='0001-01-01'");
            ResultSet rsSomme = prepState.executeQuery(); // Execution de la requete
            if(rsSomme.next())
                Somme += rsSomme.getInt(1);
            
            // On recupere le total des charges pour les taches collaboratives terminees
            prepState = conn.prepareStatement("select SUM(tempspasse) from tachescollaboratives where iditeration="+idIt+" and datefinreelle!='0001-01-01'");
            rsSomme = prepState.executeQuery(); // Execution de la requete
            if(rsSomme.next())
                Somme += rsSomme.getInt(1);
            
        }catch (SQLException ex) { // Si une SQLException survient
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
        return Somme;
    }
    
    private int nbTachesTerminees_Iteration(int idIt){
        int Somme = 0;
        
        try {
            // On recupere le nombre de taches terminees
            PreparedStatement prepState = conn.prepareStatement("select COUNT(*) from taches where iditeration="+idIt+" and datefinreelle!='0001-01-01'");
            ResultSet rsSomme = prepState.executeQuery(); // Execution de la requete
            if(rsSomme.next())
                Somme += rsSomme.getInt(1);
            
            // On recupere le nombre de taches collaboratives terminees
            prepState = conn.prepareStatement("select COUNT(*) from tachescollaboratives where iditeration="+idIt+" and datefinreelle!='0001-01-01'");
            rsSomme = prepState.executeQuery(); // Execution de la requete
            if(rsSomme.next())
                Somme += rsSomme.getInt(1);
            
        }catch (SQLException ex) { // Si une SQLException survient
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
        return Somme;
    }
    
    private int nbTaches_Iteration(int idIt){
        int Somme = 0;
        
        try {
            // On recupere le nombre de taches
            PreparedStatement prepState = conn.prepareStatement("select COUNT(*) from taches where iditeration="+idIt);
            ResultSet rsSomme = prepState.executeQuery(); // Execution de la requete
            if(rsSomme.next())
                Somme += rsSomme.getInt(1);
            
            // On recupere le nombre de taches collaboratives
            prepState = conn.prepareStatement("select COUNT(*) from tachescollaboratives where iditeration="+idIt);
            rsSomme = prepState.executeQuery(); // Execution de la requete
            if(rsSomme.next())
                Somme += rsSomme.getInt(1);
            
        }catch (SQLException ex) { // Si une SQLException survient
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
        return Somme;
    }
    
    
    
    private int nbParticipants_Iteration(int idIt){
        int nb = 0;
        Vector idMembre = new Vector();
        
        try {
            // On recupere le nombre de participants pour les taches
            PreparedStatement prepState = conn.prepareStatement("select idmembre from taches where iditeration="+idIt);
            ResultSet rs = prepState.executeQuery(); // Execution de la requete
            while(rs.next()) {
                if(!idMembre.contains(new Integer(rs.getInt(1)))){
                    idMembre.add(new Integer(rs.getInt(1)));
                    nb ++;
                }
            }
            
            // On recupere le nombre de participants pour les taches collaboratives
            prepState = conn.prepareStatement("select idtache from tachescollaboratives where iditeration="+idIt);
            rs = prepState.executeQuery(); // Execution de la requete
            while(rs.next()){
                prepState = conn.prepareStatement("select idmembre from membres_tachescollaboratives where idtache="+rs.getInt(1));
                ResultSet rsMembre = prepState.executeQuery(); // Execution de la requete
                while(rsMembre.next()) {
                    if(!idMembre.contains(new Integer(rsMembre.getInt(1)))){
                        idMembre.add(new Integer(rsMembre.getInt(1)));
                        nb ++;
                    }
                }
            }
            
        }catch (SQLException ex) { // Si une SQLException survient
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
        return nb;
    }
    
    
    public void majIndicateurProjet() throws NullValueXMLException{
        String idProjet = null;
        int totalcharges = 0;
        int tachesterminees = 0;
        float dureemoyennetache = 0;
        int nombreparticipants = 0;
        float avancementprojet = 0;
        
        try{
            idProjet = lireIdProjet();
        }catch(NullPointerException e){
                throw new NullValueXMLException();
            }
        
        try {
            // On recupere le total des charges
            PreparedStatement prepState = conn.prepareStatement("select SUM(ind.totalcharges) from iterations i, indicateurs_iteration ind where i.idprojet="+idProjet+" and ind.iditeration=i.iditeration");
            ResultSet rs = prepState.executeQuery(); // Execution de la requete
            if(rs.next())
                totalcharges = rs.getInt(1);
            
            // On recupere le nombre de taches terminees
            prepState = conn.prepareStatement("select SUM(ind.nombretachesterminees) from iterations i, indicateurs_iteration ind where i.idprojet="+idProjet+" and ind.iditeration=i.iditeration");
            rs = prepState.executeQuery(); // Execution de la requete
            if(rs.next())
                tachesterminees = rs.getInt(1);
            
            // On recupere la duree moyenne des taches
            prepState = conn.prepareStatement("select iditeration from iterations where idprojet="+idProjet);
            rs = prepState.executeQuery(); // Execution de la requete
            int nbtachesfinies = 0;
            int chTotales = 0;
            while(rs.next()){
                chTotales += calculerChargesTotalTachesTerminees_Iteration(rs.getInt(1));
                nbtachesfinies += nbTachesTerminees_Iteration(rs.getInt(1));
            }
            dureemoyennetache = (float)chTotales/(float)nbtachesfinies;
            
            // On recupere le nombre de participants
            nombreparticipants = nbParticipants_Projet();
            
            // Avancement du projet
            avancementprojet = 10.0f;
            
            // Requete SQL
            prepState = conn.prepareStatement("Select * from indicateurs_projet where idprojet="+idProjet);
            ResultSet rsSelect = prepState.executeQuery(); // Execution de la requete
            
            if(!rsSelect.next()){
                prepState = conn.prepareStatement("insert into indicateurs_projet values ("+idProjet+","+totalcharges+","+tachesterminees+","+dureemoyennetache+","+nombreparticipants+","+avancementprojet+")");
                prepState.execute(); // Execution de la requete
            }else{
                PreparedStatement updateIndicateur = conn.prepareStatement(
                        "update indicateurs_projet set totalcharges=?, tachesterminees=?, dureemoyennetache=?, nombreparticipants=?, avancementprojet=? where idprojet ="+idProjet);
                updateIndicateur.setInt(1,totalcharges);
                updateIndicateur.setInt(2,tachesterminees);
                updateIndicateur.setFloat(3,dureemoyennetache);
                updateIndicateur.setInt(4,nombreparticipants);
                updateIndicateur.setFloat(5,avancementprojet);
                
                updateIndicateur.executeUpdate();
            }
        }catch (SQLException ex) { // Si une SQLException survient
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }
    
    
    private int nbParticipants_Projet() throws NullValueXMLException{
        int nb = 0;
        Vector idMembre = new Vector();
        
        try {
            PreparedStatement prepState = conn.prepareStatement("select iditeration from iterations where idprojet="+lireIdProjet());
            ResultSet rsIt = prepState.executeQuery(); // Execution de la requete
            while(rsIt.next()) {
                // On recupere l'id de l'IT
                int idIt = rsIt.getInt(1);
                
                // On recupere le nombre de participants
                prepState = conn.prepareStatement("select idmembre from taches where iditeration="+idIt);
                ResultSet rs = prepState.executeQuery(); // Execution de la requete
                while(rs.next()) {
                    if(!idMembre.contains(new Integer(rs.getInt(1)))){
                        idMembre.add(new Integer(rs.getInt(1)));
                        nb ++;
                    }
                }
                
                // On recupere le nombre de participants pour les taches collaboratives
                prepState = conn.prepareStatement("select idtache from tachescollaboratives where iditeration="+idIt);
                rs = prepState.executeQuery(); // Execution de la requete
                while(rs.next()){
                    prepState = conn.prepareStatement("select idmembre from membres_tachescollaboratives where idtache="+rs.getInt(1));
                    ResultSet rsMembre = prepState.executeQuery(); // Execution de la requete
                    while(rsMembre.next()) {
                        if(!idMembre.contains(new Integer(rsMembre.getInt(1)))){
                            idMembre.add(new Integer(rsMembre.getInt(1)));
                            nb ++;
                        }
                    }
                }
            }
        }catch (SQLException ex) { // Si une SQLException survient
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
        return nb;
    }
}