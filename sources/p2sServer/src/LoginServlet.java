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
                                              
                    out.println("<utilisateur>"); // debut flux
                    out.println("<login>"); // login
                    out.println(rsUser.getString("login"));
                    out.println("</login>");
                    
                    out.println("<fonction>"); // fonction
                    out.println(rsUser.getString("fonction"));
                    out.println("</fonction>");
                    
                    if(rsUser.getString("fonction").compareTo("sup")==0)
                    {
                        out.println("<projets>");
                        prepState = conn.prepareStatement("Select idprojet from superviseur_projets where login = '" + rsUser.getString("login")+"'");
                        ResultSet rsIdProjet = prepState.executeQuery(); // Execution de la requete
                        
                        while(rsIdProjet.next())
                        {
                            out.println("<projet>");
                            prepState = conn.prepareStatement("Select * from projets where idprojet = '" + rsIdProjet.getString("idProjet")+"'");
                            ResultSet rsProjet = prepState.executeQuery(); // Execution de la requete
                            
                            rsProjet.next();
                            
                            out.println("<idProjet>");
                            out.println(rsProjet.getString("idprojet"));
                            out.println("</idProjet>");
                            
                            out.println("<nom>");
                            out.println(rsProjet.getString("nom"));
                            out.println("</nom>");
                            
                            out.println("<description>");
                            out.println(rsProjet.getString("description"));
                            out.println("</description>");
                            
                            out.println("<datedebut>");
                            out.println(rsProjet.getString("datedebut"));
                            out.println("</datedebut>");
                            
                            out.println("<datefin>");
                            out.println(rsProjet.getString("datefin"));
                            out.println("</datefin>");
                            
                            out.println("</projet>");
                            
                            rsProjet.close();
                        }
                        out.println("</projets>");
                        rsIdProjet.close();                        
                    }
                    
                    if(rsUser.getString("fonction").compareTo("dir")==0)
                    {
                        out.println("<membres>");
                        prepState = conn.prepareStatement("Select * from membres;");
                        ResultSet rsMembres = prepState.executeQuery(); // Execution de la requete
                        
                        while(rsMembres.next())
                        {
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
                                                                                   
                            out.println("</membre>");
                            
                            
                        }
                        out.print("</membres>");
                        rsMembres.close();                        
                    }
                    
                    out.println("</utilisateur>");
                    
                }
                // Fermeture du ResultSet
                rsUser.close();                
                prepState.close(); // Fermeture de la requete
                conn.close(); // Fermeture de la connexion
        }catch (SQLException ex) { // Si une SQLException survient
                System.out.println("SQLException: " + ex.getMessage()); 
                System.out.println("SQLState: " + ex.getSQLState()); 
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
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
