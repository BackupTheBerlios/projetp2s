/*
 * LoginServlet.java
 *
 * Created on 14 janvier 2005, 02:17
 */

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
/**
 *
 * @author Fabien
 * @version
 */
public class LoginServlet extends HttpServlet {
    
        
    /** Initializes the servlet.
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    /** Destroys the servlet.
     */
    public void destroy() {
        
    }
    
    
    
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        // On verifie que le login et que le mot de passe sont non nul
        if ((login != null) && (password != null)){
            try {                
                // Connexion a la base de donnees                
                Connection conn = DriverManager.getConnection("jdbc:mysql://"+InfosBDServlet.InfosBD.getProperty("host")+"/"+InfosBDServlet.InfosBD.getProperty("base")+"?user="+InfosBDServlet.InfosBD.getProperty("login")+"&password="+InfosBDServlet.InfosBD.getProperty("password"));
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from utilisateurs where login = '" + login + "' and password ='"+ password + "'");
                ResultSet rsUser = prepState.executeQuery(); // Execution de la requete
                if(rsUser.next()){
                    out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
                    
                    out.println("<utilisateur>"); // debut flux
                    out.println("<login>"); // login
                    out.println(rsUser.getString("login"));
                    out.println("</login>");
                    
                    out.println("<fonction>"); // fonction
                    out.println(rsUser.getString("fonction"));
                    out.println("</fonction>");
                    
                    if(rsUser.getString("fonction").compareTo("sup")==0 || rsUser.getString("fonction").compareTo("cdp")==0) {
                        /************************************************************************************************
                         *                                  SUPERVISEUR OU CHEF DE PROJET                               *
                         ************************************************************************************************/
                        //récupération des messages du superviseur ou chef de projet
                        prepState = conn.prepareStatement("Select * from messages where login = '" + rsUser.getString("login")+"'");
                        ResultSet rsMessages = prepState.executeQuery(); // Execution de la requete
                        out.println("<messages>");
                        while(rsMessages.next()) { //pour chaque message on crée des basiles <message></message>
                            out.println("<message>");
                            out.println("<sujet>");
                            out.println(rsMessages.getString("sujet"));
                            out.println("</sujet>");
                            out.println("<date>");
                            out.println(rsMessages.getString("date"));
                            out.println("</date>");
                            out.println("<detail>");
                            out.println(rsMessages.getString("message"));
                            out.println("</detail>");
                            out.println("</message>");
                        }
                        out.println("</messages>");
                        
                        
                        //Nom de la table où aller chercher les données : Superviseur ou chef de projet
                        String nomTable;
                        
                        //On choisit la table par rapport à la fonction de l'utilisateur
                        if(rsUser.getString("fonction").compareTo("sup")==0)
                            nomTable = "superviseur_projets";
                        else
                            nomTable = "chefprojet_projets";
                        
                        //Dans le cas d'un superviseur ou d'un chef de projet : il faut récupérer toutes les informations relatives à ses projets
                        
                        out.println("<projets>");
                        
                        //Récupération des identifiants de tous les projets du superviseur
                        prepState = conn.prepareStatement("Select idprojet from " + nomTable + " where login = '" + rsUser.getString("login")+"'");
                        ResultSet rsIdProjets = prepState.executeQuery(); // Execution de la requete
                        
                        while(rsIdProjets.next()) {
                            //Pour chaque projet,on crée des balises <projet></projet>
                            out.println("<projet>");
                            
                            prepState = conn.prepareStatement("Select * from projets where idprojet = '" + rsIdProjets.getString("idprojet") + "'");
                            ResultSet rsProjets = prepState.executeQuery(); // Execution de la requete
                            
                            if(rsProjets.next()){ //On se place sur le resultat de la requete (il n'y en a qu'un seul)
                                
                                /*********** RECUPERATION DES DONNEES DE BASES DU PROJET  ************/
                                
                                out.println("<id>");
                                out.println(rsProjets.getString("idprojet"));
                                out.println("</id>");
                                
                                out.println("<nom>");
                                out.println(rsProjets.getString("nom"));
                                out.println("</nom>");
                                
                                out.println("<description>");
                                
                                if(rsProjets.getString("description") != null)
                                    out.println(rsProjets.getString("description"));
                                
                                out.println("</description>");
                                
                                out.println("<dateDebut>");
                                if(rsProjets.getString("datedebut") != null)
                                    out.println(rsProjets.getString("datedebut"));
                                out.println("</dateDebut>");
                                
                                out.println("<dateFin>");
                                if(rsProjets.getString("datefin") != null)
                                    out.println(rsProjets.getString("datefin"));
                                out.println("</dateFin>");
                                
                                out.println("<budget>");
                                if(rsProjets.getFloat("budget") != -1)
                                    out.println(rsProjets.getFloat("budget"));
                                out.println("</budget>");
                                
                                /*********** RECUPERATION DES MEMBRES DU PROJET  ************/
                                prepState = conn.prepareStatement("Select m.idmembre,m.nom,m.prenom,m.adresse,m.telephone,m.email from membres m, membres_projets mp where m.idmembre = mp.idmembre AND mp.idprojet = '"+ rsIdProjets.getString("idprojet") +"'");
                                ResultSet rsMembres = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsMembres.next()){
                                    out.println("<membres>");
                                    do{
                                        out.println("<membre>");
                                        //Pour chaque membre, on récupère ses informations personnelles
                                        
                                        /************** INFORMATIONS PERSONNELLES DU MEMBRE *********/
                                        out.println("<id>");
                                        out.println(rsMembres.getString("idmembre"));
                                        out.println("</id>");
                                        
                                        out.println("<nom>");
                                        out.println(rsMembres.getString("nom"));
                                        out.println("</nom>");
                                        
                                        out.println("<prenom>");
                                        if(rsMembres.getString("prenom") != null)
                                            out.println(rsMembres.getString("prenom"));
                                        out.println("</prenom>");
                                        
                                        out.println("<adresse>");
                                        if(rsMembres.getString("adresse") != null)
                                            out.println(rsMembres.getString("adresse"));
                                        out.println("</adresse>");
                                        
                                        out.println("<telephone>");
                                        out.println(rsMembres.getString("telephone"));
                                        out.println("</telephone>");
                                        
                                        out.println("<email>");
                                        if(rsMembres.getString("email") != null)
                                            out.println(rsMembres.getString("email"));
                                        out.println("</email>");
                                        
                                        /********************* ROLES DU MEMBRE **************/
                                        
                                        prepState = conn.prepareStatement("Select r.idrole,r.nom,r.description from roles r, roles_membres rm where r.idrole = rm.idrole AND rm.idmembre = '"+ rsMembres.getString("idmembre") +"'");
                                        ResultSet rsRoles = prepState.executeQuery(); // Execution de la requete
                                        
                                        if(rsRoles.next()){
                                            //Si il y a des roles
                                            out.println("<roles>");
                                            do{
                                                /********* CREATION DE CHAQUE ROLE DU MEMBRE *********/
                                                out.println("<role>");
                                                out.println("<id>");
                                                out.println(rsRoles.getString("idrole"));
                                                out.println("</id>");
                                                
                                                out.println("<designation>");
                                                if (rsRoles.getString("nom") != null)
                                                    out.println(rsRoles.getString("nom"));
                                                out.println("</designation>");
                                                
                                                out.println("<description>");
                                                if (rsRoles.getString("description") != null)
                                                    out.println(rsRoles.getString("description"));
                                                out.println("</description>");
                                                out.println("</role>");
                                            }while(rsRoles.next());
                                            
                                            out.print("</roles>");
                                        }
                                        
                                        out.print("</membre>");
                                        
                                    }
                                    while(rsMembres.next());
                                    
                                    out.println("</membres>");
                                    rsMembres.close();
                                }
                                
                                /***************** ARTEFACTS PRESENTS DANS LE PROJETS *************/
                                prepState = conn.prepareStatement("Select a.idartefact,a.livrable,a.etat,a.nom,a.description,a.idresponsable from taches t,tachescollaboratives tc,iterations i,artefacts a, artefacts_entrees_taches aet,artefacts_sorties_taches ast,artefacts_entrees_tachescollaboratives aetc,artefacts_sorties_tachescollaboratives astc where a.idartefact = aet.idartefact AND a.idartefact = ast.idartefact AND a.idartefact = aetc.idartefact AND a.idartefact = astc.idartefact AND aet.idtache = t.idtache AND ast.idtache = t.idtache AND aetc.idtache = tc.idtache AND astc.idtache = tc.idtache AND t.iditeration = i.iditeration AND tc.iditeration = i.iditeration AND i.idprojet = '"+ rsIdProjets.getString("idprojet") +"'");
                                ResultSet rsArtefacts = prepState.executeQuery(); // Execution de la requete
                                if(rsArtefacts.next()){
                                    out.println("<artefacts>");
                                    
                                    do{
                                        out.println("<artefact>");
                                        
                                        out.println("<id>");
                                        out.println(rsArtefacts.getString("idartefact"));
                                        out.println("</id>");
                                        
                                        out.println("<livrable>");
                                        if(rsArtefacts.getString("livrable") != null)
                                            out.println(rsArtefacts.getString("livrable"));
                                        out.println("</livrable>");
                                        
                                        out.println("<etat>");
                                        if(rsArtefacts.getInt("etat") != -1)
                                            out.println(rsArtefacts.getInt("etat"));
                                        out.println("</etat>");
                                        
                                        out.println("<nom>");
                                        out.println(rsArtefacts.getString("nom"));
                                        out.println("</nom>");
                                        
                                        out.println("<description>");
                                        if(rsArtefacts.getString("description") != null)
                                            out.println(rsArtefacts.getString("description"));
                                        out.println("</description>");
                                        
                                        out.println("<idresponsable>");
                                        out.println(rsArtefacts.getString("idresponsable"));
                                        out.println("</idresponsable>");
                                        
                                        out.println("</artefact>");
                                    }while(rsArtefacts.next());
                                    
                                    out.println("</artefacts>");
                                    rsArtefacts.close();
                                }
                                
                                /********************* CREATION DES ITERATIONS *******************/
                                prepState = conn.prepareStatement("Select * from iterations where idprojet = '"+ rsIdProjets.getString("idprojet") +"'");
                                ResultSet rsIterations = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsIterations.next()){
                                    out.println("<iterations>");
                                    do{
                                        out.println("<iteration>");
                                        
                                        out.println("<id>");
                                        out.println(rsIterations.getString("iditeration"));
                                        out.println("</id>");
                                        
                                        out.println("<numero>");
                                        out.println(rsIterations.getString("numero"));
                                        out.println("</numero>");
                                        
                                        out.println("<datedebutprevue>");
                                        if(rsIterations.getString("datedebutprevue") != null)
                                            out.println(rsIterations.getString("datedebutprevue"));
                                        out.println("</datedebutprevue>");
                                        
                                        out.println("<datedebutreelle>");
                                        if(rsIterations.getString("datedebutreelle") != null)
                                            out.println(rsIterations.getString("datedebutreelle"));
                                        out.println("</datedebutreelle>");
                                        
                                        out.println("<datefinprevue>");
                                        if(rsIterations.getString("datefinprevue") != null)
                                            out.println(rsIterations.getString("datefinprevue"));
                                        out.println("</datefinprevue>");
                                        
                                        out.println("<datefinreelle>");
                                        if(rsIterations.getString("datefinreelle") != null)
                                            out.println(rsIterations.getString("datefinreelle"));
                                        out.println("</datefinreelle>");
                                        
                                        /****************** Liste des taches dans l'itération ***********/
                                        prepState = conn.prepareStatement("Select * from taches where iditeration = " + rsIterations.getString("iditeration"));
                                        ResultSet rsTaches = prepState.executeQuery(); // Execution de la requete
                                        
                                        if(rsTaches.next()){
                                            out.println("<taches>");
                                            
                                            do{
                                                out.println("<tache>");
                                                
                                                out.println("<id>");
                                                out.println(rsTaches.getString("idtache"));
                                                out.println("</id>");
                                                
                                                out.println("<nom>");
                                                out.println(rsTaches.getString("nom"));
                                                out.println("</nom>");
                                                
                                                out.println("<description>");
                                                if(rsTaches.getString("description") != null)
                                                    out.println(rsTaches.getString("description"));
                                                out.println("</description>");
                                                
                                                out.println("<etat>");
                                                if(rsTaches.getInt("etat") != -1)
                                                    out.println(rsTaches.getInt("etat"));
                                                out.println("</etat>");
                                                
                                                
                                                out.println("<chargeprevue>");
                                                if(rsTaches.getInt("chargeprevue") != -1)
                                                    out.println(rsTaches.getInt("chargeprevue"));
                                                out.println("</chargeprevue>");
                                                
                                                out.println("<tempspasse>");
                                                if(rsTaches.getFloat("tempspasse") != -1)
                                                    out.println(rsTaches.getFloat("tempspasse"));
                                                out.println("</tempspasse>");
                                                
                                                out.println("<tempsrestant>");
                                                if(rsTaches.getFloat("tempsrestant") != -1)
                                                    out.println(rsTaches.getFloat("tempsrestant"));
                                                out.println("</tempsrestant>");
                                                
                                                out.println("<datedebutprevue>");
                                                if(rsTaches.getString("datedebutprevue") != null)
                                                    out.println(rsTaches.getString("datedebutprevue"));
                                                out.println("</datedebutprevue>");
                                                
                                                out.println("<datedebutreelle>");
                                                if(rsTaches.getString("datedebutreelle") != null)
                                                    out.println(rsTaches.getString("datedebutreelle"));
                                                out.println("</datedebutreelle>");
                                                
                                                out.println("<datefinprevue>");
                                                if(rsTaches.getString("datefinprevue") != null)
                                                    out.println(rsTaches.getString("datefinprevue"));
                                                out.println("</datefinprevue>");
                                                
                                                out.println("<datefinreelle>");
                                                if(rsTaches.getString("datefinreelle") != null)
                                                    out.println(rsTaches.getString("datefinreelle"));
                                                out.println("</datefinreelle>");
                                                
                                                out.println("</tache>");
                                            } while(rsTaches.next());
                                            rsTaches.close();
                                            out.println("</taches>");
                                        }
                                        /****************** Liste des taches collaboratives dans l'itération ***********/
                                        prepState = conn.prepareStatement("Select * from tachescollaboratives where iditeration = " + rsIterations.getString("iditeration"));
                                        ResultSet rsTachesCollaboratives = prepState.executeQuery(); // Execution de la requete
                                        
                                        if(rsTachesCollaboratives.next()){
                                            out.println("<tachesCollaboratives>");
                                            
                                            do{
                                                out.println("<tacheCollaborative>");
                                                
                                                out.println("<id>");
                                                out.println(rsTachesCollaboratives.getString("idtache"));
                                                out.println("</id>");
                                                
                                                out.println("<nom>");
                                                out.println(rsTachesCollaboratives.getString("nom"));
                                                out.println("</nom>");
                                                
                                                out.println("<description>");
                                                if(rsTachesCollaboratives.getString("description") != null)
                                                    out.println(rsTachesCollaboratives.getString("description"));
                                                out.println("</description>");
                                                
                                                out.println("<etat>");
                                                if(rsTachesCollaboratives.getInt("etat") != -1)
                                                    out.println(rsTachesCollaboratives.getInt("etat"));
                                                out.println("</etat>");
                                                
                                                out.println("<chargeprevue>");
                                                if(rsTachesCollaboratives.getInt("chargeprevue") != -1)
                                                    out.println(rsTachesCollaboratives.getInt("chargeprevue"));
                                                out.println("</chargeprevue>");
                                                
                                                out.println("<tempspasse>");
                                                if(rsTachesCollaboratives.getFloat("tempspasse") != -1)
                                                    out.println(rsTachesCollaboratives.getFloat("tempspasse"));
                                                out.println("</tempspasse>");
                                                
                                                out.println("<tempsrestant>");
                                                if(rsTachesCollaboratives.getFloat("tempsrestant") != -1)
                                                    out.println(rsTachesCollaboratives.getFloat("tempsrestant"));
                                                out.println("</tempsrestant>");
                                                
                                                out.println("<datedebutprevue>");
                                                if(rsTachesCollaboratives.getString("datedebutprevue") != null)
                                                    out.println(rsTachesCollaboratives.getString("datedebutprevue"));
                                                out.println("</datedebutprevue>");
                                                
                                                out.println("<datedebutreelle>");
                                                if(rsTachesCollaboratives.getString("datedebutreelle") != null)
                                                    out.println(rsTachesCollaboratives.getString("datedebutreelle"));
                                                out.println("</datedebutreelle>");
                                                
                                                out.println("<datefinprevue>");
                                                if(rsTachesCollaboratives.getString("datefinprevue") != null)
                                                    out.println(rsTachesCollaboratives.getString("datefinprevue"));
                                                out.println("</datefinprevue>");
                                                
                                                out.println("<datefinreelle>");
                                                if(rsTachesCollaboratives.getString("datefinreelle") != null)
                                                    out.println(rsTachesCollaboratives.getString("datefinreelle"));
                                                out.println("</datefinreelle>");
                                                
                                                out.println("</tacheCollaborative>");
                                            } while(rsTachesCollaboratives.next());
                                            
                                            rsTachesCollaboratives.close();
                                            out.println("</tachesCollaboratives>");
                                            
                                        }
                                        
                                        
                                        
                                        /************************** INDICATEURS D'UNE ITERATION ***************/
                                        prepState = conn.prepareStatement("Select * from indicateurs_iteration where iditeration = " + rsIterations.getString("iditeration"));
                                        ResultSet rsIndicateursIteration = prepState.executeQuery(); // Execution de la requete
                                        
                                        if(rsIndicateursIteration.next()){
                                            out.println("<indicateursIteration>");
                                            
                                            out.println("<totalcharges>");
                                            if(rsIndicateursIteration.getInt("totalcharges") != -1)
                                                out.println(rsIndicateursIteration.getInt("totalcharges"));
                                            out.println("</totalcharges>");
                                            
                                            out.println("<nombreTachesTerminees>");
                                            if(rsIndicateursIteration.getInt("nombreTachesTerminees") != -1)
                                                out.println(rsIndicateursIteration.getInt("nombreTachesTerminees"));
                                            out.println("</nombreTachesTerminees>");
                                            
                                            out.println("<dureeMoyenneTache>");
                                            if(rsIndicateursIteration.getInt("dureeMoyenneTache") != -1)
                                                out.println(rsIndicateursIteration.getInt("dureeMoyenneTache"));
                                            out.println("</dureeMoyenneTache>");
                                            
                                            out.println("<nombreParticipants>");
                                            if(rsIndicateursIteration.getInt("nombreParticipants") != -1)
                                                out.println(rsIndicateursIteration.getInt("nombreParticipants"));
                                            out.println("</nombreParticipants>");
                                            
                                            out.println("<chargeMoyenneParticipants>");
                                            if(rsIndicateursIteration.getFloat("chargeMoyenneParticipants") != -1)
                                                out.println(rsIndicateursIteration.getFloat("chargeMoyenneParticipants"));
                                            out.println("</chargeMoyenneParticipants>");
                                            
                                            out.println("<nombreMoyenTachesParticipants>");
                                            if(rsIndicateursIteration.getInt("nombreMoyenTachesParticipants") != -1)
                                                out.println(rsIndicateursIteration.getInt("nombreMoyenTachesParticipants"));
                                            out.println("</nombreMoyenTachesParticipants>");
                                            
                                            out.println("</indicateursIteration>");
                                        }
                                        rsIndicateursIteration.close();
                                        
                                        out.println("</iteration>");
                                        
                                    }while(rsIterations.next());
                                    
                                    rsIterations.close();
                                    out.println("</iterations>");
                                }
                                
                                /**************** LES RISQUES **************/
                                prepState = conn.prepareStatement("Select * from risques where idprojet = " + rsIdProjets.getString("idprojet"));
                                ResultSet rsRisques = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsRisques.next()){
                                    out.print("<risques>");
                                    
                                    do{
                                        out.println("<risque>");
                                        
                                        out.println("<id>");
                                        out.println(rsRisques.getString("idrisque"));
                                        out.println("</id>");
                                        
                                        out.println("<nom>");
                                        out.println(rsRisques.getString("nom"));
                                        out.println("</nom>");
                                        
                                        out.println("<priorite>");
                                        if(rsRisques.getInt("priorite") != -1)
                                            out.println(rsRisques.getInt("priorite"));
                                        out.println("</priorite>");
                                        
                                        out.println("<impact>");
                                        if(rsRisques.getInt("impact") != -1)
                                            out.println(rsRisques.getInt("impact"));
                                        out.println("</impact>");
                                        
                                        out.println("<etat>");
                                        if(rsRisques.getInt("etat") != -1)
                                            out.println(rsRisques.getInt("etat"));
                                        out.println("</etat>");
                                        
                                        out.println("<description>");
                                        if(rsRisques.getString("description") != null)
                                            out.println(rsRisques.getString("description"));
                                        out.println("</description>");
                                        
                                        out.println("</risque>");
                                    }while(rsRisques.next());
                                    
                                    out.print("</risques>");
                                    
                                }
                                rsRisques.close();
                                
                                
                                /**************** LES PROBLEMES **************/
                                prepState = conn.prepareStatement("Select * from problemes where idprojet = " + rsIdProjets.getString("idprojet"));
                                ResultSet rsProblemes = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsProblemes.next()){
                                    out.print("<problemes>");
                                    
                                    do{
                                        out.println("<probleme>");
                                        
                                        out.println("<id>");
                                        out.println(rsProblemes.getString("idprobleme"));
                                        out.println("</id>");
                                        
                                        out.println("<nom>");
                                        out.println(rsProblemes.getString("nom"));
                                        out.println("</nom>");
                                        
                                        out.println("<cause>");
                                        out.println(rsProblemes.getString("cause"));
                                        out.println("</cause>");
                                        
                                        
                                        
                                        out.println("<dateDebut>");
                                        if(rsProblemes.getString("debut") != null)
                                            out.println(rsProblemes.getString("debut"));
                                        out.println("</dateDebut>");
                                        
                                        out.println("<dateFin>");
                                        if(rsProblemes.getString("fin") != null)
                                            out.println(rsProblemes.getString("fin"));
                                        out.println("</dateFin>");
                                        
                                        
                                        out.println("</probleme>");
                                    }while(rsProblemes.next());
                                    
                                    out.print("</problemes>");
                                    
                                }
                                rsProblemes.close();
                                
                                //PAS DE MESURES POUR L INSTANT
                                
                                /************************************* LES BALISES DE LIENS ***************************/
                                
                                
                                /******************* MEMBRES & TACHES ***************/
                                prepState = conn.prepareStatement("Select idtache ,idmembre from taches");
                                ResultSet rsMembresTaches = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsMembresTaches.next()){
                                    out.println("<membresTaches>");
                                    
                                    do{
                                        out.println("<membreTache>");
                                        
                                        out.println("<idmembre>");
                                        out.println(rsMembresTaches.getString("idmembre"));
                                        out.println("</idmembre>");
                                        
                                        out.println("<idtache>");
                                        out.println(rsMembresTaches.getString("idtache"));
                                        out.println("</idtache>");
                                        
                                        out.println("</membreTache>");
                                    }while(rsMembresTaches.next());
                                    
                                    out.println("</membresTaches>");
                                }
                                rsMembresTaches.close();
                                
                                /******************* MEMBRES & TACHES COLLABORATIVES ***************/
                                prepState = conn.prepareStatement("Select * from membres_tachescollaboratives");
                                ResultSet rsMembresTachesCollaboratives = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsMembresTachesCollaboratives.next()){
                                    out.println("<membresTachesCollaboratives>");
                                    
                                    do{
                                        out.println("<membreTacheCollaborative>");
                                        
                                        out.println("<idmembre>");
                                        out.println(rsMembresTachesCollaboratives.getString("idmembre"));
                                        out.println("</idmembre>");
                                        
                                        out.println("<idtache>");
                                        out.println(rsMembresTachesCollaboratives.getString("idtache"));
                                        out.println("</idtache>");
                                        
                                        out.println("</membreTacheCollaborative>");
                                    }while(rsMembresTachesCollaboratives.next());
                                    
                                    out.println("</membresTachesCollaboratives>");
                                }
                                rsMembresTachesCollaboratives.close();
                                
                                /******************* RESPONSABLE & TACHES COLLABORATIVES ***************/
                                prepState = conn.prepareStatement("Select idtache ,idresponsable from tachescollaboratives");
                                ResultSet rsRespTachesC = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsRespTachesC.next()){
                                    out.println("<responsablesTachesCollaboratives>");
                                    
                                    do{
                                        out.println("<responsableTacheCollaborative>");
                                        
                                        out.println("<idmembre>");
                                        out.println(rsRespTachesC.getString("idresponsable"));
                                        out.println("</idmembre>");
                                        
                                        out.println("<idtache>");
                                        out.println(rsRespTachesC.getString("idtache"));
                                        out.println("</idtache>");
                                        
                                        out.println("</responsableTacheCollaborative>");
                                    }while(rsRespTachesC.next());
                                    
                                    out.println("</responsablesTachesCollaboratives>");
                                }
                                rsRespTachesC.close();
                                
                                /******************* MEMBRES & ARTEFACTS ***************/
                                prepState = conn.prepareStatement("Select idartefact,idresponsable from artefacts");
                                ResultSet rsMembresArtefacts = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsMembresArtefacts.next()){
                                    out.println("<membresArtefacts>");
                                    
                                    do{
                                        out.println("<membreArtefact>");
                                        
                                        out.println("<idmembre>");
                                        out.println(rsMembresArtefacts.getString("idresponsable"));
                                        out.println("</idmembre>");
                                        
                                        out.println("<idartefact>");
                                        out.println(rsMembresArtefacts.getString("idartefact"));
                                        out.println("</idartefact>");
                                        
                                        out.println("</membreArtefact>");
                                    }while(rsMembresArtefacts.next());
                                    
                                    out.println("</membresArtefacts>");
                                }
                                rsMembresArtefacts.close();
                                
                                /******************* TACHES & ARTEFACTS ENTREES ***************/
                                prepState = conn.prepareStatement("Select idartefact,idtache from artefacts_entrees_taches");
                                ResultSet rsTachesArtefactsE = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsTachesArtefactsE.next()){
                                    out.println("<tachesArtefactsEntrees>");
                                    
                                    do{
                                        out.println("<tacheArtefactEntree>");
                                        
                                        out.println("<idartefact>");
                                        out.println(rsTachesArtefactsE.getString("idartefact"));
                                        out.println("</idartefact>");
                                        
                                        out.println("<idtache>");
                                        out.println(rsTachesArtefactsE.getString("idtache"));
                                        out.println("</idtache>");
                                        
                                        out.println("</tacheArtefactEntree>");
                                    }while(rsTachesArtefactsE.next());
                                    
                                    out.println("</tachesArtefactsEntrees>");
                                }
                                rsTachesArtefactsE.close();
                                
                                /******************* TACHES & ARTEFACTS SORTIES ***************/
                                prepState = conn.prepareStatement("Select idartefact,idtache from artefacts_sorties_taches");
                                ResultSet rsTachesArtefactsS = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsTachesArtefactsS.next()){
                                    out.println("<tachesArtefactsSorties>");
                                    
                                    do{
                                        out.println("<tacheArtefactSortie>");
                                        
                                        out.println("<idartefact>");
                                        out.println(rsTachesArtefactsS.getString("idartefact"));
                                        out.println("</idartefact>");
                                        
                                        out.println("<idtache>");
                                        out.println(rsTachesArtefactsS.getString("idtache"));
                                        out.println("</idtache>");
                                        
                                        out.println("</tacheArtefactSortie>");
                                    }while(rsTachesArtefactsS.next());
                                    
                                    out.println("</tachesArtefactsSorties>");
                                }
                                rsTachesArtefactsS.close();
                                
                                
                                /******************* TACHES COLLABORATIVES & ARTEFACTS ENTREES ***************/
                                prepState = conn.prepareStatement("Select idartefact,idtache from artefacts_entrees_tachescollaboratives");
                                ResultSet rsTachesCArtefactsE = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsTachesCArtefactsE.next()){
                                    out.println("<tachesCollaborativesArtefactsEntrees>");
                                    
                                    do{
                                        out.println("<tacheCollaborativeArtefactEntree>");
                                        
                                        out.println("<idartefact>");
                                        out.println(rsTachesCArtefactsE.getString("idartefact"));
                                        out.println("</idartefact>");
                                        
                                        out.println("<idtache>");
                                        out.println(rsTachesCArtefactsE.getString("idtache"));
                                        out.println("</idtache>");
                                        
                                        out.println("</tacheCollaborativeArtefactEntree>");
                                    }while(rsTachesCArtefactsE.next());
                                    
                                    out.println("</tachesCollaborativesArtefactsEntrees>");
                                }
                                rsTachesCArtefactsE.close();
                                
                                /******************* TACHES & ARTEFACTS SORTIES ***************/
                                prepState = conn.prepareStatement("Select idartefact,idtache from artefacts_sorties_tachescollaboratives");
                                ResultSet rsTachesCArtefactsS = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsTachesCArtefactsS.next()){
                                    out.println("<tachesCollaborativesArtefactsSorties>");
                                    
                                    do{
                                        out.println("<tacheCollaborativeArtefactSortie>");
                                        
                                        out.println("<idartefact>");
                                        out.println(rsTachesCArtefactsS.getString("idartefact"));
                                        out.println("</idartefact>");
                                        
                                        out.println("<idtache>");
                                        out.println(rsTachesCArtefactsS.getString("idtache"));
                                        out.println("</idtache>");
                                        
                                        out.println("</tacheCollaborativeArtefactSortie>");
                                    }while(rsTachesCArtefactsS.next());
                                    
                                    out.println("</tachesCollaborativesArtefactsSorties>");
                                }
                                rsTachesCArtefactsS.close();
                                
                                /****************************** LES INDICATEURS D'UN PROJET *************************/
                                
                                prepState = conn.prepareStatement("Select * from indicateurs_projet where idprojet = " + rsIdProjets.getString("idprojet"));
                                ResultSet rsIndicateursProjet = prepState.executeQuery(); // Execution de la requete
                                
                                if(rsIndicateursProjet.next()){
                                    
                                    out.println("<indicateursProjet>");
                                    
                                    out.println("<totalCharges>");
                                    if(rsIndicateursProjet.getInt("totalcharges") != -1)
                                        out.println(rsIndicateursProjet.getInt("totalcharges"));
                                    out.println("</totalCharges>");
                                    
                                    out.println("<tachesTerminees>");
                                    if(rsIndicateursProjet.getInt("tachesTerminees") != -1)
                                        out.println(rsIndicateursProjet.getInt("tachesTerminees"));
                                    out.println("</tachesTerminees>");
                                    
                                    out.println("<dureeMoyenneTache>");
                                    if(rsIndicateursProjet.getInt("dureeMoyenneTache") != -1)
                                        out.println(rsIndicateursProjet.getInt("dureeMoyenneTache"));
                                    out.println("</dureeMoyenneTache>");
                                    
                                    out.println("<nombreParticipants>");
                                    if(rsIndicateursProjet.getInt("nombreParticipants") != -1)
                                        out.println(rsIndicateursProjet.getInt("nombreParticipants"));
                                    out.println("</nombreParticipants>");
                                    
                                    out.println("<avancementProjet>");
                                    if(rsIndicateursProjet.getFloat("avancementProjet") != -1)
                                        out.println(rsIndicateursProjet.getFloat("avancementProjet"));
                                    out.println("</avancementProjet>");
                                    
                                    out.println("</indicateursProjet>");
                                    
                                }
                                
                                rsIndicateursProjet.close();
                                
                                
                                out.println("</projet>");
                            }
                            rsProjets.close();
                        }
                        out.println("</projets>");
                        rsIdProjets.close();
                    }
                    
                    if(rsUser.getString("fonction").compareTo("dir")==0) {
                        
                        /************************************************************************************************
                         *                                  SUPERVISEUR DE PROJET                                       *
                         ************************************************************************************************/
                        
                        out.println("<membres>");
                        prepState = conn.prepareStatement("Select * from membres;");
                        ResultSet rsMembres = prepState.executeQuery(); // Execution de la requete
                        
                        while(rsMembres.next()) {
                            out.println("<membre>");
                            
                            out.println("<idMembre>");
                            out.println(rsMembres.getString("idmembre"));
                            out.println("</idMembre>");
                            
                            out.println("<nom>");
                            out.println(rsMembres.getString("nom"));
                            out.println("</nom>");
                            
                            out.print("<prenom>");
                            if(rsMembres.getString("prenom") != null)
                                out.print(rsMembres.getString("prenom"));
                            out.print("</prenom>");
                            
                            out.print("<adresse>");
                            if(rsMembres.getString("adresse") != null)
                                out.print(rsMembres.getString("adresse"));
                            out.print("</adresse>");
                            
                            out.print("<telephone>");
                            if(rsMembres.getString("telephone") != null)
                                out.print(rsMembres.getString("telephone"));
                            out.print("</telephone>");
                            
                            out.print("<email>");
                            if(rsMembres.getString("email") != null)
                                out.print(rsMembres.getString("email"));
                            out.print("</email>");
                            
                            /********************* ROLES DU MEMBRE **************/
                            
                            prepState = conn.prepareStatement("Select r.idrole,r.nom,r.description from roles r, roles_membres rm where r.idrole = rm.idrole AND rm.idmembre = '"+ rsMembres.getString("idmembre") +"'");
                            ResultSet rsRoles = prepState.executeQuery(); // Execution de la requete
                            
                            if(rsRoles.next()){
                                //Si il y a des roles
                                out.println("<roles>");
                                do{
                                    /********* CREATION DE CHAQUE ROLE DU MEMBRE *********/
                                    out.println("<role>");
                                    out.println("<id>");
                                    out.println(rsRoles.getString("idrole"));
                                    out.println("</id>");
                                    
                                    out.println("<designation>");
                                    if(rsRoles.getString("nom") != null)
                                        out.println(rsRoles.getString("nom"));
                                    out.println("</designation>");
                                    
                                    out.println("<description>");
                                    if(rsRoles.getString("description") != null)
                                        out.println(rsRoles.getString("description"));
                                    out.println("</description>");
                                    
                                    out.println("</role>");
                                }while(rsRoles.next());
                                
                                out.print("</roles>");
                            }
                            rsRoles.close();
                            
                            
                            /*********************************** INDICATEURS DE PROJETS DU MEMBRE *******************/
                            prepState = conn.prepareStatement("Select p.idprojet, p.nom from projets p, membres_projets mp where mp.idprojet = p.idprojet AND mp.idmembre = '"+ rsMembres.getString("idmembre") +"'");
                            ResultSet rsProjetsMembre = prepState.executeQuery(); // Execution de la requete
                            
                            
                            //prepState = conn.prepareStatement("Select count(*),tc.tempspasse from iterations i, taches t where i.idprojet = "+ rsProjetsMembre.getString("idprojet")+" AND i.iditeration = t.iditeration AND t.idmembre = '"+ rsMembres.getString("idmembre") +"'");
                            //ResultSet rsIndicChargesProjet = prepState.executeQuery(); // Execution de la requete
                            
                            
                            if(rsProjetsMembre.next()){
                                //Si il y a des indicateurs
                                out.println("<indicateursProjets>");
                                do{
                                    /********* CREATION DES INDICATEURS DE PROJET DU MEMBRE *********/
                                    out.println("<indicateurProjet>");
                                    
                                    out.println("<nom>");
                                    if(rsProjetsMembre.getString("nom") != null)
                                        out.println(rsProjetsMembre.getString("nom")); //Nom du projet ou participe le membre
                                    out.println("</nom>");
                                    
                                    
                                    //ResultSet correspondant au temps que le membre a passé sur les taches du projet
                                    prepState = conn.prepareStatement("Select sum(t.tempspasse) from iterations i, taches t where i.idprojet = '"+ rsProjetsMembre.getString("idprojet")+"' AND i.iditeration = t.iditeration AND t.idmembre = '"+ rsMembres.getString("idmembre") +"'");
                                    ResultSet rsIndicChargesTaches = prepState.executeQuery(); // Execution de la requete
                                    
                                    //ResultSet correspondant au temps total passé sur les taches du projet
                                    prepState = conn.prepareStatement("Select sum(t.tempspasse) from iterations i, taches t where i.idprojet = '"+ rsProjetsMembre.getString("idprojet")+"' AND i.iditeration = t.iditeration");
                                    ResultSet rsIndicChargesTotalesTaches = prepState.executeQuery(); // Execution de la requete
                                    
                                    //ResultSet correspondant au temps que le membre a passé sur les taches collaboratives du projet
                                    prepState = conn.prepareStatement("Select t.tempspasse,t.idtache from iterations i, tachescollaboratives t, membres_tachescollaboratives mt where i.idprojet = '"+ rsProjetsMembre.getString("idprojet")+"' AND i.iditeration = t.iditeration AND mt.idtache = t.idtache AND mt.idmembre = '"+ rsMembres.getString("idmembre") +"'");
                                    ResultSet rsIndicChargesCollaboratives = prepState.executeQuery(); // Execution de la requete
                                    
                                    //ResultSet correspondant au temps total passé sur les taches collaboratives du projet
                                    prepState = conn.prepareStatement("Select sum(t.tempspasse) from iterations i, tachescollaboratives t where i.idprojet = '"+ rsProjetsMembre.getString("idprojet")+"' AND i.iditeration = t.iditeration");
                                    ResultSet rsIndicChargesTotalesCollaboratives = prepState.executeQuery(); // Execution de la requete
                                    
                                    //Calcul sur les taches collaboratives pour le membre
                                    int chargesCollaboratives = 0;
                                    
                                    if(rsIndicChargesCollaboratives.next()){
                                        //Pour chaque tache collaborative, on regarde le nombre de participants
                                        do{
                                            prepState = conn.prepareStatement("Select count(*) from membres_tachescollaboratives mt where mt.idtache = '"+ rsIndicChargesCollaboratives.getString("idtache")+ "'");
                                            ResultSet rsIndicNbMembre = prepState.executeQuery(); // Execution de la requete
                                            
                                            if(rsIndicNbMembre.next())
                                                chargesCollaboratives += rsIndicChargesCollaboratives.getFloat("tempspasse")/rsIndicNbMembre.getInt(1);
                                            rsIndicNbMembre.close();
                                        }while(rsIndicChargesCollaboratives.next());
                                    }
                                    
                                    
                                    float chargesMembre = 0;
                                    float chargesTotale = 0;
                                    float tempsTravail = 0;
                                    
                                    //Charges du membre sur le projet = temps passé sur ses taches + SOMME DE (temps passé sur une tache collaborative / nombre de participants à cette tache collaborative)
                                    if(rsIndicChargesTaches.next())
                                        chargesMembre = rsIndicChargesTaches.getInt(1) + chargesCollaboratives;
                                    
                                    if(rsIndicChargesTotalesTaches.next() && rsIndicChargesTotalesCollaboratives.next())
                                        chargesTotale = rsIndicChargesTotalesTaches.getInt(1) + rsIndicChargesTotalesCollaboratives.getInt(1);
                                    
                                    
                                    //Temps de travail du membre = (charges du membre sur lengthprojet / charges totales du projet) * 100
                                    if(chargesMembre != 0 && chargesTotale !=0)
                                        tempsTravail = (float) ((double) chargesMembre/ (double) chargesTotale *100);
                                    
                                    
                                    out.println("<charges>");
                                    out.println(chargesMembre); //Charges sur le projet
                                    out.println("</charges>");
                                    
                                    out.println("<tempsTravail>");
                                    out.println(tempsTravail); //Charges sur le projet
                                    out.println("</tempsTravail>");
                                    
                                    rsIndicChargesTaches.close();
                                    rsIndicChargesTotalesTaches.close();
                                    rsIndicChargesCollaboratives.close();
                                    rsIndicChargesTotalesCollaboratives.close();
                                    
                                    
                                    out.println("</indicateurProjet>");
                                }while(rsProjetsMembre.next());
                                
                                out.print("</indicateursProjets>");
                            }
                            rsProjetsMembre.close();
                            
                            
                            /*********************************** INDICATEURS DE TACHES DU MEMBRE *******************/
                            
                            //On récupère les noms et temps passé des taches du membre
                            prepState = conn.prepareStatement("Select p.nom,t.nom,t.tempspasse from projets p,iterations i, taches t where t.idmembre = '"+ rsMembres.getString("idmembre") +"' AND t.iditeration = i.iditeration AND i.idprojet = p.idprojet");
                            ResultSet rsIndicTacheMembre = prepState.executeQuery(); // Execution de la requete
                            
                            prepState = conn.prepareStatement("Select p.nom,t.nom,t.tempspasse,t.idtache from projets p,iterations i, tachescollaboratives t,membres_tachescollaboratives mt where mt.idmembre = '"+ rsMembres.getString("idmembre") +"' AND t.idtache = mt.idtache AND t.iditeration = i.iditeration AND i.idprojet = p.idprojet");
                            ResultSet rsIndicTacheCollaborativeMembre = prepState.executeQuery(); // Execution de la requete
                            
                            
                            
                            if(rsIndicTacheMembre.next()){
                                out.println("<indicateursTaches>");
                                
                                do{
                                    out.println("<indicateurTache>");
                                    
                                    out.println("<nomProjet>");
                                    if(rsIndicTacheMembre.getString(1) != null)
                                        out.println(rsIndicTacheMembre.getString(1));
                                    out.println("</nomProjet>");
                                    
                                    out.println("<nom>");
                                    if(rsIndicTacheMembre.getString(2) != null)
                                        out.println(rsIndicTacheMembre.getString(2));
                                    out.println("</nom>");
                                    
                                    out.println("<charges>");
                                    out.println(rsIndicTacheMembre.getFloat("tempspasse"));
                                    out.println("</charges>");
                                    
                                    out.println("</indicateurTache>");
                                }while(rsIndicTacheMembre.next());
                                
                                if(rsIndicTacheCollaborativeMembre.next()){
                                    do{
                                        out.println("<indicateurTache>");
                                        
                                        out.println("<nomProjet>");
                                        out.println(rsIndicTacheCollaborativeMembre.getString(1));
                                        out.println("</nomProjet>");
                                        
                                        out.println("<nom>");
                                        if(rsIndicTacheCollaborativeMembre.getString(2) != null)
                                            out.println(rsIndicTacheCollaborativeMembre.getString(2));
                                        out.println("</nom>");
                                        
                                        //ResultSet permettant de savoir combien il y a de participants sur la tache collaborative
                                        prepState = conn.prepareStatement("Select count(*) from membres_tachescollaboratives mt where mt.idtache = '"+ rsIndicTacheCollaborativeMembre.getString("idtache")+ "'");
                                        ResultSet rsIndicNbMembreTC = prepState.executeQuery(); // Execution de la requete
                                        
                                        int charges = 0;
                                        
                                        if(rsIndicNbMembreTC.next())
                                            charges = rsIndicTacheCollaborativeMembre.getInt("tempspasse") / rsIndicNbMembreTC.getInt(1);
                                        
                                        rsIndicNbMembreTC.close();
                                        
                                        out.println("<charges>");
                                        out.println(charges);
                                        out.println("</charges>");
                                        
                                        out.println("</indicateurTache>");
                                    }while(rsIndicTacheCollaborativeMembre.next());
                                }
                                
                                out.println("</indicateursTaches>");
                                
                            }
                            rsIndicTacheMembre.close();
                            rsIndicTacheCollaborativeMembre.close();
                            
                            /*********************************** INDICATEURS DES ARTEFACTS DU MEMBRE *******************/
                            //A remonter dans les taches !!
                            
                            //On récupère les artefacts
                            prepState = conn.prepareStatement("Select a.nom,a.etat from artefacts a where a.idresponsable = '"+ rsMembres.getString("idmembre") +"'");
                            ResultSet rsIndicArtefactMembre = prepState.executeQuery(); // Execution de la requete
                            
                            
                            
                            if(rsIndicArtefactMembre.next()){
                                out.println("<indicateursArtefacts>");
                                
                                do{
                                    out.println("<indicateurArtefact>");
                                    
                                    out.println("<nom>");
                                    out.println(rsIndicArtefactMembre.getString("nom"));
                                    out.println("</nom>");
                                    
                                    out.println("<etat>");
                                    if(rsIndicArtefactMembre.getString("etat") != null)
                                        out.println(rsIndicArtefactMembre.getString("etat"));
                                    out.println("</etat>");
                                    
                                    out.println("</indicateurArtefact>");
                                }while(rsIndicArtefactMembre.next());
                                
                                out.println("</indicateursArtefacts>");
                                
                            }
                            rsIndicArtefactMembre.close();
                            
                            
                            out.println("</membre>");
                            
                        }
                        out.println("</membres>");
                        rsMembres.close();
                    }
                    
                    out.println("</utilisateur>");
                    
                }
                // Fermeture du ResultSet
                rsUser.close();
                prepState.close(); // Fermeture de la requete
                conn.close(); // Fermeture de la connexion
            }catch (SQLException ex) { // Si une SQLException survient
                out.println(ex.getMessage());
            }
        }
        
        out.close();
    }
    
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    
}
