import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

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
    private String _fichierPreferences;
    /**
     * Document contenant le fichier Xml
     */
    private Document document;
    
    /** Creates a new instance of ParserXMLFichierWF */
    public ParserXMLFichierWF(String Xml) {
        this._fichierPreferences = Xml;
        
        try{
            DocumentBuilderFactory usine = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructeur = usine.newDocumentBuilder();
            this.document = constructeur.parse(new FileInputStream(new File(this._fichierPreferences)));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
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
    
    
    // CODE A OPTIMISER !!!! NE PAS PARCOURIR TOUS LES ATTRIBUTS SI L'ID EST DEJA DANS LA BASE
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
            
            // on recherche la date reelle de debut d'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateDebutReelle") != 0) {
                b++;
            }
            dateDebutReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche la date prevue de fin d'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinPrevue") != 0) {
                b++;
            }
            dateFinPrevue = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            // on recherche la date reelle de fin d'iteration
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("dateFinReelle") != 0) {
                b++;
            }
            dateFinReelle = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            try {
                // Connexion a la base de donnees
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/p2s?user=root&password=rootpass");
                
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from iterations where iditeration="+id);
                ResultSet rsit = prepState.executeQuery(); // Execution de la requete
                
                if(!rsit.next()){
                    prepState = conn.prepareStatement("insert into iterations values ("+id+","+numero+",'"+dateDebutPrevue+"','"+dateDebutReelle+"','"+dateFinPrevue+"','"+dateFinReelle+"')");
                    prepState.execute(); // Execution de la requete
                    prepState = conn.prepareStatement("insert into projetiterations values("+idProjet+","+id+")");
                    prepState.execute(); // Execution de la requete
                }
                
                
            }catch (SQLException ex) { // Si une SQLException survient
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
            }
            
        }
    }
    
    // CODE A OPTIMISER !!!! NE PAS PARCOURIR TOUS LES ATTRIBUTS SI L'ID EST DEJA DANS LA BASE
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
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/p2s?user=root&password=rootpass");
                
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from membres where idmembre="+id);
                ResultSet rsmembre = prepState.executeQuery(); // Execution de la requete
                
                if(!rsmembre.next()){
                    prepState = conn.prepareStatement("insert into membres values ("+id+",'"+nom+"','"+prenom+"','"+adresse+"','"+tel+"','"+mail+"')");
                    prepState.execute(); // Execution de la requete
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
            
            // on recherche le prenom du membre
            b = 0;
            while(listeNoeud.item(b).getNodeName().compareTo("description") != 0) {
                b++;
            }
            description = listeNoeud.item(b).getFirstChild().getNodeValue();
            
            
            try {
                // Connexion a la base de donnees
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/p2s?user=root&password=rootpass");
                
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from taches where idtache="+id);
                ResultSet rstache = prepState.executeQuery(); // Execution de la requete
                
                if(!rstache.next()){
                    prepState = conn.prepareStatement("insert into taches values ("+id+",'"+nom+"','"+description+"')");
                    prepState.execute(); // Execution de la requete
                    
                    // Mise a jour des liens iteration-taches
                    NodeList listeIterationTache = this.document.getElementsByTagName("IterationTache");
                    NodeList listeNoeudNiv1, listeNoeudNiv2;
                    
                    /*boolean trouve = false;
                    int j=0;
                    
                    while(j<listeIterationTache.getLength() && !trouve){
                        listeNoeudNiv1 = listeIterationTache.item(j).getChildNodes();
                        
                        b = 0;
                        while(listeNoeudNiv1.item(b).getNodeName().compareTo("listeIdTache") != 0) {
                            System.out.println(listeNoeudNiv1.item(b).getNodeName());
                            b++;
                        }
                        listeNoeudNiv2 = listeNoeudNiv1.item(b).getChildNodes();
                                                
                        int k = 0;
                        System.out.println(listeNoeudNiv2.getLength());
                        while(k<listeNoeudNiv2.getLength() && !trouve){ 
                            System.out.println(listeNoeudNiv2.item(k).getFirstChild().getNodeValue());
                            if(listeNoeudNiv2.item(k).getFirstChild().getNodeValue().compareTo(Integer.toString(id)) == 0)
                                trouve = true;
                            k++;
                        }
                        if(trouve){
                            b = 0;
                            while(listeNoeudNiv1.item(b).getNodeName().compareTo("idIteration") != 0) {
                                b++;
                            }
                            int idit = new Integer(listeNoeudNiv1.item(b).getFirstChild().getNodeValue()).intValue();
                            
                            prepState = conn.prepareStatement("insert into iterationtaches values ("+idit+","+id+"')");
                            prepState.execute(); // Execution de la requete
                        }
                        j++;
                    }     */               
                }
                
                
            }catch (SQLException ex) { // Si une SQLException survient
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
            }
            
        }
    }
    
}
