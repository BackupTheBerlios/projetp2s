/*
 * MAJBDFicLocalServlet.java
 *
 * Created on 5 mars 2005, 15:11
 */

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Fabien
 * @version
 */
public class MAJBDFicLocalServlet extends HttpServlet {
    
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
        String flux = request.getParameter("flux");
        String fichier = request.getParameter("fichier");
        
        // On efface le buffer lorsqu'on commence à lire un nouveau fichier
        if(lecture.compareTo("0") == 0)
            this.FluxTotal = "";
        
        this.FluxTotal = this.FluxTotal + flux;
        //System.out.println("Flux : "+this.FluxTotal);
        
        if(lecture.compareTo("2") == 0){
            String cheminBD;
            ParserConnexionBD parser = new ParserConnexionBD(getServletContext().getRealPath("/ConnexionBD.xml"));
            cheminBD = "jdbc:mysql://"+parser.lireHost()+"/"+parser.lireBase()+"?user="+parser.lireLogin()+"&password="+parser.lirePassword();
            
            try{                
                ParserXMLFichierWF parserFic = new ParserXMLFichierWF(this.FluxTotal,cheminBD,login, type, fichier);
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
