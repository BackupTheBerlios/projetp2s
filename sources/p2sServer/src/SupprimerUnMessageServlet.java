/*
 * SupprimerUnMessageServlet.java
 *
 * Created on 10 mars 2005, 20:48
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
import java.util.*;

/**
 *
 * @author kass
 * @version
 */
public class SupprimerUnMessageServlet extends HttpServlet {
    
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
       
  
        String sujet = request.getParameter("sujet");
        String message = request.getParameter("message");
       
       
        try {
                ParserConnexionBD parser = new ParserConnexionBD(getServletContext().getRealPath("/ConnexionBD.xml"));
                // Connexion a la base de donnees                                
                Connection conn = DriverManager.getConnection("jdbc:mysql://"+parser.lireHost()+"/"+parser.lireBase()+"?user="+parser.lireLogin()+"&password="+parser.lirePassword());
        
                Statement s = conn.createStatement();
                s.executeUpdate("delete from messages where sujet = '" + sujet + "' and message = '" + message + "' ");
                out.println("ok");
                s.close();
                    
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
