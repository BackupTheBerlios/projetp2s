/*
 * CreerUnMessageServlet.java
 *
 * Created on 8 mars 2005, 20:14
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
import java.sql.Date;
import java.util.*;
import java.text.DateFormat;

/**
 *
 * @author kass
 * @version
 */
public class CreerUnMessageServlet extends HttpServlet {
    
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
        
        java.util.Date d = new java.util.Date();
        d = Calendar.getInstance().getTime();
        java.sql.Date date = new java.sql.Date(d.getTime());
        
        String login = request.getParameter("login");
        String sujet = request.getParameter("sujet");
        String message = request.getParameter("message");
        String sup = "sup";
        String cdp = "cdp";
       
       
        try {
                ParserConnexionBD parser = new ParserConnexionBD(getServletContext().getRealPath("/ConnexionBD.xml"));
                // Connexion a la base de donnees                                
                Connection conn = DriverManager.getConnection("jdbc:mysql://"+parser.lireHost()+"/"+parser.lireBase()+"?user="+parser.lireLogin()+"&password="+parser.lirePassword());
                
                 // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from utilisateurs where login = '" + login + "' and (fonction= '" + sup + "' or fonction= '" + cdp + "')");
                ResultSet rsUser = prepState.executeQuery(); // Execution de la requete
                
                if(rsUser.next()){
                    //C'est bon le superviseur existe
                    out.println("ok");
                    Statement s = conn.createStatement();
                    s.executeUpdate("Insert into messages(login,sujet,date,message) values('" + login + "','" + sujet + "','" + date + "','" + message + "')");
                    s.close();
                    
                }
                else{
                    out.println("nok");
                }
                conn.close();
             }
             catch(SQLException e){
                 e.printStackTrace();
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
