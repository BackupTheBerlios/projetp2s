/*
 * LoginServlet.java
 *
 * Created on 14 janvier 2005, 02:17
 */

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                ParserConnexionBD parser = new ParserConnexionBD(getServletContext().getRealPath("/ConnexionBD.xml"));
                // Connexion a la base de donnees
                Connection conn = DriverManager.getConnection("jdbc:mysql://"+parser.lireHost()+"/"+parser.lireBase()+"?user="+parser.lireLogin()+"&password="+parser.lirePassword());
                
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
                    
                    if(rsUser.getString("fonction").compareTo("sup")==0) {
                        /************************************************************************************************
                         *                                  SUPERVISEUR DE PROJET                                       *
                         ************************************************************************************************/
                        
                        //Dans le cas d'un superviseur : il faut récupérer toutes les informations relatives à ses projets
                        
                        out.println("<projets>");
                        
                        //Récupération des identifiants de tous les projets du superviseur
                        prepState = conn.prepareStatement("Select idprojet from superviseur_projets where login = '" + rsUser.getString("login")+"'");
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
                                out.println(rsProjets.getString("description"));
                                out.println("</description>");
                                
                                out.println("<dateDebut>");
                                out.println(rsProjets.getString("datedebut"));
                                out.println("</dateDebut>");
                                
                                out.println("<dateFin>");
                                out.println(rsProjets.getString("datefin"));
                                out.println("</dateFin>");
                                
                                out.println("<budget>");
                                out.println(rsProjets.getString("budget"));
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
                                        out.println(rsMembres.getString("prenom"));
                                        out.println("</prenom>");
                                        
                                        out.println("<adresse>");
                                        out.println(rsMembres.getString("adresse"));
                                        out.println("</adresse>");
                                        
                                        out.println("<telephone>");
                                        out.println(rsMembres.getString("telephone"));
                                        out.println("</telephone>");
                                        
                                        out.println("<email>");
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
                                                out.println(rsRoles.getString("nom"));
                                                out.println("</designation>");
                                                
                                                out.println("<description>");
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
                                prepState = conn.prepareStatement("Select * from artefacts");
                                ResultSet rsArtefacts = prepState.executeQuery(); // Execution de la requete
                                if(rsArtefacts.next()){
                                    out.println("<artefacts>");
                                    
                                    do{
                                        out.println("<artefact>");
                                        
                                        out.println("<id>");
                                        out.println(rsArtefacts.getString("idartefact"));
                                        out.println("</id>");
                                        
                                        out.println("<livrable>");
                                        out.println(rsArtefacts.getString("livrable"));
                                        out.println("</livrable>");
                                        
                                        out.println("<etat>");
                                        out.println(rsArtefacts.getString("etat"));
                                        out.println("</etat>");
                                        
                                        out.println("<nom>");
                                        out.println(rsArtefacts.getString("nom"));
                                        out.println("</nom>");
                                        
                                        out.println("<description>");
                                        out.println(rsArtefacts.getString("description"));
                                        out.println("</description>");
                                        
                                        out.println("<description>");
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
                                prepState = conn.prepareStatement("Select * from iterations");
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
                                        out.println(rsIterations.getString("datedebutprevue"));
                                        out.println("</datedebutprevue>");
                                        
                                        out.println("<datedebutreelle>");
                                        out.println(rsIterations.getString("datedebutreelle"));
                                        out.println("</datedebutreelle>");
                                        
                                        out.println("<datefinprevue>");
                                        out.println(rsIterations.getString("datefinprevue"));
                                        out.println("</datefinprevue>");
                                        
                                        out.println("<datefinreelle>");
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
                                                out.println(rsTaches.getString("description"));
                                                out.println("</description>");
                                                
                                                out.println("<etat>");
                                                out.println(rsTaches.getString("etat"));
                                                out.println("</etat>");
                                                
                                                out.println("<etat>");
                                                out.println(rsTaches.getString("etat"));
                                                out.println("</etat>");
                                                
                                                out.println("<chargeprevue>");
                                                out.println(rsTaches.getString("chargeprevue"));
                                                out.println("</chargeprevue>");
                                                
                                                out.println("<tempspasse>");
                                                out.println(rsTaches.getString("tempspasse"));
                                                out.println("</tempspasse>");
                                                
                                                out.println("<datedebutprevue>");
                                                out.println(rsTaches.getString("datedebutprevue"));
                                                out.println("</datedebutprevue>");
                                                
                                                out.println("<datedebutreelle>");
                                                out.println(rsTaches.getString("datedebutreelle"));
                                                out.println("</datedebutreelle>");
                                                
                                                out.println("<datefinprevue>");
                                                out.println(rsTaches.getString("datefinprevue"));
                                                out.println("</datefinprevue>");
                                                
                                                out.println("<datefinreelle>");
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
                                                out.println(rsTachesCollaboratives.getString("description"));
                                                out.println("</description>");
                                                
                                                out.println("<etat>");
                                                out.println(rsTachesCollaboratives.getString("etat"));
                                                out.println("</etat>");
                                                
                                                out.println("<etat>");
                                                out.println(rsTachesCollaboratives.getString("etat"));
                                                out.println("</etat>");
                                                
                                                out.println("<chargeprevue>");
                                                out.println(rsTachesCollaboratives.getString("chargeprevue"));
                                                out.println("</chargeprevue>");
                                                
                                                out.println("<tempspasse>");
                                                out.println(rsTachesCollaboratives.getString("tempspasse"));
                                                out.println("</tempspasse>");
                                                
                                                out.println("<datedebutprevue>");
                                                out.println(rsTachesCollaboratives.getString("datedebutprevue"));
                                                out.println("</datedebutprevue>");
                                                
                                                out.println("<datedebutreelle>");
                                                out.println(rsTachesCollaboratives.getString("datedebutreelle"));
                                                out.println("</datedebutreelle>");
                                                
                                                out.println("<datefinprevue>");
                                                out.println(rsTachesCollaboratives.getString("datefinprevue"));
                                                out.println("</datefinprevue>");
                                                
                                                out.println("<datefinreelle>");
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
                                            out.println(rsIndicateursIteration.getString("totalcharges"));
                                            out.println("</totalcharges>");                                            
                                            
                                            out.println("<nombreTachesTerminees>");
                                            out.println(rsIndicateursIteration.getString("nombreTachesTerminees"));
                                            out.println("</nombreTachesTerminees>");
                                            
                                            out.println("<dureeMoyenneTache>");
                                            out.println(rsIndicateursIteration.getString("dureeMoyenneTache"));
                                            out.println("</dureeMoyenneTache>");
                                            
                                            out.println("<nombreParticipants>");
                                            out.println(rsIndicateursIteration.getString("nombreParticipants"));
                                            out.println("</nombreParticipants>");
                                            
                                            out.println("<chargeMoyenneParticipants>");
                                            out.println(rsIndicateursIteration.getString("chargeMoyenneParticipants"));
                                            out.println("</chargeMoyenneParticipants>");
                                            
                                            out.println("<nombreMoyenTachesParticipants>");
                                            out.println(rsIndicateursIteration.getString("nombreMoyenTachesParticipants"));
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
                                        out.println(rsRisques.getString("priorite"));
                                        out.println("</priorite>");
                                        
                                        out.println("<impact>");
                                        out.println(rsRisques.getString("impact"));
                                        out.println("</impact>");
                                        
                                        out.println("<etat>");
                                        out.println(rsRisques.getString("etat"));
                                        out.println("</etat>");
                                        
                                        out.println("<description>");
                                        out.println(rsRisques.getString("description"));
                                        out.println("</description>");
                                        
                                        out.println("</risque>");
                                    }while(rsRisques.next());
                                    
                                    out.print("</risques>");
                                    
                                }
                                rsRisques.close();
                                
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
                                    out.println(rsIndicateursProjet.getString("totalcharges"));
                                    out.println("</totalCharges>");
                                    
                                    out.println("<tachesTerminees>");
                                    out.println(rsIndicateursProjet.getString("tachesTerminees"));
                                    out.println("</tachesTerminees>");
                                    
                                    out.println("<dureeMoyenneTache>");
                                    out.println(rsIndicateursProjet.getString("dureeMoyenneTache"));
                                    out.println("</dureeMoyenneTache>");
                                    
                                    out.println("<nombreParticipants>");
                                    out.println(rsIndicateursProjet.getString("nombreParticipants"));
                                    out.println("</nombreParticipants>");
                                    
                                    out.println("<avancementProjet>");
                                    out.println(rsIndicateursProjet.getString("avancementProjet"));
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
                            out.print(rsMembres.getString("prenom"));
                            out.print("</prenom>");
                            
                            out.print("<adresse>");
                            out.print(rsMembres.getString("adresse"));
                            out.print("</adresse>");
                            
                            out.print("<telephone>");
                            out.print(rsMembres.getString("telephone"));
                            out.print("</telephone>");
                            
                            out.print("<email>");
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
                                    out.println(rsRoles.getString("nom"));
                                    out.println("</designation>");
                                    
                                    out.println("<description>");
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
                                    out.println(rsProjetsMembre.getString("nom")); //Nom du projet ou participe le membre
                                    out.println("</nom>");
                                    
                                    
                                    prepState = conn.prepareStatement("Select sum(t.tempspasse) from iterations i, taches t where i.idprojet = '"+ rsProjetsMembre.getString("idprojet")+"' AND i.iditeration = t.iditeration AND t.idmembre = '"+ rsMembres.getString("idmembre") +"'");
                                    ResultSet rsIndicChargesProjet = prepState.executeQuery(); // Execution de la requete
                                    
                                    
                                    prepState = conn.prepareStatement("Select sum(t.tempspasse) from iterations i, taches t where i.idprojet = '"+ rsProjetsMembre.getString("idprojet")+"' AND i.iditeration = t.iditeration");
                                    ResultSet rsIndicChargesTotaleProjet = prepState.executeQuery(); // Execution de la requete
                                    
                                    
                                    int chargesMembre = 0;
                                    int chargesTotale = 0;
                                    int tempsTravail = 0;
                                    
                                    if(rsIndicChargesProjet.next())
                                        chargesMembre = rsIndicChargesProjet.getInt(1);
                                    
                                    if(rsIndicChargesTotaleProjet.next())
                                        chargesTotale = rsIndicChargesTotaleProjet.getInt(1);
                                    
                                    if(chargesMembre != 0 && chargesTotale !=0)
                                        tempsTravail = (int) ((double) chargesMembre/ (double) chargesTotale *100);
                                    
                                    
                                    
                                    out.println("<charges>");
                                    out.println(chargesMembre); //Charges sur le projet
                                    out.println("</charges>");
                                    
                                    out.println("<tempsTravail>");
                                    out.println(tempsTravail); //Charges sur le projet
                                    out.println("</tempsTravail>");
                                    
                                    rsIndicChargesProjet.close();
                                    rsIndicChargesTotaleProjet.close();
                                    
                                    
                                    out.println("</indicateurProjet>");
                                }while(rsProjetsMembre.next());
                                
                                out.print("</indicateursProjets>");
                            }
                            rsProjetsMembre.close();
                            
                            
                            /*********************************** INDICATEURS DE TACHES DU MEMBRE *******************/
                            
                            //On récupère les noms et temps passé des taches du membre
                            prepState = conn.prepareStatement("Select t.nom,t.tempspasse from taches t where t.idmembre = '"+ rsMembres.getString("idmembre") +"'");
                            ResultSet rsIndicTacheMembre = prepState.executeQuery(); // Execution de la requete
                            
                            
                            if(rsIndicTacheMembre.next()){
                                out.println("<indicateursTaches>");
                                
                                do{
                                    out.println("<indicateurTache>");
                                    // TEST
                                    out.println("<nom>");
                                    out.println(rsIndicTacheMembre.getString("nom"));
                                    out.println("</nom>");
                                    
                                    out.println("<tempspasse>");
                                    out.println(rsIndicTacheMembre.getString("tempspasse"));
                                    out.println("</tempspasse>");
                                               /* out.println("<nom>");
                                                out.println(rsIndicTacheMembre.getString("nom"));
                                                out.println("</nom>");
                                                
                                                out.println("<description>");
                                                out.println(rsIndicTacheMembre.getString("description"));
                                                out.println("</description>");
                                                
                                                out.println("<etat>");
                                                out.println(rsIndicTacheMembre.getString("etat"));
                                                out.println("</etat>");
                                                
                                                out.println("<etat>");
                                                out.println(rsIndicTacheMembre.getString("etat"));
                                                out.println("</etat>");
                                                
                                                out.println("<chargeprevue>");
                                                out.println(rsIndicTacheMembre.getString("chargeprevue"));
                                                out.println("</chargeprevue>");
                                                
                                                out.println("<tempspasse>");
                                                out.println(rsIndicTacheMembre.getString("tempspasse"));
                                                out.println("</tempspasse>");
                                                
                                                out.println("<datedebutprevue>");
                                                out.println(rsIndicTacheMembre.getString("datedebutprevue"));
                                                out.println("</datedebutprevue>");
                                                
                                                out.println("<datedebutreelle>");
                                                out.println(rsIndicTacheMembre.getString("datedebutreelle"));
                                                out.println("</datedebutreelle>");
                                                
                                                out.println("<datefinprevue>");
                                                out.println(rsIndicTacheMembre.getString("datefinprevue"));
                                                out.println("</datefinprevue>");
                                                
                                                out.println("<datefinreelle>");
                                                out.println(rsIndicTacheMembre.getString("datefinreelle"));
                                                out.println("</datefinreelle>");*/
                                    
                                    out.println("</indicateurTache>");
                                }while(rsIndicTacheMembre.next());
                                
                                out.println("</indicateursTaches>");
                                
                            }
                            rsIndicTacheMembre.close();
                            
                            /*********************************** INDICATEURS DES ARTEFACTS DU MEMBRE *******************/
                            
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
