/*
 * AjoutProjetServlet.java
 *
 * Created on 26 janvier 2005, 17:15
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;
import java.net.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Fabien
 * @version
 */
public class AjoutProjetServlet extends HttpServlet {
    
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
        String nom = request.getParameter("nom");
        String jourDateDebut;
        if((jourDateDebut = request.getParameter("jourDateDebut")) == null)
            jourDateDebut = "";
        String moisDateDebut;
        if((moisDateDebut = request.getParameter("moisDateDebut")) == null)
            moisDateDebut ="";
        String anneeDateDebut;
        if((anneeDateDebut = request.getParameter("anneeDateDebut")) == null)
            anneeDateDebut ="";
        String jourDateFin;
        if((jourDateFin = request.getParameter("jourDateFin")) == null)
            jourDateFin ="";
        String moisDateFin;
        if((moisDateFin = request.getParameter("moisDateFin")) == null)
            moisDateFin ="";
        String anneeDateFin;
        if((anneeDateFin = request.getParameter("anneeDateFin")) == null)
            anneeDateFin ="";
        String description;
        if((description = request.getParameter("description")) == null)
            description ="";
        
                
        if (nom != null){
            try {
                ParserConnexionBD parser = new ParserConnexionBD(getServletContext().getRealPath("/ConnexionBD.xml"));
                // Connexion a la base de donnees                                
                Connection conn = DriverManager.getConnection("jdbc:mysql://"+parser.lireHost()+"/"+parser.lireBase()+"?user="+parser.lireLogin()+"&password="+parser.lirePassword());
                
                // Requete SQL
                PreparedStatement prepState = conn.prepareStatement("Select MAX(idprojet) from projets");
                ResultSet rsIDMaxProjet = prepState.executeQuery(); // Execution de la requete
                
                if(rsIDMaxProjet.next()){
                    int idProjet = rsIDMaxProjet.getInt(1)+1;
                    prepState = conn.prepareStatement("insert into projets values ("+idProjet+",'"+nom+"','"+anneeDateDebut+"-"+moisDateDebut+"-"+jourDateDebut+"','"+anneeDateFin+"-"+moisDateFin+"-"+jourDateFin+"','"+description+"')");
                    prepState.execute(); // Execution de la requete
                    prepState = conn.prepareStatement("insert into superviseurprojets values('"+login+"',"+idProjet+")");
                    prepState.execute(); // Execution de la requete
                }
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
