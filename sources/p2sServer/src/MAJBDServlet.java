/*
 * MAJBDServlet.java
 *
 * Created on 26 janvier 2005, 23:28
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
public class MAJBDServlet extends HttpServlet {
    
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
        
        int type = 1; // Fichier distant
                
        String login = request.getParameter("login");
        String urlfichier = request.getParameter("url");
        
        String cheminBD;
        
        cheminBD = "jdbc:mysql://"+InfosBDServlet.InfosBD.getProperty("host")+"/"+InfosBDServlet.InfosBD.getProperty("base")+"?user="+InfosBDServlet.InfosBD.getProperty("login")+"&password="+InfosBDServlet.InfosBD.getProperty("password");
                
        try{
            ParserXMLFichierWF parserFic = new ParserXMLFichierWF(urlfichier,cheminBD,login, type, urlfichier);        
            parserFic.majBase();
        }catch(FileNotFoundException e){
            out.print("0");
        }catch(NullValueXMLException e){
            e.printStackTrace();
            out.print("1");            
        }catch(NullPointerException e){            
            e.printStackTrace();
        }catch(IncorrectFileException e){
            out.print("0");
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
