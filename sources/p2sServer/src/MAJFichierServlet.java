/*
 * MAJFichierServlet.java
 *
 * Created on 8 mars 2005, 17:01
 */


import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Fabien
 * @version
 */
public class MAJFichierServlet extends HttpServlet {
    
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
        
        String cheminBD;
        
        cheminBD = "jdbc:mysql://"+InfosBDServlet.InfosBD.getProperty("host")+"/"+InfosBDServlet.InfosBD.getProperty("base")+"?user="+InfosBDServlet.InfosBD.getProperty("login")+"&password="+InfosBDServlet.InfosBD.getProperty("password");
        try{
            // Connexion a la base de donnees
            Connection conn = DriverManager.getConnection(cheminBD);
            
            // On récupère les id des projets du superviseur         
            PreparedStatement prepState = conn.prepareStatement("Select idprojet from superviseur_projets where login='"+login+"'");
            ResultSet rs = prepState.executeQuery(); // Execution de la requete  
            
            while(rs.next())
            {
                int idProjet = rs.getInt("idprojet");
                // On récupère le fichier du projet
                prepState = conn.prepareStatement("Select local, fichier from projets where idprojet="+idProjet);
                ResultSet rsfic = prepState.executeQuery(); // Execution de la requete
                
                if(rsfic.next()){
                    String local = rsfic.getString("local");
                    if(local.compareTo("FALSE") == 0){
                        out.println("distant");
                        String fichier = rsfic.getString("fichier");
                        out.println(fichier);                        
                    }
                    else if(local.compareTo("TRUE") == 0){
                        out.println("local");
                        String fichier = rsfic.getString("fichier");
                        out.println(fichier);                        
                    }
                }
            }
            
        }catch(SQLException e){e.printStackTrace();} 
        
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
