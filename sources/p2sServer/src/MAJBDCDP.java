/*
 * MAJBDCDP.java
 *
 * Created on 9 mars 2005, 09:46
 */

import java.sql.Connection;
import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Guillaume
 * @version
 */
public class MAJBDCDP extends HttpServlet {
    
    private String FluxTotal;
    
    /** Initializes the servlet.
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.FluxTotal = "";
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
        int type = 0; // Fichier local
        
        String login = request.getParameter("login");
        String lecture = request.getParameter("lecture");
        
        // On efface le buffer lorsqu'on commence � lire une nouvelle requete
        
        if(lecture.compareTo("0") == 0){
            this.FluxTotal = "";
        }
        
        if(lecture.compareTo("1") == 0){
            String flux = request.getParameter("flux");
            this.FluxTotal = this.FluxTotal + flux;
        }
        
        if(lecture.compareTo("2") == 0){
            String cheminBD;
            
            try{                
                Connection conn = DriverManager.getConnection("jdbc:mysql://"+InfosBDServlet.InfosBD.getProperty("host")+"/"+InfosBDServlet.InfosBD.getProperty("base")+"?user="+InfosBDServlet.InfosBD.getProperty("login")+"&password="+InfosBDServlet.InfosBD.getProperty("password"));
                
                Document document;
                DocumentBuilderFactory usine = DocumentBuilderFactory.newInstance();
                DocumentBuilder constructeur = usine.newDocumentBuilder();
                document= constructeur.parse(new java.io.ByteArrayInputStream(FluxTotal.getBytes()));
        
                
                NodeList liens = document.getElementsByTagName("CDP");
                for(int i = 0 ; i < liens.getLength() ; i++){
                    String loginCDP = "";
                    Vector projets = new Vector();
                    
                    NodeList attributs = liens.item(i).getChildNodes();
                    
                    for(int j = 0 ; j < attributs.getLength() ; j++){
                        if(attributs.item(j).getNodeName().equalsIgnoreCase("nom"))
                            loginCDP = attributs.item(j).getFirstChild().getNodeValue();
                        if(attributs.item(j).getNodeName().equalsIgnoreCase("projets")){
                            NodeList listprojet = attributs.item(j).getChildNodes();
                            for(int k = 0 ; k < listprojet.getLength() ; k++){
                                Node attrprojet = listprojet.item(k);
                                projets.add(attrprojet.getFirstChild().getNodeValue());
                            }
                            
                        }
                        
                    }
                    
                    PreparedStatement psAncienneProjet = conn.prepareStatement("Select p.nom from projets p, chefprojet_projets cp where cp.idprojet=p.idprojet AND cp.login = '"+ loginCDP +"'");
                    ResultSet rsAncienProjet = psAncienneProjet.executeQuery(); // Execution de la requete
                    Vector anciensProjets = new Vector();
                    while(rsAncienProjet.next())
                           anciensProjets.add(rsAncienProjet.getString("nom"));
                    
                    for(int u = 0 ; u < anciensProjets.size() ; u++)
                    {
                        System.out.println("Listing des anciens projets : " + anciensProjets.get(u));
                    }
                    
                    //Ajout des nouveaux projets
                    for(int l = 0 ; l < projets.size() ; l++){
                        
                        System.out.println("");System.out.println("");
                        System.out.println("Nouveaux projets : " + projets.get(l));
                        
                        if(anciensProjets.contains(projets.get(l))){
                            //Rien faire
                        }
                        else{
                        PreparedStatement prepState = conn.prepareStatement("Select idprojet from projets where nom = '" + projets.get(l) +"'");
                        ResultSet rs = prepState.executeQuery(); // Execution de la requete
                        int id = 0;
                        if(rs.next())
                            id = rs.getInt("idprojet");
                        Statement s = conn.createStatement();
                        System.out.println("Ajout de " + loginCDP + id);
                        
                        PreparedStatement prepState2 = conn.prepareStatement("Select * from chefprojet_projets where idprojet = '" + id +"'");
                        ResultSet rs2 = prepState2.executeQuery(); // Execution de la requete
                        
                        s.executeUpdate("Insert into chefprojet_projets values('" + loginCDP + "'," + id + ")");
                        
                        s.executeUpdate("insert into seuilsfixes_projet values ("+ id +",0,0,0,0,0.0,0.0,0,0,0,0,0,0,0,0,0.0,0.0,0,0,0.0,0.0,0.0,0.0,'"+loginCDP+"')");
                    
                        }
                    }
                    
                    //Suppression des anciens projets
                    for(int l = 0 ; l < anciensProjets.size() ; l++){
                        
                        System.out.println("");System.out.println("");
                        System.out.println("Anciens projets : " + anciensProjets.get(l));
                        
                        if(projets.contains(anciensProjets.get(l))){
                            //Rien faire
                        }
                        else{
                        PreparedStatement prepState = conn.prepareStatement("Select idprojet from projets where nom = '" + anciensProjets.get(l) +"'");
                        ResultSet rs = prepState.executeQuery(); // Execution de la requete
                        int id = 0;
                        if(rs.next())
                        
                        id = rs.getInt("idprojet");
                        Statement s = conn.createStatement();
        
                        s.executeUpdate("DELETE FROM chefprojet_projets WHERE login='" + loginCDP + "' AND idprojet=" + id + "");
                        
                        s.executeUpdate("DELETE FROM seuilsfixes_projet WHERE login='" + loginCDP + "' AND idprojet=" + id + "");
                    
                        }
                    }
                }
            } catch(SQLException e){
                e.printStackTrace();
                
            } catch(ParserConfigurationException e2){
                e2.printStackTrace();
            } catch(SAXException e3){
                e3.printStackTrace();
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
