/*
 * GestionCDPServlet.java
 *
 * Created on 5 mars 2005, 15:19
 */

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Guillaume
 * @version
 */
public class GestionCDPServlet extends HttpServlet {
    
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
        
        System.out.println("nit" + login);
        
        if(login != null  && password != null){
            try {
                ParserConnexionBD parser = new ParserConnexionBD(getServletContext().getRealPath("/ConnexionBD.xml"));
                // Connexion a la base de donnees
                Connection conn = DriverManager.getConnection("jdbc:mysql://"+parser.lireHost()+"/"+parser.lireBase()+"?user="+parser.lireLogin()+"&password="+parser.lirePassword());
                
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from utilisateurs where login = '" + login + "' and password = '" + password + "'");
                ResultSet rsUser = prepState.executeQuery(); // Execution de la requete
                
                if(rsUser.next()){
                    out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
                    
                    out.println("<assignationCDP>"); // debut flux
                    
                    //On met dans le flux tous les projets
                    prepState = conn.prepareStatement("Select p.idprojet, p.nom from projets p, superviseur_projets s where s.login = '" + login + "' and p.idprojet = s.idprojet");
                    rsUser = prepState.executeQuery(); // Execution de la requete
                    
                    out.println("<projets>");
                    while (rsUser.next()){
                        out.println("<projet>");
                        out.println("<id>"+rsUser.getInt("idprojet")+"</id>");
                        out.println("<nom>"+rsUser.getString("nom")+"</nom>");
                        out.println("</projet>");
                    }
                    out.println("</projets>");
                    
                    //On met dans le flux tous les chefs de projet
                    prepState = conn.prepareStatement("SELECT * FROM utilisateurs u WHERE fonction='cdp'");
                    rsUser = prepState.executeQuery(); // Execution de la requete
                    
                    out.println("<cdps>");
                    while (rsUser.next()){
                        out.println("<cdp>");
                        out.println(rsUser.getString("login"));
                        out.println("</cdp>");
                    }
                    out.println("</cdps>");
                    
                    //On met dans les liens entre les chefs de projet et les projets
                    prepState = conn.prepareStatement("SELECT * FROM chefprojet_projets c, utilisateurs u, superviseur_projets s WHERE u.login = c.login AND s.idprojet = c.idprojet AND s.login = '" + login + "'");
                    rsUser = prepState.executeQuery(); // Execution de la requete
                    
                    out.println("<liens>");
                    while (rsUser.next()){
                        out.println("<lien>");
                        out.println("<login>"+rsUser.getString("login")+"</login>");
                        out.println("<id>"+rsUser.getString("idprojet")+"</id>");
                        out.println("</lien>");
                    }
                    out.println("</liens>");
                    
                    out.println("</assignationCDP>"); // debut flux
                   
                }
                conn.close();
            } catch(SQLException e){
                e.printStackTrace();
                
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
