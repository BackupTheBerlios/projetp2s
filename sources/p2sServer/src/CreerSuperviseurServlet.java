/*
 * CreerSuperviseurServlet.java
 *
 * Created on 26 janvier 2005, 00:43
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
 * @author Cox
 * @version
 */
public class CreerSuperviseurServlet extends HttpServlet {
    
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
                // Connexion a la base de donnees                                
                Connection conn = DriverManager.getConnection("jdbc:mysql://"+InfosBDServlet.InfosBD.getProperty("host")+"/"+InfosBDServlet.InfosBD.getProperty("base")+"?user="+InfosBDServlet.InfosBD.getProperty("login")+"&password="+InfosBDServlet.InfosBD.getProperty("password"));
                
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select * from utilisateurs where login = '" + login + "'");
                ResultSet rsUser = prepState.executeQuery(); // Execution de la requete
                
                if(rsUser.next()){
                    //Ce login existe déjà
                    out.println("nok");
                }
                else{
                    out.println("ok");
                    Statement s = conn.createStatement();
                    s.executeUpdate("Insert into Utilisateurs values('" + login + "','" + password + "','sup')");
                }
                conn.close();
             }
             catch(SQLException e){
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
