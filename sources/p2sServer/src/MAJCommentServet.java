/*
 * MAJCommentServet.java
 *
 * Created on 24 mars 2005, 13:10
 */

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Administrateur
 * @version
 */
public class MAJCommentServet extends HttpServlet {
    
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
        String nomProjet = request.getParameter("projet");
        String comment = request.getParameter("commentaire");
        
        String cheminBD;
        
        cheminBD = "jdbc:mysql://"+InfosBDServlet.InfosBD.getProperty("host")+"/"+InfosBDServlet.InfosBD.getProperty("base")+"?user="+InfosBDServlet.InfosBD.getProperty("login")+"&password="+InfosBDServlet.InfosBD.getProperty("password");
        try{
            // Connexion a la base de donnees
            Connection conn = DriverManager.getConnection(cheminBD);
            
            PreparedStatement prepState = conn.prepareStatement("Select * from projets where nom='"+nomProjet+"'");
            ResultSet rs = prepState.executeQuery(); // Execution de la requete
            
            if(rs.next()){
                int idprojet = rs.getInt("idprojet");
                
                prepState = conn.prepareStatement("Select * from commentaire_projet where idprojet="+idprojet+" and login='"+login+"'");
                rs = prepState.executeQuery(); // Execution de la requete
                
                if(!rs.next()){
                    prepState = conn.prepareStatement("insert into commentaire_projet values ("+idprojet+",'"+login+"','"+comment+"')");
                    prepState.execute(); // Execution de la requete
                    
                }else{
                    PreparedStatement updateProjet = conn.prepareStatement(
                            "update commentaire_projet set commentaire=? where idprojet ="+idprojet+" and login='"+login+"'");
                    updateProjet.setString(1,comment);
                    
                    updateProjet.executeUpdate();
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
