/*
 * MAJBDSeuil.java
 *
 * Created on 21 mars 2005, 23:56
 */

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Guillaume
 * @version
 */
public class MAJBDSeuil extends HttpServlet {
    
    /** Initializes the servlet.
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
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
        
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String nomChamp = request.getParameter("nomChamp");
        String valeur = request.getParameter("value");
        String nomProjet = request.getParameter("nomProjet");
        
        System.out.println(login + " " + pass + " " + nomChamp + " " + valeur + " " + nomProjet);
        
        Connection conn;
        try{
        conn = DriverManager.getConnection("jdbc:mysql://"+InfosBDServlet.InfosBD.getProperty("host")+"/"+InfosBDServlet.InfosBD.getProperty("base")+"?user="+InfosBDServlet.InfosBD.getProperty("login")+"&password="+InfosBDServlet.InfosBD.getProperty("password"));
        
        // Requete SQL
        System.out.println("UPDATE seuilsfixes_projet SET " + nomChamp +  "="+ valeur +" where login = '" + login + "'");
        PreparedStatement prepState = conn.prepareStatement("UPDATE seuilsfixes_projet SET " + nomChamp +  "="+ valeur +" where login = '" + login + "'");
        prepState.executeUpdate();
        conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
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
